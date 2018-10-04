<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
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

		<c:if test='${not empty filmsInMandje}'>
		<h2>Mandje</h2>
		<ul> <c:forEach items='${filmsInMandje}' var='film'> <li>${film.titel}</li></c:forEach></ul>
		</c:if>

	</body>
</html>