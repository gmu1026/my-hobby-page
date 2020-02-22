package net.iamchan.myhobbypage.crawler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@Entity
public class CrawlData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String date;
	private String title;
	private String link;
	private String description;
}
