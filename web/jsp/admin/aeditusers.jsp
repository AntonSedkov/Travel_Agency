<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.editusers"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightgrey">

<%@include file="../header.jsp" %>

<%@include file="../greeting.jsp" %>

<section class="admin_edit_users">

    <div class="section_tittle text-center mt-2 mb-2">
        <h2><fmt:message key="label.editusers"/></h2>
    </div>

    <div class="text-uppercase text-center" style="color: darkred">
        <p><c:if test="${requestScope.activateusererror}"><fmt:message key="statement.activateusererror"/></c:if></p>
        <p><c:if test="${requestScope.deactivateusererror}"><fmt:message key="statement.deactivateusererror"/></c:if></p>
    </div>

    <div class="contaner pb-3">

        <div class="row m-3">
            <div class="col text-center"><fmt:message key="label.id"/></div>
            <div class="col text-left"><fmt:message key="label.username"/></div>
            <div class="col text-left"><fmt:message key="label.email"/></div>
            <div class="col text-left"><fmt:message key="label.role"/></div>
            <div class="col text-left"><fmt:message key="label.status"/></div>
            <div class="col text-left"><fmt:message key="label.emailapproved"/></div>
            <div class="col text-center"><fmt:message key="label.activation"/></div>
            <div class="col text-center"><fmt:message key="label.deactivation"/></div>
        </div>

        <c:forEach var="user" items="${sessionScope.users}">
            <div class="row">
                <div class="col text-center"><c:out value="${user.id}"/></div>
                <div class="col text-left"><c:out value="${user.login}"/></div>
                <div class="col text-left"><c:out value="${user.email}"/></div>
                <div class="col text-left"><c:out value="${user.role}"/></div>
                <div class="col text-left">
                    <c:choose>
                        <c:when test="${user.status}">
                            <fmt:message key="label.activeuser"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="label.deactiveuser"/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col text-left">
                    <c:choose>
                        <c:when test="${user.approvedEmail}">
                            <fmt:message key="label.yes"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="label.no"/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col text-center">
                    <form name="activateUser" method="post" action="controller">
                        <input type="hidden" name="idusermoderate" value="${user.id}"/>
                        <input type="hidden" name="command" value="activate_user"/>
                        <button type="submit" class="btn btn-primary float-center">
                            <fmt:message key="button.activateuser"/></button>
                    </form>
                </div>
                <div class="col text-center">
                    <form name="deactivateUser" method="post" action="controller">
                        <input type="hidden" name="idusermoderate" value="${user.id}"/>
                        <input type="hidden" name="command" value="deactivate_user"/>
                        <button type="submit" class="btn btn-primary float-center">
                            <fmt:message key="button.deactivateuser"/></button>
                    </form>
                </div>
            </div>
        </c:forEach>

    </div>

</section>

<%@include file="../floatlogout.jsp" %>

<%@include file="../footer.jsp" %>

</body>

</html>