package sk.habalam.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import sk.habalam.domain.QTask;
import sk.habalam.domain.Task;
import sk.habalam.respository.TaskRepository;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Task findById(int id) {
		return entityManager.find(Task.class, id);
	}

	@Override
	public List<Task> findAll() {
		return new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager).selectFrom(QTask.task).fetch();
	}
}
