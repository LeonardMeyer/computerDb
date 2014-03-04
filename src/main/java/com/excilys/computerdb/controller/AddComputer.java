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

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.excilys.computerdb.business.domain.Company;
import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.services.CompanyService;
import com.excilys.computerdb.business.services.ComputerService;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("computerId") != null) {
			int computerId = Integer.parseInt(request.getParameter("computerId"));
			if (computerId > 0) {
				Computer toGet = ComputerService.getInstance().find(computerId);
				request.setAttribute("computerToEdit", toGet);
			}
		}
		Map<Integer, String> companyNames = CompanyService.getInstance().findAllCompanyNames();
		request.setAttribute("companyNames", companyNames);
		//Renvoi vers la même page avec loading
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/addComputer.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected final void doPost(final HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
		String computerName = request.getParameter("name");
		String introducedDate = request.getParameter("introducedDate");
		String discontinuedDate = request.getParameter("discontinuedDate");
		int companyId = Integer.parseInt(request.getParameter("company"));
		int computerId = 0;
		if (!request.getParameter("computerId").isEmpty()) {
			computerId = Integer.parseInt(request.getParameter("computerId"));
		}

		Computer toCreate = new Computer(computerId, computerName, new LocalDate(introducedDate), new LocalDate(discontinuedDate), 
				new Company(companyId, ""));
		
		boolean success = ComputerService.getInstance().create(toCreate);
		request.setAttribute("success", success);
		List<Computer> computers = ComputerService.getInstance().findAllInRange(0, 20);
		request.setAttribute("computers", computers);
		int totalComputers = ComputerService.getInstance().count();
		getServletContext().setAttribute("totalComputers", totalComputers);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/dashboard.jsp");
		rd.forward(request, response);
	}

}
