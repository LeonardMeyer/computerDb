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
import com.excilys.computerdb.business.services.ServiceProvider;

/**
 * Servlet implementation class DisplayComputers
 */
@WebServlet("/Computers")
public class Computers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		if(firstBound == 0 && secondBound == 0){
			firstBound = 0;
			secondBound = 20;
		}
		
		List<Computer> computers = ComputerService.getInstance().findAllInRange(firstBound, secondBound);
		request.setAttribute("computers", computers);
		int totalComputers = ComputerService.getInstance().count();
		getServletContext().setAttribute("totalComputers", totalComputers);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/dashboard.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
