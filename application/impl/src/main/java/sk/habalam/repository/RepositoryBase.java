package sk.habalam.repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;

abstract class RepositoryBase {

	protected JPAQueryFactory jpaQueryFactory;

	@PersistenceContext
	protected EntityManager entityManager;

	@PostConstruct
	private void createJpaQueryFactory() {
		this.jpaQueryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
	}
}
