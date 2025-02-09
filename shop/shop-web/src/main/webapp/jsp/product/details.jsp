<%@page import="com.example.shop.base.dto.UserViewDto"%>
<%@ page import="com.example.shop.base.dto.ProductViewDto"%>
<%@ page import="com.example.shop.base.dto.CategoryDto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    ProductViewDto product = (ProductViewDto) request.getAttribute("product");
			UserViewDto user = (UserViewDto) request.getSession().getAttribute("user");
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
			<h2>Product Details</h2>
		</div>
		<div class="row">
			<div class="col-md-6 text-center">
				<img height="500px" style="object-fit: scale-down; max-width: 100%"
					src="<%=product.getImageUrl()%>">
			</div>

			<div class="col-md-6 text-left">
				<h3><%=product.getName()%>
				</h3>
				<hr>
				<h4>
					$<%=product.getPrice()%>
				</h4>
				<hr>
				<%
				    if (product.getQuantity() > 0 && product.isActive()) {
				%>
				<div class="row">
					<input id="productId" type="hidden" name="productId"
						value="<%=product.getId()%>">
					<div class="col-md-2">
						<input id="quantity" name="quantity" type="number" step="1"
							min="1" max="2147483646" class="form-control" value="1"
							placeholder="Quantity" required>
					</div>
					<div class="col-md-6">
						<a class="add-btn btn btn-primary mb-2 text-white">Add to Cart</a>
					</div>
				</div>
				<%
				    } else {
				%>
				<h4>Product is unavailable</h4>
				<%
				    }
				%>
				<hr>
				<h5>Description</h5>
				<p><%=product.getDescription()%>
				</p>
				<hr>
				<p class="font-weight-light">
					<span class="font-weight-bold">Categories: </span>
					<%
					    for (CategoryDto category : product.getCategories()) {
					%>
					<a href="/online-shop/category/details?id=<%=category.getId()%>"><%=category.getName()%>
					</a>
					<%
					    }
					%>
				</p>
				<%
				    if (user.isAdmin()) {
				%>
				<hr>
				<h5>Actions</h5>
				<div>
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
		<c:import url="../templates/footer.jsp" />
	</div>
</body>
<c:import url="../templates/scripts.jsp" />
<script>
	$(".add-btn").click(function() {
		const
		productId = $("#productId").val();
		const
		quantity = $("#quantity").val();
		const
		url = "/online-shop/cart/product";
		sendRequest("POST", url, function(resp) {
			createToast(resp).toast('show');
		}, null, JSON.stringify({productId,quantity})
		);
	});
</script>
</html>