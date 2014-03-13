package com.excilys.computerdb.view;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaginationTag extends TagSupport {
	
	private Logger logger = LoggerFactory.getLogger(PaginationTag.class);
	
	private int totalRecords; //Nombre d'enregistrements
	private int recordsPerPage; //Incrément du limit
	private int nbOfPages; //Nombre de pages total
	private int previousBound; //Ancienne valeur du firstBound pour calcul
	private String order; //Ordre à retenir
	private String currentSearch; //Recherche en cours
	private int currentBound; //Premier enregistrement courant
	
	public int doStartTag(){
		JspWriter out = pageContext.getOut();
		try {
			setPreviousBound(recordsPerPage);
			out.println("<ul class='paginatorList'>");
			nbOfPages = (int) Math.ceil((double)totalRecords/recordsPerPage);
			for (int i = 1; i < nbOfPages+1; i++) {
				if (i == 1) {
					out.println("<li><a href='/computerDb/Computer/Search?fromBound=0&nbElem="+recordsPerPage
							+"&orderBy="+order+"'>1</a></li>");
				}else {
					out.println("<li><a href='/computerDb/Computer/Search?fromBound="+previousBound+"&nbElem="+recordsPerPage
							+"&orderBy="+order+"'>"+i+"</a></li>");
					previousBound += recordsPerPage;
				}
			}
			out.println("</ul>");
		} catch (IOException e) {
			logger.error("Erreur d'I/O lors de la création de la pagination", e);
		}
		return SKIP_BODY;
	}
	
	public int doEndTag(){
		return EVAL_PAGE;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getNbOfPages() {
		return nbOfPages;
	}

	public void setNbOfPages(int nbOfPages) {
		this.nbOfPages = nbOfPages;
	}

	public int getPreviousBound() {
		return previousBound;
	}

	public void setPreviousBound(int previousBound) {
		this.previousBound = previousBound;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCurrentSearch() {
		return currentSearch;
	}

	public void setCurrentSearch(String currentSearch) {
		this.currentSearch = currentSearch;
	}

	public int getCurrentBound() {
		return currentBound;
	}

	public void setCurrentBound(int currentBound) {
		this.currentBound = currentBound;
	}
	
	
}
