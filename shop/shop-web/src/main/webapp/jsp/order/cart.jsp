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
			<div class="col-md-6">
				<div class="card shadow">
					<div class="card-body">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Product Name</th>
									<th scope="col">Quantity</th>
									<th scope="col">Single Price</th>
									<th scope="col">Total</th>
									<th scope="col">Actions</th>
								</tr>
							</thead>
							<tbody>
								<%
								    for (ProductOrderDto op : cart.getProducts()) {
								%>

								<tr id="product_<%=op.getProduct().getId()%>" class="product">
									<th class="productId"><%=op.getProduct().getId()%></th>
									<td><%=op.getProduct().getName()%></td>
									<td><input
										id="product_<%=op.getProduct().getId()%>_quantity"
										type="number" step="1" min="1" max="2147483646"
										class="quantity form-control" value="<%=op.getQuantity()%>"
										placeholder="Quantity" required></td>

									<td id="product_<%=op.getProduct().getId()%>_price"><%=op.getProduct().getPrice()%></td>

									<td id="product_<%=op.getProduct().getId()%>_total"><%=op.getProduct().getPrice().multiply(new BigDecimal(op.getQuantity()))%></td>
									<td><a class="removeButton btn btn-danger text-white">Remove</a></td>

								</tr>
								<%
								    }
								%>
							</tbody>
						</table>
						<hr>
						<div class="text-right">
							<span class="h4 text-uppercase"> Grand Total: <span
								id="grandTotal"><%=cart.getTotal()%><span> </span> <a
									id="placeOrder" class="btn btn-primary text-white">Place</a>
						</div>
					</div>
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
			ids.push(elems[i].value);
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
			var prPrice = $("#" + elemId + "_price").text();
			var prQuantity = $("#" + elemId + "_quantity").val();
			var prTotal = (prPrice * prQuantity).toFixed(2);
			$("#" + elemId + "_total").text(prTotal);
			total += Number(prTotal);
		}
		$("#grandTotal").text(total);
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

	update();

	$("#placeOrder").click(function() {
		$.ajax({
			type : "POST",
			url : "/online-shop/order/place",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(getProductOrders(getProductIds())),
			dataType : "json"
		});
	});

	$(".removeButton").click(function() {
		var element = this.parentElement.parentElement;
		var productId = getProductId(element.id);
		$.ajax({
			type : "POST",
			url : "/online-shop/order/product/remove",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(productId),
			dataType : "json",
			success : removeProduct(element)
		});
	});
</script>
</html>