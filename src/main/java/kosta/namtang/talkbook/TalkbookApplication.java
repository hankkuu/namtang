package kosta.namtang.talkbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TalkbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalkbookApplication.class, args);
	}

}
