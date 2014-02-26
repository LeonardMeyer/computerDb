package com.excilys.computerdb.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.services.CompanyService;
import com.excilys.computerdb.business.services.ComputerService;

/**
 * Servlet implementation class DisplayAddComputer
 */
@WebServlet("/DisplayAddComputer")
public class DisplayAddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAddComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<Integer, String> companyNames = CompanyService.getInstance().findAllCompanyNames();
		request.setAttribute("companyNames", companyNames);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/addComputer.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
