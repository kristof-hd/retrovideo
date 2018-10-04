<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%> 
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
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
		<c:import url='/WEB-INF/JSP/menuUitgebreid.jsp'/>
		<h1>Klanten</h1>

		<form:form action='${url}' modelAttribute='klantenZoekenForm' method='get'>
			<form:label path='familienaamBevat'>Familienaam bevat: <form:errors path='familienaamBevat'/></form:label>
			<form:input path='familienaamBevat' autofocus='autofocus'/>
			<input type='submit' value='Zoeken'> <form:errors cssClass='fout'/> 
		</form:form>
		<c:if test='${not empty klanten}'>
			<ul>
			<c:forEach items='${klanten}' var='klant'>
			<li>${klant.familienaam}</li>
			</c:forEach>
			</ul>
		</c:if>
	</body>
</html>

