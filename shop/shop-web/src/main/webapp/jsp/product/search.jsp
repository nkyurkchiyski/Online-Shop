<%@page import="com.example.shop.base.dto.CategoryDto"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<CategoryDto> categories = (List<CategoryDto>)request.getAttribute("categories");
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
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card shadow">
                    <div class="card-header form-title bg-warning">
                        <h4 class="m-0">Search Product</h4>
                    </div>
                    <div class="card-body">
                        <form class="form-horizontal" action="/online-shop/product/search"
                            method="post">
                            <div class="form-group row">
                                <label for="term" class="col-md-4 col-form-label text-md-right">Search Term</label>
                                <div class="col-md-6">
                                    <input type="text" id="term" name="term" placeholder="Search Term"
                                        class="form-control">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="price" class="col-md-4 col-form-label text-md-right">Price</label>
                                <div class="col-md-3">
                                    <input type="number" min="0" max="2147483646" step="0.01" id="minPrice" name="minPrice"
                                        placeholder="Minimum Price" class="form-control">
                                </div>
                                <div class="col-md-3">
                                    <input type="number" min="0" max="2147483646" step="0.01" id="maxPrice" name="maxPrice"
                                        placeholder="Maximum Price" class="form-control">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="categories"
                                    class="col-md-4 col-form-label text-md-right">Categories</label>
                                <div class="col-md-6">
                                    <select multiple class="form-control" id="categories"
                                        name="categories">
                                        <%
                                            for (CategoryDto category : categories)
                                            {
                                        %>
                                        <option value="<%=category.getId()%>"><%=category.getName()%></option>
                                        <%
                                            }
                                        %>

                                    </select>
                                </div>
                            </div>
                            <div class="row d-flex justify-content-around">
                                <a class="btn btn-lg btn-light" href="/online-shop/home">
                                    Cancel </a>
                                <button type="submit" class="btn btn-lg btn-secondary">
                                    Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="../templates/footer.jsp" />
    </div>
</body>
<c:import url="../templates/scripts.jsp" />
</html>