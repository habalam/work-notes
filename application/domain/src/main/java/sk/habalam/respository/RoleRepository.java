package sk.habalam.respository;

import sk.habalam.domain.Role;

public interface RoleRepository {

	/** Find unique {@link Role} by it's name*/
	Role findRoleByName(String roleName);
}
