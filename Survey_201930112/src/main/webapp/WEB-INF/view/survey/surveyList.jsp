<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Survey</title>
</head>
<body>
<h2>전체 응답 리스트</h2>
<table border="1" >
    <tr>
        <th>ID</th>
        <th>Q1</th>
        <th>Q2</th>
        <th>Q3</th>
        <th>응답자이름</th>
        <th>응답자나이</th>
    </tr>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.id}</td>
            <td>${item.q1}</td>
            <td>${item.q2}</td>
            <td>${item.q3}</td>
            <td>${item.respondentName}</td>
            <td>${item.respondentAge}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>