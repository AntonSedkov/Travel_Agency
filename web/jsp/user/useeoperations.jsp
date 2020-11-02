<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.operations"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightsteelblue">

    <%@include file="../header.jsp" %>

    <%@include file="../search.jsp" %>

    <%@include file="../greeting.jsp" %>

    <section class="base_info">
        <div class="container text-center p-5" style="width: 30rem">
            <div class="card-deck">
                <div class="card text-center" style="width: 20rem;">
                    <div class="card-body" style="background-color: lemonchiffon">
                        <h4 class="card-title pb-2 pt-1" style="text-align: left; font-weight: bold">
                            <fmt:message key="label.sheetnumber"/> ${sessionScope.sheet.id}<br/>
                        </h4>
                        <h4 class="card-title pb-2" style="text-align: left; font-weight: bold">
                            <fmt:message key="label.sheetsum"/> ${sessionScope.sheet.sheetSum} <fmt:message key="icon.currency"/><br/>
                        </h4>
                        <h4 class="card-title " style="text-align: left; font-weight: bold">
                            <fmt:message key="label.sheetdiscount"/> ${sessionScope.sheet.discount.value} <fmt:message
                                key="icon.percent"/>
                        </h4>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="operations">

        <p style="text-align: center; font-weight: bold; font-size: 24pt">
            <c:if test="${empty sessionScope.operations}"><fmt:message key="operations.nothing"/></c:if>
        </p>

        <c:if test="${not empty sessionScope.operations}">

            <div class="container-fluid p-3 text-center" style="max-width: 60%; background: lightgoldenrodyellow">
                <div class="form-row">
                    <div class="col" style="max-width: 10%; text-align: left"><fmt:message key="icon.number"/></div>
                    <div class="col" style="max-width: 20%; text-align: center"><fmt:message key="label.operationid"/></div>
                    <div class="col" style="max-width: 20%; text-align: center"><fmt:message key="label.operationsum"/></div>
                    <div class="col" style="max-width: 50%; text-align: center"><fmt:message
                            key="label.operationpurpose"/></div>
                </div>

                <c:forEach items="${sessionScope.operations}" varStatus="counter">
                    <div class="form-row">
                        <div class="col" style="max-width: 10%; text-align: left">${counter.count}</div>
                        <div class="col" style="max-width: 20%; text-align: center">${counter.current.id}</div>
                        <div class="col" style="max-width: 20%; text-align: center">${counter.current.operationSum}</div>
                        <div class="col" style="max-width: 50%; text-align: center">${counter.current.operationPurpose}</div>
                    </div>
                </c:forEach>

            </div>
        </c:if>
    </section>

    <%@include file="../hottours.jsp" %>

    <%@include file="../floatlogout.jsp" %>

    <%@include file="../footer.jsp" %>

</body>

</html>