package sk.habalam.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.habalam.domain.Task;
import sk.habalam.service.JsonService;
import sk.habalam.service.task.TaskService;

@RestController
public class TaskRestController extends ControllerSupport {

	private final JsonService jsonService;
	private final TaskService taskService;

	@Autowired
	public TaskRestController(TaskService taskService, JsonService jsonService)	{
		this.taskService = taskService;
		this.jsonService = jsonService;
	}

	//TODO nedávať to zbytočne ako PathVariable keď môžem použiť @RequestBody - preštudovať si, čo by som mal používať
	@GetMapping(value = "/taskById/{ID}")
	public String getTaskById(@PathVariable("ID") Integer taskId) {
		Task task = taskService.findTaskById(taskId);
		return jsonService.writeAsString(task);
	}

	@GetMapping(value = "/tasksByDay")
	public List<Task> getTasksByDay(@RequestParam(name = "date") String dateString) {
		try {
			LocalDate date = LocalDate.parse(dateString);
			return taskService.findCurrentUserTaskValidForDay(date);
		}
		catch (DateTimeParseException e) {
			logger.debug("Date{" + dateString + "} can't be parsed!", e);
			throw e;
		}
	}

	@GetMapping(value = "/task/allByUser")
	public String getAllUserTasks() {
		List<Task> tasks = taskService.findCurrentUserTasks();
		return jsonService.writeAsString(tasks);
	}

	@PostMapping(value = "/task/add")
	public void addTask(@RequestBody Task task) {
		taskService.createTask(task);
	}

	@PostMapping(value = "/task/update")
	public void updateTask(@RequestBody Task task) {
		taskService.updateTask(task);
	}

	//TODO zariadiť aby možnosť na mazanie taskov videl iba user s právami Admina
	@PostMapping(value = "/task/delete")
	public void deleteTaskById(@RequestBody Integer taskId) {
		taskService.deleteTask(taskId);
		logger.info("Task{id=" + taskId + "} deleted");
	}

	@PostMapping("/task/close")
	public void closeTaskById(@RequestBody Integer taskId) {
		taskService.closeTask(taskId);
		logger.debug("Task{id=" + taskId + "} closed");
	}
}
