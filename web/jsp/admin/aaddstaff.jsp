<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.addstaff"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightgrey">

<%@include file="../header.jsp" %>

<%@include file="../greeting.jsp" %>

<section class="admin_add_staff">

    <div class="section_tittle text-center mt-2 mb-2">
        <h2><fmt:message key="label.addstaff"/></h2>
    </div>

    <div class="text-uppercase text-center">
        <p style="color: darkred"><c:if test="${requestScope.registererror}"> <fmt:message key="statement.registererror"/></c:if></p>
        <p style="color: darkgreen"><c:if test="${requestScope.registersuccess}"> <fmt:message key="statement.registersuccess"/></c:if></p>
    </div>

    <div class="container text-center pb-3" style="max-width: 25%">

        <form name="loginForm" method="post" action="controller">
            <input type="hidden" name="command" value="register"/>

            <div class="form-row">
                <div class="col form-froup">
                    <label for="usernameLabel">${username}</label>
                    <input type="text" name="user" class="form-control" id="usernameLabel"
                           placeholder="${username}" required
                           pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                    <small id="loginHelpReg" class="form-text text-muted">
                        ${loginhelp}
                    </small>
                </div>
            </div>

            <div class="form-row">
                <div class="col form-froup">
                    <label for="passwordLabel">${pass}</label>
                    <input type="password" name="password" class="form-control"
                           id="passwordLabel" placeholder="${pass}" required
                           pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                    <small id="passHelpReg" class="form-text text-muted">
                        ${passhelp}
                    </small>
                </div>
            </div>

            <div class="form-row">
                <div class="col form-froup">
                    <label for="emailLabel">${emailVal}</label>
                    <input type="email" name="email" class="form-control" id="emailLabel"
                           placeholder="${emailVal}" required
                           pattern="[\w-\.\+!#$%&’*+\/=?`{|}~^]+@[\w-]+\.[\w]{2,6}"/>
                    <small id="emailHelp" class="form-text text-muted">
                        <fmt:message key="statement.emailhelp"/>
                    </small>
                </div>
            </div>

            <div class="form-row">
                <div class="col form-froup">
                    <label><fmt:message key="field.staffrole"/></label><br/>
                    <label>
                        <input type="radio" name="role" value="moderator" class="form-control" checked>
                        <fmt:message key="label.moderator"/><br/>
                    </label>
                    <label>
                        <input type="radio" name="role" value="admin" class="form-control">
                        <fmt:message key="label.admin"/><br/>
                    </label>
                    <small id="roleHelp" class="form-text text-muted"><fmt:message key="statement.rolehelp"/></small>
                </div>
            </div>

            <input type="submit" class="btn btn-primary float-center" value="<fmt:message key="button.register"/>"/>

        </form>
    </div>

</section>

<%@include file="../floatlogout.jsp" %>

<%@include file="../footer.jsp" %>

</body>

</html>