package com.openclassrooms.P5_SpringBoot_JG;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import com.openclassrooms.P5_SpringBoot_JG.Util.SafetyNetUtil;


public class SafetyNetUtilTest {

	@Test
	public void TestcalculateAge() {
		LocalDate currentDate = LocalDate.now();
		LocalDate dateMinus12Years = currentDate.minus(12, ChronoUnit.YEARS);
		Instant instant = dateMinus12Years.atStartOfDay(ZoneId.systemDefault()).toInstant();
		assertThat(SafetyNetUtil.calculateAge(Date.from(instant))).isBetween(11.99, 12.01);
	}

}
