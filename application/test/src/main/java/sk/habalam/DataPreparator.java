package sk.habalam;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import sk.habalam.domain.Task;
import sk.habalam.domain.support.TaskPriority;
import sk.habalam.domain.support.TaskStatus;

class DataPreparator {

	private final EntityManager entityManger;

	DataPreparator(EntityManager entityManager) {
		this.entityManger = entityManager;
	}

	public Task createTask(TaskPriority taskPriority, LocalDateTime created, LocalDateTime closed,
		TaskStatus taskStatus)
	{
		Task task = new Task();
		task.setPriority(taskPriority);
		task.setStatus(taskStatus);
		task.setText("Test_text");
		task.setCreated(created);
		task.setClosed(closed);
		entityManger.persist(task);
		return task;
	}
}
