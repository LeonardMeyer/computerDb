package com.excilys.computerdb.view;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.domain.ComputerDto;

public class ComputerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		 return ComputerDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {
		ComputerDto comp = (ComputerDto) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "name.required", "Name is required");
		if (comp.getIntroduced() == null) {
			e.rejectValue("introduced", "introduced.required", "Introduced date is required");
		}else if(comp.getIntroduced().year().get() < 1970) {
			e.rejectValue("introduced", "introduced.too.old", "Dates before 1970 are not supported");
		}
		
		if (comp.getDiscontinued() == null) {
			e.rejectValue("discontinued", "discontinued.required", "Discontinued date is required");
		}else if (comp.getDiscontinued().year().get() < 1970) {
			e.rejectValue("discontinued", "discontinued.too.old", "Dates before 1970 are not supported");
		}


	}

}
