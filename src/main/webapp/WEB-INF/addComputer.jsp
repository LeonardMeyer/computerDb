<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="i18l"%>
<jsp:include page="include/header.jsp" />

<section id="main">

	<h1><i18l:message code="addCompHeader" /></h1>
	<spring:form action="/computerDb/Computer/Save" method="POST" name="crudForm" id="crudForm" modelAttribute="computer">
		<fieldset>
			
			<spring:hidden path="computerId"
				value="${computer.computerId}" />
			<div class="clearfix">
				
				<label for="name"><i18l:message code="nameHeader"/></label>
				<div class="input">
					<spring:input type="text" path="name" />
					<span class="help-inline"><i18l:message code="required"/></span>
					<spring:errors path="name" cssClass="isa_error help-inline"/>
				</div>
			</div>
			<div class="clearfix">
				<label for="introduced"><i18l:message code="introHeader"/>:</label>
				<div class="input">
					<spring:input type="date" path="introduced"
						pattern="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])" />
					<span class="help-inline">YYYY-MM-DD</span>
					<spring:errors path="introduced" cssClass="isa_error help-inline"/>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued"><i18l:message code="discHeader"/>:</label>
				<div class="input">
					<spring:input type="date" path="discontinued"
						pattern="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])" />
					<span class="help-inline">YYYY-MM-DD</span>
					<spring:errors path="discontinued" cssClass="isa_error help-inline"/>
				</div>
			</div>
			<div class="clearfix">
				<label for="company"><i18l:message code="companyHeader"/>:</label>
				<div class="input">
					<spring:select path="companyId" multiple="true">
						<spring:option value="-1" ><i18l:message code="comboBoxDefault" /></spring:option>
						<spring:options items="${companies}" itemLabel="name" itemValue="companyId" />
					</spring:select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<c:choose>
				<c:when test="${editionMode != true}">
					<input type="submit" value="<i18l:message code="addButton" />" class="btn primary" />
				</c:when>
				<c:otherwise>
					<input type="submit" value="<i18l:message code="updateButton" />" class="btn primary" />
				</c:otherwise>
			</c:choose>
			or <a href="/computerDb/Computer/0/20" class="btn"><i18l:message code="cancelButton"/></a>
			<c:if test="${editionMode == true}">or
				<input type="button" class="btn danger" value="<i18l:message code="deleteButton" />" onclick="deleteEnabled()" />	
			</c:if>
		</div>
	</spring:form>
</section>

<jsp:include page="include/footer.jsp" />