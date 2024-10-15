<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/30/2024
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${category==null ? "Create new class" : "Update class"}</h1>
<form action="LoadCategory" method="post">
    <input type="hidden" name="action" value="${category == null ? 'save' : 'update'}">
    <input type="hidden" name="id" id="id" value="${category != null ? category.id : ''}" required>
    <label for="categoryName">Category name: </label>
    <input type="text" name="categoryName" id="categoryName" value="${category != null ? category.name : ''}" required>
    <label for="status">Status: </label>
    <input type="checkbox" name="status" id="status" ${category != null && category.status ? 'checked' : ''}>
    <button type="submit">Save</button>
</form>
</body>
</html>
