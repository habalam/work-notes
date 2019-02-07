package sk.habalam.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.habalam.domain.Task;
import sk.habalam.respository.TaskRepository;

@RestController
public class TaskRestController extends ControllerSupport {

	private final TaskRepository taskRepository;

	@Autowired
	public TaskRestController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@GetMapping(value = "/taskById/{ID}")
	public String getTaskById(@PathVariable("ID") Integer noteId) {
		Task task = taskRepository.findById(noteId);
		return task.getId().toString();
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

	@GetMapping("/loggingTest")
	public String loggingTest() {
		logger.trace("TRACE message");
		logger.debug("DEBUG message");
		logger.info("INFO message");
		logger.warn("WARN message");
		logger.error("ERROR message");
		return "Logger checked!";
	}
}