<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<body>

<section class="hot_tour">
    <div class="container-fluid mt-3" style="background-color:lightseagreen">

        <div class="section_tittle text-center">
            <h2><fmt:message key="label.offers"/></h2>
        </div>

        <p style="text-align: center; font-weight: bold; font-size: 24pt">
            <c:if test="${(not empty requestScope.hottoursnothing) and (requestScope.hottoursnothing)}">
                <fmt:message key="hottours.nothing"/></c:if>
        </p>

        <div class="container p-5">
            <div class="card-deck">

                <c:forEach var="concreteTour" items="${sessionScope.hottours}">

                    <form name="hotTourForm" method="post" action="controller">
                        <c:choose>
                            <c:when test="${isuser}">
                                <input type="hidden" name="command" value="make_order_page"/>
                                <input type="hidden" name="idtour" value="${concreteTour.id}">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="command" value="login_page"/>
                            </c:otherwise>
                        </c:choose>

                        <div class="card text-center" style="width: 20rem;">
                            <img src="${pageContext.request.contextPath}/pics/tours/${concreteTour.imagePath}"
                                 alt="tour photo" class="card-img-top">
                            <div class="card-body">
                                <h4 class="card-title"><fmt:message key="label.${concreteTour.country}"/></h4>
                                <h4 class="card-title"><fmt:message key="label.price"/> ${concreteTour.price}
                                    <fmt:message key="icon.currency"/><br/>
                                    <fmt:message key="label.discount"/> ${concreteTour.discount}
                                    <fmt:message key="icon.percent"/></h4>
                                <p class="card-text"><fmt:message key="label.hotel"/> ${concreteTour.hotelName}
                                        ${concreteTour.hotelType.category}
                                    <fmt:message key="icon.star"/>
                                </p>
                                <p class="card-text">
                                    <fmt:message key="label.tourstart"/> ${concreteTour.startDate}<br/>
                                    <fmt:message key="label.duration"/> ${concreteTour.days}
                                    <fmt:message key="label.days"/>
                                    <fmt:message key="label.quantityleft"/> ${concreteTour.availableQuantity}
                                    <fmt:message key="label.tours"/><br/>
                                    <fmt:message key="label.travelby"/>
                                    <fmt:message key="label.${concreteTour.transport.value}"/><br/>
                                    <fmt:message key="label.tourtype"/>
                                    <fmt:message key="label.${concreteTour.tourType.value}"/>
                                </p>
                                <p class="card-text"><fmt:message key="description.${concreteTour.description}"/></p>
                                <button type="submit" class="btn btn-primary"><fmt:message key="button.order"/></button>
                            </div>
                        </div>

                    </form>

                </c:forEach>

            </div>
        </div>
    </div>
</section>

</body>