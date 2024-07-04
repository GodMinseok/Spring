<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jjm42
  Date: 2024-05-03
  Time: 오후 2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>

<c:if test="${empty authInfo}">
<p>환영합니다.</p>
<a href='<c:url value="register/step1"/>'>[회원가입하기]</a>
<a href='<c:url value="/login"/>'>[로그인하기]</a>
</c:if>

<c:if test="${!empty authInfo}">
    <p>${authInfo.name}님, 환영합니다.</p>
    <a href="<c:url value="/register/step1" />">[비밀번호 변경]</a>
    <a href="<c:url value="/logout" />">[로그아웃]</a>
</c:if>

</body>
</html>
