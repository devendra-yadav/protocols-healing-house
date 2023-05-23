package com.healinghouse.protocolshealinghouse.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Meridian {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer meridianId;
	@Column(unique = true)
	private String name;
	private String alternateName;
	
	@OneToMany(mappedBy = "meridian")
	private List<Point> points;
	
	public Meridian(String name) {
		this.name = name;
	}
	
	public Meridian(String name, String alternateName) {
		this.name = name;
		this.alternateName = alternateName;
	}
	
}
