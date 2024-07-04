package com.openclassrooms.P5_SpringBoot_JG.Util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class SafetyNetUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	public static double calculateAge(Date birthDate) {
		Date currentTime = new Date();

		currentTime.setTime(System.currentTimeMillis());
		Duration duration = Duration.between(birthDate.toInstant(), currentTime.toInstant());
		return duration.toDays() / 365.0;

	}

	public static ArrayNode listSameFieldNameToJSON(String name, ArrayList<String> list) {
		ArrayNode resultArray = mapper.createArrayNode();

		for (String elem : list) {
			ObjectNode currentNode = mapper.createObjectNode();
			currentNode.put(name, elem);
			resultArray.add(currentNode);
		}

		return resultArray;
	}

	public static ObjectNode mapToJSONNodeString(Map<String, String> values) {
		ObjectNode resultNode = mapper.createObjectNode();
		for (Map.Entry<String, String> m : values.entrySet()) {

			resultNode.put(m.getKey(), m.getValue());
		}
		return resultNode;
	}

	public static ObjectNode mapToJSONNodeInt(Map<String, Integer> values) {
		ObjectNode resultNode = mapper.createObjectNode();
		for (Map.Entry<String, Integer> m : values.entrySet()) {
			resultNode.put(m.getKey(), m.getValue());
		}
		return resultNode;
	}
	
	public static JsonNode listToJSONString(List<String> list) {
		JsonNode resultNode=mapper.createObjectNode();
		try {
			resultNode= mapper.readTree(mapper.writeValueAsString(list));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultNode;
	}
}
