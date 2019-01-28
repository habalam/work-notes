package sk.habalam.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO vymyslieť obsah triedy a následne korektne pomenovať
@SuppressWarnings("unused")
@RestController
public class WorkNotesRestController {

	@Autowired
	private DataSource dataSource;

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@PersistenceContext
	private EntityManager entityManger;

	@GetMapping(value = "/notesIds")
	public String getNodeIds() {
		return Integer.toString(2);
	}
}
