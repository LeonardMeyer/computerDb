package com.excilys.computerdb.controller;

import java.util.List;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.excilys.computerdb.business.dao.SearchOrder;
import com.excilys.computerdb.business.domain.ComputerDto;
import com.excilys.computerdb.business.services.CompanyService;
import com.excilys.computerdb.business.services.ComputerService;
import com.excilys.computerdb.view.ComputerValidator;

@Controller
@RequestMapping("/Computer")
public class ComputerController {

	private Logger logger = LoggerFactory.getLogger(ComputerController.class);
	private final ComputerService computerService;
	private final CompanyService companyService;

	@Autowired
	public ComputerController(ComputerService computerService,
			CompanyService companyService) {
		this.computerService = computerService;
		this.companyService = companyService;
	}

	// Affiche la page d'ajout d'un computer
	@RequestMapping(value = "/New", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView displayAddComputer() {
		ModelAndView mav = new ModelAndView("addComputer", "computer",
				new ComputerDto());
		mav.addObject("companies", companyService.findAll());
		mav.addObject("page", "display");
		return mav;
	}

	// Affiche les ordis 
	@RequestMapping(value = "/Search", method = RequestMethod.GET)
	public ModelAndView search(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "nbElem", required = false) String nbElem,
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "fromBound", required = false) String fromBound) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashboard");
		int recordsPerPage;
		if (nbElem != null) {
			recordsPerPage = Integer.parseInt(nbElem);
		} else {
			recordsPerPage = 20;
		}
		if (recordsPerPage > 0) {
			mav.addObject("recordsPerPage", recordsPerPage);
		}
		int fromBound2 = 0;
		if (fromBound != null) {
			fromBound2 = Integer.parseInt(fromBound);
		}

		if (orderBy == null) {
			orderBy = "NAME_ASC";
		}
		
		List<ComputerDto> computersToReturn = computerService.search(name, recordsPerPage, orderBy, fromBound2);
		
		long searchResultsSize = 0;
		if (name == null) {
			searchResultsSize = computerService.count();
		}else {
			searchResultsSize = computerService.countFiltered(name);
			mav.addObject("currentSearch", name);
		}
		mav.addObject("computers", computersToReturn);
		mav.addObject("orderStrategy", orderBy);
		mav.addObject("totalComputers",  searchResultsSize);
		mav.addObject("currentBound", fromBound2);
		mav.addObject("page", "dashboard");
		return mav;
	}

	// Affiche la page d'édition avec un computer chargé depuis une id
	@RequestMapping(value = "/{id}/Display", method = RequestMethod.GET)
	public ModelAndView findById(@PathVariable int id) {
		ModelAndView mav = new ModelAndView();
		ComputerDto computer;
		try {
			computer = computerService.findById(id);
			mav.setViewName("addComputer");
			mav.addObject("computer", computer);
			mav.addObject("companies", companyService.findAll());
			mav.addObject("editionMode", true);
			mav.addObject("page", "display");
		} catch (DataRetrievalFailureException e) {
			mav.setViewName("redirect:/Computer/Search");
		}
		
		return mav;
	}

	// Edition d'un computer et redirection
	@RequestMapping(value = "/Save", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView save(@Valid @ModelAttribute("computer") ComputerDto computer,
			BindingResult result, SessionStatus status) {

		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.setViewName("addComputer");
			mav.addObject("companies", companyService.findAll());
			mav.addObject("page", "error");
			mav.addObject("computerId", computer.getComputerId());
			if (computer.getComputerId() > 0) {
				mav.addObject("editionMode", true);
			}
			return mav;
		} else {
			try {
				computerService.save(computer);
			} catch (DataRetrievalFailureException e) { //Pour se prévenir des findById(0) sur company
				logger.error("Erreur lors de la sauvegarde du model", e);
				mav.setViewName("redirect:/Computer/Search");
			}
			
		}
		mav.setViewName("redirect:/Computer/Search");
		return mav;
	}

	// Supprime un computer depuis un id
	@RequestMapping(value = "/{computerId}/Delete", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String delete(@PathVariable int computerId) {
		computerService.delete(computerId);
		return "redirect:/Computer/Search";
	}


}
