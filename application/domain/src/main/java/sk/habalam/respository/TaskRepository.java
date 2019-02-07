package sk.habalam.respository;

import java.time.LocalDate;
import java.util.List;

import sk.habalam.domain.Task;

/**
 * Repository for getting informations about {@link Task}. It is not only simple CRUD operations, but
 * also "domain" logic
 * */
public interface TaskRepository {

	Task findById(int id);

	List<Task> findAll();

	List<Task> findByDate(LocalDate date);
}
