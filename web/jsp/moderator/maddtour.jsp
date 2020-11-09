<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.addtour"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightgreen">

    <%@include file="../header.jsp" %>

    <%@include file="../greeting.jsp" %>

    <section class="moder_add_tour">

        <div class="section_tittle text-center mt-2 mb-2">
            <h2><fmt:message key="label.addtour"/></h2>
        </div>

        <div class="container-fluid text-center pb-2" style="width:50%; background: lemonchiffon">

            <c:choose>
                <c:when test="${requestScope.uploadresult and (requestScope.createtour)}">
                    <p style="color: green"><fmt:message key="statement.imagetourdone"/></p>
                </c:when>
                <c:when test="${requestScope.uploadresult and not requestScope.createtour}">
                    <p style="color: orange"><fmt:message key="statement.imagedonetourfail"/></p>
                </c:when>
                <c:when test="${requestScope.uploadresult eq false}">
                    <p style="color: red"><fmt:message key="statement.imagetourfail"/></p>
                </c:when>
            </c:choose>

            <form method="post" action="upload" enctype="multipart/form-data">
                <input type="hidden" name="command" value="add_tour">
                <input type="hidden" name="uploadtarget" value="newtourinfo">

                <div>
                    <h4 style="font-weight: bold"><fmt:message key="label.tourtype"/></h4>
                        <c:forEach var="currentType" items="${sessionScope.tourtypes}">
                            <input type="radio" name="tourtype" id="${currentType}" value="${currentType}"
                                   class="form-control" required checked>
                            <label for="${currentType}"><fmt:message key="label.${currentType}"/></label>
                        </c:forEach>
                </div>

                <div>
                    <h4 style="font-weight: bold"><fmt:message key="label.country"/></h4>
                    <label>
                        <input type="text" name="country" class="form-control"
                           placeholder="<fmt:message key='label.country'/>" required
                           pattern="[A-Za-z]{1,20}"/>
                    </label>
                    <small class="form-text text-muted">
                        <fmt:message key="label.stringhelp"/>
                    </small>
                </div>

                <div>
                    <h4 style="font-weight: bold"><fmt:message key="label.hotelname"/></h4>
                    <label>
                        <input type="text" name="hotelname" class="form-control"
                           placeholder="<fmt:message key="label.hotelname"/>" required
                           pattern="[A-Za-z]{1,20}"/>
                    </label>
                    <small class="form-text text-muted">
                        <fmt:message key="label.stringhelp"/>
                    </small>
                </div>

                <div>
                    <h4 style="font-weight: bold"><fmt:message key="label.hotelstars"/></h4>
                    <div aria-required="true">
                        <input type="radio" id="zero" name="hotelstars" value="hostel" class="form-control">
                        <label for="zero"><fmt:message key="label.hostel"/></label>
                        <input type="radio" id="three" name="hotelstars" value="three" class="form-control" checked>
                        <label for="three"><fmt:message key="label.three"/></label>
                        <input type="radio" id="four" name="hotelstars" value="four" class="form-control">
                        <label for="four"><fmt:message key="label.four"/></label>
                        <input type="radio" id="five" name="hotelstars" value="five" class="form-control">
                        <label for="five"><fmt:message key="label.five"/></label>
                    </div>
                </div>

                <div>
                    <h4 style="font-weight: bold"><fmt:message key="label.transport"/></h4>
                    <input type="radio" id="air" name="transport" value="airplane" class="form-control" checked>
                    <label for="air"><fmt:message key="label.Airplane"/></label>
                    <input type="radio" id="bus" name="transport" value="bus" class="form-control">
                    <label for="bus"><fmt:message key="label.Bus"/></label>
                </div>

                <div>
                    <h4 style="font-weight: bold"><fmt:message key="label.startdate"/></h4>
                    <label>
                    <input type="date" name="startdate" class="form-control"
                           id="startdate" placeholder="<fmt:message key="label.startdate"/>"
                           min="1000-01-01" required/>
                    </label>
                </div>

                <div>
                    <h4 style="font-weight: bold"><fmt:message key="label.tourdays"/></h4>
                    <label>
                    <input type="text" name="tourdays" class="form-control"
                           placeholder="<fmt:message key="label.tourdays"/>"
                           required pattern="\d{1,3}"/>
                    </label>
                    <small class="form-text text-muted">
                        <fmt:message key="label.threedigitshelp"/>
                    </small>
                </div>

                <div>
                    <h4 style="font-weight: bold"><fmt:message key="label.price"/></h4>
                    <label>
                    <input type="text" name="tourprice" class="form-control"
                           placeholder="<fmt:message key="label.price"/> <fmt:message key="icon.currency"/>"
                           required pattern="\d{1,7}"/>
                    </label>
                    <small class="form-text text-muted">
                        <fmt:message key="label.pricehelp"/>
                    </small>
                </div>

                <div>
                    <h4 style="font-weight: bold"><fmt:message key="label.quantitytours"/></h4>
                    <label>
                    <input type="text" name="quantitytours" class="form-control"
                           placeholder="<fmt:message key="label.quantitytours"/>"
                           required pattern="\d{1,3}"/>
                    </label>
                    <small class="form-text text-muted">
                        <fmt:message key="label.threedigitshelp"/>
                    </small>
                </div>

                <div>
                    <h4 style="font-weight: bold"><fmt:message key="label.description"/></h4>
                    <label>
                    <input type="text" name="description" class="form-control"
                           placeholder="<fmt:message key="label.description"/>"
                           required pattern="[A-Za-z]{1,20}"/>
                    </label>
                    <small class="form-text text-muted">
                        <fmt:message key="label.threedigitshelp"/>
                    </small>
                </div>

                <div>
                    <h4 style="font-weight: bold"><fmt:message key="label.image"/></h4>
                    <input type="file" name="imagecontent" accept="image/jpeg" required>
                    <small class="form-text text-muted">
                        <fmt:message key="statement.imagehelp"/>
                    </small
                </div>

                <br/>

                <input type="submit" class="btn btn-primary float-center"
                       value="<fmt:message key="button.submit"/>">

            </form>

        </div>
    </section>

    <%@include file="../floatlogout.jsp" %>

    <%@include file="../footer.jsp" %>

    <script src="${pageContext.request.contextPath}/js/addtour.js"></script>
</body>

</html>