package sk.habalam.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "sk.habalam")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		//TODO ako funguje anotácia @SpringBootTest?
	}
}
