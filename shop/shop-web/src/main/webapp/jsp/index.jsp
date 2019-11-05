<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<h1>Hello World!</h1>
<% for (String name : Collections.list(request.getAttributeNames())) { %>
<p><%= name%>
</p>
<% } %>
<p><%= new java.util.Date() %>
</p>
<div class="container-fluid">
    <main>
        <hr class="my-3"/>
        <div class="jumbotron">
            <p class="h1 display-3">Welcome to Online-Shop&trade;!</p>
            <p class="h3">The simplest, easiest to do your online shopping.</p>
            <hr class="my-3">
        </div>
        <hr class="my-3"/>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>