package sk.habalam.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WN_NOTE")
public class Note {

	private Integer id;

	@Id
	@Column(name = "ID", nullable = false, unique = true, precision = 10)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
