package com.openclassrooms.p5springbootjg.Util;

import java.util.ArrayList;
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

public class JsonTools {

	private static Logger logger = LoggerFactory.getLogger(SafetyNetUtil.class);

	private static ObjectMapper mapper = new ObjectMapper();

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
		JsonNode resultNode = mapper.createObjectNode();
		try {
			resultNode = mapper.readTree(mapper.writeValueAsString(list));
		} catch (JsonMappingException e) {
			logger.error(e.toString());
		} catch (JsonProcessingException e) {
			logger.error(e.toString());
		}
		return resultNode;
	}

	public static ArrayNode getNewArrayNode() {
		return mapper.createArrayNode();
	}

	public static ObjectNode getNewObjectNode() {
		return mapper.createObjectNode();
	}
	
	public static ObjectMapper getObjectMapper() {
		return mapper;
	}

	public static ArrayNode convertArrayStringToJsonNode(ArrayList<String> array,String name) {
		ArrayNode resultArray = getNewArrayNode();
		for (String res : array) {
			ObjectNode currentNode = JsonTools.getNewObjectNode();
			currentNode.put(name, res);
			resultArray.add(currentNode);
		}
		return resultArray;
	}
}
