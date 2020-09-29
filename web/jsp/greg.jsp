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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ta_login.css" type="text/css">
<html lang="${language}">
<head>
    <title><fmt:message key="regpage.title"/></title>
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
<h2 class="text-center" style="background-color: darkmagenta"><fmt:message key="startpage.registerbutton"/></h2>


<div class="container-fluid">
<form name="registerForm" method="post" action="controller/">
<input type="hidden" name="command" value="register">
<div class="form-row">
<div class="col">
<div class="form-froup">
<label for="exampleInputReg">
    <fmt:message key="authpage.login" var="login"/>
    ${login}
</label>
<input type="text" name="login" class="form-control" id="exampleInputReg"
       placeholder="${login}" required
       pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
<small id="loginHelpReg" class="form-text text-muted">
<fmt:message key="authpage.loginhelp"/>
    </small>
    </div>
    </div>
    <div class="col">
    <div class="form-froup">
    <label for="exampleInputReg">
    <fmt:message key="authpage.password" var="pass"/>
    ${pass}
    </label>
    <input type="password" name="password" class="form-control"
    id="exampleInputPassReg"
    placeholder="${pass}" required
    pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
    <small id="passHelpReg" class="form-text text-muted">
    <fmt:message key="authpage.passhelp"/>
        </small>
        </div>
        </div>
        <div class="col">
        <div class="form-froup">
        <label for="exampleInputReg">
        <fmt:message key="regpage.email" var="emailVal"/>
        ${emailVal}
        </label>
        <input type="email" name="email" class="form-control" id="exampleInputEmail"
        placeholder="${emailVal}" required
        pattern="[\w-\.\+!#$%&’*+\/=?`{|}~^]+@[\w-]+\.[\w]{2,6}"/>
        <small id="emailHelp" class="form-text text-muted">
        <fmt:message key="regpage.emailhelp"/>
        </small>
        </div>
        </div>
        </div>
        <fmt:message key="button.register" var="register"/>
        <input type="submit" class="btn btn-primary float-center"
        value="${register}">
        <div class="text-uppercase">
        ${registererror}<br/>
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
        <script src="${pageContext.request.contextPath}/js/ta_script.js"></script>

        </body>
        </html>
