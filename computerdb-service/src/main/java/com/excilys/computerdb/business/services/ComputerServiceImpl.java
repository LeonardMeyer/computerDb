package com.excilys.computerdb.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.business.dao.ComputerRepository;
import com.excilys.computerdb.business.dao.DtoMapper;
import com.excilys.computerdb.business.dao.SearchOrder;
import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.domain.ComputerDto;

@Service
public class ComputerServiceImpl implements ComputerService{

	private ComputerRepository computerRepo;
	private DtoMapper dtoMapper;
	
	@Override
	@Transactional(readOnly=true)
	public ComputerDto findById(int id) throws DataRetrievalFailureException {
		return dtoMapper.dtoFromComputer(computerRepo.findOne(id));
	}

	@Override
	@Transactional(readOnly=true)
	public List<ComputerDto> search(String name, int nbElem, String orderBy, int fromBound)
			throws DataRetrievalFailureException {
		PageRequest page = null;
		int pageNumber = fromBound/nbElem;
		switch (orderBy) {
		case "NAME_ASC":
			page = new PageRequest(pageNumber, nbElem, Direction.ASC, "name");
			break;
		case "NAME_DESC":
			page = new PageRequest(pageNumber, nbElem, Direction.DESC, "name");
			break;
		case "INTRO_ASC":
			page = new PageRequest(pageNumber, nbElem, Direction.ASC, "introduced");
			break;
		case "INTRO_DESC":
			page = new PageRequest(pageNumber, nbElem, Direction.DESC, "introduced");
			break;
		case "DISC_ASC":
			page = new PageRequest(pageNumber, nbElem, Direction.ASC, "discontinued");;
			break;
		case "DISC_DESC":
			page = new PageRequest(pageNumber, nbElem, Direction.DESC, "discontinued");
			break;
		case "COMPANY_ASC":
			page = new PageRequest(pageNumber, nbElem, Direction.ASC, "company.name");
			break;
		case "COMPANY_DESC":
			page = new PageRequest(pageNumber, nbElem, Direction.DESC, "company.name");
			break;
		default:
			page = new PageRequest(pageNumber, nbElem, Direction.ASC, "name");
			break;
		}
	
		Page<Computer> computers = null;
		if (name != null) {
			 computers = computerRepo.findByNameContaining(name, page);
		}else {
			computers = computerRepo.findAll(page);
		}
		
		List<ComputerDto> dtos = new ArrayList<>();
		for (Computer computer : computers) {
			dtos.add(dtoMapper.dtoFromComputer(computer));
		}
		return dtos;
	}

	@Override
	@Transactional
	public void save(ComputerDto dto) throws DataAccessException {
		computerRepo.save(dtoMapper.computerFromDto(dto));
	}

	@Override
	@Transactional
	public void delete(int id) throws DataAccessException {
		computerRepo.delete(id);
	}

	@Override
	@Transactional(readOnly=true)
	public long count() throws DataAccessException {
		return computerRepo.count();
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public long countFiltered(String name) throws DataAccessException{
		return computerRepo.countByNameContaining(name);
	}

	public ComputerRepository getComputerRepo() {
		return computerRepo;
	}

	@Autowired
	public void setComputerRepo(ComputerRepository computerRepo) {
		this.computerRepo = computerRepo;
	}

	public DtoMapper getDtoMapper() {
		return dtoMapper;
	}

	@Autowired
	public void setDtoMapper(DtoMapper dtoMapper) {
		this.dtoMapper = dtoMapper;
	}



}
