package sk.habalam.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.habalam.configuration.security.AuthData;
import sk.habalam.configuration.security.JwtTokenProvider;
import sk.habalam.configuration.security.UserDetailsCustom;
import sk.habalam.service.UserAuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthController extends ControllerSupport {

	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	private UserAuthenticationService userAuthenticationService;

	@Autowired
	public AuthController(UserAuthenticationService userAuthenticationService,
		JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager)
	{
		this.userAuthenticationService = userAuthenticationService;
		this.jwtTokenProvider = jwtTokenProvider;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthData authData) {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authData.getLoginName(), authData.getPassword()));
			//TODO aj tu sa robí zbytočný request všetky dáta pretože pri vytváraní Authentication sa
			// používa tiež UserAuthenticationService - jwtTokenProvider by mohol priamo z authentication vytvárať
			// token, keď sa potom z tokenu vytvára zas authentication
			UserDetailsCustom user = userAuthenticationService.loadUserByUsername(authData.getLoginName());
			String jwtToken = jwtTokenProvider.createToken(user.getUserId(), user.getUsername(), user.getUserRoles());
			Map<Object, Object> userData = new HashMap<>();
			userData.put("token", jwtToken);
			userData.put("userName", user.getUsername());
			return ok(userData);
		}
		catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid loginName/password supplied");
		}
	}
}
