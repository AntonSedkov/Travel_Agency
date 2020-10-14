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
        <title><fmt:message key="authpage.title"/></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    </head>

    <body style="background-color: burlywood">

        <%@include file="../header.jsp" %>

        <h2 class="text-center" style="background-color: fuchsia">${authVal}</h2>

        <div class="container-fluid text-center" style="max-width: 25%">
            <form name="loginForm" method="post" action="controller/">
                <input type="hidden" name="command" value="login"/>
                <div class="form-row">
                    <div class="col">
                        <div class="form-group">
                            <label for="exampleInputAuth">
                                ${username}
                            </label>
                            <input type="text" name="user" class="form-control" id="exampleInputAuth"
                                   placeholder="${username}" required
                                   pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                            <small id="loginHelp" class="form-text text-muted">
                                ${loginhelp}
                            </small>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col">
                        <div class="form-group">
                            <label for="exampleInputAuth">
                                ${pass}
                            </label>
                            <input type="password" name="password"
                                   class="form-control" id="exampleInputPass"
                                   placeholder="${pass}" required
                                   pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                            <small id="passHelp" class="form-text text-muted">
                                ${passhelp}
                            </small>
                        </div>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary float-center"
                       value="${enter}">
                <div class="text-uppercase">
                    <c:if test="${loginerror}"> <fmt:message key="authpage.loginerror"/></c:if> <br/>
                </div>
            </form>
        </div>

        <%@include file="../footer.jsp" %>

    </body>

</html>