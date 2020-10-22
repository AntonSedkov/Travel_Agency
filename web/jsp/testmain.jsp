<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css" type="text/css">

<html lang="${language}">

<head>
    <title>Test new Functions</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

</head>
<body>

<%@include file="header.jsp" %>

    <table>
        <c:forEach var="concreteTour" items="${hottours}">
            <tr>
                <td>${concreteTour.id}</td>
                <td>${concreteTour.tourType}</td>
                <td>${concreteTour.country}</td>
                <td>${concreteTour.hotelName}</td>
                <td>${concreteTour.hotelType}</td>
                <td>${concreteTour.transport}</td>
                <td>${concreteTour.startDate}</td>
                <td>${concreteTour.days}</td>
                <td>${concreteTour.price}</td>
                <td>${concreteTour.availableQuantity}</td>
                <td>${concreteTour.description}</td>
                <td>${concreteTour.imagePath}</td>
                <td>${concreteTour.discount}</td>
            </tr>
        </c:forEach>
    </table>


<%@include file="footer.jsp" %>


</body>
</html>
