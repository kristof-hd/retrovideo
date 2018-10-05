<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%> 
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
	<head>
		
			<title>Retrovideo</title>
			<link rel='icon' href='<c:url value="/images/retrovideo.ico"/>' type='image/x-icon'>
			<meta name='viewport' content='width=device-width,initial-scale=1'>
			<link rel='stylesheet' href='<c:url value="/css/retrovideo2.css"/>'>
		

	</head>
	<body>
		<c:import url='/WEB-INF/JSP/menuUitgebreid.jsp'/>
		<h1>Bevestigen</h1>  
		<p>${aantalArtikelsInMandje} film(s) voor ${klant.voornaam} ${klant.familienaam}</p>
		<c:url value='/bevestigen/{id}' var='url'/>
		<form:form action='${url}' modelAttribute='reservatieForm' method='post'>
			<form:input path='klantId'/>
			<input type='submit' value='Bevestigen' id='toevoegknop'>
		</form:form>

	</body>
</html>

