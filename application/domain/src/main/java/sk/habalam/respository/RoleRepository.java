package sk.habalam.respository;

import sk.habalam.domain.Role;

//TODO ak toto nebudem potrebovať dlhodobejšie - zrušiť
public interface RoleRepository {

	/** Find unique {@link Role} by it's name*/
	Role findRoleByName(String roleName);
}
