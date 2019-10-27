package sk.habalam.repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO pridať všeobecné metódy (select, update, delete, insert) kde by na vstupe vchádzali nejaké predikáty
abstract class RepositoryBase {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected JPAQueryFactory jpaQueryFactory;

	@PersistenceContext
	protected EntityManager entityManager;

	@PostConstruct
	private void createJpaQueryFactory() {
		this.jpaQueryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
	}
}
