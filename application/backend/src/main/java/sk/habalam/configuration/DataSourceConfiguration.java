package sk.habalam.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataSourceConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
		emFactory.setDataSource(dataSource());
		emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emFactory.setJpaProperties(additionalJpaProperties());
		//TODO setup ehcache, second level cache, query cache
		//TODO konfigurácia môže ísť rovno do application.properties
		//TODO ak spravím DB init bean v main a test scope s rovnakým názvom, použije sa v teste ten
		// testový a v prode prodový - skúsiť nejaké experimenty
		emFactory.setPersistenceXmlLocation("classpath:persistence.xml");
		return emFactory;
	}

	private Properties additionalJpaProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		return properties;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:testdb");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}
}
