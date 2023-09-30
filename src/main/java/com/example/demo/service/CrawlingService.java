package com.example.demo.service;

import com.example.demo.dto.Promotion;
import enums.ItemCategory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CrawlingService {
    @Value("${item.img-path}")
    private String IMG_PATH;
    public List<Promotion> crawling() {
        String[] brands = {"cu","gs25","seven","emart24"};
        final List<Promotion> promotionList = new ArrayList<>();
        Arrays.stream(brands).parallel()
                .forEach(brand -> IntStream.range(1,5).parallel().forEach(categoryInt -> {
                    try {
                        crawling(brand,1, categoryInt, -1, promotionList);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));
        return promotionList;
    }
    private void crawling(String brand, int page, int category,int lastPage, List<Promotion> promotionList) throws IOException {
        Document document = Jsoup.connect("https://pyony.com/brands/"+brand+"/?page="+page+"&category="+category+"&item=100").get();
        if(lastPage==-1)
            lastPage = getMaxPage(document,brand);
        Element container = document.select("div.container").get(1);
        Elements col_md_6s = container.select("div.col-md-6");
        col_md_6s.parallelStream().forEach(element -> {
            Element promotionData = element.select("div:not([class])").first();
            Element promotionImg = element.select("img").first();
            List<Promotion> promotions = promotionData.textNodes().stream()
                    .filter(n -> n.toString().contains("원"))
                    .map(textNode -> new Promotion(promotionData, textNode,category,brand, promotionImg))
                    .collect(Collectors.toList());
            saveImage(promotions, brand);
            promotionList.addAll(promotions);
        });
        if(page<lastPage){
            crawling(brand, ++page, category, lastPage, promotionList);
        }
    }
    private void saveImage(List<Promotion> promotions, String brand){
        promotions.parallelStream().forEach(promotion -> {
            try {
                String imgUrlString = promotion.getImgUrl();
                URL url = new URL(promotion.getImgUrl());
                String fileName = imgUrlString.substring(imgUrlString.lastIndexOf("/")+1);

                fileName = brand+"_"+fileName;
                URLConnection imgConnection = url.openConnection();
                if(!fileName.contains(".")){
                    String contentType = imgConnection.getContentType();
                    String extension = contentType.split(";")[0].split("/")[1];
                    fileName = fileName+"."+extension;
                }
                promotion.setImgUrl(fileName);
                try (InputStream in = imgConnection.getInputStream()) {
                    Files.copy(in, Paths.get(IMG_PATH+File.separator+fileName), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.err.println(promotion);
                    System.err.println("이미지 다운로드 중 오류가 발생했습니다.");
                }
            } catch (IOException e) {
                System.err.println(promotion);
                System.err.println("URL에 연결 중 오류가 발생했습니다.");
            }
        });
    }
    private int getMaxPage(Document document,String brand){
        String lastPageHref = document.select("a.page-link").last().attr("href");
        String[] split = lastPageHref.split("=");
        int pagePrefix = 0;
        for (int i = 0; i < split.length; i++) {
            if(split[i].equals("page")){
                pagePrefix = i;
                break;
            }
        }
        return Integer.parseInt(split[pagePrefix+1].split("&")[0]);
    }
}
