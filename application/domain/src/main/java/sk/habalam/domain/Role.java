package sk.habalam.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "WN_ROLE")
public class Role extends BaseEntity<Integer> {

	private String name;

	@Override
	@Id
	@Column(name = "WN_ROLE_ID", nullable = false, unique = true)
	@GeneratedValue(generator = "WN_ROLE_GEN")
	@GenericGenerator(
		name = "WN_ROLE_GEN",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
			@Parameter(name = "sequence_name", value = "role_sequence"),
			@Parameter(name = "increment_size", value = "1")
		}
	)
	public Integer getId() {
		return super.getId();
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
