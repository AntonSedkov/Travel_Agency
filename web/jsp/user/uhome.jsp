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
        <title>Dear traveler, welcome</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    </head>

    <body>

        <%@include file="../header.jsp" %>

        <div class="login-block">
            <div class="container">
                <h3 class="text-center">Welcome!!!</h3>
                <br/>
                <h2 class="text-center">${user}, hello!</h2>
                <br/>
                <h2 class="text-center">${login}, Sun rise hello!</h2>
                <br/>

                <h2 class="text-center">${email}, hello!</h2>
                <br/>
                <h2 class="text-center">${role}, hello!</h2>
                <br/>
                <h2 class="text-center">${URL}, hello!</h2>
                <br/>
                <h2 class="text-center">${URI}, hello!</h2>
                <br/>
                <div class="copy-text">
                    Created with <i class="fa fa-heart"></i> from Antonius :)
                </div>
                <div>
                    <a href="controller?command=logout" class="btn btn-login float-right">Logout</a>
                </div>
            </div>

            <br/>

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

        <%@include file="../footer.jsp" %>

    </body>

</html>