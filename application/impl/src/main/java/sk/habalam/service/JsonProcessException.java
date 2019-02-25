package sk.habalam.service;

public class JsonProcessException extends RuntimeException {
	public JsonProcessException(String message, Throwable exception) {
		super(message, exception);
	}
}
