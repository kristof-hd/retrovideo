<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
	<head>
			<title>Retrovideo</title>
			<link rel='icon' href='images/retrovideo.ico' type='image/x-icon'>
			<meta name='viewport' content='width=device-width,initial-scale=1'>
			<link rel='stylesheet' href='css/retrovideo2.css'>
	</head>
	<body>
		<c:import url='/WEB-INF/JSP/menuUitgebreid.jsp'/>

		<c:if test='${not empty filmsInMandje}'>

		<h2>Mandje</h2>
<!-- 		<ul>  -->
<%-- 			<c:forEach items='${filmsInMandje}' var='film'>  --%>
<%-- 				<li>${film.titel}</li> --%>
<%-- 				<c:url value='mandje' var='url'/> --%>
<%-- 				<form:form action='${url}' method='post'> --%>
<%-- 					<input type='checkbox' name='verwijderid' value='${film.id}'> --%>
<!-- 					<input type='submit' value='Verwijderen'> -->
<%-- 				</form:form> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->

			<table border="1">
				<thead>
					<tr>
						<th>Titel</th>
						<th>Prijs</th>
						<th> </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items='${filmsInMandje}' var='film'>
					<tr>
						<td>${film.titel}</td> 
						<td>${film.prijs}</td>
						<td>
						
							<form:form action='${url}' method='post'>
		 						<input type='checkbox' name='verwijderid' value='${film.id}'>
		 						<input type='submit' value='Verwijderen'>
							</form:form>
						
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>

		</c:if>
		<p>Totaal: &euro; ${totalePrijs}</p>
	</body>
</html>