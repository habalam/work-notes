package sk.habalam;

import javax.persistence.EntityManager;

class DataPreparator {

	private final EntityManager entityManger;

	DataPreparator(EntityManager entityManager) {
		this.entityManger = entityManager;
	}
}
