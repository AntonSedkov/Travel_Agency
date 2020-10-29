<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${language}">

<head>
    <title><fmt:message key="label.userhome"/> TOURS</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightsteelblue">

    <%@include file="../header.jsp" %>

    <%@include file="../search.jsp" %>

    <%@include file="../greeting.jsp" %>

                                                            <%@include file="currentfunction1.jsp" %>

    <%@include file="../hottours.jsp" %>

    <%@include file="../floatlogout.jsp" %>

    <%@include file="../footer.jsp" %>

</body>

</html>