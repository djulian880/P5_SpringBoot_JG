package com.openclassrooms.P5_SpringBoot_JG.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class Firestation {

	private @Id Long id;

	private String address;
	private int station;

	public Firestation(String address, int station) {
		this.address = address;
		this.station = station;

	}
}
