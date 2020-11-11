<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.authpage"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background-color: burlywood">

    <%@include file="../header.jsp" %>

    <%@include file="../carousel.jsp" %>

    <div class="section_tittle text-center">
        <h2 class="p-3">${authVal}</h2>
    </div>

    <div class="container-fluid p-3 text-center" style="max-width: 25%; background: lightseagreen">

        <form name="loginForm" method="post" action="controller">
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
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="col">
                    <div class="form-group">
                        <label for="exampleInputPass">
                            ${pass}
                        </label>
                        <input type="password" name="password"
                               class="form-control" id="exampleInputPass"
                               placeholder="${pass}" required
                               pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                    </div>
                </div>
            </div>

            <input type="submit" class="btn btn-primary float-center" value="${enter}">

            <div class="text-uppercase" style="color: red">
                <c:if test="${requestScope.loginerror}"><fmt:message key="statement.loginerror"/></c:if>
            </div>

        </form>

    </div>

    <div class="col text-center">
        <button type="submit" class="btn btn-primary m-2">
            <a href="controller?command=register_page" style="color: white">${regVal}</a>
        </button>
    </div>

    <%@include file="../gallery.jsp"%>

    <%@include file="../footer.jsp" %>

</body>

</html>