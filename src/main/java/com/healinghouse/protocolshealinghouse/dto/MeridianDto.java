package com.healinghouse.protocolshealinghouse.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class MeridianDto {
	@NotBlank(message = "this field cant be blank")
	private String name;
	private String alternateNames;
}