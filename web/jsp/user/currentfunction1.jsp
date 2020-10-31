<body>

<section class="see_all_orders">


    <p style="text-align: center; font-weight: bold; font-size: 20pt">
        <c:if test="${empty orders}"><fmt:message key="orders.nothing"/></c:if>
    </p>

    <c:if test="${not empty orders}">

        <div class="container-fluid p-3 text-center" style="max-width: 65%; background: lightseagreen">
            <div class="form-row pb-2">
                <div class="col" style="max-width: 4%; text-align: left"><fmt:message key="icon.number"/></div>
                <div class="col" style="max-width: 14%; text-align: center"><fmt:message key="label.surname"/></div>
                <div class="col" style="max-width: 14%; text-align: center"><fmt:message key="label.name"/></div>
                <div class="col" style="max-width: 14%; text-align: center"><fmt:message key="label.birthdate"/></div>
                <div class="col" style="max-width: 14%; text-align: center"><fmt:message key="label.passportno"/></div>
                <div class="col" style="max-width: 24%; text-align: center"><fmt:message key="label.passportimage"/></div>
                <div class="col" style="max-width: 16%; text-align: center"></div>
            </div>

            <c:forEach items="${orders}" varStatus="counter">
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
                                smth DO</button>
                        </form>
                    </div>
                </div>
            </c:forEach>

        </div>
    </c:if>
</section>


</body>