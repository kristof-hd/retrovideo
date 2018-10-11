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
		<c:choose>
			<c:when test='${empty filmsInMandje}'>
				<vdab:menu/> 
				<p>Uw mandje is momenteel ledig.</p>
			</c:when>
			<c:otherwise>
				<vdab:menuUitgebreid/>
				<h2>Mandje</h2>
					<c:url value='mandje' var='url'/>
					<form:form action='${url}' method='post'>
						<table>
							<thead>
								<tr>
									<th>Film</th>
									<th>Prijs</th>
									<th><input type='submit' value='Verwijderen'></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items='${filmsInMandje}' var='film'>
								<tr>
									<td>${film.titel}</td> 
									<td>&euro; <spring:eval expression='film.prijs'/></td>
									<td>
										<input type='checkbox' name='verwijderid' value='${film.id}'>						
									</td>
								</tr>
								</c:forEach>
								<tr>
									<td>Totaal:</td>
									<td>&euro; <spring:eval expression='totalePrijs.waarde'/></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</form:form>
			</c:otherwise>
		</c:choose>
	</body>
</html>