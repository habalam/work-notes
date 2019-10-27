package sk.habalam.configuration.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import sk.habalam.domain.support.Role;
import sk.habalam.service.UserAuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	private final UserAuthenticationService userAuthenticationService;

	@Autowired
	public WebSecurityConfig(JwtTokenProvider jwtTokenProvider, UserAuthenticationService userAuthenticationService) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.userAuthenticationService = userAuthenticationService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userAuthenticationService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().csrf().disable().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
			.antMatchers("/auth/login").permitAll()
			.antMatchers("/user/registration").permitAll()
			.antMatchers("/user/**").hasAuthority(Role.USER.name())
			.antMatchers("/task/delete").hasAuthority(Role.ADMIN.name())
			.antMatchers("/task/**").hasAuthority(Role.USER.name())
			//TODO copy/paste reevaluate
			.anyRequest().authenticated().and().csrf()
			.disable().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint()).and()
			.apply(new JwtConfigurer(jwtTokenProvider));
	}

	@Bean
	PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
			"Unauthorized");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
