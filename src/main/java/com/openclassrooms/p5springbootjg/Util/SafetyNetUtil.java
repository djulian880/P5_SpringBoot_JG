package com.openclassrooms.p5springbootjg.Util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.p5springbootjg.controller.FireStationController;


public class SafetyNetUtil {

	public static double calculateAge(Date birthDate) {
		Date currentTime = new Date();
		currentTime.setTime(System.currentTimeMillis());
		Duration duration = Duration.between(birthDate.toInstant(), currentTime.toInstant());
		return duration.toDays() / 365.0;
	}

	

}
