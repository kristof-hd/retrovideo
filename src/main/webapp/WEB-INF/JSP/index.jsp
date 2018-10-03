<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%> 
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
		
			<title>Retrovideo</title>
			<link rel='icon' href='images/retrovideo.ico' type='image/x-icon'>
			<meta name='viewport' content='width=device-width,initial-scale=1'>
			<link rel='stylesheet' href='css/retrovideo.css'>
		

	</head>
	<body>
		<c:import url='/WEB-INF/JSP/menu.jsp'/>
		<h1>Retrovideo</h1>

		<%-- h2>${boodschap}</h2> --%>

		<ul> 
			<c:forEach var='genre' items='${genres}'>
				<spring:url value='/genres/{id}' var='url'>
					<spring:param name='id' value='${genre.id}'/>
				</spring:url>
				<li><a href='${url}'>${genre.naam}</a></li>
				<%-- <li>${genre.naam}</li> --%>
			</c:forEach>
					
		</ul>

		<c:if test='${not empty films}'>
			<p>hallo</p> 
			<ul>
				<c:forEach var='film' items='${films}'>
					<li>${film.titel}</li>
				</c:forEach>
				
			</ul>
		</c:if>

	</body>
</html>

