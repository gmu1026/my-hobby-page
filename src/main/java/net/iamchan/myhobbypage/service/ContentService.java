package net.iamchan.myhobbypage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.iamchan.myhobbypage.domain.Content;
import net.iamchan.myhobbypage.domain.ContentRepository;
import net.iamchan.myhobbypage.dto.ContentResponseDto;
import net.iamchan.myhobbypage.dto.ContentSaveRequestDto;

@RequiredArgsConstructor
@Service
public class ContentService {
	private final ContentRepository contentRepository;
	
	@Transactional
	public Long save(ContentSaveRequestDto requestDto) {
		return contentRepository.save(requestDto.toEntity()).getId();
	}
	
	public ContentResponseDto findById(Long id) {
		Content entity = contentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 컨텐츠가 없습니다. ID = " + id));
		
		return new ContentResponseDto(entity);
	}
}
