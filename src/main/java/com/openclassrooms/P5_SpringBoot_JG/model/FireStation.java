package com.openclassrooms.P5_SpringBoot_JG.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Entity
@Table(name = "firestations")
public class FireStation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
