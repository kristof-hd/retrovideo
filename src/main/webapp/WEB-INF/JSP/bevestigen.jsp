<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%> 
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
		<vdab:head title='Retrovideo'/>
	</head>
	<body>
		<vdab:menuUitgebreid/>
		<h1>Bevestigen</h1>  
		<p>${aantalArtikelsInMandje} film(s) voor ${klant.voornaam} ${klant.familienaam}</p>
		<spring:url value='/bevestigen/{id}' var='url'>
			<spring:param name='id' value='${klant.id}'/>
		</spring:url>
<%-- 		<form:form action='${url}' modelAttribute='reservatieForm' method='post'> --%>
		<form:form action='${url}' method='post'>
			<input type='submit' value='Bevestigen' id='toevoegknop'>
		</form:form>
<!-- 	<script>document.getElementById('invoerveld').style.display="none";</script> -->
	</body>
</html>


