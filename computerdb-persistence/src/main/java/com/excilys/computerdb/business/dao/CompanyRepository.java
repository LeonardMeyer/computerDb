package com.excilys.computerdb.business.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import com.excilys.computerdb.business.domain.Company;

@Profile(value="jpa")
public interface CompanyRepository extends JpaRepository<Company, Integer>{


}
