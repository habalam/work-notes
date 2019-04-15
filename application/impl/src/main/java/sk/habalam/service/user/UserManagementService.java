package sk.habalam.service.user;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sk.habalam.domain.User;
import sk.habalam.respository.RoleRepository;
import sk.habalam.respository.UserRepository;

/** Serves to drive operations with Users (create, delete, activate/deactivate, ...)*/
@Service
public class UserManagementService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Autowired
	public UserManagementService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public void createUser(UserRegistrationData userRegistrationData) {
		User user = new User();
		user.setName(userRegistrationData.getUserName());
		user.setPassword(new BCryptPasswordEncoder().encode(userRegistrationData.getPassword()));
		user.setEmail(userRegistrationData.getEmailAddress());
		user.setActive(true);
		user.setRoles(Collections.singletonList(roleRepository.findRoleByName("USER")));
		userRepository.createUser(user);
	}
}
