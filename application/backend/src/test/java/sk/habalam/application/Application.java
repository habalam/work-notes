package sk.habalam.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "sk.habalam")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		//TODO treba vytvoriť nejaký DBinit bean, ktorý bude podľa konfigurácie buď iba v in-memory DB nainicializuje
		// štruktúru, alebo aj naplní dáta pre prípad, že budem spúšťať testovací localhost - zvážiť EmbededDatabaseBuilder

		//TODO ako funguje anotácia @SpringBootTest?
	}
}
