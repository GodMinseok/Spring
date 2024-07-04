<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/styles.css' />">
    <title><spring:message code="survey.survey"/></title>
</head>
<body>
<h2><spring:message code="survey.survey"/></h2>
<form:form action="surveyComplete" modelAttribute="surveyCommand" method="post">
    <p>
        <label for="q1">1. 당신의 희망 분야는?</label><br>
        <form:radiobutton path="q1" value="서버"/> 서버개발자<br>
        <form:radiobutton path="q1" value="프론트"/> 프론트개발자<br>
        <form:radiobutton path="q1" value="풀스택"/> 풀스택개발자<br>
        <form:errors path="q1" cssClass="error"/><br><br>
    </p>

    <p>
        <label for="q2">2. 가장 많이 사용하는 개발 도구는?</label><br>
        <form:radiobutton path="q2" value="Eclipse"/> Eclipse<br>
        <form:radiobutton path="q2" value="IntelliJ"/> IntelliJ<br>
        <form:radiobutton path="q2" value="VSCode"/> VSCode<br>
        <form:errors path="q2" cssClass="error"/><br><br>
    </p>

    <p>
        <label for="q3">3. 하고 싶은 말:</label>
        <form:input path="q3"/><br>
        <form:errors path="q3" cssClass="error"/><br>
    </p>

    <p>
        <label for="respondentName">응답자 이름:</label>
        <form:input path="respondentName"/><br>
        <form:errors path="respondentName" cssClass="error"/><br>
    </p>

    <p>
        <label for="respondentAge">응답자 나이:</label>
        <form:input path="respondentAge" type="number"/><br>
        <form:errors path="respondentAge" cssClass="error"/><br><br>
    </p>

    <input type="submit" value="<spring:message code='survey.surveyComplete'/>">
</form:form>
</body>
</html>