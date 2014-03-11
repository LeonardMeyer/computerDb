package com.excilys.computerdb.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.excilys.computerdb.business.domain.ComputerDto;
import com.excilys.computerdb.business.services.CompanyService;
import com.excilys.computerdb.business.services.ComputerService;
import com.excilys.computerdb.view.ComputerValidator;

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
		ModelAndView mav = new ModelAndView("addComputer", "computer", new ComputerDto());
		mav.addObject("companies", companyService.findAll());
		return mav;
	}

	// Affiche les ordis depuis un index avec tant de résultat
	@RequestMapping(value = "/{fromBound}/{maxResult}", method = RequestMethod.GET)
	public ModelAndView findByRange(@PathVariable String fromBound,
			@PathVariable String maxResult){
		int fromBound2 = Integer.parseInt(fromBound);
		int maxResult2 = Integer.parseInt(maxResult);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboard");
		mav.addObject("computers",
				computerService.findByRange(fromBound2, maxResult2));
		mav.addObject("totalComputers", computerService.count());
		return mav;
	}

	// Affiche les ordis depuis un index avec tant de résultats
	@RequestMapping(value = "/Search", method = RequestMethod.GET)
	public ModelAndView findByName(@RequestParam(value = "name", required = false) String name) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboard");
		if (name != null) {
			mav.addObject("computers", computerService.findByName(name));
			mav.addObject("totalComputers", computerService.count());
		}
		return mav;
	}

	// Affiche la page d'édition avec un computer chargé depuis une id
	@RequestMapping(value = "/{id}/Display", method = RequestMethod.GET)
	public ModelAndView findById(@PathVariable int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addComputer");
		mav.addObject("computer", computerService.findById(id));
		mav.addObject("companies", companyService.findAll());
		mav.addObject("editionMode", true);
		return mav;
	}

	// Edition d'un computer et redirection
	@RequestMapping(value = "/Save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("computer") ComputerDto computer, BindingResult result, SessionStatus status) {
		new ComputerValidator().validate(computer, result);
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.setViewName("addComputer");
			mav.addObject("companies", companyService.findAll());
			if (computer.getComputerId() > 0) {
				mav.addObject("editionMode", true);	
			}
			return mav;
		}else {
			computerService.save(computer);
		}
		mav.setViewName("dashboard");
		mav.addObject("computers", computerService.findByRange(0, 20));
		mav.addObject("totalComputers", computerService.count());
		return mav;
	}

	// Supprime un computer depuis un id
	@RequestMapping(value = "/{computerId}/Delete", method = RequestMethod.POST)
	public String delete(@PathVariable int computerId) {
		computerService.delete(computerId);
		return "redirect:/Computer/0/20";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
	}


}
