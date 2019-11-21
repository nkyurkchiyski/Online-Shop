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
	<div class="container-fluid m-5">
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
											class="removeButton btn btn-danger text-white">Remove</a></td>

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
<script type="text/javascript">
	function getProductIds() {
		var ids = [];
		var elems = $(".productId");
		for (var i = 0; i < elems.length; i++) {
			ids.push(elems[i].innerText);
		}
		return ids;
	}

	function getProductOrders(ids) {
		var products = [];
		for (var i = 0; i < ids.length; i++) {
			var quantity = $("#product_" + ids[i] + "_quantity").val();
			products.push({
				productId : ids[i],
				quantity : quantity
			});
		}
		return products;
	}

	function getProductId(id) {
		return id.replace('product_', '');
	}

	function updateCart() {
		var productElements = $(".product");
		var total = 0;
		for (var i = 0; i < productElements.length; i++) {
			var elemId = productElements[i].id;
			var prPrice = $("#" + elemId + "_price").text().replace('$', '');
			var prQuantity = $("#" + elemId + "_quantity").val();
			var prTotal = (prPrice * prQuantity).toFixed(2);
			$("#" + elemId + "_total").text("$" + prTotal);
			total += Number(prTotal);
		}
		$("#grandTotal").text("$" + total.toFixed(2));
	}

	function removeProduct(element) {
		element.remove()
		updateCart();
	}

	function update() {
		var elements = $('.quantity');
		for (var i = 0; i < elements.length; i++) {
			elements[i].addEventListener('change', updateCart, false);
		}

	}

	function redirectToHome() {
		window.location.href = "http://localhost:8181/online-shop/home";
	}

	update();

	$("#placeOrder").click(function() {
		var data = JSON.stringify(getProductOrders(getProductIds()));
		$.ajax({
			type : "POST",
			url : "/online-shop/order/place",
			contentType : "application/json; charset=utf-8",
			data : data,
			dataType : "json",
			success : function(success) {
				if (success)
					redirectToHome();
			}
		});
	});

	$("#updateCart").click(function() {
		var data = JSON.stringify(getProductOrders(getProductIds()));
		$.ajax({
			type : "POST",
			url : "/online-shop/cart/update",
			contentType : "application/json; charset=utf-8",
			data : data,
			dataType : "json"
		});
	});

	$(".removeButton").click(function() {
		var element = this.parentElement.parentElement;
		var productId = getProductId(element.id);
		$.ajax({
			type : "DELETE",
			url : "/online-shop/cart/product?" + $.param({
				"productId" : productId
			}),
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : removeProduct(element)
		});
	});
</script>
</html>