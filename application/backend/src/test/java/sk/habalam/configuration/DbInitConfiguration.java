package sk.habalam.configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
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

//	@Profile("h2-mem")
//	@DependsOn("dbInitBase")
//	@PostConstruct
//	@Bean
//	public void dbInitH2Mem() {
//		System.out.println("test");
//	}
}
