package com.excilys.computerdb.business.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.LocalDate;
import org.springframework.jdbc.core.RowMapper;

public class ComputerMapper implements RowMapper<Computer> {

	public ComputerMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Computer mapRow(ResultSet rs, int arg1) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		Timestamp introducedTs = rs.getTimestamp("introduced");
		LocalDate introduced = null;
		if (introducedTs != null) {
		introduced = new LocalDate(introducedTs);
		}
		Timestamp discontinuedTs = rs.getTimestamp("discontinued");
		LocalDate discontinued = null;
		if (discontinuedTs != null) {
		discontinued = new LocalDate(discontinuedTs);
		}
		int companyId = rs.getInt("company_id");
		String companyName = rs.getString("company.name");

		return new Computer(id, name, introduced, discontinued, new Company(companyId, companyName));   
	}

}
