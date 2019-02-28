package sk.habalam.respository;

import java.time.LocalDate;
import java.util.List;

import sk.habalam.domain.Task;

/**
 * Repository for getting informations about {@link Task}. It is not only simple CRUD operations, but
 * also "domain" logic
 * */
//TODO dopísať javadoc
public interface TaskRepository {

	Task findById(int id);

	List<Task> findAll();

	List<Task> findByDate(LocalDate date);

	void addTask(Task task);

	void deleteTask(Integer taskId);
}
