package sk.habalam.configuration;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonMapperConfiguration {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

		SimpleModule module = new SimpleModule();
		module.addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE);
		module.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);

		objectMapper.registerModule(module);
		return objectMapper;
	}
}
