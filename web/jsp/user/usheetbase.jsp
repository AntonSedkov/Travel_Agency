<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.sheet"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightsteelblue">

    <%@include file="../header.jsp" %>

    <%@include file="../search.jsp" %>

    <%@include file="../greeting.jsp" %>

    <section class="base_info">
        <div class="container text-center p-5">
            <div class="card-deck">

                <div class="card text-center" style="width: 20rem;">
                    <div class="card-body" style="background-color: lemonchiffon">
                        <h4 class="card-title pb-2 pt-1" style="text-align: left; font-weight: bold">
                            <fmt:message key="label.sheetnumber"/> ${sessionScope.sheet.id}<br/>
                        </h4>
                        <h4 class="card-title pb-2" style="text-align: left; font-weight: bold">
                            <fmt:message key="label.sheetsum"/> ${sessionScope.sheet.sheetSum} <fmt:message key="icon.currency"/><br/>
                        </h4>
                        <h4 class="card-title " style="text-align: left; font-weight: bold">
                            <fmt:message key="label.sheetdiscount"/> ${sessionScope.sheet.discount.value} <fmt:message
                                key="icon.percent"/>
                        </h4>
                        <p class="card-text">
                            <button type="submit" class="btn btn-primary m-2">
                                <a href="controller?command=see_operations" style="color: white">
                                    <fmt:message key="label.seeoperations"/></a>
                            </button>
                        </p>
                    </div>
                </div>

                <form name="addSumForm" method="post" action="controller">
                    <input type="hidden" name="command" value="add_sheet_sum"/>

                    <div class="card text-center" style="width: 30rem;">
                        <div class="card-body">
                            <h4 class="card-title" ><fmt:message key="label.addsum"/></h4>
                            <p class="card-text">
                                <label>
                                    <input type="text" name="paycard" class="form-control"
                                           placeholder="<fmt:message key="field.paycardnumber"/>" required
                                           pattern="[\d]{7}"/>
                                </label>
                            </p>

                            <c:if test="${requestScope.addsumresult}">
                                <p class="card-text" style="color: darkgreen; font-weight: bold">
                                    <fmt:message key="statement.addsumresultsuccess"/>
                                </p>
                            </c:if>
                            <c:if test="${requestScope.addsumresult eq false}">
                                <p class="card-text" style="color: darkred; font-weight: bold">
                                    <fmt:message key="statement.addsumresultfail"/>
                                </p>
                            </c:if>

                            <button type="submit" class="btn btn-primary"><fmt:message key="button.replenish"/></button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </section>

    <%@include file="../hottours.jsp" %>

    <%@include file="../floatlogout.jsp" %>

    <%@include file="../footer.jsp" %>

</body>

</html>