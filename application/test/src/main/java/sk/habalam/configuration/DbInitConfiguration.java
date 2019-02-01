package sk.habalam.configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@Import(DataSourceConfiguration.class)
public class DbInitConfiguration {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	@Bean
	public void dbInitBase() {
		DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(resourceLoader.getResource("classpath:db_scripts/create_schema.sql"));
		databasePopulator.addScript(resourceLoader.getResource("classpath:db_scripts/create_data.sql"));
		databasePopulator.execute(dataSource);
	}
}
