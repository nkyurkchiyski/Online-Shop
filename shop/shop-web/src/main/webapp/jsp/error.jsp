<%@page import="com.example.shop.base.dto.UserViewDto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<c:import url="templates/head.jsp" />
</head>
<body>
	<header>
		<c:import url="templates/nav.jsp" />
	</header>
	<div class="container-fluid my-5">

		<div class="jumbotron">
			<h3>HTTP ERROR 404</h3>
			<h5>The page you are trying to access either does not exist or
				you do not have the needed permission!</h5>
			<hr class="my-3">
		</div>
	</div>
	<c:import url="templates/footer.jsp" />
</body>
<c:import url="templates/scripts.jsp" />
</html>