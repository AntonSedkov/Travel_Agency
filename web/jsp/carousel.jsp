<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<body>

<div class="container-fluid p-0">
    <div id="carouselIndicators" class="carousel slide" data-ride="carousel">

        <ol class="carousel-indicators">
            <li class="active" data-target="#carouselIndicators" data-slide-to="0"></li>
            <li data-target="#carouselIndicators" data-slide-to="1"></li>
            <li data-target="#carouselIndicators" data-slide-to="2"></li>
        </ol>

        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath}/pics/carouselOne.jpg" alt="Carousel photo one"
                     class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/pics/carouselTwo.jpg" alt="Carousel photo one"
                     class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/pics/carouselThree.jpg" alt="Carousel photo one"
                     class="d-block w-100">
            </div>
        </div>

        <a href="#carouselIndicators" class="carousel-control-prev" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only"><fmt:message key="button.previous"/></span>
        </a>

        <a href="#carouselIndicators" class="carousel-control-next" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only"><fmt:message key="button.next"/></span>
        </a>

    </div>
</div>

</body>