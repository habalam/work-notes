package sk.habalam.service.user;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sk.habalam.domain.User;
import sk.habalam.respository.RoleRepository;
import sk.habalam.respository.UserRepository;

/** Serves to drive operations with Users (create, delete, activate/deactivate, ...)*/
@Service
public class UserManagementService {

	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Autowired
	public UserManagementService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public void createUser(UserRegistrationData userRegistrationData) {
		boolean userExists = userRepository.userWithNameOrEmailExists(userRegistrationData.getUserName(),
			userRegistrationData.getEmailAddress());
		if (userExists) {
			throw new BadCredentialsException("User with UserName: " + userRegistrationData.getUserName() + " already exists!");
		}

		User user = new User();
		user.setName(userRegistrationData.getUserName());
		user.setPassword(new BCryptPasswordEncoder().encode(userRegistrationData.getPassword()));
		user.setEmail(userRegistrationData.getEmailAddress());
		user.setActive(true);
		user.setRoles(Collections.singletonList(roleRepository.findRoleByName("USER")));
		userRepository.createUser(user);
		logger.info("User={" + user.getName() + "} registered.");
	}
}
