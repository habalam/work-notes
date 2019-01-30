package sk.habalam.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import sk.habalam.domain.Note;
import sk.habalam.domain.QNote;
import sk.habalam.respository.NoteRepository;

@Repository
public class NoteRepositoryImpl implements NoteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Note findById(int id) {
		return entityManager.find(Note.class, id);
	}

	@Override
	public List<Note> findAll() {
		return new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager).selectFrom(QNote.note).fetch();
	}
}
