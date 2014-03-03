<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/PaginationTag.tld" prefix="paging" %>
<jsp:include page="include/header.jsp" />
<section id="main">
	<h1 id="homeTitle">${applicationScope.totalComputers}</h1>
	<div id="actions">
		<form action="/computer-database/SearchComputer" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="/computer-database/AddComputer">Add Computer</a>
	</div>
	<c:if test="${success == false}">
		<div class="alert-message error">Ajout échoué</div>
	</c:if>
	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<th>Computer Name</th>
				<th>Introduced Date</th>
				<!-- Table header for Discontinued Date -->
				<th>Discontinued Date</th>
				<!-- Table header for Company -->
				<th>Company</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${computers}" var="computer" varStatus="status"> 
			<tr>
			    <td><a href="/computer-database/AddComputer?computerId=<c:out value='${computer.computerId}' />">${computer.name}</a></td>  
			    <td>${computer.introduced}</td> 
			    <td>${computer.discontinued}</td> 
			    <td>${computer.company.name}</td> 
			 </tr>
			</c:forEach> 
		</tbody>
	</table>
	<paging:display totalRecords="${totalComputers}" recordsPerPage="20"/>
</section>

<jsp:include page="include/footer.jsp" />
