<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.seeorders"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightsteelblue">

<%@include file="../header.jsp" %>

<%@include file="../search.jsp" %>

<%@include file="../greeting.jsp" %>

<section class="all_orders">

    <p style="text-align: center; font-weight: bold; font-size: 20pt">
        <c:if test="${empty sessionScope.orders}"><fmt:message key="orders.nothing"/></c:if>
    </p>

    <c:if test="${requestScope.cancelorder}">
        <p style="color: darkgreen; font-weight: bold; text-align: center">
            <fmt:message key="statement.cancelordersuccess"/>
        </p>
    </c:if>

    <c:if test="${requestScope.cancelorder eq false}">
        <p style="color: darkred; font-weight: bold; text-align: center">
            <fmt:message key="statement.cancelorderfail"/>
        </p>
    </c:if>


    <c:if test="${not empty sessionScope.orders}">

        <div class="user_new_state">
            <div class="container p-5">
                <div class="card-deck">

                    <c:forEach items="${sessionScope.orders}" var="order">
                        <c:if test="${order.orderState.value.equals('new')}">

                            <div class="card text-center" style="width: 20rem; background: lightgreen">
                                <div class="card-body">
                                    <h4><fmt:message key="label.wapproval"/></h4>
                                    <h4>${order.tour.price}<fmt:message key="icon.currency"/></h4>
                                    <p class="card-text">
                                            ${order.tour.country}, ${order.tour.tourType.value}<br/>
                                        <fmt:message key="label.startdate"/> ${order.tour.startDate},
                                            ${order.tour.days} <fmt:message key="label.days"/>
                                    </p>
                                    <p class="card-text">
                                            ${order.passport.surname}
                                            ${order.passport.name}
                                    </p>
                                    <form name="cancelNewOrderForm" method="post" action="controller">
                                        <input type="hidden" name="command" value="cancel_order">
                                        <input type="hidden" name="idorder" value="${order.id}">
                                        <button type="submit" class="btn btn-primary">
                                            <fmt:message key="label.cancelorder"/>
                                        </button>
                                    </form>
                                </div>
                            </div>

                        </c:if>
                    </c:forEach>

                </div>
            </div>
        </div>

        <div class="user_confirm_state">
            <div class="container p-5">
                <div class="card-deck">

                    <c:forEach items="${sessionScope.orders}" var="order">
                        <c:if test="${order.orderState.value.equals('confirm')}">

                            <div class="card text-center" style="width: 20rem; background: lightseagreen">
                                <div class="card-body">
                                    <h4><fmt:message key="label.wpayment"/></h4>
                                    <h4>${order.tour.price}<fmt:message key="icon.currency"/></h4>
                                    <p class="card-text">
                                            ${order.tour.country}, ${order.tour.tourType.value}<br/>
                                        <fmt:message key="label.startdate"/> ${order.tour.startDate},
                                            ${order.tour.days} <fmt:message key="label.days"/>
                                    </p>
                                    <p class="card-text">
                                            ${order.passport.surname}
                                            ${order.passport.name}
                                    </p>
                                    <form name="cancelOrderConfirmForm" method="post" action="controller">
                                        <input type="hidden" name="command" value="cancel_order">
                                        <input type="hidden" name="idorder" value="${order.id}">
                                        <button type="submit" class="btn btn-primary">
                                            <fmt:message key="label.cancelorder"/>
                                        </button>
                                    </form>
                                    <form name="payOrderForm" method="post" action="controller">
                                        <input type="hidden" name="command" value="pay_order">
                                        <input type="hidden" name="idorder" value="${order.id}">
                                        <button type="submit" class="btn btn-primary">
                                            <fmt:message key="label.payorder"/>
                                        </button>
                                    </form>
                                </div>
                            </div>

                        </c:if>
                    </c:forEach>

                </div>
            </div>
        </div>

        <div class="user_paid_state">
            <div class="container p-5">
                <div class="card-deck">

                    <c:forEach items="${sessionScope.orders}" var="order">
                        <c:if test="${order.orderState.value.equals('paid')}">

                            <div class="card text-center" style="width: 20rem;background: lightcyan">
                                <div class="card-body">
                                    <h4><fmt:message key="label.wdocs"/></h4>
                                    <p class="card-text">
                                            ${order.tour.country}, ${order.tour.tourType.value}<br/>
                                        <fmt:message key="label.startdate"/> ${order.tour.startDate},
                                            ${order.tour.days} <fmt:message key="label.days"/><br/>
                                            ${order.tour.price}
                                        <fmt:message key="icon.currency"/>
                                    </p>
                                    <p class="card-text">
                                            ${order.passport.surname}
                                            ${order.passport.name}
                                    </p>
                                </div>
                            </div>

                        </c:if>
                    </c:forEach>

                </div>
            </div>
        </div>

        <div class="user_add_docs_state">
            <div class="container p-5">
                <div class="card-deck">

                    <c:forEach items="${sessionScope.orders}" var="order">
                        <c:if test="${order.orderState.value.equals('add_docs')}">

                            <div class="card text-center" style="width: 20rem; background: lightskyblue">
                                <div class="card-body">
                                    <h4><fmt:message key="label.enjoytour"/></h4>
                                    <form name="seDocsForm" method="post" action="controller">
                                        <input type="hidden" name="command" value="see_travel_docs">
                                        <input type="hidden" name="idorder" value="${order.id}">
                                        <input type="hidden" name="iddocs" value="${order.travelDocs.id}">
                                        <button type="submit" class="btn btn-primary">
                                            <fmt:message key="label.seedocs"/>
                                        </button>
                                    </form>
                                    <p class="card-text">
                                            ${order.tour.country}, ${order.tour.tourType.value}<br/>
                                        <fmt:message key="label.startdate"/> ${order.tour.startDate},
                                            ${order.tour.days} <fmt:message key="label.days"/><br/>
                                            ${order.tour.price}
                                        <fmt:message key="icon.currency"/>
                                    </p>
                                    <p class="card-text">
                                            ${order.passport.surname}
                                            ${order.passport.name}
                                    </p>
                                    <form name="finishOrderForm" method="post" action="controller">
                                        <input type="hidden" name="command" value="finish_order">
                                        <input type="hidden" name="idorder" value="${order.id}">
                                        <p><fmt:message key="label.entercomment"/></p>
                                        <p><label>
                                            <textarea rows="10" cols="45" name="comment"></textarea>
                                        </label></p>
                                        <button type="submit" class="btn btn-primary">
                                            <fmt:message key="label.finishorder"/>
                                        </button>
                                    </form>
                                </div>
                            </div>

                        </c:if>
                    </c:forEach>

                </div>
            </div>
        </div>

    </c:if>

</section>

<%@include file="../hottours.jsp" %>

<%@include file="../floatlogout.jsp" %>

<%@include file="../footer.jsp" %>

</body>

</html>