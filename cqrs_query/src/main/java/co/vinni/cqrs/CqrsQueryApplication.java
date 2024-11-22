package co.vinni.cqrs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@EnableMongoRepositories(basePackages = "co.vinni.cqrs.persistence.repository")
@SpringBootApplication
public class CqrsQueryApplication {

	public static void main(String[] args) {
		log.info("Inicio la aplicación Query");
		SpringApplication.run(CqrsQueryApplication.class, args);
	}

}
