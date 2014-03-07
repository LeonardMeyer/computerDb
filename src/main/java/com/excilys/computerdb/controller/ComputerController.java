package com.excilys.computerdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdb.business.domain.Computer;
import com.excilys.computerdb.business.services.CompanyService;
import com.excilys.computerdb.business.services.ComputerService;

@Controller
@RequestMapping("/Computer")
public class ComputerController {

	private final ComputerService computerService;
	private final CompanyService companyService;

	@Autowired
	public ComputerController(ComputerService computerService, CompanyService companyService) {
		this.computerService = computerService;
		this.companyService = companyService;
	}

	// Affiche la page d'ajout d'un computer
	@RequestMapping(value = "/New", method = RequestMethod.GET)
	public ModelAndView displayAddComputer() {
		ModelAndView mav = new ModelAndView("addComputer", "computer", new Computer());
		mav.addObject("companies", companyService.findAll());
		return mav;
	}

	// Affiche les ordis depuis un index avec tant de résultat
	@RequestMapping(value = "/{fromBound}/{maxResult}", method = RequestMethod.GET)
	public ModelAndView findByRange(@PathVariable String fromBound,
			@PathVariable String maxResult) {
		int fromBound2 = Integer.parseInt(fromBound);
		int maxResult2 = Integer.parseInt(maxResult);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboard");
		mav.addObject("computers",
				computerService.findByRange(fromBound2, maxResult2));
		mav.addObject("totalComputers", computerService.count());
		return mav;
	}

	// Affiche les ordis depuis un index avec tant de résultat
	@RequestMapping(value = "/Search/{name}", method = RequestMethod.GET)
	public ModelAndView findByName(@PathVariable String name) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboard");
		mav.addObject("computers", computerService.findByName(name));
		mav.addObject("totalComputers", computerService.count());
		return mav;
	}

	// Affiche la page d'édition avec un computer chargé depuis une id
	@RequestMapping(value = "/{id}/Display", method = RequestMethod.GET)
	public ModelAndView findById(@PathVariable int id) {
		Computer toRetrieve = computerService.findById(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addComputer");
		mav.addObject("computer", toRetrieve);
		return mav;
	}

	// Edition d'un computer et redirection
	@RequestMapping(value = "/{id}/Edit", method = RequestMethod.POST)
	public String save(@ModelAttribute("computer") Computer computer) {
		computerService.save(computer);
		return "redirect:/Computer/0/20";
	}

	// Supprime un computer depuis un id
	@RequestMapping(value = "/{id}/Delete", method = RequestMethod.DELETE)
	public String delete(@PathVariable int id) {
		computerService.delete(id);
		return "redirect:/Computer/0/20";
	}


}
