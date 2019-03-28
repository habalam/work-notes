package sk.habalam.configuration.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sk.habalam.domain.Role;
import sk.habalam.service.UserAuthenticationService;

@Component
public class JwtTokenProvider {

	private final UserAuthenticationService userAuthenticationService;

	@Autowired
	public JwtTokenProvider(UserAuthenticationService userAuthenticationService) {
		this.userAuthenticationService = userAuthenticationService;
	}

	@Value("${security.jwt.token.secret-key}")
	private String secretKey = "secred";

	@Value("${security.jwt.token.validity-in-millis:360000}")
	private long tokenValidityInMillis = 360000;

	@PostConstruct
	private void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String userName, List<Role> roles) {
		Claims claims = Jwts.claims().setSubject(userName);
		claims.put("roles", roles);
		Date now = new Date();
		Date validityEnds = new Date(now.getTime() + tokenValidityInMillis);
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(validityEnds)
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	private String getUserName(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userAuthenticationService.loadUserByUsername(getUserName(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
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
}
