package com.excilys.computerdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.services.ComputerService;

/**
 * Servlet implementation class DeleteComputer
 */
@WebServlet("/DeleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ComputerService computerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("computerId") != null) {
			int computerId = Integer.parseInt(request.getParameter("computerId"));
			if (computerId > 0) {
				boolean success = computerService.delete(computerId);
			}
		}

		List<Computer> computers = computerService.findAllInRange(0, 20);
		request.setAttribute("computers", computers);
		getServletContext().setAttribute("totalComputers", computerService.count());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/dashboard.jsp");
		rd.forward(request, response);
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	@Autowired
	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}


}
