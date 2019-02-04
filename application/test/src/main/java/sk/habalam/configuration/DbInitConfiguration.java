package sk.habalam.configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@Import(DataSourceConfiguration.class)
public class DbInitConfiguration {

	private final DataSource dataSource;

	@Autowired
	public DbInitConfiguration(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@PostConstruct
	public void dbInitBase() {
		DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(resourceLoader.getResource("classpath:db_scripts/create_schema.sql"));
		databasePopulator.execute(dataSource);
	}
}
