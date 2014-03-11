<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="i18l"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">

body {
   background-color:#282828;
  background: url(https://raw.github.com/subtlepatterns/SubtlePatterns/gh-pages/dark_leather.png);
 /* background:url('http://uppix.net/InVoQc.jpg'); */
}

p {
  font-family: 'Press Start 2P', cursive;
  margin-left:auto;
  margin-top:200px;
  margin-right:auto;
  font-size:80px;
  text-align:center;
  margin-bottom:0px;
  color:#4ec7ff;
  text-shadow: 0px 0px 1000px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
}

.flickering {
  animation: flickering 0.7s;
  -webkit-animation: flickering 0.88s;
  color:white;
  animation-iteration-count:infinite;
  
}

.bigger
{
  font-size:135px;
}

@keyframes flickering
{
0% {text-shadow: 0px 0px 15px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:white;}
10% {text-shadow: 0px 0px 0px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:black;}
20% {text-shadow: 0px 0px 8px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:#4ec7ff;}
30% {text-shadow: 0px 0px 10px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:white;}
40% {text-shadow: 0px 0px 0px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:black;}
50% {text-shadow: 0px 0px 10px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:white;}
52% {text-shadow: 0px 0px 20px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:white;}
55% {text-shadow: 0px 0px 5px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:#4ec7ff;}
60% {text-shadow: 0px 0px 5px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:white;}
75% {text-shadow: 0px 0px 0px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:black;}
85% {text-shadow: 0px 0px 5px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:#4ec7ff;}
100% {text-shadow: 0px 0px 10px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
    color:white;
  }
}

@-webkit-keyframes flickering
{
0% {text-shadow: 0px 0px 15px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:white;}
10% {text-shadow: 0px 0px 0px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:black;}
20% {text-shadow: 0px 0px 8px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:#4ec7ff;}
30% {text-shadow: 0px 0px 10px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:white;}
40% {text-shadow: 0px 0px 0px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:black;}
50% {text-shadow: 0px 0px 10px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:white;}
52% {text-shadow: 0px 0px 20px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:white;}
55% {text-shadow: 0px 0px 5px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:#4ec7ff;}
60% {text-shadow: 0px 0px 5px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:white;}
75% {text-shadow: 0px 0px 0px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:black;}
85% {text-shadow: 0px 0px 5px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
  color:#4ec7ff;}
100% {text-shadow: 0px 0px 10px #38d1ff;
        filter: dropshadow(color=#38d1ff, offx=0, offy=0);
    color:white;
  }
}

#info {
  margin-top:20px; 
  color:#4ec7ff;
  font-size:14px;
}
</style> 
<title><i18l:message code="error"/></title>
</head>
<body>
<p>lol c cassé<br/><span class="bigger flickering">Error</span></p>
<p id="info"><c:out value="${exception}"></c:out></p>
</body>
</html>


