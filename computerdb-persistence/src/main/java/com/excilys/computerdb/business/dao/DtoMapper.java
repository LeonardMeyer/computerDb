package com.excilys.computerdb.business.dao;

import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.excilys.computerdb.business.domain.Company;
import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.domain.ComputerDto;

@Service
public class DtoMapper {
	
	private ComputerRepository computerRepository;
	private CompanyRepository companyRepository;
	
	public DtoMapper() {
	}
	
	@Autowired
	public DtoMapper(ComputerRepository computerRepository, CompanyRepository companyRepository) {
		this.setComputerRepository(computerRepository);
		this.setCompanyRepository(companyRepository);
	}
	
	public Computer computerFromDto(ComputerDto dto){
		Locale locale = LocaleContextHolder.getLocale();
		String format = "yyyy-MM-dd";
		if (locale.equals(Locale.FRENCH)) {
			format = "dd/MM/yyyy";
		}
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
		
		Computer comp = new Computer();
		comp.setComputerId(dto.getComputerId());
		comp.setName(dto.getName());
		comp.setIntroduced(LocalDate.parse(dto.getIntroduced(), dateTimeFormatter));
		comp.setDiscontinued(LocalDate.parse(dto.getDiscontinued(), dateTimeFormatter));
		if (dto.getCompanyId() == -1) {;
			comp.setCompany(null);
		}else {
			comp.setCompany(companyRepository.findOne(dto.getCompanyId()));
		}
		
		return comp;
	}
	
	public ComputerDto dtoFromComputer(Computer comp){
		Locale locale = LocaleContextHolder.getLocale();
		String format = "yyyy-MM-dd";
		if (locale.equals(Locale.FRENCH)) {
			format = "dd/MM/yyyy";
		}
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
		
		ComputerDto dto = new ComputerDto();
		dto.setComputerId(comp.getComputerId());
		dto.setName(comp.getName());
		LocalDate intro = comp.getIntroduced();
		if (intro == null) {
			dto.setIntroduced("");
		}else {
			dto.setIntroduced(intro.toString(dateTimeFormatter));
		}
		LocalDate discont = comp.getDiscontinued();
		if (discont == null) {
			dto.setDiscontinued("");
		}else {
			dto.setDiscontinued(discont.toString(dateTimeFormatter));
		}
		if (comp.getCompany() != null) {
			dto.setCompanyId(comp.getCompany().getCompanyId());
			dto.setCompanyName(comp.getCompany().getName());
		}
		
		return dto;
	}

	public ComputerRepository getComputerRepository() {
		return computerRepository;
	}

	public void setComputerRepository(ComputerRepository computerRepository) {
		this.computerRepository = computerRepository;
	}

	public CompanyRepository getCompanyRepository() {
		return companyRepository;
	}

	public void setCompanyRepository(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}


}
