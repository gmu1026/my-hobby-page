package net.iamchan.myhobbypage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://127.0.0.1:8080")
		.allowedOrigins("http://localhost:8080")
		.allowedOrigins("http://my-hobby-page-front.s3-website.ap-northeast-2.amazonaws.com/")
		.allowedMethods("GET", "POST", "PUT", "DELETE")
		.allowCredentials(true);
	}
}