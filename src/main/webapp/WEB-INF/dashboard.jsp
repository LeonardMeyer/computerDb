<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/PaginationTag.tld" prefix="paging"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="i18l"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<jsp:include page="include/header.jsp" />
<section id="main">
	<h1 id="homeTitle">${totalComputers}</h1>
	<div id="actions">
		<spring:form action="/computerDb/Computer/Search" method="GET">
			<input type="search" id="name" name="name" value=""
				placeholder="<i18l:message code="filterBox"/>" />
			<input name="fromBound" type="hidden" value="0" />
			<input name="nbElem" type="hidden" value="${nbElements}" />
			<input name="orderBy" type="hidden" value="${orderStrategy}" />
			<input type="submit" value="<i18l:message code="filterButton"/>"
				class="btn primary" />
		</spring:form>
		<a class="btn success" id="add" href="<c:url value="/Computer/New"/>"><i18l:message
				code="addCompHeader" /></a>
	</div>
	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<th><i18l:message code="nameHeader" /></th>
				<th>
					<a href="/computerDb/Computer/Search?fromBound=0&nbElem=${nbElements}&orderBy=INTRO_ASC">
						<i18l:message code="introHeader" /></a>	
				</th>
				<!-- Table header for Discontinued Date -->
				<th><i18l:message code="discHeader" /></th>
				<!-- Table header for Company -->
				<th><i18l:message code="companyHeader" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${computers}" var="computer" varStatus="status">
				<tr>
					<td><a
						href="/computerDb/Computer/<c:out value='${computer.computerId}' />/Display">${computer.name}</a></td>
					<td>${computer.introduced}</td>
					<td>${computer.discontinued}</td>
					<td>${computer.companyName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<paging:display totalRecords="${totalComputers}"
		order="${orderStrategy}" currentSearch="${currentSearch}"
		recordsPerPage="${recordsPerPage}" />
</section>
<jsp:include page="include/footer.jsp" />
