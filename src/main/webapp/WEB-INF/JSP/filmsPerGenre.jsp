<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%> 
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
 			<title>Retrovideo</title>
			<link rel='icon' href='<c:url value="/images/retrovideo.ico"/>' type='image/x-icon'>
			<meta name='viewport' content='width=device-width,initial-scale=1'>
			<link rel='stylesheet' href='<c:url value="/css/retrovideo2.css"/>'>
<%-- 		<c:import url='/WEB-INF/JSP/head.jsp'> --%>
<%-- 			<c:param name='title' value='retrovideo'/> --%>
<%-- 		</c:import> --%>
	</head>
	<body>
		<c:import url='/WEB-INF/JSP/menu.jsp'/>
		<h1>Retrovideo</h1>
		<%-- id van het geselecteerde genre: ${geselecteerdeId} --%>
		<nav>
			<ul>
				<c:forEach var='genre' items='${genres}'>
					<spring:url value='/genres/{id}' var='url'>
						<spring:param name='id' value='${genre.id}'/>
					</spring:url>
					<c:choose>
						<c:when test='${genre.id==geselecteerdeId}'>
								<li><a href='${url}' class='geselecteerdGenre'>${genre.naam}</a></li>
						</c:when>
						<c:otherwise>
								<li><a href='${url}'>${genre.naam}</a> </li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</nav>
		<c:if test='${not empty films}'>
			<ul>
				<c:forEach var='film' items='${films}'>
					<spring:url value='/films/{id}' var='url'>
						<spring:param name='id' value='${film.id}'/>
					</spring:url>
					<c:choose> 

						<c:when test='${film.gereserveerd<film.voorraad}'> 
							<a href='${url}'>
								<img src='<c:url value="/images/${film.id}.jpg"/>' title='${film.titel} : reservatie mogelijk'>					
							</a>
						</c:when>
						<c:otherwise>
							<a href='${url}'>
								<img src='<c:url value="/images/${film.id}.jpg"/>' title='${film.titel} : reservatie niet mogelijk'>
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
			</ul>
		</c:if>

	</body>
</html>

