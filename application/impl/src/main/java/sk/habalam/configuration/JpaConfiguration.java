package sk.habalam.configuration;

import java.util.HashMap;
import java.util.Map;

public class JpaConfiguration {

	private final Map<String, String> configuration = new HashMap<>();

	public Map<String, String> getConfiguration() {
		return configuration;
	}
}
