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
	private String date;
	private String title;
	private String link;
	private String description;
	
	@Builder
	public Content(String date, String title, String link, String description) {
		this.date = date;
		this.title = title;
		this.link = link;
		this.description = description;
	}
}
