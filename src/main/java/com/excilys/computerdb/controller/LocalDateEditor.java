package com.excilys.computerdb.controller;

import java.beans.PropertyEditorSupport;

import org.joda.time.LocalDate;

public class LocalDateEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(new LocalDate(text));
	}

}
