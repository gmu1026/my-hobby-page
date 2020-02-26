package net.iamchan.myhobbypage.crawler;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.iamchan.myhobbypage.domain.Content;

@Component
public class WoowaCrawler {
	private WebDriver driver;
	
	// 추후 properties로 대체
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
	
	// 추후 복수 개의 url 저장을 위해 collection으로 대체
	private String target_url;
	
	private RestTemplate restTemplate;
	
	public WoowaCrawler() {
		super();
		
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		
		driver = new ChromeDriver(options);
		target_url = "https://woowabros.github.io";
		driver.get(target_url);
		
		
	}
	
	@Scheduled(fixedRate = 86400000)
	public void crawlToInfo() throws Exception {
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
					.date(postMetaDate.getText())
					.title(postTitle.getText())
					.link(postLink.getAttribute("href"))
					.description(description)
					.build();
			
			
			String apiUrl = "http://localhost:9000/api/v1/content";
			restTemplate.postForEntity(apiUrl, crawlData, Integer.class);
		}
	}
	
	public void closeCrawler() {
		driver.close();
	}
}
