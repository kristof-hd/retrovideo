<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%> 
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
<%-- 		<c:import url='/WEB-INF/JSP/head.jsp'> --%>
<%-- 			<c:param name='title' value='retrovideo'/> --%>
<%-- 		</c:import> --%>
			<title>Retrovideo</title>
			<link rel='icon' href='<c:url value="/images/retrovideo.ico"/>' type='image/x-icon'>
			<meta name='viewport' content='width=device-width,initial-scale=1'>
			<link rel='stylesheet' href='<c:url value="/css/retrovideo2.css"/>'>
	</head>
	<body>
		<c:import url='/WEB-INF/JSP/menu.jsp'/>
		<h1>Retrovideo</h1>

		<nav>
			<ul>
				<c:forEach var='genre' items='${genres}'>
					<spring:url value='/genres/{id}' var='url'>
						<spring:param name='id' value='${genre.id}'/>
					</spring:url>
					<li><a href='${url}'>${genre.naam}</a></li>
				</c:forEach>
			</ul>
		</nav>
	</body>
</html>

