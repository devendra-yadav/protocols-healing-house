package com.healinghouse.protocolshealinghouse.dto;

import org.springframework.web.multipart.MultipartFile;

import com.healinghouse.protocolshealinghouse.validator.ValidImageFile;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class PointDto {
	
	@NotBlank(message = "point id cant be blank")
	private String pointId;
	
	@Min(value = 0, message = "Please select a value for meridian.")
	private Integer meridianId;
	
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
	
	@ValidImageFile
	private MultipartFile image;
	
}
