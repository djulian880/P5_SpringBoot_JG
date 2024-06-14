package com.openclassrooms.P5_SpringBoot_JG;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.P5_SpringBoot_JG.service.ManageRepositoriesFromFile;

@SpringBootTest
public class ManageRepositoriesFromFileTests {

	@Test
	void contextLoads() {
	
	}
	
	// Test de lecture d'un fichier et bon retour du contenu
	@Test
    public void testreturnContentOfFileAsStringCorrect(){
		String path="src/test/fileTest.json";
		String contenuFichier=null;
		try {
			contenuFichier=ManageRepositoriesFromFile.returnContentOfFileAsString(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(contenuFichier,"{\"persons\": []}");
    }
	
	// Test de lecture d'un fichier et erreur générée car chemin du fichier NOK
	@Test
    public void testreturnContentOfFileAsStringIncorrect(){
		String path="src/test/";
		String contenuFichier=null;
		try {
			contenuFichier=ManageRepositoriesFromFile.returnContentOfFileAsString(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		assertEquals(contenuFichier,null);
    }
	
	
	// Test de lecture d'un noeud dans une chaîne JSON
	@Test
    public void returnContentOfJSONNodeAsStringCorrect(){
		String filecontent="{\n"
				+ "    \"persons\": [\n"
				+ "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }\n"
				+ "     ], \n"
				+ "    \"firestations\": [\n"
				+ "	{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n"
				+ "        { \"address\":\"29 15th St\", \"station\":\"2\" }\n"
				+ "	],\n"
				+ "    \"medicalrecords\": [\n"
				+ "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n"
				+ "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n"
				+ "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n"
				+ "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] }\n"
				+ "        ] \n"
				+ "}";
		String contenuNoeud=null;
		
		 
		contenuNoeud=ManageRepositoriesFromFile.returnContentOfJSONNodeAsString(filecontent,"persons");
		
		try {
			JSONAssert.assertEquals(contenuNoeud, "[\\n\"\n"
					+ "				+ \"        { \\\"firstName\\\":\\\"John\\\", \\\"lastName\\\":\\\"Boyd\\\", \\\"address\\\":\\\"1509 Culver St\\\", \\\"city\\\":\\\"Culver\\\", \\\"zip\\\":\\\"97451\\\", \\\"phone\\\":\\\"841-874-6512\\\", \\\"email\\\":\\\"jaboyd@email.com\\\" }\\n\"\n"
					+ "				+ \"     ]", JSONCompareMode.LENIENT);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
    }
	
	
}
