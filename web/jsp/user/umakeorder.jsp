<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${language}">

<head>
    <title><fmt:message key="label.makeorder"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightsteelblue">

    <%@include file="../header.jsp" %>

    <%@include file="../search.jsp" %>

    <%@include file="../greeting.jsp" %>

    <section class="make_order ">
        <div class="container text-center" style="max-width: 35%; background: lightseagreen">

            <form name="makeOrderForm" method="post" action="controller">
                <input type="hidden" name="command" value="make_order"/>

                <div class="form-row">
                    <div class="col form-froup">

                        <h4 class="card-title"><fmt:message key="label.makeorder"/></h4>
                        <br/>
                        <h4 class="card-title"><fmt:message key="label.tour"/></h4>

                        <c:choose>

                            <c:when test="${not empty concretetour}">
                                <input type="hidden" name="idtour" value="${concretetour.id}"/>
                                <p>
                                        ${concretetour.country}, ${concretetour.tourType.value},
                                        ${concretetour.price}<fmt:message key="icon.currency"/>,
                                    <fmt:message key="label.startdate"/> ${concretetour.startDate},
                                        ${concretetour.days} <fmt:message key="label.days"/>
                                </p>
                            </c:when>

                            <c:otherwise>
                                <p>
                                    <select class="nc_select" name="idtour" required>
                                        <option selected><fmt:message key="label.choosetour"/></option>
                                        <c:forEach var="concreteTour" items="${alltours}">
                                            <option value="${concreteTour.id}">
                                                    ${concreteTour.country}, ${concreteTour.tourType.value},
                                                    ${concreteTour.price}<fmt:message key="icon.currency"/>,
                                                <fmt:message key="label.startdate"/> ${concreteTour.startDate},
                                                    ${concreteTour.days} <fmt:message key="label.days"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                </p>
                            </c:otherwise>
                        </c:choose>

                        <h4 class="card-title"><fmt:message key="label.passport"/></h4>

                        <p class="card-text">
                            <select class="nc_select" name="idpassport" required>
                                <option selected><fmt:message key="label.choosepassport"/></option>
                                <c:forEach var="concretePassport" items="${passports}">
                                    <option value="${concretePassport.id}">
                                            ${concretePassport.surname}, ${concretePassport.name},
                                            ${concretePassport.passportNumber}
                                    </option>
                                </c:forEach>
                            </select>
                        </p>

                        <button type="submit" class="btn btn-primary"><fmt:message key="button.order"/></button>
                        <br/>

                        <c:if test="${createorder}">
                            <p class="card-text" style="color: darkgreen; font-weight: bold">
                                <fmt:message key="statement.createordersuccess"/>
                            </p>
                        </c:if>

                        <c:if test="${not empty createorder and not createorder}">
                            <p class="card-text" style="color: darkred; font-weight: bold">
                                <fmt:message key="statement.createorderfail"/>
                            </p>
                        </c:if>

                    </div>
                </div>

            </form>
        </div>
    </section>

    <%@include file="../hottours.jsp" %>

    <%@include file="../floatlogout.jsp" %>

    <%@include file="../footer.jsp" %>

</body>

</html>