package sk.habalam.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import sk.habalam.domain.support.TaskPriority;
import sk.habalam.domain.support.TaskState;
import sk.habalam.utils.BaseUtils;

@Entity
@Table(name = "WN_TASK")
public class Task extends BaseEntity<Integer> {

	private String text;
	private TaskPriority priority;
	private TaskState state;
	private LocalDateTime created;
	private LocalDateTime closed;

	//TODO doplniť funkčnosť - toto by malo technicky pokryť všetko čo som aktuálne mal v Notepade
	private TaskTheme taskTheme;
//	private List<Task> childTasks;

	/** Owner of this {@link Task}*/
	private User user;

	//TODO doplniť rôzne "views" nad taskami - všetky nakopu, rozdelené po dňoch (takto to mám v notepade), podľa stavov, ...
	//TODO možno pridať údaj dokedy by mala byť úloha splnená
	//TODO zvážiť nejaké notifikácie keď má vypršať čas, dokedy by mala byť úloha hotová

	@Id
	@Column(name = "ID", nullable = false, unique = true, precision = 10)
	@GeneratedValue(generator = "WN_TASK_GEN")
	@GenericGenerator(
		name = "WN_TASK_GEN",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
			@Parameter(name = "sequence_name", value = "task_sequence"),
			@Parameter(name = "increment_size", value = "1")
		}
	)
	@Override
	public Integer getId() {
		return super.getId();
	}

	@Column(name = "TEXT", nullable = false)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TASK_PRIORITY", nullable = false)
	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	@Column(name = "CREATED", nullable = false)
	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Column(name = "CLOSED")
	public LocalDateTime getClosed() {
		return closed;
	}

	public void setClosed(LocalDateTime closed) {
		this.closed = closed;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TASK_STATE")
	public TaskState getState() {
		return state;
	}

	public void setState(TaskState state) {
		this.state = state;
	}

	@ManyToOne
	@JoinColumn(name = "WN_TASK_THEME_ID")
	public TaskTheme getTaskTheme() {
		return this.taskTheme;
	}

	public void setTaskTheme(TaskTheme taskTheme) {
		this.taskTheme = taskTheme;
	}

	//TODO chcem toto naozaj mať namapované? malo by stačiť iba ID usera resp. určite by som tieto dáta nemal posielať na klienta
	@ManyToOne
	@JoinColumn(name = "WN_USER_ID", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Task{" +
			"id=" + id + ", " +
			", text='" + text +
			", priority=" + priority.name() +
			", state=" + state.name() +
			", created=" + BaseUtils.formatDateTimeUiFormat(created) +
			", closed=" + BaseUtils.formatDateTimeUiFormat(closed) +
			", user=" + user.getId() +
			'}';
	}
}
