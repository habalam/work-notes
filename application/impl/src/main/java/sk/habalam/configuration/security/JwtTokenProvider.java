package sk.habalam.configuration.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import sk.habalam.domain.Role;

@Component
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key}")
	private String secretKey = "secret";

	@Value("${security.jwt.token.validity-in-millis:360000}")
	private long tokenValidityInMillis = 360000;

	@PostConstruct
	private void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public Authentication getAuthentication(String token) {
		UserDetailsCustom userDetails = getUserData(token);
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private UserDetailsCustom getUserData(String token) {
		Claims jwtClaims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		@SuppressWarnings("unchecked") List<String> userRoles = (List<String>) jwtClaims.get("userRoles", List.class);
		Integer userId = jwtClaims.get("userId", Integer.class);
		String userName = jwtClaims.getSubject();
		List<GrantedAuthority> authorities = userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		return new UserDetailsCustom(userId, userName, "", authorities, null);
	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return !claims.getBody().getExpiration().before(new Date());
		}
		catch (JwtException | IllegalArgumentException e) {
			throw new JwtException("Expired or invalid JWT token");
		}
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public String createToken(UserDetailsCustom userDetails) {
		Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("userId", userDetails.getUserId());
		claims.put("userRoles", userDetails.getUserRoles().stream().map(Role::getName).collect(Collectors.toList()));
		Date now = new Date();
		Date validityEnds = new Date(now.getTime() + tokenValidityInMillis);
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(validityEnds)
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}
}
