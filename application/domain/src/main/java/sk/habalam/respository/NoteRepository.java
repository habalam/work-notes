package sk.habalam.respository;

import java.util.List;

import sk.habalam.domain.Note;

/**
 * NoteRepository.
 *
 * @author habala
 */
public interface NoteRepository {

	Note findById(int id);

	List<Note> findAll();
}
