package net.iamchan.myhobbypage.crawler;

public class Test {
	public static void main(String[] args) {
		WoowaCrawler crawler = new WoowaCrawler();
		
		try {
			crawler.crawlToAll();
			crawler.crawlToInfo();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			crawler.closeCrawler();
		}
	}
}
