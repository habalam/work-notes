package sk.habalam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

	private final ObjectMapper objectMapper;

	@Autowired
	public JsonService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public String writeAsString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		}
		catch (JsonProcessingException e) {
			throw new JsonProcessException("Object can't be deserialized", e);
		}
	}
}
