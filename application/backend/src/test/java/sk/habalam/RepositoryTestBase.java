package sk.habalam;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPADeleteClause;
import sk.habalam.domain.QTask;
import sk.habalam.domain.QUser;

public class RepositoryTestBase extends IntegrationTestBase {

	@Override
	protected void deleteData() {
		deleteTable(QTask.task);
		deleteTable(QUser.user);
	}

	private void deleteTable(EntityPathBase<?> qEntity) {
		long count = new JPADeleteClause(entityManager, qEntity).execute();
		logger.debug("Deleting " + qEntity + ": " + count + " rows deleted.");
	}
}
