<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.tourdocs"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightsteelblue">

<%@include file="../header.jsp" %>

<%@include file="../carousel.jsp" %>

<section class="see_docs">

    <p style="text-align: center; font-weight: bold; font-size: 20pt">
        <c:if test="${empty sessionScope.tourdocs}"><fmt:message key="docs.nothing"/></c:if>
    </p>

    <c:if test="${not empty sessionScope.tourdocs and not empty sessionScope.order}">

        <div class="container-fluid p-3 text-center" style="max-width: 35%; background: lightsteelblue">
            <div class="form-row pb-2">
                <div class="col">
                    <fmt:message key="label.tourdocs"/>
                </div>
            </div>
            <div class="form-row pb-2">
                <div class="col">
                    <fmt:message key="label.tour"/><fmt:message key="icon.colon"/>
                    <fmt:message key="label.${sessionScope.order.tour.country}"/>,
                    <fmt:message key="label.${sessionScope.order.tour.tourType.value}"/>,
                    <fmt:message key="label.startdate"/> ${sessionScope.order.tour.startDate},
                        ${sessionScope.order.tour.days} <fmt:message key="label.days"/>
                </div>
            </div>
            <div class="form-row pb-2">
                <div class="col" style="text-align: center">
                    <fmt:message key="label.voucher"/>
                    <a href="${pageContext.request.contextPath}/pics/tourdoc/${sessionScope.tourdocs.voucher}"
                       target="_blank" style="font-weight: bold">
                            ${sessionScope.tourdocs.voucher}
                    </a>
                </div>
            </div>
            <div class="form-row pb-2">
                <div class="col" style="text-align: center">
                    <fmt:message key="label.insurance"/>
                    <a href="${pageContext.request.contextPath}/pics/tourdoc/${sessionScope.tourdocs.insurance}"
                       target="_blank" style="font-weight: bold">
                            ${sessionScope.tourdocs.insurance}
                    </a>
                </div>
            </div>
            <div class="form-row pb-2">
                <div class="col" style="text-align: center">
                    <fmt:message key="label.tickets"/>
                    <a href="${pageContext.request.contextPath}/pics/tourdoc/${sessionScope.tourdocs.ticket}"
                       target="_blank" style="font-weight: bold">
                            ${sessionScope.tourdocs.ticket}
                    </a>
                </div>
            </div>

        </div>
    </c:if>
</section>

<div class="col text-center">
    <button type="submit" class="btn btn-primary m-2">
        <a href="controller?command=actual_orders" style="color: white"><fmt:message key="label.actualorders"/></a>
    </button>
    <button type="submit" class="btn btn-primary m-2">
        <a href="controller?command=all_orders" style="color: white"><fmt:message key="label.allorders"/></a>
    </button>
</div>

<%@include file="../floatlogout.jsp" %>

<%@include file="../footer.jsp" %>

</body>

</html>