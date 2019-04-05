package sk.habalam.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContext {

	public static String getCurrentUserName() {
		UserDetailsCustom userData = checkAndTransformAuthentication(SecurityContextHolder.getContext().getAuthentication());
		if (userData != null) {
			return userData.getUsername();
		}
		return null;
	}

	public static Integer getCurrentUserId() {
		UserDetailsCustom userData = checkAndTransformAuthentication(SecurityContextHolder.getContext().getAuthentication());
		if (userData != null) {
			return userData.getUserId();
		}
		return null;
	}

	private static UserDetailsCustom checkAndTransformAuthentication(Authentication authentication) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return null;
		}

		Object principal = auth.getPrincipal();
		if (principal instanceof UserDetailsCustom) {
			return  (UserDetailsCustom) principal;
		}
		return null;
	}
}
