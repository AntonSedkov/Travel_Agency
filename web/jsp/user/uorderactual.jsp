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

    <p style="text-align: center; font-weight: bold; font-size: 20pt; color: darkred">
        <c:if test="${requestScope.shortofmoney}"><fmt:message key="statement.nomoney"/></c:if>
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

    <c:if test="${requestScope.finishorder}">
        <p style="color: darkgreen; font-weight: bold; text-align: center">
            <fmt:message key="statement.finishordersuccess"/>
        </p>
    </c:if>

    <c:if test="${requestScope.finishorder eq false}">
        <p style="color: darkred; font-weight: bold; text-align: center">
            <fmt:message key="statement.finishorderfail"/>
        </p>
    </c:if>

    <c:if test="${requestScope.payorder}">
        <p style="color: darkgreen; font-weight: bold; text-align: center">
            <fmt:message key="statement.payordersuccess"/>
        </p>
    </c:if>

    <c:if test="${requestScope.payorder eq false}">
        <p style="color: darkred; font-weight: bold; text-align: center">
            <fmt:message key="statement.payorderfail"/>
        </p>
    </c:if>

    <c:if test="${not empty sessionScope.orders}">

        <div class="user_added_docs_state">
            <div class="container p-5">
                <div class="card-deck">

                    <c:forEach items="${sessionScope.orders}" var="order">
                        <c:if test="${order.orderState.value.equals('added_docs')}">

                            <form name="seDocsForm" method="post" action="controller">
                                <input type="hidden" name="command" value="see_travel_docs">
                                <input type="hidden" name="idorder" value="${order.id}">
                                <input type="hidden" name="iddocs" value="${order.travelDocs.id}">

                                <div class="card text-center" style="width: 30rem; background: lightskyblue">
                                    <div class="card-body">
                                        <h4><fmt:message key="label.enjoytour"/></h4>

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
                                        <button type="submit" class="btn btn-primary">
                                            <fmt:message key="label.seedocs"/>
                                        </button>
                                    </div>
                                </div>
                            </form>

                            <form name="finishOrderForm" method="post" action="controller">
                                <input type="hidden" name="command" value="finish_order">
                                <input type="hidden" name="idorder" value="${order.id}">

                                <div class="card text-center" style="width: 30rem; background: lightskyblue">
                                    <div class="card-body">
                                        <h4><fmt:message key="label.enjoytour"/></h4>

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
                                        <p><fmt:message key="field.entercomment"/></p>
                                        <p><label>
                                            <textarea rows="5" cols="55" name="comment"></textarea>
                                        </label></p>
                                        <button type="submit" class="btn btn-success">
                                            <fmt:message key="label.finishorder"/>
                                        </button>
                                    </div>
                                </div>
                            </form>

                        </c:if>
                    </c:forEach>

                </div>
            </div>
        </div>

        <div class="user_confirmed_state">
            <div class="container p-5">
                <div class="card-deck">
                    <c:if test="${not empty sessionScope.orderstopaywithsumtopay}">
                        <c:forEach items="${sessionScope.orderstopaywithsumtopay}" var="current">

                            <form name="cancelOrderConfirmedForm" method="post" action="controller">
                                <input type="hidden" name="command" value="cancel_order">
                                <input type="hidden" name="idorder" value="${current.key.id}">

                                <div class="card text-center" style="width: 30rem; background: lightseagreen">
                                    <div class="card-body">
                                        <h4><fmt:message key="label.wpayment"/></h4>
                                        <h4><fmt:message key="label.price"/> ${current.key.tour.price}
                                            <fmt:message key="icon.currency"/></h4>
                                        <h4><fmt:message key="label.sumtopay"/> ${current.value}
                                            <fmt:message key="icon.currency"/></h4>
                                        <p class="card-text">
                                                ${current.key.tour.country}, ${current.key.tour.tourType.value}<br/>
                                            <fmt:message key="label.startdate"/> ${current.key.tour.startDate},
                                                ${current.key.tour.days} <fmt:message key="label.days"/>
                                        </p>
                                        <p class="card-text">
                                                ${current.key.passport.surname}
                                                ${current.key.passport.name}
                                        </p>

                                        <button type="submit" class="btn btn-danger">
                                            <fmt:message key="label.cancelorder"/>
                                        </button>

                                    </div>
                                </div>
                            </form>

                            <form name="payOrderForm" method="post" action="controller">
                                <input type="hidden" name="command" value="change_state"/>
                                <input type="hidden" name="targetstate" value="paid"/>
                                <input type="hidden" name="idorder" value="${current.key.id}">
                                <input type="hidden" name="sumtopay" value="${current.value}">

                                <div class="card text-center" style="width: 30rem; background: lightseagreen">
                                    <div class="card-body">
                                        <h4><fmt:message key="label.wpayment"/></h4>
                                        <h4><fmt:message key="label.price"/> ${current.key.tour.price}
                                            <fmt:message key="icon.currency"/></h4>
                                        <h4><fmt:message key="label.sumtopay"/> ${current.value}
                                            <fmt:message key="icon.currency"/></h4>
                                        <p class="card-text">
                                                ${current.key.tour.country}, ${current.key.tour.tourType.value}<br/>
                                            <fmt:message key="label.startdate"/> ${current.key.tour.startDate},
                                                ${current.key.tour.days} <fmt:message key="label.days"/>
                                        </p>
                                        <p class="card-text">
                                                ${current.key.passport.surname}
                                                ${current.key.passport.name}
                                        </p>

                                        <button type="submit" class="btn btn-success">
                                            <fmt:message key="label.payorder"/>
                                        </button>

                                    </div>
                                </div>
                            </form>

                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="user_paid_state">
            <div class="container p-5">
                <div class="card-deck">

                    <c:forEach items="${sessionScope.orders}" var="order">
                        <c:if test="${order.orderState.value.equals('paid')}">

                            <form>
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
                            </form>

                        </c:if>
                    </c:forEach>

                </div>
            </div>
        </div>

        <div class="user_new_state">
            <div class="container p-5">
                <div class="card-deck">

                    <c:forEach items="${sessionScope.orders}" var="order">
                        <c:if test="${order.orderState.value.equals('new')}">

                            <form name="cancelNewOrderForm" method="post" action="controller">
                                <input type="hidden" name="command" value="cancel_order">
                                <input type="hidden" name="idorder" value="${order.id}">

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

                                        <button type="submit" class="btn btn-danger">
                                            <fmt:message key="label.cancelorder"/>
                                        </button>

                                    </div>
                                </div>
                            </form>

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