package sk.habalam.configuration.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.JwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean {

	private JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		if (token != null) {
			try {
				jwtTokenProvider.validateToken(token);
			}
			catch (JwtException e) {
				HttpServletResponse httpServletResponse = (HttpServletResponse) response;
				httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
			Authentication authentication = jwtTokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}
}
