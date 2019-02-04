package sk.habalam;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.habalam.application.Application;
import sk.habalam.respository.NoteRepository;

@SpringBootTest(classes = Application.class)
public class NoteRepositoryTest extends IntegrationTestBase {

	@Autowired
	private NoteRepository noteRepository;

	@Test
	public void testTest() {
		System.out.println("test");
	}
}

