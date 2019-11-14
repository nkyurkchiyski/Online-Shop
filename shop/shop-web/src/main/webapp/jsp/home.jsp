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
	<%
	    UserViewDto user = (UserViewDto)request.getSession().getAttribute("user");
	%>
	<div class="container-fluid m-5">

		<div class="jumbotron">
			<%
			    if (user == null)
			    {
			%>
			<div id="notLoggedId">
				<p class="h1 display-3">Welcome to Online-Shop&trade;!</p>

				<p class="h3">The simplest, easiest to do your online shopping.</p>
				<p>
					<a href="/online-shop/auth/login">Login</a> if you have an account
					or <a href="/online-shop/auth/register">Register</a> now and start
					shopping.
				</p>
			</div>
			<%
			    }
			    else
			    {
			%>
			<div id="loggedId">
				<p class="h1 display-3">
					Welcome back
					<%=String.format("%s %s", user.getFirstName(), user.getLastName())%>!
				</p>

				<p class="h3">Continue your shopping now!</p>
			</div>
			<%
			    }
			%>
			<hr class="my-3">
		</div>
	</div>
	<c:import url="templates/footer.jsp" />
</body>
<c:import url="templates/scripts.jsp" />
</html>