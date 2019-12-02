<%@page import="com.example.shop.base.dto.OrderDetailsDto"%>
<%@ page import="com.example.shop.base.dto.ProductOrderDto"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    OrderDetailsDto order = (OrderDetailsDto)request.getAttribute("order");
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
			<h2>
				Order #<%=order != null ? order.getId() : "N/A"%></h2>
		</div>
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card shadow">
					<div class="card-body">
						<%
						    if (order == null)
						    {
						%>
						<div>
							<h3 class="font-italic text-muteds">Order does not exist!</h3>
						</div>
						<%
						    }
						    else
						    {
						%>
						<div>
							<table class="table">
								<thead>
									<tr class="row">
										<th scope="col" class="col-md-1">#</th>
										<th scope="col" class="col-md-3">Product Name</th>
										<th scope="col" class="col-md-1">Quantity</th>
										<th scope="col" class="col-md-2">Single Price</th>
										<th scope="col" class="col-md-3">Total</th>
										<th scope="col" class="col-md-2">Actions</th>
									</tr>
								</thead>
								<tbody>
									<%
									    for (ProductOrderDto op : order.getProducts())
									        {
									%>

									<tr id="product_<%=op.getProduct().getId()%>"
										class="product row">
										<th class="productId col-md-1"><%=op.getProduct().getId()%>
										</th>
										<td class="col-md-3"><%=op.getProduct().getName()%></td>
										<td class="col-md-1"><%=op.getQuantity()%></td>
										<td class="col-md-2">$<%=op.getProduct().getPrice()%>
										</td>
										<td class="col-md-3">$<%=op.getProduct().getPrice().multiply(new BigDecimal(op.getQuantity()))%>
										</td>
										<td class="col-md-2"><a
											href="/online-shop/product/details?id=<%=op.getProduct().getId()%>"
											class="btn btn-primary text-white">Details</a></td>
									</tr>
									<%
									    }
									%>
								</tbody>
							</table>
							<hr>
							<div class="text-right">
								<span class="h5 text-uppercase"> Grand Total: <span
									id="grandTotal">$<%=order.getTotal()%></span>
								</span>
							</div>
						</div>
						<%
						    }
						%>
					</div>
				</div>
			</div>
		</div>
		<div></div>
		<c:import url="../templates/footer.jsp" />
	</div>
</body>
<c:import url="../templates/scripts.jsp" />
</html>