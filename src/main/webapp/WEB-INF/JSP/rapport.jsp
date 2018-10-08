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
		<c:import url='/WEB-INF/JSP/menu.jsp'/>
		<h1>Rapport</h1>
		<c:choose>
			<c:when test='${empty titelsMislukteReservaties}'> 
				<p>De reservatie is OK.</p>
			</c:when>
			<c:otherwise>
				<p>Één of meerdere reservaties zijn mislukt, met name:</p>

					<table border="1">
						<thead>
							<tr><th>Film</th></tr>
						</thead>
						<tbody>
							<c:forEach items='${titelsMislukteReservaties}' var='titel'>
								<tr>
									<td>${titel}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</c:otherwise>
		</c:choose>
	</body>
</html>

