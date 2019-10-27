package sk.habalam.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.habalam.configuration.security.AuthData;
import sk.habalam.configuration.security.JwtTokenProvider;
import sk.habalam.configuration.security.UserDetailsCustom;
import sk.habalam.domain.Role;

@RestController
@RequestMapping("/auth")
public class AuthController extends ControllerSupport {

	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	public AuthController(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager)
	{
		this.jwtTokenProvider = jwtTokenProvider;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthData authData) {
		try {
			Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authData.getLoginName(), authData.getPassword()));
			UserDetailsCustom userDetails = (UserDetailsCustom) authentication.getPrincipal();
			String jwtToken = jwtTokenProvider.createToken(userDetails);
			Map<Object, Object> userData = new HashMap<>();
			userData.put("token", jwtToken);
			userData.put("userName", userDetails.getUsername());
			userData.put("roles", userDetails.getUserRoles().stream().map(Role::getName).collect(Collectors.toList()));
			logger.info("User={" + userDetails.getUsername() + "} logged in.");
			return ok(userData);
		}
		catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid loginName/password supplied");
		}
	}
}
