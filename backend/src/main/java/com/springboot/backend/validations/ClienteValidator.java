package com.springboot.backend.validations;

import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import com.springboot.backend.models.dto.ClienteDto;

@Component
public class ClienteValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ClienteDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombres", "NotEmpty.clientedto.nombres");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombres", "NotEmpty.clientedto.apellidos");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombres", "NotEmpty.clientedto.dni");
	}

}
