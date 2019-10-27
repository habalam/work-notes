package sk.habalam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import sk.habalam.annotation.MethodScopeData;
import sk.habalam.application.Application;
import sk.habalam.domain.Task;
import sk.habalam.domain.User;
import sk.habalam.domain.support.TaskPriority;
import sk.habalam.domain.support.TaskState;
import sk.habalam.respository.TaskRepository;

@MethodScopeData
@SpringBootTest(classes = Application.class)
public class TaskRepositoryTest extends RepositoryTestBase {

	@Autowired
	private TaskRepository taskRepository;

	private Integer firstUserId;
	private Integer secondUserId;

	@Override
	protected void prepareData() {
		User firstUser = dataPreparator.createUser("Homelander", "74theWinn", "home.lander@seven.com", true);
		firstUserId = firstUser.getId();

		dataPreparator.createTask(firstUser, TaskPriority.NONE, LocalDateTime.parse("2019-02-06T11:44:59"),
			LocalDateTime.parse("2019-02-06T12:34:39"), TaskState.OPENED);
		dataPreparator.createTask(firstUser, TaskPriority.TOP, LocalDateTime.parse("2019-02-11T12:32:28"),
			LocalDateTime.parse("2019-02-18T12:32:28"), TaskState.OPENED);
		dataPreparator.createTask(firstUser, TaskPriority.TOP, LocalDateTime.parse("2019-02-12T12:32:28"),
			LocalDateTime.parse("2019-02-21T12:32:28"), TaskState.OPENED);
		dataPreparator.createTask(firstUser, TaskPriority.TOP, LocalDateTime.parse("2019-02-13T12:32:28"),
			LocalDateTime.parse("2019-02-20T14:32:28"), TaskState.CLOSED);
		dataPreparator.createTask(firstUser, TaskPriority.TOP, LocalDateTime.parse("2019-02-14T12:32:28"),
			LocalDateTime.parse("2019-02-19T12:32:28"), TaskState.OPENED);
		dataPreparator.createTask(firstUser, TaskPriority.TOP, LocalDateTime.parse("2019-02-15T12:32:28"),
			LocalDateTime.parse("2019-02-22T12:32:28"), TaskState.OPENED);
		dataPreparator.createTask(firstUser, TaskPriority.MEDIUM, LocalDateTime.parse("2019-02-25T08:08:30"),
			null, TaskState.OPENED);

		User secondUser = dataPreparator.createUser("Deep", "Blow", "deep@seven.com", true);
		secondUserId = secondUser.getId();

		dataPreparator.createTask(secondUser, TaskPriority.NONE, LocalDateTime.parse("2019-02-06T19:45:50"),
			LocalDateTime.parse("2019-02-06T23:59:59"), TaskState.OPENED);
		dataPreparator.createTask(secondUser, TaskPriority.TOP, LocalDateTime.parse("2019-02-13T00:00:01"),
			LocalDateTime.parse("2019-09-09T12:00:00"), TaskState.OPENED);
	}

	@Test
	public void findAllTasks() {
		List<Task> tasks = taskRepository.findAllUserTasks(firstUserId);
		Assertions.assertThat(tasks.size()).isEqualTo(7);

		Task task = tasks.get(0);
		assertTask(task, "2019-02-06T11:44:59", "2019-02-06T12:34:39",
			TaskState.OPENED, TaskPriority.NONE);
	}

	@Test
	public void findByDay() {
		List<Task> tasksByDate = taskRepository.findAllTasksByUserAndValidInDay(firstUserId, LocalDate.parse("2019-02-06"));
		Assertions.assertThat(tasksByDate.size()).isEqualTo(1);

		tasksByDate = taskRepository.findAllTasksByUserAndValidInDay(firstUserId, LocalDate.parse("2019-02-07"));
		Assertions.assertThat(tasksByDate.size()).isEqualTo(0);

		tasksByDate = taskRepository.findAllTasksByUserAndValidInDay(firstUserId, LocalDate.parse("2019-02-11"));
		Assertions.assertThat(tasksByDate.size()).isEqualTo(1);

		tasksByDate = taskRepository.findAllTasksByUserAndValidInDay(firstUserId, LocalDate.parse("2019-02-12"));
		Assertions.assertThat(tasksByDate.size()).isEqualTo(2);

		tasksByDate = taskRepository.findAllTasksByUserAndValidInDay(firstUserId, LocalDate.parse("2019-02-18"));
		Assertions.assertThat(tasksByDate.size()).isEqualTo(5);

		tasksByDate = taskRepository.findAllTasksByUserAndValidInDay(firstUserId, LocalDate.parse("2019-02-19"));
		Assertions.assertThat(tasksByDate.size()).isEqualTo(4);

		tasksByDate = taskRepository.findAllTasksByUserAndValidInDay(firstUserId, LocalDate.parse("2019-02-22"));
		Assertions.assertThat(tasksByDate.size()).isEqualTo(1);

		tasksByDate = taskRepository.findAllTasksByUserAndValidInDay(firstUserId, LocalDate.parse("2019-03-22"));
		Assertions.assertThat(tasksByDate.size()).isEqualTo(1);
	}

	@Test
	public void logTest() {
		logger.trace("TRACE message");
		logger.debug("DEBUG message");
		logger.info("INFO message");
		logger.warn("WARN message");
		logger.error("ERROR message");
	}

	private void assertTask(Task task, String createdDate, String closedDate, TaskState taskStatus,
		TaskPriority taskPriority)
	{
		Assertions.assertThat(task.getText()).isEqualTo("Test_text");
		Assertions.assertThat(task.getPriority()).isEqualByComparingTo(taskPriority);
		Assertions.assertThat(task.getState()).isEqualByComparingTo(taskStatus);
		Assertions.assertThat(task.getId()).isNotNull();
		Assertions.assertThat(task.getCreated()).isEqualTo(createdDate);
		Assertions.assertThat(task.getClosed()).isEqualTo(closedDate);
	}
}

