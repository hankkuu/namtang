package kosta.namtang.talkbook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
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
