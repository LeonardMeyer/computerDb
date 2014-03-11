<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="i18l"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title><i18l:message code="appTitle"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/css/main.css" />" rel="stylesheet" media="screen">
<script type="text/javascript" src="<c:url value="/js/utility.js" />"></script>
</head>
<body>
	<header class="topbar">
		<h1 class="fill">
			<a href="/computerDb/Computer/0/20"><i18l:message code="appHeader"/></a>
			<span style="float: right">
		    	<a href="?lang=en"><i18l:message code="english"/></a>-<a href="?lang=fr"><i18l:message code="french"/></a>
			</span>
		</h1>
	</header>