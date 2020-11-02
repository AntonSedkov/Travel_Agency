<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.moderatorhome"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightgreen">

    <%@include file="../header.jsp" %>

    <%@include file="../greeting.jsp" %>

    <div class="col text-center">
        <button type="submit" class="btn btn-primary m-2">
            <a href="controller?command=edit_tours" style="color: white"><fmt:message key="label.edittours"/></a>
        </button>
        <button type="submit" class="btn btn-primary m-2">
            <a href="controller?command=add_tour_page" style="color: white"><fmt:message key="label.addtour"/></a>
        </button>
        <p></p>
        <button type="submit" class="btn btn-primary m-2">
            <a href="controller?command=edit_orders_page" style="color: white"><fmt:message key="label.editorders"/></a>
        </button>
        <button type="submit" class="btn btn-primary m-2">
            <a href="controller?command=add_order_docs_page" style="color: white"><fmt:message key="label.addorderdoc"/></a>
        </button>
    </div>

    <%@include file="../carousel.jsp" %>

    <%@include file="../floatlogout.jsp"%>

    <%@include file="../footer.jsp" %>

</body>

</html>