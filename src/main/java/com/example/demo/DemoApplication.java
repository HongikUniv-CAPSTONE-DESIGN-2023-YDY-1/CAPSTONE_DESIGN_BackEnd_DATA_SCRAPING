package com.example.demo;

import com.example.demo.dto.Promotion;
import com.example.demo.entity.Item;
import com.example.demo.entity.PromotionInfo;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.PromotionInfoRepository;
import com.example.demo.service.CrawlingService;
import com.example.demo.service.PromotionInfoService;
import com.example.demo.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {
	private final CrawlingService crawlingService;
	private final PromotionInfoService promotionInfoService;
	private final SubCategoryService subCategoryService;

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		List<Promotion> crawling = crawlingService.crawling();
		subCategoryService.calcSubCategory(crawling);
		promotionInfoService.saveAll(crawling);
	}
}
