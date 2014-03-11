package com.excilys.computerdb.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.business.dao.ComputerRepository;
import com.excilys.computerdb.business.dao.DtoMapper;
import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.domain.ComputerDto;

@Service
public class ComputerServiceImpl implements ComputerService{

	private ComputerRepository computerRepo;
	private DtoMapper dtoMapper;
	
	@Override
	@Transactional(readOnly=true)
	public ComputerDto findById(int id) throws DataRetrievalFailureException {
		return dtoMapper.dtoFromComputer(computerRepo.findById(id));
	}

	@Override
	@Transactional(readOnly=true)
	public List<ComputerDto> findByRange(int fromBound, int maxResult)
			throws DataRetrievalFailureException {
		List<Computer> computers = computerRepo.findByRange(fromBound, maxResult);
		List<ComputerDto> dtos = new ArrayList<>();
		for (Computer computer : computers) {
			dtos.add(dtoMapper.dtoFromComputer(computer));
		}
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public List<ComputerDto> findByName(String name)
			throws DataRetrievalFailureException {
		List<Computer> computers = computerRepo.findByName(name);
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
	@Transactional
	public int count() throws DataAccessException {
		return computerRepo.count();
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
