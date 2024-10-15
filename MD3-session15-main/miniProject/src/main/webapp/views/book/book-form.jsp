<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/30/2024
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${book == null ? "Create new book" : "Update book"}</title>
</head>
<body>
<h1>${book == null ? "Create new book" : "Update book"}</h1>
<form action="LoadBook" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="${book == null ? 'save' : 'update'}">
    <input type="hidden" name="id" id="id" value="${book != null ? book.id : ''}">

    <label for="categoryId">Category: </label>
    <select name="categoryId" id="categoryId" required>
        <c:forEach items="${Categories}" var="category">
            <option value="${category.id}" ${book != null && book.categoryId == category.id ? 'selected' : ''}>
                    ${category.name}
            </option>
        </c:forEach>
    </select>
    <br>

    <label for="name">Book Name: </label>
    <input type="text" name="name" id="name" value="${book != null ? book.name : ''}" required>
    <br>

    <label for="price">Price: </label>
    <input type="text" name="price" id="price" value="${book != null ? book.price : ''}" required>
    <br>

    <label for="stock">Stock: </label>
    <input type="number" name="stock" id="stock" value="${book != null ? book.stock : ''}" required>
    <br>

    <label for="totalPages">Total Pages: </label>
    <input type="number" name="totalPages" id="totalPages" value="${book != null ? book.totalPages : ''}" required>
    <br>

    <label for="yearCreated">Year Created: </label>
    <input type="number" name="yearCreated" id="yearCreated" value="${book != null ? book.yearCreated : ''}" required>
    <br>

    <label for="author">Author: </label>
    <input type="text" name="author" id="author" value="${book != null ? book.author : ''}" required>
    <br>

    <label for="bookImage">Book Image: </label>
    <input type="file" name="bookImage" id="bookImage" ${book == null ? 'required' : ''}>
    <br>

    <label for="status">Status: </label>
    <input type="checkbox" name="status" id="status" ${book != null && book.status ? 'checked' : ''}>
    <br>

    <button type="submit">Save</button>
</form>
</body>
</html>
