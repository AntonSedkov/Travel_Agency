<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css" type="text/css">

    <body>

        <section class="banner_part">
            <div class="container">
                <div class="row align-items-center justify-content-center">
                    <div class="col-lg-10">
                        <div class="banner_text text-center">
                            <div class="banner_text_iner">
                                <h1><fmt:message key="label.company"/></h1>
                                <p><fmt:message key="label.slogan"/></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="booking_part">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="booking_menu">
                            <ul class="nav nav-tabs" id="searchTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="rest-tab" data-toggle="tab" href="#rest" role="tab"
                                       aria-controls="rest" aria-selected="true"><fmt:message key="label.Rest"/></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="excursion-tab" data-toggle="tab" href="#excursion" role="tab"
                                       aria-controls="excursion" aria-selected="false"><fmt:message key="label.Excursion"/></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="shopping-tab" data-toggle="tab" href="#shopping" role="tab"
                                       aria-controls="shopping" aria-selected="false"><fmt:message key="label.Shopping"/></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="booking_content">
                            <div class="tab-content" id="currentTabContent">

                                <div class="tab-pane fade show active" id="rest" role="tabpanel" aria-labelledby="rest-tab">
                                    <div class="booking_form">
                                        <form name="searchForm" method="post" action="controller/">
                                            <input type="hidden" name="command" value="search_tour">
                                            <input type="hidden" name="tourpurpose" value="rest">
                                            <div class="form-row">
                                                <div class="form_colum">
                                                    <select class="nc_select" name="country" required>
                                                        <option selected><fmt:message key="label.choosecountry"/></option>
                                                        <c:forEach var="currentCountry" items="${countries}">
                                                            <option value="${currentCountry}"><fmt:message
                                                                    key="label.${currentCountry}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form_colum">
                                                    <fmt:message key="label.startdate" var="startDate"/>
                                                    <input type="date" placeholder="${startDate}" name="startdate" required>
                                                </div>
                                                <div class="form_colum">
                                                    <fmt:message key="label.tourdays" var="tourDays"/>
                                                    <input type="text" placeholder="${tourDays}" name="tourdays"
                                                           pattern="\d{1,3}" required>
                                                </div>
                                                <div class="form_colum">
                                                    <fmt:message key="label.maxprice" var="maxPrice"/>
                                                    <input type="text" placeholder="${maxPrice}" name="maxprice"
                                                           pattern="\d{1,7}" required>
                                                </div>
                                                <div class="form_btn">
                                                    <fmt:message key="button.search" var="search"/>
                                                    <input type="submit" class="btn btn-primary" value="${search}">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="tab-pane fade" id="excursion" role="tabpanel" aria-labelledby="excursion-tab">
                                    <div class="booking_form">
                                        <form name="searchForm" method="get" action="controller/">
                                            <input type="hidden" name="command" value="search_tour">
                                            <input type="hidden" name="tourpurpose" value="excursion">
                                            <div class="form-row">
                                                <div class="form_colum">
                                                    <select class="nc_select" name="country" required>
                                                        <option selected><fmt:message key="label.choosecountry"/></option>
                                                        <c:forEach var="currentCountry" items="${countries}">
                                                            <option value="${currentCountry}"><fmt:message
                                                                    key="label.${currentCountry}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form_colum">
                                                    <input type="date" placeholder="${startDate}" name="startdate" required>
                                                </div>
                                                <div class="form_colum">
                                                    <input type="text" placeholder="${tourDays}" name="tourdays"
                                                           pattern="\d{1,3}" required>
                                                </div>
                                                <div class="form_colum">
                                                    <input type="text" placeholder="${maxPrice}" name="maxprice"
                                                           pattern="\d{1,7}" required>
                                                </div>
                                                <div class="form_btn">
                                                    <input type="submit" class="btn btn-primary" value="${search}">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="tab-pane fade" id="shopping" role="tabpanel" aria-labelledby="shopping-tab">
                                    <div class="booking_form">
                                        <form name="searchForm" method="get" action="controller/">
                                            <input type="hidden" name="command" value="search_tour">
                                            <input type="hidden" name="tourpurpose" value="shopping">
                                            <div class="form-row">
                                                <div class="form_colum">
                                                    <select class="nc_select" name="country" required>
                                                        <option selected><fmt:message key="label.choosecountry"/></option>
                                                        <c:forEach var="currentCountry" items="${countries}">
                                                            <option value="${currentCountry}"><fmt:message
                                                                    key="label.${currentCountry}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form_colum">
                                                    <input type="date" placeholder="${startDate}" name="startdate" required>
                                                </div>
                                                <div class="form_colum">
                                                    <input type="text" placeholder="${tourDays}" name="tourdays"
                                                           pattern="\d{1,3}" required>
                                                </div>
                                                <div class="form_colum">
                                                    <input type="text" placeholder="${maxPrice}" name="maxprice"
                                                           pattern="\d{1,7}" required>
                                                </div>
                                                <div class="form_btn">
                                                    <input type="submit" class="btn btn-primary" value="${search}">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="search_part">
            <div class="container-fluid mt-5" style="background-color:lightseagreen">
                <p>
                    <c:if test="${searchnothing}"><fmt:message key="search.nothing"/></c:if>
                </p>
                <c:if test="${not empty searchtours}">
                    <div class="section_tittle text-center">
                        <h2><fmt:message key="title.findtours"/></h2>
                    </div>
                </c:if>
                <div class="container p-5">
                    <div class="card-deck">

                        <c:forEach var="findTour" items="${searchtours}">
                            <div class="card text-center" style="width: 20rem;">
                                <img src="${pageContext.request.contextPath}/pics/tours/${findTour.imagePath}" alt="tour photo"
                                     class="card-img-top">
                                <div class="card-body">
                                    <h4 class="card-title"><fmt:message key="label.${findTour.country}"/></h4>
                                    <h4 class="card-title"><fmt:message key="label.price"/> ${findTour.price}<fmt:message key="icon.currency"/></h4>
                                    <p class="card-text"><fmt:message key="label.hotel"/> ${findTour.hotelName}
                                            ${findTour.hotelType.category}<fmt:message key="icon.star"/>
                                    </p>
                                    <p class="card-text">
                                        <fmt:message key="label.tourstart"/> ${findTour.startDate}<br/>
                                        <fmt:message key="label.duration"/> ${findTour.days} <fmt:message key="label.days"/>
                                        <fmt:message key="label.quantityleft"/> ${findTour.availableQuantity} <fmt:message key="label.tours"/><br/>
                                        <fmt:message key="label.travelby"/> <fmt:message key="label.${findTour.transport.value}"/><br/>
                                        <fmt:message key="label.tourtype"/> <fmt:message key="label.${findTour.tourType.value}"/>
                                    </p>
            <%----%>                <p class="card-text">${findTour.description}</p>
            <%----%>                <a href="#" class="btn btn-primary"><fmt:message key="button.submit"/></a>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </section>

    </body>