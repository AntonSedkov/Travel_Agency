<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.addorderdoc"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightgreen">

<%@include file="../header.jsp" %>

<%@include file="../greeting.jsp" %>

<section class="moder_add_order_doc">
    <h2 class="text-center"><fmt:message key="label.addorderdoc"/></h2>

    <p style="text-align: center; font-weight: bold; font-size: 20pt">
        <c:if test="${empty sessionScope.ordersandusers}"><fmt:message key="docs.nothing"/></c:if>
    </p>

    <c:if test="${not empty sessionScope.ordersandusers}">

        <c:if test="${requestScope.uploadresult and requestScope.adddoc}">
            <p class="card-text" style="color: darkgreen; font-weight: bold; text-align: center">
                <fmt:message key="statement.adddocsuccess"/>
            </p>
        </c:if>
        <c:if test="${requestScope.uploadresult eq false and requestScope.adddoc eq false}">
            <p class="card-text" style="color: darkred; font-weight: bold; text-align: center">
                <fmt:message key="statement.adddocfail"/>
            </p>
        </c:if>

        <c:if test="${requestScope.adddocsorder}">
            <p class="card-text" style="color: darkgreen; font-weight: bold; text-align: center">
                <fmt:message key="statement.adddocsordersuccess"/>
            </p>
        </c:if>
        <c:if test="${requestScope.adddocsorder eq false}">
            <p class="card-text" style="color: darkred; font-weight: bold; text-align: center">
                <fmt:message key="statement.adddocsorderfail"/>
            </p>
        </c:if>

        <div class="container-fluid p-3 text-center" style="background: lemonchiffon">
            <c:forEach items="${sessionScope.ordersandusers}" var="current">

                <div class="form-row pb-2">
                    <div class="col" style="font-weight: bold">
                        <fmt:message key="label.user"/><fmt:message key="icon.colon"/>
                            ${current.value}
                    </div>
                </div>

                <div class="form-row pb-2">
                    <div class="col" style="font-weight: bold">
                        <fmt:message key="label.tour"/><fmt:message key="icon.colon"/>
                        <fmt:message key="label.${current.key.tour.country}"/>,
                        <fmt:message key="label.${current.key.tour.tourType.value}"/>,
                        <fmt:message key="label.startdate"/> ${current.key.tour.startDate},
                            ${current.key.tour.days} <fmt:message key="label.days"/>
                    </div>
                </div>

                <div class="form-row pb-2">
                    <div class="col" style="font-weight: bold">
                            ${current.key.passport.surname}
                            ${current.key.passport.name}
                    </div>
                </div>

                <div class="form-row pb-2">

                    <div class="col" style="text-align: center; max-width: 33%; border: 4px double black;">
                        <form method="post" action="upload" enctype="multipart/form-data">
                            <input type="hidden" name="command" value="add_order_doc"/>
                            <input type="hidden" name="uploadtarget" value="newtourdoc"/>
                            <input type="hidden" name="iddocs" value="${current.key.travelDocs.id}"/>
                            <input type="hidden" name="doctype" value="voucher"/>

                            <p><fmt:message key="label.voucher"/></p>
                            <c:choose>
                                <c:when test="${not empty current.key.travelDocs.voucher}">
                                    <p><a href="${pageContext.request.contextPath}/pics/tourdoc/${current.key.travelDocs.voucher}"
                                       target="_blank">
                                            ${current.key.travelDocs.voucher}
                                    </a></p>
                                </c:when>
                                <c:otherwise>
                                    <p><fmt:message key="label.nodoc"/></p>
                                </c:otherwise>
                            </c:choose>
                            <p><input class="p-2" type="file" name="imagecontent" accept=".jpg, .jpeg, .pdf" required/></p>
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="button.adddoc"/></button>
                        </form>
                    </div>

                    <div class="col" style="text-align: center; max-width: 33%; border: 4px double black;">
                        <form method="post" action="upload" enctype="multipart/form-data">
                            <input type="hidden" name="command" value="add_order_doc"/>
                            <input type="hidden" name="uploadtarget" value="newtourdoc"/>
                            <input type="hidden" name="iddocs" value="${current.key.travelDocs.id}"/>
                            <input type="hidden" name="doctype" value="insurance"/>

                            <p><fmt:message key="label.insurance"/></p>
                            <c:choose>
                                <c:when test="${not empty current.key.travelDocs.insurance}">
                                    <p><a href="${pageContext.request.contextPath}/pics/tourdoc/${current.key.travelDocs.insurance}"
                                       target="_blank">
                                            ${current.key.travelDocs.insurance}
                                    </a></p>
                                </c:when>
                                <c:otherwise>
                                    <p><fmt:message key="label.nodoc"/></p>
                                </c:otherwise>
                            </c:choose>
                            <p><input class="p-2" type="file" name="imagecontent" accept=".jpg, .jpeg, .pdf" required/></p>
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="button.adddoc"/></button>
                        </form>
                    </div>

                    <div class="col" style="text-align: center; max-width: 33%; border: 4px double black;">
                        <form method="post" action="upload" enctype="multipart/form-data">
                            <input type="hidden" name="command" value="add_order_doc"/>
                            <input type="hidden" name="uploadtarget" value="newtourdoc"/>
                            <input type="hidden" name="iddocs" value="${current.key.travelDocs.id}"/>
                            <input type="hidden" name="doctype" value="ticket"/>

                            <p><fmt:message key="label.tickets"/></p>
                            <c:choose>
                                <c:when test="${not empty current.key.travelDocs.ticket}">
                                    <p><a href="${pageContext.request.contextPath}/pics/tourdoc/${current.key.travelDocs.ticket}"
                                       target="_blank">
                                            ${current.key.travelDocs.ticket}
                                    </a></p>
                                </c:when>
                                <c:otherwise>
                                    <p><fmt:message key="label.nodoc"/></p>
                                </c:otherwise>
                            </c:choose>
                            <p><input class="p-2" type="file" name="imagecontent" accept=".jpg, .jpeg, .pdf" required/></p>
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="button.adddoc"/></button>
                        </form>
                    </div>

                </div>

                <div class="form-row pb-2">
                    <div class="col">
                        <fmt:message key="statement.imagehelp"/>
                    </div>
                </div>
                <div class="form-row pb-2">
                    <div class="col text-center">
                        <form name="addedDocsForm" method="post" action="controller">
                            <input type="hidden" name="command" value="change_state"/>
                            <input type="hidden" name="targetstate" value="added_docs"/>
                            <input type="hidden" name="idorder" value="${current.key.id}"/>
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="button.stateadddocs"/>
                            </button>
                        </form>
                    </div>
                </div>

            </c:forEach>
        </div>
    </c:if>

</section>

<%@include file="../floatlogout.jsp" %>

<%@include file="../footer.jsp" %>

</body>

</html>