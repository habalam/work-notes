package sk.habalam;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import sk.habalam.domain.Task;
import sk.habalam.domain.User;
import sk.habalam.domain.support.TaskPriority;
import sk.habalam.domain.support.TaskState;

class DataPreparator {

	private final EntityManager entityManger;

	DataPreparator(EntityManager entityManager) {
		this.entityManger = entityManager;
	}

	public Task createTask(User user, TaskPriority taskPriority, LocalDateTime created, LocalDateTime closed,
		TaskState taskState)
	{
		Task task = new Task();
		task.setPriority(taskPriority);
		task.setState(taskState);
		task.setText("Test_text");
		task.setCreated(created);
		task.setClosed(closed);
		task.setUser(user);
		entityManger.persist(task);
		return task;
	}

	public User createUser(String name, String password, String email, boolean active) {
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		user.setActive(active);
		user.setRoles(null);
		entityManger.persist(user);
		return user;
	}
}
