<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="login.title"/></title>
</head>
<body>
<form:form modelAttribute="loginCommand">

    <form:errors />
    <p>
        <label><spring:message code="email"/> </label>
        <form:input path="email" />
        <form:errors path="email" />
    </p>
    <p>
        <label><spring:message code="password"/> </label>
        <form:input path="password" />
        <form:errors path="password" />
    </p>

    <p>
        <label><spring:message code="rememberEmail"/> </label>
        <form:checkbox path="rememberEmail" />
    </p>
    <input type="submit" value="<spring:message code="login.btn"/> " />
</form:form>
</body>
</html>