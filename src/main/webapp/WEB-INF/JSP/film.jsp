<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
		<vdab:head title='Retrovideo'/> 
	</head>
	<body>
		<vdab:menu/>
		<c:if test='${empty film}'>
			<h1>Film niet gevonden</h1>
		</c:if>
		<c:if test='${not empty film}'>
			<h1>${film.titel}</h1>
			<img src='<c:url value="/images/${film.id}.jpg"/>' title='${film.titel}' alt='${film.titel}'>
			<dl>
				<dt>Prijs</dt>
				<dd>&euro; <spring:eval expression='film.prijs'/></dd>
				<dt>Voorraad</dt>
				<dd>${film.voorraad}</dd>
				<dt>Gereserveerd</dt>
				<dd>${film.gereserveerd}</dd>
				<dt>Beschikbaar</dt>
				<dd>${film.beschikbaar}</dd>
			</dl>
		</c:if>
		<c:url value='/films/{id}' var='url'/>
		<c:if test='${film.beschikbaar>0 && not empty mandjeForm}'>
			<form:form action='${url}' modelAttribute='mandjeForm' method='post' id='mandjeform'>
				<form:input path='filmId' id='invoerveld'/>
				<input type='submit' value='In mandje' id='toevoegknop'>
			</form:form>
		</c:if>
	<script>
		document.getElementById('invoerveld').style.display="none";</script>

	</body>
</html>