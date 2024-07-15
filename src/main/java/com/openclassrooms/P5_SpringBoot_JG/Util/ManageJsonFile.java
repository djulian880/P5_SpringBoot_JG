package com.openclassrooms.P5_SpringBoot_JG.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ManageJsonFile {
	
 	private static Logger logger = LoggerFactory.getLogger(ManageJsonFile.class);
		
	public static String returnContentOfFileAsString(String filepath) {
		String content = null;
		Path filePath = Path.of(filepath);
		try {
			content = Files.readString(filePath);
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return content;
	}

	public static String returnContentOfJSONNodeAsString(String content, String nodeName) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		String contentOfNode = null;
		try {
			jsonNode = objectMapper.readTree(content);
			contentOfNode = jsonNode.get(nodeName).toString();
		} catch (JsonMappingException e) {
			logger.error(e.toString());
		} catch (JsonProcessingException e) {
			logger.error(e.toString());
		}
		return contentOfNode;
	}
}
