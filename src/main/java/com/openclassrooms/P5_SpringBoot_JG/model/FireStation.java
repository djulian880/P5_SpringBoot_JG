package com.openclassrooms.P5_SpringBoot_JG.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class FireStation {

	private Long id;

	public String address;
	public int station;

	public FireStation(String address, int station) {
		this.address = address;
		this.station = station;

	}
	public FireStation() {
		this.address = null;
		this.station = -1;

	}
}
