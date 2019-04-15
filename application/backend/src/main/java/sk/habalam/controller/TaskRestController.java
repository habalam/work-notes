package sk.habalam.controller;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.habalam.configuration.security.UserContext;
import sk.habalam.domain.Task;
import sk.habalam.respository.TaskRepository;
import sk.habalam.service.JsonService;
import sk.habalam.utils.BaseUtils;

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

	@GetMapping(value = "/task/allByUser")
	public String getAllUserTasks() {
		List<Task> tasks = taskRepository.findAllUserTasks(UserContext.getCurrentUserId());
		return jsonService.writeAsString(tasks);
	}

	@PostMapping(value = "/task/add")
	public void addTask(@RequestBody Task task) {
		//TODO taketo veci by som mal odlozit do nejakej servisnej triedy... TaskRestService?
		task.setCreated(BaseUtils.applyTimezoneAtLocalDateTime(task.getCreated(), ZoneOffset.UTC));
		task.setClosed(BaseUtils.applyTimezoneAtLocalDateTime(task.getClosed(), ZoneOffset.UTC));
		taskRepository.addTask(task);
		logger.info(task.toString());
	}

	@PostMapping(value = "/task/update")
	public void updateTask(@RequestBody Task task) {
		taskRepository.updateTask(task);
		logger.info(task.toString());
	}

	//TODO nejak vyriešiť navratove hodnoty a samozrejme logovanie
	//TODO deletovanie taskov nechcem - tasky sa close-uju a closenute sa v ďalších dňoch už zobrazovať nebudú resp. vo
	// výhľade s aktívnymi taskami
	@PostMapping(value = "/task/delete")
	public void deleteTaskById(@RequestBody Integer taskId) {
		taskRepository.deleteTask(taskId);
		logger.info("Task{id=" + taskId + "} deleted");
	}
}
