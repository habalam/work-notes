package sk.habalam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.habalam.domain.User;
import sk.habalam.respository.RoleRepository;
import sk.habalam.respository.UserRepository;

@Service
public class UserAuthenticationService implements UserDetailsService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	@Autowired
	public UserAuthenticationService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findUserByName(userName);
		if (user != null) {
			List<GrantedAuthority> authorities = getUserAuthorities(user);
			return createUserIdentity(user, authorities);
		} else {
			throw new UsernameNotFoundException("User with name{" + userName + "} not found!");
		}
	}

	private org.springframework.security.core.userdetails.User createUserIdentity(User user, List<GrantedAuthority> authorities) {
		//TODO zrejme by sa hodilo vytvoriť vlastný objekt, ktorý by držal aj UserId... JwtTokenFilter by mal toto
		// toto nasetovať do SpringContextHolder-a odkiaľ by som mal byť schopný si to v prípade potreby vytiahnuť
		//TODO inou možnosťou by bolo všetko (aj vrátane autentifikácie a userId) napchať do tokenu a odtiaľ to iba vyťahovať
		// v prípade potreby - aktuálne riešenie pri každom request-e ťahá info o userovi (Authority) z DB, lebo z tokenu sa berie iba userName
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
	}

	private List<GrantedAuthority> getUserAuthorities(User user) {
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
