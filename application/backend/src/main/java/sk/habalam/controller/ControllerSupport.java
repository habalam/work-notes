package sk.habalam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class ControllerSupport {

	protected static final String OK_RESPONSE_CODE = "OK";

	protected final Logger logger = LoggerFactory.getLogger(getClass());
}
