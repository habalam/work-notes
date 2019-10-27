package sk.habalam.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "WN_USER")
public class User extends BaseEntity<Integer> {

	private String name;
	private String email;
	private String password;
	private boolean active = true;
	private List<Role> roles;

	@Id
	@Column(name = "WN_USER_ID", nullable = false, unique = true)
	@GeneratedValue(generator = "WN_USER_GEN")
	@GenericGenerator(
		name = "WN_USER_GEN",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
			@Parameter(name = "sequence_name", value = "user_sequence"),
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

	@Column(name = "EMAIL", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ACTIVE", nullable = false)
	@Type(type = "yes_no")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@ManyToMany
	@JoinTable(
		name = "WN_USER_ROLE",
		joinColumns = @JoinColumn(name = "WN_USER_ID", referencedColumnName = "WN_USER_ID"),
		inverseJoinColumns = @JoinColumn(name = "WN_ROLE_ID", referencedColumnName = "WN_ROLE_ID")
	)
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String toString() {
		return "User{id=" + id +
			", name=" + name +
			", email=" + email +
			", password=" + password +
			", active=" + active + "}";
	}
}
