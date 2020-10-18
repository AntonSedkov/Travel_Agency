<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${language}">

    <head>
        <title>MODERATOR Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    </head>

    <body style="background: lightgreen">

        <%@include file="../header.jsp" %>

        <div class="container">
            <h3 class="text-center"><fmt:message key="text.greetings"/> ${user}<fmt:message key="text.smileend"/></h3>
        </div>

        <h3 class="font-weight-bold text-center" style="color: #BA4E27">
            Edit Tours</h3>

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
                    </tr>
                </c:forEach>
            </table>
        </div>

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

        <div class="container">
            <button type="submit" class="btn btn-primary float-right">
                <a href="controller?command=logout" style="color: white">
                <fmt:message key="button.logout"/></a>
            </button>
        </div>

        <%@include file="../footer.jsp" %>

    </body>

</html>