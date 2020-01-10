package kosta.namtang.talkbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class TalkbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalkbookApplication.class, args);

		log.error("error");
		log.warn("warn");
		log.info("info");
		log.trace("trace");
		log.debug("debug");
	}

}
