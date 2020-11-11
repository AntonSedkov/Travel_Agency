<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.adminhome"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightgrey">

    <%@include file="../header.jsp" %>

    <%@include file="../greeting.jsp" %>

    <div class="col text-center">
        <button type="submit" class="btn btn-primary m-2">
            <a href="controller?command=edit_users" style="color: white"><fmt:message key="label.editusers"/></a>
        </button>
        <button type="submit" class="btn btn-primary m-2">
            <a href="controller?command=add_staff" style="color: white"><fmt:message key="label.addstaff"/></a>
        </button>
    </div>

    <div class="col text-left">
        <h4><fmt:message key="label.totalusers"/> ${sessionScope.quantityusers} <fmt:message key="label.men"/></h4>
        <c:forEach var="quantityUsersByRole" items="${sessionScope.usersbyroles}">
            <h5><fmt:message key="label.${quantityUsersByRole.key}"/> <fmt:message key="icon.colon"/>
                    ${quantityUsersByRole.value} <fmt:message key="label.men"/></h5>
        </c:forEach>
    </div>

    <%@include file="../carousel.jsp" %>

    <%@include file="../floatlogout.jsp" %>

    <%@include file="../footer.jsp" %>

</body>

</html>