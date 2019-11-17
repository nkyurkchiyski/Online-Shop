<%@page import="com.example.shop.base.dto.ProductDto" %>
<%@page import="com.example.shop.base.dto.UserViewDto" %>
<%@page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    List<ProductDto> products = (List<ProductDto>) request.getAttribute("products");
    UserViewDto user = (UserViewDto) request.getSession().getAttribute("user");
    String filter = (String) request.getSession().getAttribute("filter");
%>
<html>
<head>
    <c:import url="../templates/head.jsp"/>
</head>
<body>
<header>
    <c:import url="../templates/nav.jsp"/>
</header>
<div class="container-fluid my-5">
    <div class="text-center pb-4">
        <h2>
            Products:
            <%=filter%>
        </h2>
    </div>
    <div class="d-flex justify-content-center">
        <div class="row w-75">
            <%
                if (products.size() == 0) {
            %>
            <div>
                <h3 class="font-italic text-muteds">There are currently no
                    products</h3>
            </div>
            <%
            } else {
                for (ProductDto product : products) {
            %>
            <div class="col-md-3 my-2">
                <div class="card shadow text-left">
                    <img src="<%=product.getImageUrl()%>" class="card-img-top mt-4" height="150px"
                         style="object-fit: scale-down">
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
                                if (user.isAdmin()) {
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
    <c:import url="../templates/footer.jsp"/>
</div>
</body>
<c:import url="../templates/scripts.jsp"/>
</html>