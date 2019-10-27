package sk.habalam.service.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.habalam.configuration.security.UserContext;
import sk.habalam.domain.Task;
import sk.habalam.domain.User;
import sk.habalam.respository.TaskRepository;
import sk.habalam.respository.UserRepository;
import sk.habalam.utils.BaseUtils;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;

	@Autowired
	public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}

	/**
	 * Complete task to final state (set current user, normalize dates, ...) and add to DB
	 * */
	public void createTask(Task task) {
		task.setCreated(BaseUtils.applyTimezoneAtLocalDateTime(task.getCreated(), ZoneOffset.UTC));
		task.setClosed(BaseUtils.applyTimezoneAtLocalDateTime(task.getClosed(), ZoneOffset.UTC));
		User user = userRepository.findUserById(UserContext.getCurrentUserId());
		task.setUser(user);
		taskRepository.addTask(task);
	}

	/** Update data ({@link Task#text}, {@link Task#priority}, {@link Task#state}) of existing task*/
	public void updateTask(Task task) {
		taskRepository.updateTask(task);
	}

	/** Return all tasks for current (signed and get from authentication) user*/
	public List<Task> findCurrentUserTasks() {
		return taskRepository.findAllUserTasks(UserContext.getCurrentUserId());
	}

	/** Return all tasks for current user and valid (created before or equals and closed after or never) in selected day*/
	public List<Task> findCurrentUserTaskValidForDay(LocalDate day) {
		return taskRepository.findAllTasksByUserAndValidInDay(UserContext.getCurrentUserId(), day);
	}

	/** Find task by it's ID*/
	public Task findTaskById(Integer taskId) {
		return taskRepository.findById(taskId);
	}

	/** Delete task by it's ID*/
	public void deleteTask(Integer taskId) {
		taskRepository.deleteTask(taskId);
	}

	/** Close task by it's ID to current DateTime*/
	public void closeTask(Integer taskId) {
	 	taskRepository.closeTask(taskId, LocalDateTime.now());
	}
}
