package sk.habalam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sk.habalam.domain.Task;
import sk.habalam.respository.TaskRepository;

//TODO vymyslieť obsah triedy a následne korektne pomenovať
@RestController
public class WorkNotesRestController {

	private final TaskRepository taskRepository;

	@Autowired
	public WorkNotesRestController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@GetMapping(value = "/noteById/{ID}")
	public String getNodeIds(@PathVariable("ID") Integer noteId) {
		Task task = taskRepository.findById(noteId);
		return task.getId().toString();
	}
}
