<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
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
				<dd>&euro; ${film.prijs}</dd>
				<dt>Voorraad</dt>
				<dd>${film.voorraad}</dd>
				<dt>Gereserveerd</dt>
				<dd>${film.gereserveerd}</dd>
				<dt>Beschikbaar</dt>
 				<dd>${film.voorraad-film.gereserveerd}</dd> 
			</dl>
		</c:if>
		<c:url value='/films/{id}' var='url'/>
		<form:form action='${url}' modelAttribute='mandjeForm' method='post' id='mandjeform'>
			<form:input path='filmId'/>
			<input type='submit' value='In mandje' id='toevoegknop'>
		</form:form>
		<c:if test='${not empty filmsInMandje}'>
		<h2>Mandje</h2>
		<ul> <c:forEach items='${filmsInMandje}' var='film'> <li>${film.naam}</li></c:forEach></ul>
		</c:if>

	</body>
</html>