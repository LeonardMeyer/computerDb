<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="appTitle"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/css/main.css" />" rel="stylesheet" media="screen">
<script type="text/javascript" src="<c:url value="/js/utility.js" />"></script>
</head>
<body>
	<header class="topbar">
		<h1 class="fill">
			<a href="${pageContext.request.contextPath}/Computer/Search"><spring:message code="appHeader"/></a>
			<span style="float: right">
			<!-- Détermination des URL de switch de lang en fonction de la page en cours -->
				<c:choose> 
					<c:when test="${page == 'dashboard'}">
						<spring:url value="" var="urlEng">
							<c:if test="${currentSearch != null}"><spring:param name="name" value="${currentSearch}" /></c:if>
							<c:if test="${currentBound != null}"><spring:param name="fromBound" value="${currentBound}" /></c:if>
							<c:if test="${recordsPerPage != null}"><spring:param name="nbElem" value="${recordsPerPage}" /></c:if>
							<c:if test="${orderStrategy != null}"><spring:param name="orderBy" value="${orderStrategy}" /></c:if>
							<spring:param name="lang" value="en" />
						</spring:url>
						<spring:url value="" var="urlFr">
							<c:if test="${currentSearch != null}"><spring:param name="name" value="${currentSearch}" /></c:if>
							<c:if test="${currentBound != null}"><spring:param name="fromBound" value="${currentBound}" /></c:if>
							<c:if test="${recordsPerPage != null}"><spring:param name="nbElem" value="${recordsPerPage}" /></c:if>
							<c:if test="${orderStrategy != null}"><spring:param name="orderBy" value="${orderStrategy}" /></c:if>
							<spring:param name="lang" value="fr" />
						</spring:url>
					</c:when>
					<c:when test="${page == 'error'}">
						<c:choose>
							<c:when test="${computerId > 0}">
								<spring:url value="/Computer/${computerId}/Display" var="urlEng">
									<spring:param name="lang" value="en" />
								</spring:url>
								<spring:url value="/Computer/${computerId}/Display" var="urlFr">
									<spring:param name="lang" value="fr" />
								</spring:url>
							</c:when>
							<c:otherwise>
								<spring:url value="/Computer/New" var="urlEng">
									<spring:param name="lang" value="en" />
								</spring:url>
								<spring:url value="/Computer/New" var="urlFr">
									<spring:param name="lang" value="fr" />
								</spring:url>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<spring:url value="" var="urlEng">
							<spring:param name="lang" value="en" />
						</spring:url>
						<spring:url value="" var="urlFr">
							<spring:param name="lang" value="fr" />
						</spring:url>
					</c:otherwise>
				</c:choose>
				
		    	<a href="${urlEng}">
		    		<spring:message code="english"/>
		    	</a>-
		    	<a href="${urlFr}">
		    		<spring:message code="french"/>
		    	</a>
			</span>
		</h1>
	</header>