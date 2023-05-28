package com.healinghouse.protocolshealinghouse.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class MeridianDto {
	@NotBlank(message = "meridian name cant be blank")
	private String name;
	private String alternateNames;
}