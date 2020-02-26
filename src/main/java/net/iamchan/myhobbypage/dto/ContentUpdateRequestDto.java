package net.iamchan.myhobbypage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContentUpdateRequestDto {
	private String date;
	private String title;
	private String link;
	private String description;
	
	@Builder
	public ContentUpdateRequestDto(String date, String title, String link, String description) {
		this.date = date;
		this.title = title;
		this.link = link;
		this.description = description;
	}
}