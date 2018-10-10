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
		<vdab:menu/>
		<h1>Rapport</h1>
		<c:choose>
			<c:when test='${empty fouten}'> 
				<p>De reservatie is OK.</p>
			</c:when>
			<c:otherwise>
				<p>Één of meerdere reservaties zijn mislukt, met name:</p>
					<table border="1">
						<thead>
							<tr><th>Film</th></tr>
						</thead>
						<tbody>
							<c:forEach items='${fouten}' var='filmId'>
								<tr>
									<td>${filmId}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</c:otherwise>
		</c:choose>
	</body>
</html>