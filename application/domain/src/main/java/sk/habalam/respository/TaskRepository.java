package sk.habalam.respository;

import java.util.List;

import sk.habalam.domain.Task;

/**
 * TaskRepository.
 *
 * @author habala
 */
public interface TaskRepository {

	Task findById(int id);

	List<Task> findAll();
}
