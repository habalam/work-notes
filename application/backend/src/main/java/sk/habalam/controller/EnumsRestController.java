package sk.habalam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.habalam.domain.support.TaskPriority;
import sk.habalam.domain.support.TaskState;

@RestController
public class EnumsRestController extends ControllerSupport {

	@GetMapping(value = "/task/priorities")
	public String getTaskPriorities() {
		return enumToString(TaskPriority.class);
	}
	
	@GetMapping(value = "/task/states")
	public String getTaskStates() {
		return enumToString(TaskState.class);
	}
}
