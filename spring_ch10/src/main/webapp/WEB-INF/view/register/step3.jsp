<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원가입</title>
</head>
<h2>Step3 - <spring:message code="register.btn"/></h2>
<p>
    <spring:message code="register.done" arguments="${formData.name}"/>
    <%--    <strong>${formData.name}님</strong>--%>
</p>
<p>회원가입을 완료했습니다.</p>
<a href="<c:url value='/main'/>">[<spring:message code="go.main"/>]</a>
</body>
</html>