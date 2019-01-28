package sk.habalam.respository;

import sk.habalam.domain.Note;

/**
 * NoteRepository.
 *
 * @author habala
 */
public interface NoteRepository {

	Note findById(int id);
}
