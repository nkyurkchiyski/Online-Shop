<%@page import="com.example.shop.base.dto.UserViewDto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="/online-shop/home">Online-shop</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<%
	    UserViewDto user = (UserViewDto)request.getSession().getAttribute("user");
	%>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<%
		    if (user != null)
		    {
		%>
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="/online-shop/home">Home <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item dropdown active"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Categories </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="/online-shop/category/all">All
						Categories</a>
					<%
					    if (user.isAdmin())
					        {
					%>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/online-shop/category/create">Create
						Category</a>
					<%
					    }
					%>
				</div></li>
			<li class="nav-item dropdown active"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Products </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="/online-shop/product/all">All
						Products</a> <a class="dropdown-item"
						href="/online-shop/product/all?recentlyAdded">Recently added</a> <a
						class="dropdown-item" href="/online-shop/product/all?bestSellers">Best
						sellers</a>
					<%
					    if (user.isAdmin())
					        {
					%>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/online-shop/products/create">Create
						Product</a>
					<%
					    }
					%>
				</div></li>
			<li class="nav-item dropdown active"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Orders </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="/online-shop/order/my">My orders</a>
					<%
					    if (user.isAdmin())
					        {
					%>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/online-shop/order/all">Manage
						orders</a>
					<%
					    }
					%>
				</div></li>
			<li class="nav-item active"><a class="nav-link"
				href="/online-shop/auth/logout">Logout</a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-warning my-2 my-sm-0" type="submit">Search</button>
		</form>
		<%
		    }
		    else
		    {
		%>
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="/online-shop/home">Home <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item active"><a class="nav-link"
				href="/online-shop/auth/login">Login</a></li>
			<li class="nav-item active"><a class="nav-link"
				href="/online-shop/auth/register">Register</a></li>
		</ul>
		<%
		    }
		%>

	</div>
</nav>