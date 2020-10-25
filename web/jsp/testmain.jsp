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

<section class="tour_add">
    <div class="container-fluid text-center">
        <form name="addTourForm" method="post" action="upload/" enctype="multipart/form-data">
            <input type="hidden" name="command" value="add_tour">
            <input type="hidden" name="uploadtarget" value="newtourinfo">
            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="tourType">
                            Tour Type
                        </label>
                        <div id="tourType" aria-required="true">
                            <c:forEach var="currentType" items="${tourtypes}">
                                <input type="radio" name="tourtype" value="${currentType}"
                                       class="form-control" required checked>${currentType}
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="country">
                            Country
                        </label>
                        <input type="text" name="country" class="form-control"
                               id="country" placeholder="Country" required
                               pattern="[A-Za-z]{1,20}"/>
                        <small id="countryHelp" class="form-text text-muted">
                            Country by Latin 1-20 chars
                        </small>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="hotelname">
                            Hotel name
                        </label>
                        <input type="text" name="hotelname" class="form-control"
                               id="hotelname" placeholder="Hotel Name" required
                               pattern="[A-Za-z]{1,20}"/>
                        <small id="hotelHelp" class="form-text text-muted">
                            Hotel name by Latin 1-20 chars
                        </small>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="hotelstars">
                            Hotel Stars
                        </label>
                        <div id="hotelstars" aria-required="true">
                            <div><input type="radio" name="hotelstars" value="hostel" class="form-control">Hostel</div>
                            <div><input type="radio" name="hotelstars" value="three" class="form-control" checked>3*
                            </div>
                            <div><input type="radio" name="hotelstars" value="four" class="form-control">4*</div>
                            <div><input type="radio" name="hotelstars" value="five" class="form-control">5*</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label>Transport</label>
                        <input type="radio" name="transport" value="bus" class="form-control">Bus
                        <input type="radio" name="transport" value="airplane" class="form-control" checked>Airplane
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="startdate">
                            Start Date
                        </label>
                        <input type="date" name="startdate" class="form-control"
                               id="startdate" placeholder="Start Date" min="1000-01-01" required/>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="tourdays">
                            Days
                        </label>
                        <input type="text" name="tourdays" class="form-control"
                               id="tourdays" placeholder="Tour Days" required
                               pattern="\d{1,3}"/>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="tourprice">
                            Price
                        </label>
                        <input type="text" name="tourprice" class="form-control"
                               id="tourprice" placeholder="Tour Price &yen;" required
                               pattern="\d{1,7}"/>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="quantitytours">
                            Quantity tours
                        </label>
                        <input type="text" name="quantitytours" class="form-control"
                               id="quantitytours" placeholder="Quantity tours" required
                               pattern="\d{1,3}"/>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="description">
                            Description link
                        </label>
                        <input type="text" name="description" class="form-control"
                               id="description" placeholder="Description link" required
                               pattern="[A-Za-z]{1,20}"/>
                        <small id="descriptionHelp" class="form-text text-muted">
                            Description link by Latin 1-20 chars
                        </small>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="description">
                            Image
                        </label>
                        <input type="file" name="imagecontent" accept="image/jpeg" required>
                    </div>
                </div>
            </div>


            <input type="submit" class="btn btn-primary float-center"
                   value="SUBMIT">
        </form>

        <c:if test="${(not empty uploadresult) and (uploadresult.equals(true)) and (createtourerror.equals(false))}">
            Image has been upload to server successfully and New Tour is created.
        </c:if>
        <c:if test="${(not empty uploadresult) and (ulpoadresult.equals(true)) and (createtourerror.equals(true))}">
            Image has been upload to server successfully but New Tour isn't created.
        </c:if>
        <c:if test="${(not empty uploadresult) and (ulpoadresult.equals(false))}">
            Image hasn't been upload to server and New Tour isn't created
        </c:if>
    </div>
</section>
<%--
<form action="/upload" enctype="multipart/form-data" method="post"
      autocomplete="off">

    <input type="text"  required maxlength="10">

    <input type="file" name="content" accept="image/jpeg" required>

    <textarea name="description" cols="30" rows="7" required maxlength="1000"></textarea>


    <input type="hidden" name="commandName" value="add_tattoo_command">--%>


<%@include file="footer.jsp" %>


<script>
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1;
    var yyyy = today.getFullYear();
    if (dd < 10) {
        dd = '0' + dd
    }
    if (mm < 10) {
        mm = '0' + mm
    }
    today = yyyy + '-' + mm + '-' + dd;
    document.getElementById("startdate").setAttribute("min", today);
</script>
</body>
</html>
