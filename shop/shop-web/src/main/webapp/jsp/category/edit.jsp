<%@page import="com.example.shop.base.dto.CategoryDto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<header>
		<c:import url="../templates/nav.jsp" />
	</header>
	<%
	    CategoryDto category = (CategoryDto)request.getAttribute("category");
	%>
	<div class="container-fluid m-5">
		<div class="row justify-content-center">
			<div class="col-md-5">
				<div class="card shadow">
					<div class="card-header form-title bg-warning">
						<h4 class="m-0">Edit Category</h4>
					</div>
					<div class="card-body">
						<form class="form-horizontal" action="/online-shop/category/edit"
							method="post">
							<div class="form-group row">
								<input type="hidden" id="id" name="id"
									value="<%=category.getId()%>" required> <label
									for="name" class="col-md-4 col-form-label text-md-right">Name</label>
								<div class="col-md-6">
									<input type="text" id="name" name="name" placeholder="Name"
										class="form-control" value="<%=category.getName()%>" required>
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