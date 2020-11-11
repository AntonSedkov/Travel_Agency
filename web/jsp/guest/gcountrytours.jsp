<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.countrytours"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body>

    <%@include file="../header.jsp" %>

    <%@include file="../search.jsp" %>

    <section class="country_tours">
        <div class="container-fluid mt-5" style="background-color:lightseagreen">

            <div class="section_tittle text-center">
                <h2 class="p-3"><fmt:message key="label.countrytours"/></h2>
            </div>

            <c:choose>

                <c:when test="${empty sessionScope.toursbycountries}">
                    <div class="section_tittle text-center">
                        <h2 class="p-3"><fmt:message key="currenttours.nothing"/></h2>
                    </div>
                </c:when>

                <c:otherwise>

                    <c:forEach var="countryMapEntry" items="${sessionScope.toursbycountries}">

                        <div class="section_tittle text-center">
                            <h2 class="p-3"><fmt:message key="label.${countryMapEntry.key}"/></h2>
                        </div>

                        <c:choose>
                            <c:when test="${empty countryMapEntry.value}">
                                <div class="section_tittle text-center">
                                    <h2 class="p-3"><fmt:message key="countrytours.nothing"/></h2>
                                </div>
                            </c:when>

                            <c:otherwise>
                                <div class="container p-5">
                                    <div class="card-deck">

                                        <c:forEach var="currentCountryTour" items="${countryMapEntry.value}">

                                            <form name="hotTourForm" method="post" action="controller">

                                                <c:choose>
                                                    <c:when test="${isuser}">
                                                        <input type="hidden" name="command" value="make_order_page"/>
                                                        <input type="hidden" name="idtour" value="${currentCountryTour.id}">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="hidden" name="command" value="login_page"/>
                                                    </c:otherwise>
                                                </c:choose>

                                                <div class="card text-center" style="width: 20rem;">
                                                    <img src="${pageContext.request.contextPath}/pics/tours/${currentCountryTour.imagePath}"
                                                         alt="tour photo" class="card-img-top">
                                                    <div class="card-body">
                                                        <h4 class="card-title"><fmt:message key="label.price"/> ${currentCountryTour.price}
                                                            <fmt:message key="icon.currency"/><br/>
                                                            <fmt:message key="label.discount"/> ${currentCountryTour.discount}<fmt:message
                                                                key="icon.percent"/>
                                                        </h4>
                                                        <p class="card-text"><fmt:message key="label.hotel"/> ${currentCountryTour.hotelName}
                                                                ${currentCountryTour.hotelType.category}<fmt:message key="icon.star"/>
                                                        </p>
                                                        <p class="card-text">
                                                            <fmt:message key="label.tourstart"/> ${currentCountryTour.startDate}<br/>
                                                            <fmt:message key="label.duration"/> ${currentCountryTour.days}
                                                            <fmt:message key="label.days"/>
                                                            <fmt:message key="label.quantityleft"/> ${currentCountryTour.availableQuantity}
                                                            <fmt:message key="label.tours"/><br/>
                                                            <fmt:message key="label.travelby"/> <fmt:message
                                                                key="label.${currentCountryTour.transport.value}"/><br/>
                                                            <fmt:message key="label.tourtype"/> <fmt:message
                                                                key="label.${currentCountryTour.tourType.value}"/>
                                                        </p>
                                                        <p class="card-text"><fmt:message key="description.${currentCountryTour.description}"/></p>
                                                        <button type="submit" class="btn btn-primary"><fmt:message
                                                                key="button.order"/></button>
                                                    </div>
                                                </div>

                                            </form>
                                        </c:forEach>

                                    </div>
                                </div>
                            </c:otherwise>

                        </c:choose>

                    </c:forEach>
                </c:otherwise>

            </c:choose>

        </div>
    </section>

    <%@include file="../gallery.jsp" %>

    <%@include file="../footer.jsp" %>

</body>

</html>