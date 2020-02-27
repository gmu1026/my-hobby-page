package net.iamchan.myhobbypage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.iamchan.myhobbypage.domain.Content;

@Getter
@NoArgsConstructor
public class ContentSaveRequestDto {
	private String metaData;
	private String title;
	private String link;
	private String description;
	
	@Builder
	public ContentSaveRequestDto(String metaData, String title, String link, String description) {
		this.metaData = metaData;
		this.title = title;
		this.link = link;
		this.description = description;
	}
	
	public Content toEntity() {
		return Content.builder()
				.metaData(metaData)
				.title(title)
				.link(link)
				.description(description)
				.build();
	}
}
