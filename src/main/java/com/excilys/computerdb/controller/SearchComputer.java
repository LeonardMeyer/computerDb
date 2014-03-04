package com.excilys.computerdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.services.ComputerService;

/**
 * Servlet implementation class SearchComputer
 */
@WebServlet("/SearchComputer")
public class SearchComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nameToSearch = request.getParameter("search");
		List<Computer> computers = ComputerService.getInstance().filterByName(nameToSearch.trim());
		
		request.setAttribute("computers", computers);
		getServletContext().setAttribute("computerCount", computers.size());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/dashboard.jsp");
		rd.forward(request, response);
	}

}
