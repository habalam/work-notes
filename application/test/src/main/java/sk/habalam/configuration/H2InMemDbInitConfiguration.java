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
		//TODO doplniť konfiguráciu pre H2-mem - script na naplnanie dodatočných dát okrem schemy a
		// možno nejakých číselníkov
		System.out.println("test");
	}
}
