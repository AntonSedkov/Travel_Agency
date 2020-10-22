<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<body>

<section class="hot_tour">
    <div class="container-fluid mt-5" style="background-color:lightseagreen">
        <div class="section_tittle text-center">
            <h2><fmt:message key="title.offers"/></h2>
        </div>
        <p>
            <c:if test="${hottoursnothing}"><fmt:message key="hottours.nothing"/></c:if>
        </p>
        <div class="container p-5">
            <div class="card-deck">

                <c:forEach var="concreteTour" items="${hottours}">
                    <div class="card text-center" style="width: 20rem;">
                        <img src="${pageContext.request.contextPath}/pics/tours/${concreteTour.imagePath}" alt="tour photo"
                             class="card-img-top">
                        <div class="card-body">
                            <h4 class="card-title"><fmt:message key="label.${concreteTour.country}"/></h4>
                            <h4 class="card-title"><fmt:message key="label.price"/> ${concreteTour.price}<fmt:message key="icon.currency"/><br/>
                                <fmt:message key="label.discount"/> ${concreteTour.discount}<fmt:message key="icon.percent"/></h4>
                            <p class="card-text"><fmt:message key="label.hotel"/> ${concreteTour.hotelName}
                                    ${concreteTour.hotelType.category}<fmt:message key="icon.star"/>
                            </p>
                            <p class="card-text">
                                <fmt:message key="label.tourstart"/> ${concreteTour.startDate}<br/>
                                <fmt:message key="label.duration"/> ${concreteTour.days} <fmt:message key="label.days"/>
                                <fmt:message key="label.quantityleft"/> ${concreteTour.availableQuantity} <fmt:message key="label.tours"/><br/>
                                <fmt:message key="label.travelby"/> <fmt:message key="label.${concreteTour.transport.value}"/><br/>
                                <fmt:message key="label.tourtype"/> <fmt:message key="label.${concreteTour.tourType.value}"/>
                            </p>
    <%----%>                <p class="card-text">${concreteTour.description}</p>
    <%----%>                <a href="#" class="btn btn-primary"><fmt:message key="button.submit"/></a>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
</section>

</body>