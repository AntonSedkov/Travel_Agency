<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.addpassport"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightsteelblue">

<%@include file="../header.jsp" %>

<%@include file="../search.jsp" %>

<section class="add_passport">
    <div class="container text-center" style="max-width: 35%; background: lightseagreen">

        <c:if test="${requestScope.addpassport}">
            <p class="card-text" style="color: darkgreen; font-weight: bold">
                <fmt:message key="statement.addpassportsuccess"/>
            </p>
        </c:if>
        <c:if test="${requestScope.addpassport eq false}">
            <p class="card-text" style="color: darkred; font-weight: bold">
                <fmt:message key="statement.addpassportfail"/>
            </p>
        </c:if>

        <form method="post" action="upload" enctype="multipart/form-data">
            <input type="hidden" name="command" value="add_passport"/>
            <input type="hidden" name="uploadtarget" value="newpersdoc">

            <div class="form-row">
                <div class="col form-froup">

                    <h4 class="card-title"><fmt:message key="label.addpassport"/></h4>

                    <p>
                        <fmt:message key="field.surname"/><br/>
                        <label>
                            <input type="text" name="surname" class="form-control"
                                   placeholder="<fmt:message key='label.surname'/>" required
                                   pattern="[A-Za-z]{1,30}"/>
                        </label>
                        <small class="form-text text-muted">
                            <fmt:message key="statement.passstringhelp"/>
                        </small>
                    </p>

                    <p>
                        <fmt:message key="field.name"/><br/>
                        <label>
                            <input type="text" name="name" class="form-control"
                                   placeholder="<fmt:message key='label.name'/>" required
                                   pattern="[A-Za-z]{1,30}"/>
                        </label>
                        <small class="form-text text-muted">
                            <fmt:message key="statement.passstringhelp"/>
                        </small>
                    </p>

                    <p>
                        <fmt:message key="field.birthdate"/><br/>
                        <label>
                            <input type="date" class="gj-datepicker"
                                   placeholder="<fmt:message key='label.birthdate'/>"
                                   id="birthdate" max="3000-10-10" name="birthdate" required>
                        </label>
                    </p>

                    <p>
                        <fmt:message key="field.passportno"/><br/>
                        <label>
                            <input type="text" name="passportno" class="form-control"
                                   placeholder="<fmt:message key='label.passportno'/>" required
                                   pattern="^[A-Za-z]{2}[\d]{7}$"/>
                        </label>
                        <small class="form-text text-muted">
                            <fmt:message key="statement.passnumberhelp"/>
                        </small>
                    </p>

                    <p>
                        <fmt:message key="field.passportimage"/><br/>
                        <input type="file" name="imagecontent" accept=".jpg, .jpeg, .pdf" required>
                        <small class="form-text text-muted">
                            <fmt:message key="statement.imagehelp"/>
                        </small
                    </p>

                    <button type="submit" class="btn btn-primary"><fmt:message key="button.addpassport"/></button>
                </div>
            </div>

        </form>
    </div>
</section>

<%@include file="../floatlogout.jsp" %>

<%@include file="../footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/addpassport.js"></script>
</body>

</html>