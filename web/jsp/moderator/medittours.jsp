<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.edittours"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightgreen">

<%@include file="../header.jsp" %>

<%@include file="../greeting.jsp" %>

<section class="all_tours_header">
    <div class="container-fluid mt-5" style="background-color:lightseagreen">

        <div class="section_tittle text-center mt-2 mb-2">
            <h2><fmt:message key="label.edittours"/></h2>
        </div>

            <div class="text-center">
                <c:if test="${requestScope.closetour}">
                    <h4 style="color: darkgreen"><fmt:message key="statement.closetoursuccess"/></h4>
                </c:if>
                <c:if test="${requestScope.closetour eq false}">
                    <h4 style="color: red"><fmt:message key="statement.closetourfail"/></h4>
                </c:if>
            </div>

            <div class="text-center">
                <c:if test="${requestScope.hottourgood}">
                    <h4 style="color: darkgreen"><fmt:message key="statement.hottoursuccess"/></h4>
                </c:if>
                <c:if test="${requestScope.hottourgood eq false}">
                    <h4 style="color: red"><fmt:message key="statement.hottourfail"/></h4>
                </c:if>
            </div>

    </div>
</section>

<section class="set_hot_tours">
    <div class="container-fluid mt-5" style="background-color:lightseagreen">

        <div class="section_tittle text-center mt-2 mb-2">
            <h4 style="font-weight: bold"><fmt:message key="button.setdiscount"/></h4>
        </div>

        <c:choose>

            <c:when test="${empty sessionScope.alltours}">
                <fmt:message key="currenttours.nothing"/>
            </c:when>

            <c:otherwise>
                <div class="container p-5">
                    <div class="card-deck">

                        <c:forEach var="concreteTour" items="${sessionScope.alltours}">

                            <form name="hotTourForm" method="post" action="controller">
                                <input type="hidden" name="command" value="set_hot_tour"/>
                                <input type="hidden" name="idtour" value="${concreteTour.id}">

                                <div class="card text-center" style="width: 20rem">
                                    <img src="${pageContext.request.contextPath}/pics/tours/${concreteTour.imagePath}"
                                         alt="tour photo" class="card-img-top">
                                    <div class="card-body">
                                        <h4 class="card-title"><fmt:message key="label.${concreteTour.country}"/></h4>
                                        <h4 class="card-title"><fmt:message key="label.price"/> ${concreteTour.price}
                                            <fmt:message key="icon.currency"/><br/>
                                            <fmt:message key="label.discount"/> ${concreteTour.discount}<fmt:message
                                                key="icon.percent"/></h4>
                                        <p class="card-text"><fmt:message key="label.hotel"/> ${concreteTour.hotelName}
                                                ${concreteTour.hotelType.category}
                                            <fmt:message key="icon.star"/>
                                        </p>
                                        <p class="card-text">
                                            <fmt:message key="label.tourstart"/> ${concreteTour.startDate}<br/>
                                            <fmt:message key="label.duration"/> ${concreteTour.days} <fmt:message
                                                key="label.days"/>
                                            <fmt:message key="label.quantityleft"/> ${concreteTour.availableQuantity}
                                            <fmt:message key="label.tours"/><br/>
                                            <fmt:message key="label.travelby"/> <fmt:message
                                                key="label.${concreteTour.transport.value}"/><br/>
                                            <fmt:message key="label.tourtype"/> <fmt:message
                                                key="label.${concreteTour.tourType.value}"/>
                                        </p>
                                        <p class="card-text"><fmt:message key="description.${concreteTour.description}"/></p>
                                        <p><label>
                                            <input type="text" name="discount"
                                                   placeholder="<fmt:message key="field.moderdiscount"/>"
                                                   pattern="\d{1,2}" required/>
                                        </label></p>
                                        <button type="submit" class="btn btn-primary">
                                            <fmt:message key="button.setdiscount"/></button>
                                    </div>
                                </div>
                            </form>

                        </c:forEach>

                    </div>
                </div>
            </c:otherwise>

        </c:choose>
    </div>
</section>

<section class="close_tours">
    <div class="container-fluid mt-5" style="background-color:lightseagreen">

        <div class="section_tittle text-center mt-2 mb-2">
            <h4 style="font-weight: bold"><fmt:message key="button.closetour"/></h4>
        </div>

        <c:choose>

            <c:when test="${empty sessionScope.alltours}">
                <fmt:message key="currenttours.nothing"/>
            </c:when>

            <c:otherwise>
                <div class="container p-5">
                    <div class="card-deck">

                        <c:forEach var="concreteTour" items="${sessionScope.alltours}">

                            <form name="closeTourForm" method="post" action="controller">
                                <input type="hidden" name="command" value="close_tour"/>
                                <input type="hidden" name="idtour" value="${concreteTour.id}">

                                <div class="card text-center" style="width: 20rem">
                                    <img src="${pageContext.request.contextPath}/pics/tours/${concreteTour.imagePath}"
                                         alt="tour photo" class="card-img-top">
                                    <div class="card-body">
                                        <h4 class="card-title"><fmt:message key="label.${concreteTour.country}"/></h4>
                                        <h4 class="card-title"><fmt:message key="label.price"/> ${concreteTour.price}
                                            <fmt:message key="icon.currency"/><br/>
                                            <fmt:message key="label.discount"/> ${concreteTour.discount}<fmt:message
                                                key="icon.percent"/></h4>
                                        <p class="card-text"><fmt:message key="label.hotel"/> ${concreteTour.hotelName}
                                                ${concreteTour.hotelType.category}
                                            <fmt:message key="icon.star"/>
                                        </p>
                                        <p class="card-text">
                                            <fmt:message key="label.tourstart"/> ${concreteTour.startDate}<br/>
                                            <fmt:message key="label.duration"/> ${concreteTour.days} <fmt:message
                                                key="label.days"/>
                                            <fmt:message key="label.quantityleft"/> ${concreteTour.availableQuantity}
                                            <fmt:message key="label.tours"/><br/>
                                            <fmt:message key="label.travelby"/> <fmt:message
                                                key="label.${concreteTour.transport.value}"/><br/>
                                            <fmt:message key="label.tourtype"/> <fmt:message
                                                key="label.${concreteTour.tourType.value}"/>
                                        </p>
                                        <p class="card-text"><fmt:message key="description.${concreteTour.description}"/></p>
                                        <button type="submit" class="btn btn-primary"><fmt:message
                                                key="button.closetour"/></button>
                                    </div>
                                </div>
                            </form>

                        </c:forEach>

                    </div>
                </div>
            </c:otherwise>

        </c:choose>

    </div>
</section>

<%@include file="../floatlogout.jsp" %>

<%@include file="../footer.jsp" %>

</body>

</html>