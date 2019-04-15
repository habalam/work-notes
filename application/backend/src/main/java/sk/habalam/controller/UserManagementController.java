package sk.habalam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.habalam.respository.UserRepository;
import sk.habalam.service.user.UserManagementService;
import sk.habalam.service.user.UserRegistrationData;

@RestController
@RequestMapping(value = "/user")
public class UserManagementController extends ControllerSupport {

	private final UserManagementService userManagementService;
	private final UserRepository userRepository;

	@Autowired
	public UserManagementController(UserManagementService userManagementService,
		UserRepository userRepository)
	{
		this.userManagementService = userManagementService;
		this.userRepository = userRepository;
	}

	//TODO musím vracať info o tom, či úspešne dobehlo... ako je to najsprávnejšie?
	@PostMapping(value = "/registration")
	public void registration(@RequestBody UserRegistrationData registrationData) {
		boolean userExists = userRepository.userWithNameOrEmailExists(registrationData.getUserName(),
			registrationData.getEmailAddress());
		if (userExists) {
			throw new BadCredentialsException("User with UserName: " + registrationData.getUserName() + " already exists!");
		}
		userManagementService.createUser(registrationData);
	}

	//TODO dorobiť možnosť zmeny používateľských dát, typicky heslo
//	@PostMapping(value = "/userDataUpdate")
//	public void userDataUpdate(@RequestBody UserUpdateData updateData) {
//		Integer userId = UserContext.getCurrentUserId();
//	}
}
