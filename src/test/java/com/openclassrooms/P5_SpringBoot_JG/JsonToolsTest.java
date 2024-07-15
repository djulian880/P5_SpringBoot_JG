package com.openclassrooms.P5_SpringBoot_JG;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.P5_SpringBoot_JG.Util.JsonTools;

public class JsonToolsTest {
	
	private static ObjectMapper mapper;
	
	@BeforeAll
	static void contextLoads() {
		mapper = new ObjectMapper();
	}
	
	
	@Test
	public void TestlistSameFieldNameToJSON() {
		ArrayList<String> list=new ArrayList<String>();
		String name="nomnoeud";
		list.add("Test1");
		list.add("Test2");
		list.add("Test3");
		ArrayNode resultArray = mapper.createArrayNode();
		
		ObjectNode currentNode = mapper.createObjectNode();	
		currentNode.put("nomnoeud", "Test1");
		resultArray.add(currentNode);
		 currentNode = mapper.createObjectNode();	
		currentNode.put("nomnoeud", "Test2");
		resultArray.add(currentNode);
		 currentNode = mapper.createObjectNode();	
		currentNode.put("nomnoeud", "Test3");
		resultArray.add(currentNode);
		assertEquals(JsonTools.listSameFieldNameToJSON(name, list), resultArray);
		
	}
	
	
	@Test
	public void TestmapToJSONNodeString() {
		Map<String, String> values= new HashMap<String, String>();
		values.put("clé1", "valeur1");
		values.put("clé2", "valeur2");
		values.put("clé3", "valeur3");
		
		ObjectNode resultNode = mapper.createObjectNode();
		resultNode.put("clé1", "valeur1");
		resultNode.put("clé2", "valeur2");
		resultNode.put("clé3", "valeur3");
		assertEquals(JsonTools.mapToJSONNodeString(values), resultNode);
	}

	
	@Test	
	public void TestmapToJSONNodeInt() {
		Map<String, Integer> values= new HashMap<String, Integer>();
		values.put("clé1", 1);
		values.put("clé2", 2);
		values.put("clé3", 3);
		
		
		ObjectNode resultNode = mapper.createObjectNode();
		resultNode.put("clé1", 1);
		resultNode.put("clé2", 2);
		resultNode.put("clé3", 3);
		assertEquals(JsonTools.mapToJSONNodeInt(values), resultNode);

	}
	
	@Test
	public void TestlistToJSONString() {
		ArrayList<String> list=new ArrayList<String>();
	
		list.add("Test1");
		list.add("Test2");
		list.add("Test3");
		
		JsonNode resultNode = JsonTools.listToJSONString(list);
		assertEquals(resultNode.get(0).asText(), "Test1");
		assertEquals(resultNode.get(1).asText(), "Test2");
		assertEquals(resultNode.get(2).asText(), "Test3");
	}
	
	@Test
	public void TestconvertArrayStringToJsonNode() {
		ArrayList<String> list=new ArrayList<String>();
		list.add("Test1");
		list.add("Test2");
		list.add("Test3");
		
		ArrayNode resultArray  = JsonTools.getNewArrayNode();
		ObjectNode currentNode = JsonTools.getNewObjectNode();
		currentNode.put("testname", "Test1");
		resultArray.add(currentNode);
		 currentNode = JsonTools.getNewObjectNode();
		currentNode.put("testname", "Test2");
		resultArray.add(currentNode);
		 currentNode = JsonTools.getNewObjectNode();
		currentNode.put("testname", "Test3");
		resultArray.add(currentNode);
		
		assertEquals(resultArray,JsonTools.convertArrayStringToJsonNode(list,"testname"));
	}
	

	
}
