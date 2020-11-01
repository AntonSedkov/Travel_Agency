<body>

<section class="see_all_orders">


    <p style="text-align: center; font-weight: bold; font-size: 20pt">
        <c:if test="${empty orders}"><fmt:message key="orders.nothing"/></c:if>
    </p>

    <c:if test="${not empty orders}">

        <div class="container-fluid p-3 text-center" style="max-width: 100%; background: lightseagreen">
            <div class="form-row pb-2">
                <div class="col" style="max-width: 25%; text-align: left">ORDER</div>
                <div class="col" style="max-width: 25%; text-align: center">TOUR</div>
                <div class="col" style="max-width: 25%; text-align: center">PASSPORT</div>
                <div class="col" style="max-width: 25%; text-align: center">TRAVEL DOCS</div>
            </div>

            <c:forEach items="${orders}" var="currentOrder">
                <div class="form-row">
                    <div class="col" style="max-width: 25%; text-align: left">
                            ${currentOrder.idTour},
                            ${currentOrder.idPassport},
                            ${currentOrder.dateTimeOrder},
                            ${currentOrder.orderState.value}

                    </div>

                    <div class="col" style="max-width: 25%; text-align: center">
                            ${currentOrder.passport.surname}
                            ${currentOrder.passport.name}
                    </div>
                    <div class="col" style="max-width: 25%; text-align: center">
                            ${currentOrder.tour.tourType.value},
                            ${currentOrder.tour.country},
                            ${currentOrder.tour.startDate},
                            ${currentOrder.tour.price},
                    </div>
                    <div class="col" style="max-width: 25%; text-align: center">
                            ${currentOrder.idTravelDoc}
                    </div>

                </div>
            </c:forEach>

        </div>
    </c:if>
</section>


</body>