package sk.habalam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sk.habalam.domain.Note;
import sk.habalam.respository.NoteRepository;

//TODO vymyslieť obsah triedy a následne korektne pomenovať
@RestController
public class WorkNotesRestController {

	private final NoteRepository noteRepository;

	@Autowired
	public WorkNotesRestController(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	@GetMapping(value = "/noteById/{ID}")
	public String getNodeIds(@PathVariable("ID") Integer noteId) {
		Note note = noteRepository.findById(noteId);
		return note.getId().toString();
	}
}
