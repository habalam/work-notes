package sk.habalam.configuration;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

@Profile("h2-mem")
@DependsOn("dbInitConfiguration")
@Configuration
public class H2InMemDbInitConfiguration {

	@PostConstruct
	public void dbInitH2Mem() {
		System.out.println("test");
	}
}
