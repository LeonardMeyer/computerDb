<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<jsp:include page="include/header.jsp" />
<section id="main">

	<h1>Add Computer</h1>

	<spring:form action="/computerDb/AddComputer" method="POST" commandName="computer">
		<fieldset>
			<spring:input type="hidden" path="computerId"
				value="${computerToEdit.computerId}" />
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<spring:input type="text" path="name" value="${computerToEdit.name}" required />
					<span class="help-inline">Required</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<spring:input type="date" path="introduced"
						value="${computerToEdit.introduced}" required
						pattern="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<spring:input type="date" path="discontinuedDate"
						value="${computerToEdit.discontinued}" required
						pattern="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<spring:select path="company">
						<spring:option value="-1"></spring:option>
						<spring:options items="${companies}" />
					</spring:select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<c:choose>
				<c:when test="${computerToEdit == null}">
					<input type="submit" value="Add" class="btn primary">
				</c:when>
				<c:otherwise>
					<input type="submit" value="Update" class="btn primary">
				</c:otherwise>
			</c:choose>
			or <a href="/computerDb/" class="btn">Cancel</a>
			<c:if test="${computerToEdit != null}">or
				<a href="/computerDb/${computerToEdit.computerId}/Delete" class="btn danger">Delete</a>
			</c:if>
		</div>
	</spring:form>
</section>

<jsp:include page="include/footer.jsp" />