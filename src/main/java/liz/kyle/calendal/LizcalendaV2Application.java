package liz.kyle.calendal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LizcalendaV2Application {

	public static void main(String[] args) {
		SpringApplication.run(LizcalendaV2Application.class, args);
	}

}
