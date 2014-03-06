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
import com.excilys.computerdb.business.services.ServiceProvider;

/**
 * Servlet implementation class DisplayComputers
 */
@WebServlet("/Computers")
public class Computers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ComputerService computerService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Computers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int firstBound;
		int secondBound;
		
		if (request.getParameter("firstBound") == null || request.getParameter("secondBound") == null) {
			firstBound = 0;
			secondBound = 20;
		}else{
			firstBound = Integer.parseInt(request.getParameter("firstBound"));
			secondBound = Integer.parseInt(request.getParameter("secondBound"));
		}
		if(firstBound <= 0 || secondBound <= 0){
			firstBound = 0;
			secondBound = 20;
		}
		
		List<Computer> computers = computerService.findAllInRange(firstBound, secondBound);
		request.setAttribute("computers", computers);
		int totalComputers = computerService.count();
		getServletContext().setAttribute("totalComputers", totalComputers);
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
