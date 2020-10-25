<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${language}">

<head>
    <title><fmt:message key="startpage.title"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background-color: burlywood">

    <%@include file="../header.jsp" %>

    <%@include file="../search.jsp" %>

    <div class="container-fluid">

        <div class="container">
            <div class="row text-center justify-content-center m-3 p-1">
                <h3><fmt:message key="startpage.maincontext"/></h3>
            </div>
        </div>

        <div class="col text-center">
            <button type="submit" class="btn btn-primary m-2">
                <a href="controller?command=change_page&targetpage=path.guest.auth" style="color: white">${authVal}</a>
            </button>
            <button type="submit" class="btn btn-primary m-2">
                <a href="controller?command=change_page&targetpage=path.guest.reg" style="color: white">${regVal}</a>
            </button>
        </div>

    </div>

    <%--TEST FUNCTIONS BUTTON--%>
    <div class="col text-center">
        <button type="submit" class="btn btn-primary m-2">
            <a href="controller?command=change_page&targetpage=path.test" style="color: white">Test Page</a></button>
    </div>
    <%----%>

    <%@include file="../hottours.jsp" %>

    <%@include file="../gallery.jsp" %>

    <%--
    <div class="contaner p-5">
        <div class="row">

            <div class="col">
                <div class="embed-responsive embed-responsive-16by9">
                    <iframe src="https://www.youtube.com/embed/Tc0LU7y3btU"
                            class="embed-responsive-item" allowfullscreen></iframe>
                </div>
            </div>

            <div class="col">
                <div class="embed-responsive embed-responsive-16by9">
                    <iframe src="https://www.youtube.com/embed/QNdYybI3Pgs"
                            class="embed-responsive-item" allowfullscreen></iframe>
                </div>
            </div>

            <div class="col">
                <div class="embed-responsive embed-responsive-16by9">
                    <iframe src="https://www.youtube.com/embed/yZc9vCXLPAo"
                            class="embed-responsive-item" allowfullscreen></iframe>
                </div>
            </div>

        </div>
    </div>
    --%>

    <%@include file="../footer.jsp" %>

</body>

</html>