package net.iamchan.myhobbypage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EnableConfigurationProperties
public class MyhobbypageApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyhobbypageApplication.class, args);
	}
}