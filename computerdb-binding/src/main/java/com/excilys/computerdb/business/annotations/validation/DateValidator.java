package com.excilys.computerdb.business.annotations.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.computerdb.business.annotations.ValidDate;

public class DateValidator implements ConstraintValidator<ValidDate, String>,
		MessageSourceAware {

	private MessageSource messageSource; //Récupère la regex depuis les fichiers properties
	Pattern pattern;
	Matcher matcher;

	@Override
	public void initialize(ValidDate validDate) {
		Locale local = LocaleContextHolder.getLocale();
		pattern = Pattern.compile(messageSource.getMessage("date.format", null,
				local));
	}

	@Override
	public boolean isValid(String date,
			ConstraintValidatorContext constraintContext) {
		CharSequence dateStr = date;
		matcher = pattern.matcher(dateStr);

		// Si la date n'est pas là
		if (date == null || date.isEmpty()) {
			constraintContext
					.buildConstraintViolationWithTemplate("{date.required}")
					.addConstraintViolation();
			return false;
		}
		// Si le format n'est pas respecté
				if (!matcher.matches()) {
					constraintContext.buildConstraintViolationWithTemplate("{date.error}").addConstraintViolation();
					return false;
				}
		// Si la date est inférieure à 1970
		Locale locale = LocaleContextHolder.getLocale();
		String format = "yyyy-MM-dd";
		if (locale.equals(Locale.FRENCH)) {
			format = "dd/MM/yyyy";
		}
		DateFormat df = new SimpleDateFormat(format, locale);

		Date parsedDate = null;
		try {
			parsedDate =  df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		Calendar calendar = Calendar.getInstance(locale);
		if (parsedDate != null) {
			calendar.setTime(parsedDate);
		}
		
		if (calendar.get(Calendar.YEAR) < 1970) {
			constraintContext
					.buildConstraintViolationWithTemplate("{date.too.old}")
					.addConstraintViolation();
			return false;
		}
		
		return true;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}


}
