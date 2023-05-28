package com.healinghouse.protocolshealinghouse.validator;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageFileValidator implements ConstraintValidator<ValidImageFile, MultipartFile>{

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		
		if(value.isEmpty() || value.getContentType().startsWith("image/")) {
			return true;
		}
		return false;
	}

}
