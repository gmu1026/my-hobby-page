package net.iamchan.myhobbypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import net.iamchan.myhobbypage.service.ContentService;

@RequiredArgsConstructor
@Controller
public class IndexController {
	private final ContentService contentService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
