package net.iamchan.myhobbypage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.iamchan.myhobbypage.domain.Content;

@Getter
@NoArgsConstructor
public class ContentSaveRequestDto {
	private String date;
	private String title;
	private String link;
	private String description;
	
	@Builder
	public ContentSaveRequestDto(String date, String title, String link, String description) {
		this.date = date;
		this.title = title;
		this.link = link;
		this.description = description;
	}
	
	public Content toEntity() {
		return Content.builder()
				.date(date)
				.title(title)
				.link(link)
				.description(description)
				.build();
	}
}
