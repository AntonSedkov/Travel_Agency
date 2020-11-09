<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/travel_agency.css" type="text/css">

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="label.userhome"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body style="background: lightsteelblue">

    <%@include file="../header.jsp" %>

    <%@include file="../search.jsp" %>

    <%@include file="../greeting.jsp" %>

    <section class="buttons_user">
        <div class="col text-center">
            <button type="submit" class="btn btn-primary m-2">
                <a href="controller?command=all_passports" style="color: white"><fmt:message key="label.allpassports"/></a>
            </button>
            <button type="submit" class="btn btn-primary m-2">
                <a href="controller?command=all_orders" style="color: white"><fmt:message key="label.allorders"/></a>
            </button>
            <button type="submit" class="btn btn-primary m-2">
                <a href="controller?command=sheet_page" style="color: white"><fmt:message key="label.sheet"/></a>
            </button>
            <button type="submit" class="btn btn-primary m-2">
                <a href="controller?command=all_tours" style="color: white"><fmt:message key="label.allcurrenttours"/></a>
            </button>
        </div>
    </section>

    <section class="change_info">
        <div class="container p-5">
            <div class="card-deck">

                <form name="changePassForm" method="post" action="controller">
                    <input type="hidden" name="command" value="change_password"/>

                    <div class="card text-center" style="width: 20rem;">
                        <div class="card-body">
                            <h4 class="card-title">
                                <fmt:message key="label.changepass"/>
                            </h4>

                            <p class="card-text">
                                <label>
                                    <input type="password" name="currentpassword" class="form-control"
                                           placeholder=" <fmt:message key="label.currentpass"/>" required
                                           pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                                </label>
                            </p>

                            <p class="card-text">
                                <label>
                                    <input type="password" name="newpassword" class="form-control"
                                           placeholder="<fmt:message key="label.newpass"/>" required
                                           pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                                </label>
                                <small class="form-text text-muted">
                                    "<fmt:message key="authpage.passhelp"/>"
                                </small>
                            </p>

                            <c:if test="${requestScope.changepassword}">
                                <p class="card-text" style="color: darkgreen; font-weight: bold">
                                    <fmt:message key="statement.changepasswordsuccess"/>
                                </p>
                            </c:if>
                            <c:if test="${requestScope.changepassword eq false}">
                                <p class="card-text" style="color: darkred; font-weight: bold">
                                    <fmt:message key="statement.changepasswordfail"/>
                                </p>
                            </c:if>

                            <button type="submit" class="btn btn-primary"><fmt:message key="button.change"/></button>
                        </div>
                    </div>

                </form>

                <form name="changeLoginForm" method="post" action="controller">
                    <input type="hidden" name="command" value="change_login"/>

                    <div class="card text-center" style="width: 20rem;">
                        <div class="card-body">
                            <h4 class="card-title">
                                <fmt:message key="label.changelogin"/>
                            </h4>

                            <p class="card-text">
                                <label>
                                    <input type="text" name="newusername" class="form-control"
                                           placeholder="<fmt:message key="label.newusername"/>" required
                                           pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                                </label>
                                <small class="form-text text-muted">
                                    <fmt:message key="authpage.loginhelp"/>
                                </small>
                            </p>

                            <c:if test="${requestScope.changelogin}">
                                <p class="card-text" style="color: darkgreen; font-weight: bold">
                                    <fmt:message key="statement.changeloginsuccess"/>
                                </p>
                            </c:if>
                            <c:if test="${requestScope.changelogin eq false}">
                                <p class="card-text" style="color: darkred; font-weight: bold">
                                    <fmt:message key="statement.changeloginfail"/>
                                </p>
                            </c:if>

                            <button type="submit" class="btn btn-primary"><fmt:message key="button.change"/></button>
                        </div>
                    </div>

                </form>

                <form name="changeEmailForm" method="post" action="controller">
                    <input type="hidden" name="command" value="change_email"/>

                    <div class="card text-center" style="width: 20rem;">
                        <div class="card-body">
                            <h4 class="card-title">
                                <fmt:message key="label.changeemail"/>
                            </h4>

                            <p class="card-text">
                                <label>
                                    <input type="email" name="newemail" class="form-control"
                                           placeholder="<fmt:message key="label.newemail"/>" required
                                           pattern="[\w-\.\+!#$%&’*+\/=?`{|}~^]+@[\w-]+\.[\w]{2,6}"/>
                                </label>
                                <small class="form-text text-muted">
                                    <fmt:message key="regpage.emailhelp"/>
                                </small>
                            </p>

                            <c:if test="${requestScope.changeemail}">
                                <p class="card-text" style="color: darkgreen; font-weight: bold">
                                    <fmt:message key="statement.changeemailsuccess"/>
                                </p>
                            </c:if>
                            <c:if test="${requestScope.changeemail eq false}">
                                <p class="card-text" style="color: darkred; font-weight: bold">
                                    <fmt:message key="statement.changeemailfail"/>
                                </p>
                            </c:if>

                            <button type="submit" class="btn btn-primary"><fmt:message key="button.change"/></button>
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