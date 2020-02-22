package net.iamchan.myhobbypage.crawler;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WoowaCrawler {
	private WebDriver driver;
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\Users\\gmu01\\Desktop\\dev\\chromedriver.exe";
	public static final String OUTPUT_PATH = "C:\\Users\\gmu01\\Desktop\\dev\\";
	
	private String target_url;
	
	public WoowaCrawler() {
		super();
		
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		
		driver = new ChromeDriver(options);
		target_url = "https://woowabros.github.io";
		driver.get(target_url);
	}
	
	public void crawlToAll() throws Exception {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Path outputPath = Paths.get(OUTPUT_PATH, "All_" + dateFormat.format(now) + ".txt");
		
		Charset charset = Charset.defaultCharset();
		ByteBuffer crawlDate = charset.encode(driver.getPageSource());
		
		FileChannel fileChannel = FileChannel.open(
				outputPath,
				StandardOpenOption.CREATE_NEW,
				StandardOpenOption.WRITE
		);
		
		fileChannel.write(crawlDate);
		
		fileChannel.close();
	}
	
	public void crawlToInfo() throws Exception {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Path outputPath = Paths.get(OUTPUT_PATH, "INFO_" + dateFormat.format(now) + ".txt");
		
		int i = 0;
		
		Map<Integer, CrawlData> resultMap = 
				new HashMap<Integer, CrawlData>();
		List<WebElement> infoList = driver.findElements(By.className("list-module"));
		for (WebElement info : infoList) {
			WebElement postMetaDate = info.findElement(By.className("post-meta"));
			WebElement postLink = info.findElement(By.tagName("a"));
			WebElement postTitle = info.findElement(By.className("post-link"));
//			WebElement postDescription = info.findElement(By.className("post-description"));
			
			CrawlData crawlData = CrawlData.builder()
					.date(postMetaDate.getText())
					.title(postTitle.getText())
					.link(postLink.getAttribute("href"))
//					.description(postDescription.getText())
					.build();
			resultMap.put(i++, crawlData);
		}
		
		StringBuilder resultText = new StringBuilder("");
		Set<Integer> keys = resultMap.keySet();
		for (int key : keys) {
			resultText.append(resultMap.get(key).toString());
			resultText.append(System.getProperty( "line.separator"));
		}
		
		Charset charset = Charset.defaultCharset();
		ByteBuffer crawlDate = charset.encode(resultText.toString());
		
		FileChannel fileChannel = FileChannel.open(
				outputPath,
				StandardOpenOption.CREATE_NEW,
				StandardOpenOption.WRITE
		);
		
		fileChannel.write(crawlDate);
		
		fileChannel.close();
	}
	
	public void closeCrawler() {
		driver.close();
	}
}
