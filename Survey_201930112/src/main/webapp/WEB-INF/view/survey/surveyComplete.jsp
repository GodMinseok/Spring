<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Survey Complete</title>
</head>
<body>
<h2><spring:message code="survey.request"/></h2>

    1 : ${survey.q1}<br><br>
    2 : ${survey.q2}<br><br>
    3 : ${survey.q3}<br><br>
    응답자 이름: ${survey.respondentName}<br><br>
    응답자 나이: ${survey.respondentAge}<br><br>

<a href="surveyList">응답 리스트 보기</a>
</body>
</html>