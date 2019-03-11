package sk.habalam.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.lang.NonNull;

public class BaseUtils {

	public static final DateTimeFormatter FORMATTER_UI = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss");

	public static String formatDateTime(LocalDateTime dateTime, DateTimeFormatter formatter) {
		return dateTime != null ? dateTime.format(formatter) : null;
	}

	public static String formatDateTimeUiFormat(LocalDateTime dateTime) {
		return formatDateTime(dateTime, FORMATTER_UI);
	}

	public static LocalDateTime applyTimezoneAtLocalDateTime(LocalDateTime dateTime, @NonNull ZoneId zoneId) {
		if (dateTime == null) {
			return null;
		}
		return dateTime.atZone(zoneId).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
	}
}
