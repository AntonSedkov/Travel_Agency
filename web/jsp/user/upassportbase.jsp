<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${language}">

<head>
    <title><fmt:message key="label.allpassports"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightsteelblue">

    <%@include file="../header.jsp" %>

    <%@include file="../search.jsp" %>

    <%@include file="../greeting.jsp" %>

    <section class="see_all_passports">

        <p style="text-align: center; font-weight: bold; font-size: 20pt">
            <c:if test="${empty passports}"><fmt:message key="passports.nothing"/></c:if>
        </p>

        <c:if test="${not empty passports}">

            <div class="container-fluid p-3 text-center" style="max-width: 65%; background: lightseagreen">
                <div class="form-row pb-2">
                    <div class="col" style="max-width: 4%; text-align: left"><fmt:message key="icon.number"/></div>
                    <div class="col" style="max-width: 14%; text-align: center"><fmt:message key="label.surname"/></div>
                    <div class="col" style="max-width: 14%; text-align: center"><fmt:message key="label.name"/></div>
                    <div class="col" style="max-width: 14%; text-align: center"><fmt:message key="label.birthdate"/></div>
                    <div class="col" style="max-width: 14%; text-align: center"><fmt:message key="label.passportno"/></div>
                    <div class="col" style="max-width: 24%; text-align: center"><fmt:message
                            key="label.passportimage"/></div>
                    <div class="col" style="max-width: 16%; text-align: center"></div>
                </div>

                <c:forEach items="${passports}" varStatus="counter">
                    <div class="form-row">
                        <div class="col" style="max-width: 4%; text-align: left">
                                ${counter.count}</div>
                        <div class="col" style="max-width: 14%; text-align: center">
                                ${counter.current.surname}</div>
                        <div class="col" style="max-width: 14%; text-align: center">
                                ${counter.current.name}</div>
                        <div class="col" style="max-width: 14%; text-align: center">
                                ${counter.current.birthDate}</div>
                        <div class="col" style="max-width: 14%; text-align: center">
                                ${counter.current.passportNumber}</div>
                        <div class="col" style="max-width: 24%; text-align: center">
                                ${counter.current.passportImage}</div>
                        <div class="col" style="max-width: 16%; text-align: center">
                            <form name="smthDo" method="post" action="controller">
                                <input type="hidden" name="idusermoderate" value="${counter.current.id}"/>
                                <input type="hidden" name="command" value="smth_do"/>
                                <button type="submit" class="btn btn-primary float-center">
                                    smth DO
                                </button>
                            </form>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </c:if>
    </section>

    <%@include file="../hottours.jsp" %>

    <%@include file="../floatlogout.jsp" %>

    <%@include file="../footer.jsp" %>

</body>

</html>