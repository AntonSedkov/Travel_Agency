<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="udt" uri="/WEB-INF/tdl/custom.tdl" %>--%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script> document.addEventListener('keydown', (event) => {
        if (event.keyCode === 116) event.preventDefault();
    })</script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark sticky-top" style="background-color: cornflowerblue">
    <a href="controller?command=change_page&targetpage=path.guest.home" class="navbar-brand">
        <img src="${pageContext.request.contextPath}/pics/img_logo.jpg" width="30" height="30" alt="logo">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle-navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a href="controller?command=change_page&targetpage=path.guest.home" class="nav-link">
                    <fmt:message key="startpage.homebutton"/></a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link" data-toggle="modal" data-target="#authModal">
                    <fmt:message key="startpage.authbutton" var="authVal"/> ${authVal}</a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link" data-toggle="modal" data-target="#regModal">
                    <fmt:message key="startpage.registerbutton" var="regVal"/> ${regVal}</a>
            </li>
            <li class="nav-item">
                <a href="controller?command=change_page&targetpage=path.guest.about" class="nav-link">
                    <fmt:message key="startpage.aboutusbutton"/></a>
            </li>

        </ul>

        <ul class="navbar-nav mr-auto">
       <%--     <udt:userdata/>--%>
        </ul>


        <form class="form-inline m-2 my-lg-0">
            <label for="language">
                <select class="btn btn-outline-success my-2 my-sm-0"
                        style="background-color: lightgreen"
                        id="language" onchange="location=value">
                    <option selected><fmt:message key="label.language"/></option>
                    <option value="controller?command=change_lang&language=en_En"><fmt:message
                            key="label.english"/></option>
                    <option value="controller?command=change_lang&language=ru_RU"><fmt:message
                            key="label.russian"/></option>
                </select></label>
        </form>


        <form class="form-inline my-2 my-lg-0" action="http://www.google.com/search" method="get">
            <fmt:message key="button.search" var="search"/>
            <input type="text" name="q" class="form-control mr-sm-2" placeholder="${search}" aria-label="${search}">
            <input type="submit" class="btn btn-outline-success my-2 my-sm-0" style="background-color: lightgreen"
                   value="${search}">
        </form>
    </div>
</nav>



</body>
</html>