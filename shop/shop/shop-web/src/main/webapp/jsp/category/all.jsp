<%@page import="com.example.shop.base.dto.UserViewDto"%>
<%@page import="java.util.List"%>
<%@page import="com.example.shop.base.dto.CategoryDto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<CategoryDto> categories = (List<CategoryDto>)request.getAttribute("categories");
    UserViewDto user = (UserViewDto)request.getSession().getAttribute("user");

    Integer pages = (Integer)request.getAttribute("pages");
    Integer currPage = (Integer)request.getAttribute("currPage");
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
			<h2>All Categories</h2>
		</div>
		<div class="d-flex justify-content-center">
			<div class="row w-75">
				<%
				    if (categories.size() == 0)
				    {
				%>
				<div>
					<h3 class="font-italic text-muteds">There are currently no
						categories</h3>
				</div>
				<%
				    }
				    else
				    {
				        for (CategoryDto category : categories)
				        {
				%>
				<div class="col-md-3 my-2">
					<div class="card shadow text-center">
						<div class="card-header">Category</div>
						<div class="card-body">
							<h5 class="card-title"><%=category.getName()%></h5>
							<div class="btn-group" role="group" aria-label="Basic example">
								<a href="/online-shop/category/details?id=<%=category.getId()%>"
									class="btn btn-primary">Details</a>
								<%
								    if (user.isAdmin())
								            {
								%>
								<a href="/online-shop/category/edit?id=<%=category.getId()%>"
									class="btn btn-warning">Edit</a> <a
									href="/online-shop/category/delete?id=<%=category.getId()%>"
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
		<div class="mt-5 d-flex justify-content-center">
			<nav>
				<ul class="pagination">

					<li class="page-item"><a
						class="page-link <%=currPage == 0 ? "disabled" : ""%>"
						href="/online-shop/category/all?page=<%=currPage == 0 ? 0 : currPage - 1%>"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
					</a></li>
					<li class="p-2"><%=currPage + 1%> of <%=pages%></li>
					<li class="page-item"><a class="page-link"
						href="/online-shop/category/all?page=<%=currPage + 1 >= pages ? currPage : currPage + 1%>"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
							class="sr-only">Next</span>
					</a></li>
				</ul>
			</nav>
		</div>

		<c:import url="../templates/footer.jsp" />
	</div>
</body>
<c:import url="../templates/scripts.jsp" />
</html>