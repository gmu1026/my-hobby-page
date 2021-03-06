package net.iamchan.myhobbypage.crawler;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.iamchan.myhobbypage.domain.Content;

@Component
@ConfigurationProperties("webdriver")
public class WoowaCrawler {
	private WebDriver driver;
	
//	@Autowired
//	private CrawlerProperties crawlerProperties;
	// 추후 properties로 대체
	// properties profile을 이용해 local test 환경과 deployment 환경 분리할 것
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "/usr/local/bin/chromedriver";
//	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	// 추후 복수 개의 url 저장을 위해 collection으로 대체
	
	private RestTemplate restTemplate;
	
	public WoowaCrawler() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		
		driver = new ChromeDriver(options);
	}
	
	@Scheduled(fixedRate = 86400000)
	public void crawlToInfo() throws Exception {
		driver.get("https://woowabros.github.io");

		restTemplate = new RestTemplate();
		String description = "";
		
		List<WebElement> infoList = driver.findElements(By.className("list-module"));
		for (WebElement info : infoList) {
			WebElement postMetaDate = info.findElement(By.className("post-meta"));
			WebElement postLink = info.findElement(By.tagName("a"));
			WebElement postTitle = info.findElement(By.className("post-link"));
			description =
					postLink.getText().split("\n").length >= 2 ? postLink.getText().split("\n")[1] : "설명 없음"; 
					
			Content crawlData = Content.builder()
					.metaData(postMetaDate.getText())
					.title(postTitle.getText())
					.link(postLink.getAttribute("href"))
					.description(description)
					.build();
			
			String apiUrl = "http://localhost:8080/api/v1/content";
			restTemplate.postForEntity(apiUrl, crawlData, Integer.class);
		}
	}
	
	public void closeCrawler() {
		driver.close();
	}
}
