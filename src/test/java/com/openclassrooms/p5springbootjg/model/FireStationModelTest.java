package com.openclassrooms.p5springbootjg.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.openclassrooms.p5springbootjg.model.FireStation;

public class FireStationModelTest {
	@Test
	public void testGettersAndSetters() {
		FireStation fireStation = new FireStation();
		fireStation.setStation(1);
		fireStation.setAddress("address");

		assertThat(fireStation.getStation(), is(1));
		assertThat(fireStation.getAddress(), is("address"));
	}

	@Test
	public void testEqualsAndHashCode() {
		FireStation fireStation1 = new FireStation();
		fireStation1.setStation(1);
		fireStation1.setAddress("address");
		FireStation fireStation2 = new FireStation();
		fireStation2.setStation(1);
		fireStation2.setAddress("address");

		assertThat(fireStation1, is(fireStation2));
		assertThat(fireStation1.hashCode(), is(fireStation2.hashCode()));
	}

	@Test
	public void testToString() {
		FireStation fireStation = new FireStation();
		fireStation.setStation(1);
		fireStation.setAddress("address");

		assertThat(fireStation.toString(), containsString("address=address"));
		assertThat(fireStation.toString(), containsString("station=1"));

	}
}
