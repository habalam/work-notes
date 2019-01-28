package sk.habalam.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@PropertySource("classpath:jpa-configuration.properties")
public class DataSourceConfiguration {

	@Bean
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
	@ConfigurationProperties(prefix = "spring.datasource.conf")
	public DataSource dataSource() {
		return new DriverManagerDataSource();
	}
}
