package sk.habalam.configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Profile("h2-mem")
@DependsOn("dbInitConfiguration")
@Import(DataSourceConfiguration.class)
@Configuration
public class H2InMemDbInitConfiguration {

	private final DataSource dataSource;

	@Autowired
	public H2InMemDbInitConfiguration(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@PostConstruct
	public void dbInitH2Mem() {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(
			new DefaultResourceLoader().getResource("classpath:db_scripts/create_data.sql"));
		resourceDatabasePopulator.execute(dataSource);
	}
}
