<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<body>


    <section class="all_tours_section">
        <div class="container-fluid mt-5" style="background-color:lightseagreen">

            <div class="section_tittle text-center">
                <h2 class="p-3"><fmt:message key="label.allcurrenttours"/></h2>
            </div>

            <c:choose>

                <c:when test="${empty alltours}">
                    <fmt:message key="label.nocurrenttours"/>
                </c:when>

                <c:otherwise>
                    <div class="container p-5">
                        <div class="card-deck">

                            <c:forEach var="concreteTour" items="${alltours}">

                                <form name="hotTourForm" method="post" action="controller/">
                                    <input type="hidden" name="command" value="make_order"/>
                                    <input type="hidden" name="id_tour" value="${concreteTour.id}">

                                    <div class="card text-center" style="width: 20rem;">
                                        <img src="${pageContext.request.contextPath}/pics/tours/${concreteTour.imagePath}"
                                             alt="tour photo" class="card-img-top">
                                        <div class="card-body">
                                            <h4 class="card-title"><fmt:message key="label.${concreteTour.country}"/></h4>
                                            <h4 class="card-title"><fmt:message key="label.price"/> ${concreteTour.price}
                                                <fmt:message key="icon.currency"/>
                                            </h4>
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
                                                <%----%> <p class="card-text">${concreteTour.description}</p>
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

        </div>
    </section>


    <br/>
    <br/>
    <h3 class="font-weight-bold text-center" style="color: #BA4E27">Edit Tours</h3>

    <div class="contaner">
        <table>
            <c:forEach var="tour" items="${tours}">
                <tr>
                    <td><c:out value="${tour.id}"/></td>
                    <td><c:out value="${tour.tourType}"/></td>
                    <td><c:out value="${tour.country}"/></td>
                    <td><c:out value="${tour.hotelName}"/></td>
                    <td><c:out value="${tour.hotelType}"/></td>
                    <td><c:out value="${tour.transport}"/></td>
                    <td><c:out value="${tour.startDate}"/></td>
                    <td><c:out value="${tour.days}"/></td>
                    <td><c:out value="${tour.price}"/></td>
                    <td><c:out value="${tour.availableQuantity}"/></td>
                    <td><c:out value="${tour.description}"/></td>
                    <td><c:out value="${tour.imagePath}"/></td>
                    <td>
                        Edit
                    </td>
                    <td>
                        Delete
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="container">
            Add
        </div>

    </div>



</body>