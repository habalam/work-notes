package sk.habalam.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import sk.habalam.domain.QTask;
import sk.habalam.domain.Task;
import sk.habalam.respository.TaskRepository;

@Repository
public class TaskRepositoryImpl extends RepositoryBase implements TaskRepository {

	@Override
	public Task findById(int id) {
		return entityManager.find(Task.class, id);
	}

	@Override
	public List<Task> findAll() {
		return jpaQueryFactory.selectFrom(QTask.task).fetch();
	}

	@Override
	public List<Task> findByDate(LocalDate date) {
		return jpaQueryFactory.selectFrom(QTask.task)
			.where(QTask.task.created.loe(LocalDateTime.of(date, LocalTime.MAX))
				.and(QTask.task.closed.goe(LocalDateTime.of(date, LocalTime.MIDNIGHT))
					.or(QTask.task.closed.isNull())))
			.fetch();
	}

	@Override
	@Transactional
	public void addTask(Task task) {
		task.setCreated(LocalDateTime.now());
		entityManager.persist(task);
	}

	@Override
	@Transactional
	public void deleteTask(Integer taskId) {
		jpaQueryFactory.delete(QTask.task).where(QTask.task.id.eq(taskId)).execute();
		//TODO vyskúmať ako toto funguje a či je to potrebné/škodlivé
		entityManager.flush();
	}

	@Override
	@Transactional
	public void updateTask(Task task) {
		QTask qTask = QTask.task;
		jpaQueryFactory.update(qTask).where(qTask.id.eq(task.getId()))
			.set(qTask.text, task.getText())
			.set(qTask.priority, task.getPriority())
			.set(qTask.state, task.getState())
			.execute();
	}
}
