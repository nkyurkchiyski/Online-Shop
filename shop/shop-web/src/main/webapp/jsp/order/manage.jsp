<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.example.shop.base.dto.OrderViewDto"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<OrderViewDto> orderDtos = (List<OrderViewDto>) request.getAttribute("orders");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
			<h2>Manage Orders</h2>
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
										<th scope="col" class="col-md-2">User</th>
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
										<td class="col-md-2"><%=order.getUser().getEmail()%></td>
										<td id="order_<%=order.getId()%>_status" class="col-md-2"><%=order.getStatus()%></td>
										<td class="col-md-3">$<%=order.getTotal()%>
										</td>
										<td class="col-md-2">
											<%
											    if (order.getStatus().equalsIgnoreCase("pending")) {
											%> <a id="order_<%=order.getId()%>_button"
											class="approve-btn btn btn-danger text-white">Approve</a> <%
     }
 %>
										</td>

									</tr>
									<%
									    }
									%>
								</tbody>
							</table>
						</div>
					</div>
					<%
					    }
					%>
				</div>
			</div>
		</div>
		<div></div>
		<c:import url="../templates/footer.jsp" />
	</div>
</body>
<c:import url="../templates/scripts.jsp" />
<script>
	$(".approve-btn").click(function() {
		let
		element = this.parentElement.parentElement;
		const
		orderId = getOrderId(element.id);
		const
		url = "/online-shop/order/approve";
		const
		data = JSON.stringify(orderId);
		sendRequest("POST", url, function(resp) {
			createToast(resp).toast('show');
			if (resp.successful) {
				approveOrder(orderId);
			}
		}, null, data);
	});
</script>
</html>