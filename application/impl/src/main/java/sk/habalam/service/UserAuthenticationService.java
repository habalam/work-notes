package sk.habalam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.habalam.configuration.security.UserDetailsCustom;
import sk.habalam.domain.User;
import sk.habalam.respository.UserRepository;

@Service
public class UserAuthenticationService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserAuthenticationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetailsCustom loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findUserByName(userName);
		if (user != null) {
			return createUserIdentity(user);
		} else {
			throw new UsernameNotFoundException("User with name{" + userName + "} not found!");
		}
	}

	private UserDetailsCustom createUserIdentity(User user) {
		List<GrantedAuthority> authorities = getUserAuthorities(user);
		return new UserDetailsCustom(user.getId(), user.getName(), user.getPassword(), authorities, user.getRoles());
	}

	private List<GrantedAuthority> getUserAuthorities(User user) {
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
