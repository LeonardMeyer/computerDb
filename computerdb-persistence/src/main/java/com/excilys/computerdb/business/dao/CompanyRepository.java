package com.excilys.computerdb.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.excilys.computerdb.business.domain.Company;


public interface CompanyRepository extends JpaRepository<Company, Integer>{


}
