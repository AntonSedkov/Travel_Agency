<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/aboutus.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.aboutus"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightseagreen">

    <%@include file="../header.jsp" %>

    <section class="breadcrumb breadcrumb_bg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb_iner">
                        <div class="breadcrumb_iner_item text-center">
                            <h2><fmt:message key="label.aboutus"/></h2>
                            <p><fmt:message key="aboutus.intro"/></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="about_us section_padding">
        <div class="container">
            <div class="row align-items-center">

                <div class="col-lg-6">
                    <div class="about_img">
                        <img src="${pageContext.request.contextPath}/pics/about_us.png" alt="About us image">
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="about_text">
                        <h5><fmt:message key="aboutus.textheadline"/></h5>
                        <h2><fmt:message key="aboutus.textfirstline"/></h2>
                        <p><fmt:message key="aboutus.firstparagraph"/></p>
                        <p><fmt:message key="aboutus.textsecondline"/><br/>
                            <fmt:message key="aboutus.secondparagraph"/></p>
                        <p><fmt:message key="aboutus.textthirdline"/><br/>
                            <fmt:message key="aboutus.thirdparagraph"/></p>
                    </div>
                </div>

            </div>
        </div>
    </section>

    <%@include file="../gallery.jsp" %>

    <%@include file="../footer.jsp" %>

</body>

</html>