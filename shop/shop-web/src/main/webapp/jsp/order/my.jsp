<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.example.shop.base.dto.OrderViewDto"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    final List<OrderViewDto> orderDtos = (List<OrderViewDto>) request.getAttribute("orders");
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			final Integer pages = (Integer) request.getAttribute("pages");
			final Integer currPage = (Integer) request.getAttribute("currPage");
%>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<header>
		<c:import url="../templates/nav.jsp" />
	</header>
	<div class="container-fluid m-5">
		<div class="text-center pb-4">
			<h2>My Orders</h2>
		</div>
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card shadow">
					<div class="card-body">
						<%
						    if (orderDtos == null || orderDtos.size() == 0) {
						%>
						<div>
							<h3 class="font-italic text-muteds">Currently you have no
								placed orders</h3>
						</div>
						<%
						    } else {
						%>
						<div>
							<table class="table">
								<thead>
									<tr class="row">
										<th scope="col" class="col-md-1">#</th>
										<th scope="col" class="col-md-2">Ordered On</th>
										<th scope="col" class="col-md-2">Approved On</th>
										<th scope="col" class="col-md-2">Status</th>
										<th scope="col" class="col-md-3">Total</th>
										<th scope="col" class="col-md-2">Actions</th>
									</tr>
								</thead>
								<tbody>
									<%
									    for (OrderViewDto order : orderDtos) {
									%>

									<tr id="order_<%=order.getId()%>" class="order row">
										<th class="orderId col-md-1"><%=order.getId()%></th>
										<td class="col-md-2"><%=order.getOrderedOn().format(formatter)%></td>
										<td class="col-md-2"><%=order.getApprovedOn() != null ? order.getApprovedOn().format(formatter) : "N/A"%></td>
										<td class="col-md-2"><%=order.getStatus()%></td>
										<td class="col-md-3">$<%=order.getTotal()%>
										</td>
										<td class="col-md-2"><a
											href="/online-shop/order/details?id=<%=order.getId()%>"
											class="btn btn-primary text-white">Details</a> <%
     if (order.getStatus().equalsIgnoreCase("approved")) {
 %> <a href="/online-shop/order/invoice?id=<%=order.getId()%>"
											class="invoice-btn btn btn-secondary text-white">Invoice</a>
											<%
											    }
											%></td>

									</tr>
									<%
									    }
									%>
								</tbody>
							</table>
						</div>
						<%
						    }
						%>
					</div>
				</div>
			</div>
		</div>
		<div class="mt-5 row justify-content-center">
			<nav>
				<ul class="pagination">

					<li class="page-item"><a
						class="page-link btn <%=currPage == 0 ? "disabled" : ""%>"
						href="/online-shop/order/my?page=<%=currPage == 0 ? 0 : currPage - 1%>"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
					</a></li>
					<li class="p-2"><%=currPage + 1%> of <%=pages == 0 ? 1 : pages%></li>
					<li class="page-item"><a
						class="page-link btn <%=currPage + 1 >= pages ? "disabled" : ""%>"
						href="/online-shop/order/my?page=<%=currPage + 1 >= pages ? currPage : currPage + 1%>"
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
<script>

</script>
</html>