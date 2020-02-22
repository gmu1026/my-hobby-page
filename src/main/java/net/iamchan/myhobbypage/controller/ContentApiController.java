package net.iamchan.myhobbypage.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.iamchan.myhobbypage.dto.ContentListResponseDto;
import net.iamchan.myhobbypage.dto.ContentResponseDto;
import net.iamchan.myhobbypage.dto.ContentSaveRequestDto;
import net.iamchan.myhobbypage.dto.ContentUpdateRequestDto;
import net.iamchan.myhobbypage.service.ContentService;

@RequiredArgsConstructor
@RestController
public class ContentApiController {
	private final ContentService contentService;
	
	@PutMapping("/api/v1/content")
	public Long save(@RequestBody ContentSaveRequestDto requestDto) {
		return contentService.save(requestDto);
	}
	
	@GetMapping("/api/v1/content/{id}")
	public ContentResponseDto findById(@PathVariable Long id) {
		return contentService.findById(id);
	}
	
	@PutMapping("/api/v1/content/{id}")
	public Long update(@PathVariable Long id, @RequestBody ContentUpdateRequestDto requestDto) {
		return contentService.update(id, requestDto);
	}
	
	@DeleteMapping("/api/v1/content/{id}")
	public Long delete(@PathVariable Long id) {
		contentService.delete(id);
		
		return id;
	}
	
	@GetMapping("/api/v1/content/list")
	public List<ContentListResponseDto> findAll() {
		return contentService.findAll();
	}
}
