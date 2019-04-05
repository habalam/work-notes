package sk.habalam.configuration.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import sk.habalam.domain.Role;

public class UserDetailsCustom extends User {

	private Integer userId;
	private List<Role> userRoles;

	public UserDetailsCustom(Integer userId, String username, String password, Collection<? extends GrantedAuthority> authorities, List<Role> userRoles) {
		super(username, password, authorities);
		this.userId = userId;
		this.userRoles = userRoles;
	}

	public Integer getUserId() {
		return userId;
	}

	public List<Role> getUserRoles() {
		return userRoles;
	}
}
