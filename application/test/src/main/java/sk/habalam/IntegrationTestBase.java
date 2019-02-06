package sk.habalam;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import sk.habalam.annotation.ClassScopeData;
import sk.habalam.annotation.MethodScopeData;

/**
 * Configuration class for integration tests cross modules (depend on test module). It uses own
 * JPA configuration (jpa-integration-test.properties) which replaces local (module) JPA configuration
 * (searched in jpa.properties by default). ApplicationContext not set up, this is provided by
 * @SpringBootTest annotation on test class. Commented out annotations worked as default configuration,
 * but in this case test classes (extends this one) can't resolve autowired components (they are
 * autowired but IDEA don't know about it).
 * */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {DbInitConfiguration.class})
//@ComponentScan("sk.habalam")
@EnableConfigurationProperties
@TestPropertySource(locations = "classpath:jpa-integration-test.properties")
public abstract class IntegrationTestBase extends AbstractTestNGSpringContextTests {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	protected EntityManager entityManager;
	protected DataPreparator dataPreparator;

	@BeforeMethod
	public final void reportMethodName(Method method) {
		logger.info("TEST method: " + method.getDeclaringClass().getName() + " - " + method.getName());
	}

	@BeforeClass
	public final void initDataBeforeClass() {
		if(getClass().getAnnotation(ClassScopeData.class) != null) {
			init();
		}
	}

	@BeforeMethod
	public final void initDataBeforeMethod() {
		if(getClass().getAnnotation(MethodScopeData.class) != null) {
			init();
		}
	}

	private void init() {
		createPersistenceContext();
		beginTransaction();
		deleteData();
		prepareData();
		commitTransaction();
		destoyPersistenceContext();
	}

	private void beginTransaction() {
		if(entityManager == null) {
			throw new IllegalStateException("EntityManager not exists - transaction can't be created!");
		}
		entityManager.getTransaction().begin();
	}

	private void commitTransaction() {
		if(entityManager == null) {
			throw new IllegalStateException("EntityManager not exists - transaction can't be commited!");
		}
		entityManager.getTransaction().commit();
	}

	@BeforeMethod(dependsOnMethods = "initDataBeforeMethod")
	private void createPersistenceContext() {
		if(entityManager != null) {
			throw new IllegalStateException("EntityManager already exists!");
		}
		this.entityManager = entityManagerFactory.createEntityManager();
		createDataPreparator();
	}

	@AfterMethod
	private void destoyPersistenceContext() {
		if (entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().rollback();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		entityManager = null;
		dataPreparator = null;
	}

	protected void deleteData() {
		//default empty - if we have not data for delete
	}

	protected  void prepareData() {
		//default empty - if we not want initialize data fot test
	}

	protected void recreatePersistenceContext() {
		destoyPersistenceContext();
		createPersistenceContext();
	}

	private void createDataPreparator() {
		this.dataPreparator = new DataPreparator(entityManager);
	}
}
