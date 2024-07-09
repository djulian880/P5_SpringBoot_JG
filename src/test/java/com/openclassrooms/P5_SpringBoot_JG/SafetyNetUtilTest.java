package com.openclassrooms.P5_SpringBoot_JG;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.openclassrooms.P5_SpringBoot_JG.Util.SafetyNetUtil;

public class SafetyNetUtilTest {

	@Test
	public void TestcalculateAge() {
		
		//Date currentTime = new Date(System.currentTimeMillis());
		Date birthDate=new Date(System.currentTimeMillis() - 12*365*24*60 * 60 * 1000);
		
		
		assertEquals(SafetyNetUtil.calculateAge(birthDate), 12);
		System.out.println("Test");
	}
	
}
