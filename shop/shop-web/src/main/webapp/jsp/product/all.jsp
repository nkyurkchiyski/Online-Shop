<%@page import="com.example.shop.base.dto.ProductDto"%>
<%@page import="com.example.shop.base.dto.UserViewDto"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    final List<ProductDto> products = (List<ProductDto>)request.getAttribute("products");
    final String filter = (String)request.getAttribute("filter");
    final UserViewDto user = (UserViewDto)request.getSession().getAttribute("user");

    final String searchFilter = (String)request.getAttribute("searchFilter");
    final Integer pages = (Integer)request.getAttribute("pages");
    final Integer currPage = (Integer)request.getAttribute("currPage");
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
			<h2><%=filter%>
				Products
			</h2>
		</div>
		<div class="d-flex justify-content-center">
			<div class="row w-75">
				<%
				    if (products.size() == 0)
				    {
				%>
				<div>
					<h3 class="font-italic text-muteds">There are currently no
						products</h3>
				</div>
				<%
				    }
				    else
				    {
				        for (ProductDto product : products)
				        {
				%>
				<div class="col-md-3 my-2">
					<div class="card shadow text-left">
						<img src="<%=product.getImageUrl()%>" class="card-img-top mt-4"
							height="150px" style="object-fit: scale-down">
						<div class="card-body">
							<h6 class="card-title"><%=product.getName()%>
							</h6>
							<h5 class="card-text">
								$<%=product.getPrice()%>
							</h5>
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
		<div class="mt-5 d-flex justify-content-center">
			<nav>
				<ul class="pagination">

					<%
					    final String location = searchFilter == null ? "all?" : "search?" + searchFilter;
					    final int previousPage = currPage == 0 ? 0 : currPage - 1;
					    final int nextPage = currPage + 1 >= pages ? currPage : currPage + 1;
					%>
					<li class="page-item"><a
						class="page-link btn <%=currPage == 0 ? "disabled" : ""%>"
						href="/online-shop/product/<%=location%>&page=<%=previousPage%>"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
					</a></li>
					<li class="p-2"><%=currPage + 1%> of <%=pages == 0 ? 1 : pages%></li>
					<li class="page-item"><a
						class="page-link btn <%=currPage + 1 >= pages ? "disabled" : ""%>"
						href="/online-shop/product/<%=location%>&page=<%=nextPage%>"
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