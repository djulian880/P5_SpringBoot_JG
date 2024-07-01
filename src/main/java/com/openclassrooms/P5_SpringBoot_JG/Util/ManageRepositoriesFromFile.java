package com.openclassrooms.P5_SpringBoot_JG.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.P5_SpringBoot_JG.model.FireStation;
import com.openclassrooms.P5_SpringBoot_JG.model.MedicalRecord;
import com.openclassrooms.P5_SpringBoot_JG.model.Person;
import com.openclassrooms.P5_SpringBoot_JG.repository.FirestationRepository;
import com.openclassrooms.P5_SpringBoot_JG.repository.MedicalRecordRepository;
import com.openclassrooms.P5_SpringBoot_JG.repository.PersonRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Component
public class ManageRepositoriesFromFile {
	
	@Autowired
 
	
	private static Logger logger = LoggerFactory.getLogger(ManageRepositoriesFromFile.class);
	
	
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

	
	public static LocalDate formatDateFromString(String stringDate) {
		int year=Integer.parseInt(stringDate.substring(6, 10));
		int month=Integer.parseInt(stringDate.substring(0, 2));
		int day=Integer.parseInt(stringDate.substring(3, 5));
		LocalDate date = LocalDate.of(year, month, day);
		
		return date;

	}
	

}
