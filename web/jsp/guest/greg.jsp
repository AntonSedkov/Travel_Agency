<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.regpage"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background-color: burlywood">

    <%@include file="../header.jsp" %>

    <%@include file="../carousel.jsp" %>

    <div class="section_tittle text-center">
        <h2 class="p-3">${regVal}</h2>
    </div>

    <div class="container-fluid p-3 text-center" style="max-width: 25%; background: lightseagreen">

        <form name="registerForm" method="post" action="controller">
            <input type="hidden" name="command" value="register">

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="inputReg">
                            ${username}
                        </label>
                        <input type="text" name="user" class="form-control" id="inputReg"
                               placeholder="${username}" required
                               pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                        <small id="loginHelpReg" class="form-text text-muted">
                            ${loginhelp}
                        </small>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="inputPassReg">
                            ${pass}
                        </label>
                        <input type="password" name="password" class="form-control"
                               id="inputPassReg" placeholder="${pass}" required
                               pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                        <small id="passHelpReg" class="form-text text-muted">
                            ${passhelp}
                        </small>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-froup">
                        <label for="inputEmailReg">
                            ${emailVal}
                        </label>
                        <input type="email" name="email" class="form-control" id="inputEmailReg"
                               placeholder="${emailVal}" required
                               pattern="[\w-\.\+!#$%&’*+\/=?`{|}~^]+@[\w-]+\.[\w]{2,6}"/>
                        <small id="emailHelp" class="form-text text-muted">
                            <fmt:message key="statement.emailhelp"/>
                        </small>
                    </div>
                </div>
            </div>

            <input type="submit" class="btn btn-primary float-center" value="${register}">

            <div class="text-uppercase" style="color: red">
                <c:if test="${requestScope.registererror}"> <fmt:message key="statement.registererror"/></c:if><br/>
                <c:if test="${requestScope.activateemailerror}"> <fmt:message key="statement.activateemailerror"/></c:if>
                <c:if test="${requestScope.activateemailsuccess}"> <fmt:message key="statement.activateemailsuccess"/></c:if>
            </div>

        </form>
    </div>

    <div class="col text-center">
        <button type="submit" class="btn btn-primary m-2">
            <a href="controller?command=login_page" style="color: white">${authVal}</a>
        </button>
    </div>

    <%@include file="../gallery.jsp"%>

    <%@include file="../footer.jsp" %>

</body>

</html>