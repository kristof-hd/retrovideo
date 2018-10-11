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
		<h1>Klanten</h1>
		<form:form action='${url}' modelAttribute='klantenZoekenForm' method='get'>
			<form:label path='familienaamBevat'>Familienaam bevat: <form:errors path='familienaamBevat'/></form:label>
			<form:input path='familienaamBevat' autofocus='autofocus' required='required'/>
			<input type='submit' value='Zoeken'> <form:errors cssClass='fout'/> 
		</form:form>
		<br>
		<c:if test='${not empty klanten}'>
			<table class="klanten">
				<thead>
					<tr>
						<th>Naam</th>
						<th>Straat - Huisnummer</th>
						<th>Postcode</th>
						<th>Gemeente</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items='${klanten}' var='klant'>
						<spring:url value='/bevestigen/{id}' var='url'>
							<spring:param name='id' value='${klant.id}'/>
						</spring:url>
						<tr>
							<td>
								<a href='${url}'>${klant.voornaam} ${klant.familienaam}</a>
							</td>
							<td>${klant.straatNummer}</td>
							<td>${klant.postcode}</td>
							<td>${klant.gemeente}</td> 
						</tr>
					</c:forEach>				
				</tbody>

			</table>
		</c:if>
	</body>
</html>

