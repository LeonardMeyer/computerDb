package com.excilys.computerdb.view;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.computerdb.business.domain.ComputerDto;

public class ComputerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		 return ComputerDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "name.required", "Name is required");
	}

}
