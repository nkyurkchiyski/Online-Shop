<%@page import="com.example.shop.base.dto.MessageDto"%>
<%@page import="com.example.shop.base.dto.UserViewDto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div aria-live="polite" aria-atomic="true">
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
		    MessageDto message = (MessageDto)request.getAttribute("message");
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
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown1"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Categories </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
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
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Products </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown2">
						<a class="dropdown-item" href="/online-shop/product/all">All
							Products</a> <a class="dropdown-item"
							href="/online-shop/product/search/form">Search Products</a>
						<%
						    if (user.isAdmin())
						        {
						%>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="/online-shop/product/create">Create
							Product</a>
						<%
						    }
						%>
					</div></li>
				<li class="nav-item dropdown active"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown3"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Orders </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown3">
						<a class="dropdown-item" href="/online-shop/order/my">My
							Orders</a>
						<%
						    if (user.isAdmin())
						        {
						%>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="/online-shop/order/manage">Manage
							Orders</a>
						<%
						    }
						%>
					</div></li>
			</ul>
			<ul class="navbar-nav my-2 my-lg-0">
				<li class="nav-item active"><a class="nav-link"
					href="/online-shop/cart/details">Cart</a></li>
				<li class="nav-item dropdown active"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown4"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Account </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown4">
						<a class="dropdown-item" href="/online-shop/user/profile">My
							Profile</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="/online-shop/auth/logout">Logout</a>
					</div></li>
			</ul>
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

	<%
	    if (message != null)
	    {
	        final boolean isSuccessful = message.isSuccessful();
	        final String alertColor = isSuccessful ? "alert-success" : "alert-danger";
	        final String alertHeading = isSuccessful ? "Success!" : "Error!";
	%>
	<div class="row my-2 mx-0 justify-content-center">
		<div class='alert <%=alertColor%> col-md-8 m-0' role="alert">
			<span class="font-weight-bold"><%=alertHeading%></span> <%=message.getDescription()%>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</div>
	<%
	    }
	%>
	<div id="toasts" aria-live="polite" aria-atomic="true"
		style="position: absolute; top: 60px; right: 0; z-index: 2;">
		<div id="toast_template" class="toast d-none" role="alert"
			aria-live="assertive" aria-atomic="true" data-autohide="true"
			data-delay="1500">
			<div class="toast-header">
				<strong class="mr-auto">Online-Shop</strong>
				<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="toast-body">
				<span id="toast_success">Success!</span> <span id="toast_text">Hello
					World! Keep up the great work!</span>
			</div>
		</div>
	</div>
</div>