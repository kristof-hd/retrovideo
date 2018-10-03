<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
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
		<c:if test='${empty film}'>
			<h1>Film niet gevonden</h1>
		</c:if>
		<c:if test='${not empty film}'>
			<h1>${film.titel}</h1>
			<img src='<c:url value="/images/${film.id}.jpg"/>' title='${film.titel}' alt='${film.titel}'>
			<dl>
				<dt>Prijs</dt>
				<dd>${film.prijs}</dd>
				<dt>Voorraad</dt>
				<dd>${film.voorraad}</dd>
				<dt>Gereserveerd</dt>
				<dd>${film.gereserveerd}</dd>
				<dt>Beschikbaar</dt>
 				<dd>${film.voorraad-film.gereserveerd}</dd> 
			</dl>
		</c:if>
	</body>
</html>