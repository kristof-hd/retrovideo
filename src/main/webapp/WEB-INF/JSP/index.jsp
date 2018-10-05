<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%> 
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
		
			<title>Retrovideo</title>
			<link rel='icon' href='images/retrovideo.ico' type='image/x-icon'>
			<meta name='viewport' content='width=device-width,initial-scale=1'>
			<link rel='stylesheet' href='css/retrovideo2.css'>
		

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
					<%-- <li>${genre.naam}</li> --%>
				</c:forEach>
						
			</ul>
		</nav>
		<c:if test='${not empty films}'>
			<ul>
				<c:forEach var='film' items='${films}'>
					<%-- <li>${film.titel}</li> --%>
					<%-- <img src="/images/1.jpg" title='${film.titel}'> --%>
					<%-- <img src='<c:url value="/images/1.jpg"/>' title='${film.titel}'> --%>
					<c:choose> 
						<c:when test='${film.gereserveerd<film.voorraad}'> 
							<spring:url value='/films/{id}' var='url'>
								<spring:param name='id' value='${film.id}'/>
							</spring:url>
							<a href='${url}'>
								<img src='<c:url value="/images/${film.id}.jpg"/>' title='${film.titel} reservatie mogelijk'>					
							</a>
						</c:when>
						<c:otherwise>
							<img src='<c:url value="/images/${film.id}.jpg"/>' title='${film.titel} reservatie niet mogelijk'>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
			</ul>
		</c:if>

	</body>
</html>

