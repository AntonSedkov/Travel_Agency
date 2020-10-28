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
        <title>USER Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    </head>

    <body style="background: lightsalmon">

        <%@include file="../header.jsp" %>

        <div class="container">
            <h3 class="text-center"><fmt:message key="text.greetings"/> ${user}<fmt:message key="text.smileend"/></h3>
        </div>


        <div class="container">
           <h4>sheet.id = ${sheet.id}</h4><br/>
           <h4>sheet.sheetsum = ${sheet.sheetSum}</h4><br/>
           <h4>sheet.discount = ${sheet.discount}</h4><br/>
           <h4>user = ${user}</h4><br/>
           <h4>role = ${role}</h4><br/>
           <h4>email = ${email}</h4><br/>
        </div>

        <h3 class="font-weight-bold text-center" style="color: #BA4E27">
            See Tours</h3>
        <div class="contaner">
            Your tours
        </div>

        <h3 class="font-weight-bold text-center" style="color: #BA4E27">
            Choose Tours</h3>
        <div class="contaner">
            All tours - paging
        </div>

        <h3 class="font-weight-bold text-center" style="color: #BA4E27">
            See Travel Docs</h3>
        <div class="contaner">
            Your Travel Docs
        </div>

        <h3 class="font-weight-bold text-center" style="color: #BA4E27">
            Add/Edit Passports</h3>
        <div class="contaner">
            Your passports
        </div>

        <div class="container">
            <button type="submit" class="btn btn-primary float-right">
                <a href="controller?command=logout" style="color: white">
                    <fmt:message key="button.logout"/></a>
            </button>
        </div>

        <%@include file="../footer.jsp" %>

    </body>

</html>