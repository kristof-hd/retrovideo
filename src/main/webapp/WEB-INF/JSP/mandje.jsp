<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
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

		<c:if test='${not empty filmsInMandje}'>

		<h2>Mandje</h2>

			<c:url value='mandje' var='url'/>
			<form:form action='${url}' method='post'>

			<table border="1">
				<thead>
					<tr>
						<th>Titel</th>
						<th>Prijs</th>
						<th><input type='submit' value='Verwijderen'></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items='${filmsInMandje}' var='film'>
					<tr>
						<td>${film.titel}</td> 
						<td>&euro; ${film.prijs}</td>
						<td>
								<input type='checkbox' name='verwijderid' value='${film.id}'>						
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td>Totaal:</td>
						<td>&euro; ${totalePrijs}</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			</form:form>
		</c:if>
	</body>
</html>