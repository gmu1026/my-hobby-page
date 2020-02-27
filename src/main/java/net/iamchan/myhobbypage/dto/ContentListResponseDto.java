package net.iamchan.myhobbypage.dto;

import lombok.Getter;
import net.iamchan.myhobbypage.domain.Content;

@Getter
public class ContentListResponseDto {
	private Long id;
	private String metaData;
	private String title;
	private String link;
	private String description;
	
	public ContentListResponseDto(Content entity) {
		this.id = entity.getId();
		this.metaData = entity.getMetaData();
		this.title = entity.getTitle();
		this.link = entity.getLink();
		this.description = entity.getDescription();
	}
}
