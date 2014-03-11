package com.excilys.computerdb.business.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.domain.ComputerDto;

@Repository
public class DtoMapper {
	
	private ComputerRepository computerRepository;
	private CompanyRepository companyRepository;
	
	@Autowired
	public DtoMapper(ComputerRepository computerRepository, CompanyRepository companyRepository) {
		this.setComputerRepository(computerRepository);
		this.setCompanyRepository(companyRepository);
	}
	
	public Computer computerFromDto(ComputerDto dto){
		Computer comp = new Computer();
		comp.setComputerId(dto.getComputerId());
		comp.setName(dto.getName());
		comp.setIntroduced(dto.getIntroduced());
		comp.setDiscontinued(dto.getDiscontinued());
		comp.setCompany(companyRepository.findById(dto.getCompanyId()));
		return comp;
	}
	
	public ComputerDto dtoFromComputer(Computer comp){
		ComputerDto dto = new ComputerDto();
		dto.setComputerId(comp.getComputerId());
		dto.setName(comp.getName());
		dto.setIntroduced(comp.getIntroduced());
		dto.setDiscontinued(comp.getDiscontinued());
		dto.setCompanyId(comp.getCompany().getCompanyId());
		dto.setCompanyName(comp.getCompany().getName());
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
