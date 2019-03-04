package sk.habalam.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseUtils {

	public static final DateTimeFormatter FORMATTER_UI = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss");

	public static String formatDateTime(LocalDateTime dateTime, DateTimeFormatter formatter) {
		return dateTime != null ? dateTime.format(formatter) : null;
	}

	public static String formatDateTimeUiFormat(LocalDateTime dateTime) {
		return formatDateTime(dateTime, FORMATTER_UI);
	}
}
