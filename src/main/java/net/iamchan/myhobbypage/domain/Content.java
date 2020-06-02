package net.iamchan.myhobbypage.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String metaData;
	private String title;
	private String link;
	private String description;
	
	@Builder
	public Content(String metaData, String title, String link, String description) {
		this.metaData = metaData;
		this.title = title;
		this.link = link;
		this.description = description;
	}
	
	public void update(String metaData, String title, String link, String description) {
		this.metaData = metaData;
		this.title = title;
		this.link = link;
		this.description = description;
	}
}
