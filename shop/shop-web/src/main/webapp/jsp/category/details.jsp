<%@page import="com.example.shop.base.dto.ProductDto"%>
<%@page import="com.example.shop.base.dto.UserViewDto"%>
<%@page import="java.util.List"%>
<%@page import="com.example.shop.base.dto.CategoryProductsDto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    CategoryProductsDto category = (CategoryProductsDto)request.getAttribute("category");
    UserViewDto user = (UserViewDto)request.getSession().getAttribute("user");
%>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<header>
		<c:import url="../templates/nav.jsp" />
	</header>
	<div class="container-fluid my-5">
		<div class="text-center pb-4">
			<h2>
				Category:
				<%=category.getName()%></h2>
		</div>
		<div class="d-flex justify-content-center">
			<div class="row w-75">
				<%
				    if (category.getProducts().size() == 0)
				    {
				%>
				<div>
					<h3 class="font-italic text-muteds">There are currently no
						products in this category</h3>
				</div>
				<%
				    }
				    else
				    {
				        for (ProductDto product : category.getProducts())
				        {
				%>
				<div class="col-md-3 my-2">
					<div class="card shadow text-center">
						<img src="<%=product.getImageUrl()%>" class="card-img-top">
						<div class="card-body">
							<h5 class="card-title"><%=product.getName()%></h5>
							<p class="text-right card-text">
								Price:
								<%=product.getPrice()%></p>
							<div class="btn-group" role="group">
								<a href="/online-shop/product/details?id=<%=product.getId()%>"
									class="btn btn-primary">Details</a>
								<%
								    if (user.isAdmin())
								            {
								%>
								<a href="/online-shop/product/edit?id=<%=product.getId()%>"
									class="btn btn-warning">Edit</a> <a
									href="/online-shop/product/delete?id=<%=product.getId()%>"
									class="btn btn-danger">Delete</a>
								<%
								    }
								%>
							</div>

						</div>
					</div>
				</div>

				<%
				    }
				    }
				%>
			</div>
		</div>
		<c:import url="../templates/footer.jsp" />
	</div>
</body>
<c:import url="../templates/scripts.jsp" />
</html>