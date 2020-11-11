<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.toursbytype"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body>

    <%@include file="../header.jsp" %>

    <%@include file="../search.jsp" %>

    <section class="country_tours">
    <div class="container-fluid mt-5" style="background-color:lightseagreen">

        <div class="section_tittle text-center">
            <h2 class="p-3"><fmt:message key="label.toursbytype"/></h2>
        </div>

        <c:choose>

            <c:when test="${empty sessionScope.toursbytypes}">
                <div class="section_tittle text-center">
                    <h2 class="p-3"><fmt:message key="currenttours.nothing"/></h2>
                </div>
            </c:when>

            <c:otherwise>

                <c:forEach var="typeMapEntry" items="${sessionScope.toursbytypes}">

                    <div class="section_tittle text-center">
                        <h2 class="p-3"> <fmt:message key="label.${typeMapEntry.key}"/></h2>
                    </div>

                    <c:choose>
                        <c:when test="${empty typeMapEntry.value}">
                            <div class="section_tittle text-center">
                                <h2 class="p-3"><fmt:message key="typetours.nothing"/></h2>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="container p-5">
                                <div class="card-deck">

                                    <c:forEach var="currentTypeTour" items="${typeMapEntry.value}">

                                        <form name="hotTourForm" method="post" action="controller">

                                            <c:choose>
                                                <c:when test="${isuser}">
                                                    <input type="hidden" name="command" value="make_order_page"/>
                                                    <input type="hidden" name="idtour" value="${currentTypeTour.id}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="hidden" name="command" value="login_page"/>
                                                </c:otherwise>
                                            </c:choose>

                                            <div class="card text-center" style="width: 20rem;">
                                                <img src="${pageContext.request.contextPath}/pics/tours/${currentTypeTour.imagePath}"
                                                     alt="tour photo" class="card-img-top">
                                                <div class="card-body">
                                                    <h4 class="card-title"><fmt:message key="label.${currentTypeTour.country}"/></h4>
                                                    <h4 class="card-title"><fmt:message key="label.price"/> ${currentTypeTour.price}
                                                        <fmt:message key="icon.currency"/><br/>
                                                        <fmt:message key="label.discount"/> ${currentTypeTour.discount}<fmt:message
                                                            key="icon.percent"/>
                                                    </h4>
                                                    <p class="card-text"><fmt:message key="label.hotel"/> ${currentTypeTour.hotelName}
                                                            ${currentTypeTour.hotelType.category}<fmt:message key="icon.star"/>
                                                    </p>
                                                    <p class="card-text">
                                                        <fmt:message key="label.tourstart"/> ${currentTypeTour.startDate}<br/>
                                                        <fmt:message key="label.duration"/> ${currentTypeTour.days}
                                                        <fmt:message key="label.days"/>
                                                        <fmt:message key="label.quantityleft"/> ${currentTypeTour.availableQuantity}
                                                        <fmt:message key="label.tours"/><br/>
                                                        <fmt:message key="label.travelby"/> <fmt:message key="label.${currentTypeTour.transport.value}"/>
                                                    </p>
                                                    <p class="card-text"><fmt:message key="description.${currentTypeTour.description}"/></p>
                                                    <button type="submit" class="btn btn-primary"><fmt:message key="button.order"/></button>
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