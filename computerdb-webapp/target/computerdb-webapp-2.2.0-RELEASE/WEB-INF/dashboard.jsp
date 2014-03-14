<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/PaginationTag.tld" prefix="paging"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="include/header.jsp" />
<section id="main">
	<h1 id="homeTitle">${totalComputers}</h1>
	<div id="actions">
		<form:form action="${pageContext.request.contextPath}/Computer/Search" method="GET">
			<input type="search" id="name" name="name" value=""
				placeholder="<spring:message code="filterBox"/>" />
			<input name="fromBound" type="hidden" value="0" />
			<input name="nbElem" type="hidden" value="${recordsPerPage}" />
			<input name="orderBy" type="hidden" value="${orderStrategy}" />
			<input type="submit" class="btn primary" value="<spring:message code="filterButton"/>" />

		</form:form>
		<a class="btn success" id="add" href="<c:url value="/Computer/New"/>"><spring:message code="addCompHeader" /></a>
	</div>
	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<th><c:choose>
						<c:when test="${orderStrategy == 'NAME_ASC'}">
							<spring:url value="/Computer/Search" var="url">
								<spring:param name="name" value="${currentSearch}" />
								<spring:param name="fromBound" value="${currentBound}" />
								<spring:param name="nbElem" value="${recordsPerPage}" />
								<spring:param name="orderBy" value="NAME_DESC" />
							</spring:url>
							<a href="<c:out value='${url}'/>">
								<spring:message code="nameHeader" />
							</a>
						</c:when>
						<c:otherwise>
							<spring:url value="/Computer/Search" var="url">
								<spring:param name="name" value="${currentSearch}" />
								<spring:param name="fromBound" value="${currentBound}" />
								<spring:param name="nbElem" value="${recordsPerPage}" />
								<spring:param name="orderBy" value="NAME_ASC" />
							</spring:url>
							<a href="<c:out value='${url}'/>">
								<spring:message code="nameHeader" />
							</a>
						</c:otherwise>
					</c:choose></th>
				<th><c:choose>
						<c:when test="${orderStrategy == 'INTRO_ASC'}">
							<spring:url value="/Computer/Search" var="url">
								<spring:param name="name" value="${currentSearch}" />
								<spring:param name="fromBound" value="${currentBound}" />
								<spring:param name="nbElem" value="${recordsPerPage}" />
								<spring:param name="orderBy" value="INTRO_DESC" />
							</spring:url>
							<a href="<c:out value='${url}'/>">
								<spring:message code="introHeader" />
							</a>
						</c:when>
						<c:otherwise>
							<spring:url value="/Computer/Search" var="url">
								<spring:param name="name" value="${currentSearch}" />
								<spring:param name="fromBound" value="${currentBound}" />
								<spring:param name="nbElem" value="${recordsPerPage}" />
								<spring:param name="orderBy" value="INTRO_ASC" />
							</spring:url>
							<a href="<c:out value='${url}'/>">
								<spring:message code="introHeader" />
							</a>
						</c:otherwise>
					</c:choose></th>
				<!-- Table header for Discontinued Date -->
				<th><c:choose>
						<c:when test="${orderStrategy == 'DISC_ASC'}">
							<spring:url value="/Computer/Search" var="url">
								<spring:param name="name" value="${currentSearch}" />
								<spring:param name="fromBound" value="${currentBound}" />
								<spring:param name="nbElem" value="${recordsPerPage}" />
								<spring:param name="orderBy" value="DISC_DESC" />
							</spring:url>
							<a href="<c:out value='${url}'/>">
								<spring:message code="discHeader" />
							</a>
						</c:when>
						<c:otherwise>
							<spring:url value="/Computer/Search" var="url">
								<spring:param name="name" value="${currentSearch}" />
								<spring:param name="fromBound" value="${currentBound}" />
								<spring:param name="nbElem" value="${recordsPerPage}" />
								<spring:param name="orderBy" value="DISC_ASC" />
							</spring:url>
							<a href="<c:out value='${url}'/>">
								<spring:message code="discHeader" />
							</a>
						</c:otherwise>
					</c:choose></th>
				<!-- Table header for Company -->
				<th><c:choose>
						<c:when test="${orderStrategy == 'COMPANY_ASC'}">
							<spring:url value="/Computer/Search" var="url">
								<spring:param name="name" value="${currentSearch}" />
								<spring:param name="fromBound" value="${currentBound}" />
								<spring:param name="nbElem" value="${recordsPerPage}" />
								<spring:param name="orderBy" value="COMPANY_DESC" />
							</spring:url>
							<a href="<c:out value='${url}'/>">
								<spring:message code="companyHeader" />
							</a>
						</c:when>
						<c:otherwise>
							<spring:url value="/Computer/Search" var="url">
								<spring:param name="name" value="${currentSearch}" />
								<spring:param name="fromBound" value="${currentBound}" />
								<spring:param name="nbElem" value="${recordsPerPage}" />
								<spring:param name="orderBy" value="COMPANY_ASC" />
							</spring:url>
							<a href="<c:out value='${url}'/>">
								<spring:message code="companyHeader" />
							</a>
						</c:otherwise>
					</c:choose></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${computers}" var="computer" varStatus="status">
				<tr>
					<td><a
						href="${pageContext.request.contextPath}/Computer/<c:out value='${computer.computerId}' />/Display">${computer.name}</a></td>
					<td>${computer.introduced}</td>
					<td>${computer.discontinued}</td>
					<td>${computer.companyName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<paging:display totalRecords="${totalComputers}"
		currentBound="${currentBound}" order="${orderStrategy}"
		currentSearch="${currentSearch}" recordsPerPage="${recordsPerPage}" />
</section>
<jsp:include page="include/footer.jsp" />
