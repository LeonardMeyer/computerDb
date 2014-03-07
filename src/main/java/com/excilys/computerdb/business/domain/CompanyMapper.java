package com.excilys.computerdb.business.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CompanyMapper implements RowMapper<Company> {

	public CompanyMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Company mapRow(ResultSet rs, int arg1) throws SQLException {
		return new Company(rs.getInt("id"), rs.getString("name"));
	}

}
