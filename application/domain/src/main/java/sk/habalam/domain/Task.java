package sk.habalam.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import sk.habalam.domain.support.TaskPriority;
import sk.habalam.domain.support.TaskState;
import sk.habalam.utils.BaseUtils;

@Entity
@Table(name = "WN_TASK")
public class Task {

	//TODO setovanie ID oddeliť do nejakej nadtriedy... typicky chceme prakticky u všetkých entít
	private Integer id;

	private String text;
	private TaskPriority priority;
	private TaskState state;
	private LocalDateTime created;
	private LocalDateTime closed;
	//TODO pridať nejaku temu, ku ktorej task moze patriť

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
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	//TODO ak by som chcel ma domain "čisté" tj. bez závislostí ohľadom JSON mappingu, tak by som si
	// zrejme musel spraviť nejaké DTO-čka... čo aktuálne nechcem... do budúcna zvážiť
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "CREATED", nullable = false)
	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	//TODO ak by som chcel ma domain "čisté" tj. bez závislostí ohľadom JSON mappingu, tak by som si
	// zrejme musel spraviť nejaké DTO-čka... čo aktuálne nechcem... do budúcna zvážiť
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
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

	@Override
	public String toString() {
		return "Task{" +
			"id=" + id + ", " +
			"text='" + text + ", " +
			"priority=" + priority.name() + ", " +
			"state=" + state.name() + ", " +
			"created=" + BaseUtils.formatDateTimeUiFormat(created) + ", " +
			"closed=" + BaseUtils.formatDateTimeUiFormat(closed) +
			'}';
	}
}
