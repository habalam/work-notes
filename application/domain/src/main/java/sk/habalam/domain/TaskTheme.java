package sk.habalam.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import sk.habalam.domain.support.TaskThemeState;
import sk.habalam.utils.BaseUtils;

@Entity
@Table(name = "WN_TASK_THEME")
public class TaskTheme extends BaseEntity<Integer> {

	private String name;
	private String description;
	private TaskThemeState state;
	private LocalDateTime created;
	private LocalDateTime closed;

	private User user;

	@Id
	@Column(name = "WN_TASK_THEME_ID", nullable = false, updatable = false, unique = true)
	@GeneratedValue(generator = "WN_TASK_THEME_GEN")
	@GenericGenerator(name = "WN_TASK_THEME_GEN",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
			@Parameter(name = "sequence_name", value = "task_theme_sequence"),
			@Parameter(name = "increment_size", value = "1")
		})
	@Override
	public Integer getId() {
		return super.getId();
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Lob
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Enumerated(value = EnumType.STRING)
	@Column(name = "STATE", nullable = false)
	public TaskThemeState getState() {
		return state;
	}

	public void setState(TaskThemeState state) {
		this.state = state;
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
		return "TaskTheme{" +
			"id=" + id +
			", name=" + name +
			", state=" + state +
			", created=" + BaseUtils.formatDateTimeUiFormat(created) +
			", closed=" + BaseUtils.formatDateTimeUiFormat(closed) +
			", user=" + user.getId() + "}";
	}
}
