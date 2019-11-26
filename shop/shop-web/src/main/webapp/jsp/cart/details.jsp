<%@page import="com.example.shop.base.dto.OrderDetailsDto"%>
<%@ page import="com.example.shop.base.dto.ProductOrderDto"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    OrderDetailsDto cart = (OrderDetailsDto) request.getAttribute("cart");
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
			<h2>Shopping cart</h2>
		</div>
		<div class="row justify-content-center">
			<div class="col-md-10">
				<div class="card shadow">
					<div class="card-body">
						<%
						    if (cart == null || cart.getProducts().size() == 0) {
						%>
						<div>
							<h3 class="font-italic text-muteds">Your shopping cart is
								currently empty!</h3>
						</div>
						<%
						    } else {
						%>
						<div id="cart">
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
									    for (ProductOrderDto op : cart.getProducts()) {
									%>

									<tr id="product_<%=op.getProduct().getId()%>"
										class="product row">
										<th class="productId col-md-1"><%=op.getProduct().getId()%>
										</th>
										<td class="col-md-3"><%=op.getProduct().getName()%></td>
										<td class="col-md-1"><input
											id="product_<%=op.getProduct().getId()%>_quantity"
											type="number" step="1" min="1" max="2147483646"
											class="quantity form-control" value="<%=op.getQuantity()%>"
											placeholder="Quantity" required></td>

										<td class="col-md-2"
											id="product_<%=op.getProduct().getId()%>_price">$<%=op.getProduct().getPrice()%>
										</td>

										<td class="col-md-3"
											id="product_<%=op.getProduct().getId()%>_total">$<%=op.getProduct().getPrice().multiply(new BigDecimal(op.getQuantity()))%>
										</td>
										<td class="col-md-2"><a
											class="remove-btn btn btn-danger text-white">Remove</a></td>

									</tr>
									<%
									    }
									%>
								</tbody>
							</table>
							<hr>
							<div class="d-flex justify-content-between">
								<div class="text-right">
									<span class="h5 text-uppercase"> Grand Total: <span
										id="grandTotal">$<%=cart.getTotal()%></span>
									</span>
								</div>
								<div>
									<a id="updateCart" class="btn btn-secondary text-white">Update
										Cart</a> <a id="placeOrder" class="btn btn-primary text-white">Place
										Order</a>
								</div>

							</div>
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
	update();

	$("#placeOrder").click(function() {
		const
		data = JSON.stringify(getProductOrders(getProductIds()));
		const
		url = "/online-shop/order/place";
		sendRequest("POST", url, function(resp) {
			createToast(resp).toast('show');
			if (resp.successful)
				placeOrder();
		}, null, data);
	});

	$("#updateCart").click(function() {
		const
		data = JSON.stringify(getProductOrders(getProductIds()));
		const
		url = "/online-shop/cart/update";
		sendRequest("POST", url, function(resp) {
			createToast(resp).toast('show');
		}, null, data);
	});

	$(".remove-btn").click(function() {
		let
		element = this.parentElement.parentElement;
		const
		productId = getProductId(element.id);
		const
		url = "/online-shop/cart/product?" + $.param({
			"productId" : productId
		});
		sendRequest("DELETE", url, function(resp) {
			createToast(resp).toast('show');
			if (resp.successful)
				removeProduct(element);
		}, null, null);
	});
</script>
</html>