package com.openclassrooms.P5_SpringBoot_JG.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class Firestation {

	private Long id;

	private String address;
	private int station;

	public Firestation(String address, int station) {
		this.address = address;
		this.station = station;

	}
}
