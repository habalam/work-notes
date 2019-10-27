package sk.habalam.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;

abstract class ControllerSupport {

	protected static final String OK_RESPONSE_CODE = "OK";

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	//TODO vytvoriť nejakú supportnú tridu... toto by sa mi mohlo hodiť aj niekde inde
	protected  <T extends Enum<T>> String enumToString(Class<T> enumClass) {
		return new JSONArray(Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).collect(Collectors.toList())).toString();
	}
}
