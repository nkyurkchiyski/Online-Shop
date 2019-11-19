<%@ page import="com.example.shop.base.dto.OrderViewDto" %>
<%@ page import="com.example.shop.base.dto.ProductOrderDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    OrderViewDto cart = (OrderViewDto) request.getAttribute("cart");
%>
<html>
<head>
    <c:import url="../templates/head.jsp"/>
</head>
<body>
<header>
    <c:import url="../templates/nav.jsp"/>
</header>
<div class="container-fluid m-5">
    <div class="text-center pb-4">
        <h2>Shopping cart
        </h2>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Product Name</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (ProductOrderDto op : cart.getProducts()) {
                        %>
                        <tr>
                            <form>
                                <td><%=op.getProduct().getName()%>
                                </td>
                                <td><input name="quantity" type="number" step="1" min="1"
                                           max="2147483646" class="form-control" value="<%=op.getQuantity()%>"
                                           placeholder="Quantity" required></td>
                                <td>
                                    <button type="submit" class="btn btn-primary">Update</button>
                                    <a href="/online-shop/order/product/remove?productId=<%=op.getProduct().getId()%>"
                                       class="btn btn-danger">Remove</a>
                                </td>
                            </form>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div>
    </div>
    <c:import url="../templates/footer.jsp"/>
</div>
</body>
<c:import url="../templates/scripts.jsp"/>
</html>