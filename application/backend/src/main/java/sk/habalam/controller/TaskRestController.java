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
import sk.habalam.respository.TaskRepository;
import sk.habalam.service.JsonService;

@RestController
public class TaskRestController extends ControllerSupport {

	private final TaskRepository taskRepository;
	private final JsonService jsonService;

	@Autowired
	public TaskRestController(TaskRepository taskRepository, JsonService jsonService) {
		this.taskRepository = taskRepository;
		this.jsonService = jsonService;
	}

	@GetMapping(value = "/taskById/{ID}")
	public String getTaskById(@PathVariable("ID") Integer noteId) {
		Task task = taskRepository.findById(noteId);
		return jsonService.writeAsString(task);
	}

	@GetMapping(value = "/tasksByDay")
	public List<Task> getTasksByDay(@RequestParam(name = "date") String dateString) {
		try {
			LocalDate date = LocalDate.parse(dateString);
			return taskRepository.findByDate(date);
		}
		catch (DateTimeParseException e) {
			logger.debug("Date{" + dateString + "} can't be parsed!", e);
			throw e;
		}
	}

	@GetMapping(value = "/task/all")
	public String getAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		return jsonService.writeAsString(tasks);
	}

	@PostMapping(value = "/task/add")
	public String addTask(@RequestBody Task task) {
		taskRepository.addTask(task);
		logger.info(task.toString());
		return OK_RESPONSE_CODE;
	}

	@PostMapping(value = "/task/update")
	public String updateTask(@RequestBody Task task) {
		taskRepository.updateTask(task);
		logger.info(task.toString());
		return OK_RESPONSE_CODE;
	}

	//TODO nejak vyriešiť navratove hodnoty a samozrejme logovanie
	@PostMapping(value = "/task/delete")
	public String deleteTaskById(@RequestBody Integer taskId) {
		taskRepository.deleteTask(taskId);
		logger.info("Task{id=" + taskId + "} deleted");
		return OK_RESPONSE_CODE;
	}
}
