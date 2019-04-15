package sk.habalam.respository;

import sk.habalam.domain.User;

public interface UserRepository {

	/** Find unique user by it's {@link User#getName()} */
	User findUserByName(String userName);

	/** Get info about existence of {@link User} with {@link User#name} OR {@link User#email}*/
	boolean userWithNameOrEmailExists(String userName, String email);

	/** Find unique user by it's {@link User#getEmail()}*/
	User findUserByEmail(String userEmail);

	/** Persist new {@link User} to DB*/
	void createUser(User user);

	/** Delete {@link User} from DB by it's ID*/
	void deleteUser(Integer userId);

	/** Deactivate {@link User} by it's ID */
	void deactivateUser(Integer userId);

	/** Activate {@link User} */
	void activateUser(Integer userId);
}
