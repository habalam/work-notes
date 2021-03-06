package sk.habalam.respository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import sk.habalam.domain.Task;

/**
 * Repository for getting informations about {@link Task}. It is not only simple CRUD operations, but
 * also "domain" logic
 * */
public interface TaskRepository {

	/** Return unique {@link Task} by id */
	Task findById(int id);

	/** Return all {@link Task}'s for {@link sk.habalam.domain.User} */
	List<Task> findAllUserTasks(Integer userId);

	/** Return all {@link Task}'s valid in selected day (created before or equals and closed after or never) for user*/
	List<Task> findAllTasksByUserAndValidInDay(Integer userId, LocalDate date);

	/** Add new {@link Task} to DB*/
	void addTask(Task task);

	/** Delete {@link Task} from DB identified by ID */
	void deleteTask(Integer taskId);

	/** Update {@link Task} in DB identified by ID (took from Task in method parameter) with data
	 * from parameter */
	void updateTask(Task task);

	/** Mark task as {@link sk.habalam.domain.support.TaskState#CLOSED} to given time*/
	void closeTask(Integer taskId, LocalDateTime dateTime);
}
