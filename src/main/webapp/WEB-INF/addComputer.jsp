<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="include/header.jsp" />

<section id="main">

	<h1><spring:message code="addCompHeader" /></h1>
	<form:form action="/computerDb/Computer/Save" method="POST" name="crudForm" id="crudForm" modelAttribute="computer">
		<fieldset>
			
			<form:hidden path="computerId"
				value="${computer.computerId}" />
			<div class="clearfix">
				
				<label for="name"><spring:message code="nameHeader"/></label>
				<div class="input">
					<form:input type="text" path="name" />
					<span class="help-inline"><spring:message code="required"/></span>
					<form:errors path="name" cssClass="isa_error help-inline"/>
				</div>
			</div>
			<div class="clearfix">
				<label for="introduced"><spring:message code="introHeader"/>:</label>
				<div class="input">
					<form:input type="date" path="introduced"
						pattern="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])" />
					<span class="help-inline">YYYY-MM-DD</span>
					<form:errors path="introduced" cssClass="isa_error help-inline"/>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued"><spring:message code="discHeader"/>:</label>
				<div class="input">
					<form:input type="date" path="discontinued"
						pattern="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])" />
					<span class="help-inline">YYYY-MM-DD</span>
					<form:errors path="discontinued" cssClass="isa_error help-inline"/>
				</div>
			</div>
			<div class="clearfix">
				<label for="company"><spring:message code="companyHeader"/>:</label>
				<div class="input">
					<form:select path="companyId" multiple="true">
						<form:option value="-1" ><spring:message code="comboBoxDefault" /></form:option>
						<form:options items="${companies}" itemLabel="name" itemValue="companyId" />
					</form:select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<c:choose>
				<c:when test="${editionMode != true}">
					<input type="submit" value="<spring:message code="addButton" />" class="btn primary" />
				</c:when>
				<c:otherwise>
					<input type="submit" value="<spring:message code="updateButton" />" class="btn primary" />
				</c:otherwise>
			</c:choose>
			or <a href="/computerDb//Computer/Search" class="btn"><spring:message code="cancelButton"/></a>
			<c:if test="${editionMode == true}">or
				<input type="button" class="btn danger" value="<spring:message code="deleteButton" />" onclick="deleteEnabled()" />	
			</c:if>
		</div>
	</form:form>
</section>

<jsp:include page="include/footer.jsp" />