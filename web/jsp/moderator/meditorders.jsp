<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.editorders"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightgreen">

<%@include file="../header.jsp" %>

<%@include file="../greeting.jsp" %>

<section class="moder_edit_orders" style="text-align: center">

    <h2><fmt:message key="label.editorders"/></h2>

    <p style="font-weight: bold; font-size: 20pt">
        <c:if test="${empty sessionScope.ordersandusers}"><fmt:message key="docs.nothing"/></c:if>
    </p>

    <c:if test="${not empty sessionScope.ordersandusers}">

        <c:if test="${requestScope.confirmorder}">
            <p class="card-text" style="color: darkgreen; font-weight: bold">
                <fmt:message key="statement.confirmordersuccess"/>
            </p>
        </c:if>
        <c:if test="${requestScope.confirmorder eq false}">
            <p class="card-text" style="color: darkred; font-weight: bold">
                <fmt:message key="statement.confirmorderfail"/>
            </p>
        </c:if>

        <c:if test="${requestScope.declineorder}">
            <p class="card-text" style="color: darkgreen; font-weight: bold">
                <fmt:message key="statement.declineordersuccess"/>
            </p>
        </c:if>
        <c:if test="${requestScope.declineorder eq false}">
            <p class="card-text" style="color: darkred; font-weight: bold">
                <fmt:message key="statement.declineorderfail"/>
            </p>
        </c:if>

        <div class="container-fluid p-3 text-center" style="background: lemonchiffon">
            <c:forEach items="${sessionScope.ordersandusers}" var="current">

                <div class="form-row pb-2">
                    <div class="col" style="font-weight: bold">
                        <fmt:message key="label.user"/><fmt:message key="icon.colon"/>
                            ${current.value}
                    </div>
                </div>

                <div class="form-row pb-2">
                    <div class="col" style="font-weight: bold">
                        <fmt:message key="label.tour"/><fmt:message key="icon.colon"/>
                        <fmt:message key="label.${current.key.tour.country}"/>,
                        <fmt:message key="label.${current.key.tour.tourType.value}"/>,
                        <fmt:message key="label.startdate"/> ${current.key.tour.startDate},
                            ${current.key.tour.days} <fmt:message key="label.days"/>
                    </div>
                </div>

                <div class="form-row pb-2">
                    <div class="col" style="font-weight: bold">
                            ${current.key.passport.surname},
                            ${current.key.passport.name},
                            ${current.key.passport.birthDate},
                            ${current.key.passport.passportNumber} <br/>
                        <a href="${pageContext.request.contextPath}/pics/persdoc/${current.key.passport.passportImage}">
                                ${current.key.passport.passportImage}
                        </a>
                    </div>
                </div>

                <div class="form-row pb-2">

                    <div class="col" style="text-align: right; max-width: 40%">
                        <form name="confirmForm" method="post" action="controller">
                            <input type="hidden" name="command" value="change_state"/>
                            <input type="hidden" name="targetstate" value="confirmed"/>
                            <input type="hidden" name="idorder" value="${current.key.id}"/>
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="button.confirm"/></button>
                        </form>
                    </div>

                    <div class="col" style="max-width: 60%">
                        <form name="declineForm" method="post" action="controller">
                            <input type="hidden" name="command" value="change_state"/>
                            <input type="hidden" name="targetstate" value="declined"/>
                            <input type="hidden" name="idorder" value="${current.key.id}"/>
                            <p><fmt:message key="field.entercomment"/></p>
                            <p><label>
                                <textarea rows="5" cols="45" name="comment"></textarea>
                            </label></p>
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="button.decline"/>
                            </button>
                        </form>
                    </div>

                </div>
            </c:forEach>
        </div>
    </c:if>

</section>

<%@include file="../floatlogout.jsp" %>

<%@include file="../footer.jsp" %>

</body>

</html>