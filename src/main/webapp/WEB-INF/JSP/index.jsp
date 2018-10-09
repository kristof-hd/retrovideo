<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%> 
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
	<head>
 		<vdab:head title='Retrovideo'/>
<%-- 		<c:import url='/WEB-INF/JSP/head.jsp'>
			<c:param name='title' value='Retrovideo'/>
		</c:import>
 --%>	</head>
	<body>
		<vdab:menu/>
		<h1>Retrovideo</h1>

		<nav>
			<ul>
				<c:forEach var='genre' items='${genres}'>
					<spring:url value='/genres/{id}' var='url'>
						<spring:param name='id' value='${genre.id}'/>
					</spring:url>
					<li><a href='${url}'>${genre.naam}</a></li>
				</c:forEach>
			</ul>
		</nav>
	</body>
</html>

