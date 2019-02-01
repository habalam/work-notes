package sk.habalam.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Provide DB connection configuration and JPA configuration. Configuration file is diffent for "main" and "test"
 * scope, so application run in test scope will work on H2 "in-memory" DB and "main" on other
 * */
@Configuration
@PropertySource("classpath:jpa.properties")
public class DataSourceConfiguration {

	//TODO mohol by som pouzit proste len application.properties co by my dalo vacsiu volnost pri pouzivani Spring profilov
	@Bean(name = "entityMangerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
		emFactory.setDataSource(dataSource());
		emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emFactory.setJpaPropertyMap(jpaConfiguration().getConfiguration());
		//TODO setup ehcache, second level cache, query cache
		emFactory.setPersistenceXmlLocation("classpath:persistence.xml");
		return emFactory;
	}

	@Bean
	@ConfigurationProperties(prefix = "jpa")
	public JpaConfiguration jpaConfiguration() {
		return new JpaConfiguration();
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.conf")
	public DataSource dataSource() {
		//TODO spraviť nejaký script na vytvorenie "produkčnej" DB - ofc by sa spustal priamo nad DB
		// a nie v kode
		return new DriverManagerDataSource();
	}
}