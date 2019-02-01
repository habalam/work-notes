package sk.habalam;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.habalam.configuration.DbInitConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DbInitConfiguration.class})
@EnableConfigurationProperties
//TODO spravit samostatny novy modul, ktory by obsahoval konfiguraciu testov/test scope a vsetky ostatne
// moduly by od neho dedili (ale tiez iba v test scope)
public abstract class IntegrationTestBase  {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	protected EntityManager entityManager;
	protected DataPreparator dataPreparator;

	@PostConstruct
	private void init() {
		createPersistenceContext();
	}

	private void createPersistenceContext() {
		if(entityManager != null) {
			throw new IllegalStateException("EntityManager already exists!");
		}
		this.entityManager = entityManagerFactory.createEntityManager();
		createDataPreparator();
	}

	protected void recreatePersistenceContext() {
		if (entityManager != null) {
			entityManager.close();
		}
		createPersistenceContext();
	}

	private void createDataPreparator() {
		this.dataPreparator = new DataPreparator(entityManager);
	}
}
