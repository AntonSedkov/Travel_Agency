<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css" type="text/css">

<body>

<section class="banner_part">
    <div class="container">
        <div class="row align-items-center justify-content-center">
            <div class="col-lg-10">
                <div class="banner_text text-center">
                    <div class="banner_text_iner">
                        <h1><fmt:message key="label.company"/></h1>
                        <p><fmt:message key="statement.slogan"/></p>
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
                                <form name="searchForm" method="post" action="controller">
                                    <input type="hidden" name="command" value="search_tour">
                                    <input type="hidden" name="tourpurpose" value="rest">
                                    <div class="form-row">
                                        <div class="form_colum">
                                            <select class="nc_select" name="country" required>
                                                <option selected><fmt:message key="field.choosecountry"/></option>
                                                <c:forEach var="currentCountry" items="${sessionScope.countries}">
                                                    <option value="${currentCountry}"><fmt:message
                                                            key="label.${currentCountry}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form_colum">
                                            <input type="date" class="gj-datepicker"
                                                   placeholder="<fmt:message key="label.startdate"/><fmt:message key="icon.star"/>"
                                                   id="startdate1" min="1000-10-10" name="startdate" required>
                                        </div>
                                        <div class="form_colum">
                                            <fmt:message key="field.tourdays" var="tourDays"/>
                                            <input type="text" placeholder="${tourDays}" name="tourdays"
                                                   pattern="\d{1,3}" required>
                                        </div>
                                        <div class="form_colum">
                                            <fmt:message key="field.maxprice" var="maxPrice"/>
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
                                <form name="searchForm" method="post" action="controller">
                                    <input type="hidden" name="command" value="search_tour">
                                    <input type="hidden" name="tourpurpose" value="excursion">
                                    <div class="form-row">
                                        <div class="form_colum">
                                            <select class="nc_select" name="country" required>
                                                <option selected><fmt:message key="field.choosecountry"/></option>
                                                <c:forEach var="currentCountry" items="${sessionScope.countries}">
                                                    <option value="${currentCountry}"><fmt:message
                                                            key="label.${currentCountry}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form_colum">
                                            <input type="date" class="gj-datepicker"
                                                   placeholder="<fmt:message key="label.startdate"/><fmt:message key="icon.star"/>"
                                                   id="startdate2" min="1000-10-10" name="startdate" required>
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
                                <form name="searchForm" method="post" action="controller">
                                    <input type="hidden" name="command" value="search_tour">
                                    <input type="hidden" name="tourpurpose" value="shopping">
                                    <div class="form-row">
                                        <div class="form_colum">
                                            <select class="nc_select" name="country" required>
                                                <option selected><fmt:message key="field.choosecountry"/></option>
                                                <c:forEach var="currentCountry" items="${sessionScope.countries}">
                                                    <option value="${currentCountry}"><fmt:message
                                                            key="label.${currentCountry}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form_colum">
                                            <input type="date" class="gj-datepicker"
                                                   placeholder="<fmt:message key="label.startdate"/><fmt:message key="icon.star"/>"
                                                   id="startdate3" min="1000-10-10" name="startdate" required>
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

        <p style="text-align: center; font-weight: bold; font-size: 24pt">
            <c:if test="${(not empty requestScope.searchnothing) and (requestScope.searchnothing)}">
                <fmt:message key="search.nothing"/></c:if>
        </p>

        <c:if test="${not empty requestScope.searchtours}">

            <div class="section_tittle text-center">
                <h2><fmt:message key="label.findtours"/></h2>
            </div>

            <div class="container p-5">
                <div class="card-deck">

                    <c:forEach var="findTour" items="${requestScope.searchtours}">

                        <form name="foundTourForm" method="post" action="controller">
                            <c:choose>
                                <c:when test="${isuser}">
                                    <input type="hidden" name="command" value="make_order_page"/>
                                    <input type="hidden" name="idtour" value="${findTour.id}">
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="command" value="login_page"/>
                                </c:otherwise>
                            </c:choose>

                            <div class="card text-center" style="width: 20rem;">
                                <img src="${pageContext.request.contextPath}/pics/tours/${findTour.imagePath}"
                                     alt="tour photo" class="card-img-top">
                                <div class="card-body">
                                    <h4 class="card-title"><fmt:message key="label.${findTour.country}"/></h4>
                                    <h4 class="card-title"><fmt:message key="label.price"/> ${findTour.price}
                                        <fmt:message key="icon.currency"/><br/>
                                        <fmt:message key="label.discount"/> ${findTour.discount}<fmt:message
                                            key="icon.percent"/></h4>
                                    <p class="card-text"><fmt:message key="label.hotel"/> ${findTour.hotelName}
                                            ${findTour.hotelType.category}
                                        <fmt:message key="icon.star"/>
                                    </p>
                                    <p class="card-text">
                                        <fmt:message key="label.tourstart"/> ${findTour.startDate}<br/>
                                        <fmt:message key="label.duration"/> ${findTour.days} <fmt:message
                                            key="label.days"/>
                                        <fmt:message key="label.quantityleft"/> ${findTour.availableQuantity}
                                        <fmt:message
                                                key="label.tours"/><br/>
                                        <fmt:message key="label.travelby"/> <fmt:message
                                            key="label.${findTour.transport.value}"/><br/>
                                        <fmt:message key="label.tourtype"/> <fmt:message
                                            key="label.${findTour.tourType.value}"/>
                                    </p>
                                    <p class="card-text"><fmt:message key="description.${findTour.description}"/></p>
                                    <button type="submit" class="btn btn-primary">
                                        <fmt:message key="button.order"/></button>
                                </div>
                            </div>
                        </form>

                    </c:forEach>

                </div>
            </div>
        </c:if>

    </div>
</section>

<script src="${pageContext.request.contextPath}/js/search.js"></script>

</body>