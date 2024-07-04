<%--
  Created by IntelliJ IDEA.
  User: jjm42
  Date: 2024-05-03
  Time: 오전 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="member.register"/></title>
</head>
<body>
<h2><spring:message code="member.info"/></h2>
<form:form action="step3" modelAttribute="formData">
<%--<form action="step3" method="post">--%>
    <p>
        <label for="email"><spring:message code="email"/> :</label>
        <form:input path="email" />
        <form:errors path="email" />
<%--        <input type="text" name="email" id="email" value="${formData.email}">--%>
    </p>

    <p>
        <label for="name"><spring:message code="name"/> :</label>
        <form:input path="name" />
        <form:errors path="name" />
<%--        <input type="text" name="name" id="name" value="${formData.name}">--%>
    </p>

    <p>
        <label for="password"><spring:message code="password"/> :</label>
        <form:input path="password" />
        <form:errors path="password" />
<%--        <input type="text" name="password" id="password">--%>
    </p>

    <p>
        <label for="confirmPassword"><spring:message code="password.confirm"/> :</label>
        <form:input path="confirmPassword" />
        <form:errors path="confirmPassword" />
<%--        <input type="text" name="confirmPassword" id="confirmPassword">--%>
    </p>
    <input type="submit" value="<spring:message code="register.btn"/>">

</form:form>
<%--</form>--%>
</body>
</html>
