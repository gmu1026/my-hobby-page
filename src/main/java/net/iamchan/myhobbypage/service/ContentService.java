package net.iamchan.myhobbypage.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.iamchan.myhobbypage.domain.Content;
import net.iamchan.myhobbypage.domain.ContentRepository;
import net.iamchan.myhobbypage.dto.ContentListResponseDto;
import net.iamchan.myhobbypage.dto.ContentResponseDto;
import net.iamchan.myhobbypage.dto.ContentSaveRequestDto;
import net.iamchan.myhobbypage.dto.ContentUpdateRequestDto;

@RequiredArgsConstructor
@Service
public class ContentService {
	private final ContentRepository contentRepository;
	
	@Transactional
	public Long save(ContentSaveRequestDto requestDto) {
		return contentRepository.save(requestDto.toEntity()).getId();
	}
	
	@Transactional(readOnly = true)
	public ContentResponseDto findById(Long id) {
		Content entity = contentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 컨텐츠가 없습니다. ID = " + id));
		
		return new ContentResponseDto(entity);
	}
	
	@Transactional
	public Long update(Long id, ContentUpdateRequestDto requestDto) {
		Content content = contentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 컨텐츠가 없습니다. ID = " + id));
		
		content.update(requestDto.getDate(), requestDto.getTitle(), requestDto.getLink(), requestDto.getDescription());
		
		return id;
	}
	
	@Transactional
	public void delete(Long id) {
		Content content = contentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 컨텐츠가 없습니다. ID = " + id));
		
		contentRepository.delete(content);
	}
	
	@Transactional(readOnly = true)
	public List<ContentListResponseDto> findAll() {
		return contentRepository.findAll().stream()
				.map(ContentListResponseDto::new)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<Content> findAll(Pageable pageable) {
		return contentRepository.findAll(pageable);
	}
}
