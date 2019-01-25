package sk.habalam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO vymyslieť obsah triedy a následne korektne pomenovať
@SuppressWarnings("unused")
@RestController
public class WorkNotesRestController {

	@GetMapping(value = "/notesIds")
	public String getNodeIds() {
		return Integer.toString(2);
	}
}
