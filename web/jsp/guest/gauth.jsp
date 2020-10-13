<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">
<html lang="${language}">
<head>
    <title><fmt:message key="authpage.title"/></title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>

<form>
    <label for="language"><select id="language" name="language" onchange="submit()">
        <option><fmt:message key="label.language"/></option>
        <option value="en_US" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select></label>
</form>
<h2 class="text-center" style="background-color: fuchsia"><fmt:message key="startpage.authbutton"/></h2>
<div class="container-fluid">
    <form name="loginForm" method="post" action="controller/">
        <input type="hidden" name="command" value="user"/>
        <div class="form-row">
            <div class="col">
                <div class="form-group">
                    <label for="exampleInputAuth">
                        <fmt:message key="authpage.user" var="user"/>
                        ${user}
                    </label>
                    <input type="text" name="user" class="form-control" id="exampleInputAuth"
                           placeholder="${user}" required
                           pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                    <small id="loginHelp" class="form-text text-muted">
                        <fmt:message key="authpage.loginhelp" var="loginhelp"/>
                    </small>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label for="exampleInputAuth">
                        <fmt:message key="authpage.password" var="pass"/>
                        ${pass}
                    </label>
                    <input type="password" name="password"
                           class="form-control" id="exampleInputPass"
                           placeholder="${pass}" required
                           pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                    <small id="passHelp" class="form-text text-muted">
                        <fmt:message key="authpage.passhelp" var="passhelp"/>
                    </small>
                </div>
            </div>
        </div>
        <fmt:message key="button.enter" var="enter"/>
        <input type="submit" class="btn btn-primary float-center"
               value="${enter}">
        <div class="text-uppercase">
            <c:if test="${loginerror}"> <fmt:message key="authpage.loginerror"/></c:if> <br/><br/>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/travel_agency.js"></script>

</body>
</html>