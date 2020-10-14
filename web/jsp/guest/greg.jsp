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
        <title><fmt:message key="regpage.title"/></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    </head>

    <body style="background-color: burlywood">

        <%@include file="../header.jsp" %>

        <h2 class="text-center" style="background-color: darkmagenta">${regVal}</h2>

        <div class="container-fluid text-center" style="max-width: 25%">
            <form name="registerForm" method="post" action="controller/">
                <input type="hidden" name="command" value="register">
                <div class="form-row">
                    <div class="col">
                        <div class="form-froup">
                            <label for="exampleInputReg">
                                ${username}
                            </label>
                            <input type="text" name="user" class="form-control" id="exampleInputReg"
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
                            <label for="exampleInputReg">
                                ${pass}
                            </label>
                            <input type="password" name="password" class="form-control"
                                   id="exampleInputPassReg"
                                   placeholder="${pass}" required
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
                            <label for="exampleInputReg">
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
                <input type="submit" class="btn btn-primary float-center"
                       value="${register}">
                <div class="text-uppercase">
                    <c:if test="${registererror}"> <fmt:message key="regpage.registererror"/></c:if> <br/><br/>
                    <c:if test="${activateemailerror}"> <fmt:message key="regpage.activateemailerror"/></c:if> <br/>
                    <c:if test="${activateemailsuccess}"> <fmt:message key="regpage.activateemailsuccess"/></c:if> <br/>
                </div>
            </form>
        </div>

        <%@include file="../footer.jsp" %>

    </body>

</html>