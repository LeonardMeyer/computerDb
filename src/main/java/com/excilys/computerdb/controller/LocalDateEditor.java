package com.excilys.computerdb.controller;

import java.beans.PropertyEditorSupport;

import org.joda.time.LocalDate;

public class LocalDateEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null) {
			if (!text.isEmpty()) {
				if (text.matches("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])")) {
					setValue(new LocalDate(text));
				}
			}
		}
	}

}
