package sk.habalam;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import sk.habalam.annotation.MethodScopeData;
import sk.habalam.application.Application;
import sk.habalam.domain.Task;
import sk.habalam.domain.support.TaskPriority;
import sk.habalam.domain.support.TaskStatus;
import sk.habalam.respository.TaskRepository;

@MethodScopeData
@SpringBootTest(classes = Application.class)
public class TaskRepositoryTest extends IntegrationTestBase {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	protected void prepareData() {
		dataPreparator.createTask(TaskPriority.NONE, LocalDateTime.now(), LocalDateTime.now(),
			"Test_text", TaskStatus.OPENED);
	}

	@Test
	public void findAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		Assertions.assertThat(tasks.size()).isEqualTo(1);
	}
}

