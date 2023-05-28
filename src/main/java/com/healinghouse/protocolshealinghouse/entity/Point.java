package com.healinghouse.protocolshealinghouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Point {

	@Id
	private String pointId;
	
	@ManyToOne
	@JoinColumn(name="meridian_id")
	private Meridian meridian;
	
	private String name;
	private String englishName;
	private String location;
	private String attributes;
	private String indications;
	private String functions;
	private String needlingMethods;
	private String contraIndications;
	private String localApplication;
	private String miscellaneousUse;
	private String needlingCombinations;
	private String notes;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[] image;
	
}
