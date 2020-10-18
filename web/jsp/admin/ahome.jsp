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
        <title><fmt:message key="label.adminhome"/></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    </head>

    <body style="background: lightcoral">

        <%@include file="../header.jsp" %>

        <div class="container">
            <h3 class="text-center"><fmt:message key="text.greetings"/> ${user}<fmt:message key="text.smileend"/></h3>
        </div>

        <h3 class="font-weight-bold text-center" style="color: #BA4E27">
            Edit Users </h3>
        <div class="contaner">

            <c:forEach var="user" items="${users}">
                    <div class="row">
                        <div class="col text-center"><c:out value="${user.id}"/></div>
                        <div class="col text-left"><c:out value="${user.login}"/></div>
                        <div class="col text-left"><c:out value="${user.email}"/></div>
                        <div class="col text-left"><c:out value="${user.role}"/></div>
                        <div class="col text-left"><c:out value="${user.status}"/></div>
                        <div class="col text-left"><c:out value="${user.approvedEmail}"/></div>
                        <div class="col text-center">
                            <form name="activateUser" method="post" action="controller/">
                                <input type="hidden" name="iduser" value="${user.id}"/>
                                <input type="hidden" name="command" value="activate_user"/>
                                <button type="submit" class="btn btn-primary float-center">
                                    <fmt:message key="admin.activate"/></button>
                            </form>
                        </div>
                        <div class="col text-center">
                            <form name="deactivateUser" method="post" action="controller/">
                                <input type="hidden" name="iduser" value="${user.id}"/>
                                <input type="hidden" name="command" value="deactivate_user"/>
                                <button type="submit" class="btn btn-primary float-center">
                                    <fmt:message key="admin.deactivate"/></button>
                            </form>
                        </div>
                    </div>
            </c:forEach>

            <div class="text-uppercase">
                <c:if test="${activateusererror}"> <fmt:message key="admin.activateusererror"/></c:if> <br/>
                <c:if test="${deactivateusererror}"> <fmt:message key="admin.deactivateusererror"/></c:if> <br/>
            </div>

        </div>

        <div class="container text-center" style="max-width: 25%">

            <div class="h3 font-weight-bold text-center" style="margin-bottom: .5rem; color: #1f6c06">
                Add Staff </div>

            <form name="loginForm" method="post" action="controller/">
            <input type="hidden" name="command" value="add_staff"/>

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
                        <fmt:message key="regpage.emailhelp"/>
                    </small>
                </div>
            </div>

            <div class="form-row">
                <div class="col form-froup">
                    <fmt:message key="admin.role" var="roleVal"/>
                    <label >${roleVal}</label>
                    <input type="radio" name="role" value="moderator" class="form-control" checked>
                        <fmt:message key="admin.moderator"/><br/>
                    <input type="radio" name="role" value="admin" class="form-control">
                        <fmt:message key="admin.admin"/><br/>
                    <small id="roleHelp" class="form-text text-muted">
                        <fmt:message key="admin.rolehelp"/>
                    </small>
                </div>
            </div>

            <input type="submit" class="btn btn-primary float-center"value="${register}"/>

            <div class="text-uppercase">
                <c:if test="${registererror}"> <fmt:message key="regpage.registererror"/></c:if> <br/><br/>
                <c:if test="${registersuccess}"> <fmt:message key="regpage.registersuccess"/></c:if> <br/><br/>
            </div>

        </form>
    </div>

    <div class="container">
        <button type="submit" class="btn btn-primary float-right">
            <a href="controller?command=logout" style="color: white">
                <fmt:message key="button.logout"/></a></button>
    </div>

        <%@include file="../footer.jsp" %>

    </body>

</html>