<%@page import="com.example.shop.base.dto.CategoryDto"%>
<%@page import="java.util.List"%>
<%@ page import="com.example.shop.base.dto.ProductViewDto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<CategoryDto> categories = (List<CategoryDto>)request.getAttribute("categories");
    ProductViewDto product = (ProductViewDto)request.getAttribute("product");
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
		<div class="row justify-content-center">
			<div class="col-md-5">
				<div class="card shadow">
					<div class="card-header form-title bg-warning">
						<h4 class="m-0">Edit Product</h4>
					</div>
					<div class="card-body">
						<form class="form-horizontal" action="/online-shop/product/edit"
							method="post">
							<input type="hidden" id="id" name="id"
								value="<%=product.getId()%>" required>
							<div class="form-group row">
								<label for="name" class="col-md-4 col-form-label text-md-right">Name</label>
								<div class="col-md-6">
									<input type="text" id="name" name="name" placeholder="Name"
										class="form-control" value="<%=product.getName()%>" required>
								</div>
							</div>
							<div class="form-group row">
								<label for="imageUrl"
									class="col-md-4 col-form-label text-md-right">Image Url</label>
								<div class="col-md-6">
									<input type="text" id="imageUrl" name="imageUrl"
										value="<%=product.getImageUrl()%>" placeholder="Image Url"
										class="form-control" required>
								</div>
							</div>
							<div class="form-group row">
								<label for="price" class="col-md-4 col-form-label text-md-right">Price</label>
								<div class="col-md-6">
									<input type="number" step="0.01" id="price" name="price"
										placeholder="Price" class="form-control"
										value="<%=product.getPrice()%>" required>
								</div>
							</div>
							<div class="form-group row">
								<label for="quantity"
									class="col-md-4 col-form-label text-md-right">Quantity</label>
								<div class="col-md-6">
									<input type="number" step="1" id="quantity" name="quantity"
										placeholder="Quantity" class="form-control"
										value="<%=product.getQuantity()%>" required>
								</div>
							</div>
							<div class="form-group row">
								<label for="categories"
									class="col-md-4 col-form-label text-md-right">Categories</label>
								<div class="col-md-6">
									<select multiple class="form-control" id="categories"
										name="categories">
										<%
										    for (CategoryDto category : categories)
										    {
										%>
										<option value="<%=category.getId()%>"><%=category.getName()%>
										</option>

										<%
										    }
										%>

									</select>
								</div>
							</div>
							<div class="form-group row">
								<label for="description"
									class="col-md-4 col-form-label text-md-right">Description</label>
								<div class="col-md-6">
									<textarea id="description" name="description"
										placeholder="Description" class="form-control" required
										rows="3"><%=product.getDescription()%></textarea>
								</div>
							</div>
							<div class="row d-flex justify-content-around">
								<a class="btn btn-lg btn-light" href="/online-shop/home">
									Cancel </a>
								<button type="submit" class="btn btn-lg btn-secondary">
									Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<c:import url="../templates/footer.jsp" />
	</div>
</body>
<c:import url="../templates/scripts.jsp" />
</html>