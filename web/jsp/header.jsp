<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="udt" uri="customtags" %>

<body>

<nav class="navbar navbar-expand-lg navbar-dark sticky-top" style="background-color: cornflowerblue">

    <c:if test="${sessionScope.role.equals('guest')}" var="isguest"/>
    <c:if test="${sessionScope.role.equals('user')}" var="isuser"/>
    <c:if test="${sessionScope.role.equals('moderator')}" var="ismoderator"/>
    <c:if test="${sessionScope.role.equals('admin')}" var="isadmin"/>

    <c:if test="${isguest}">
        <a href="controller?command=guest_in" class="navbar-brand">
            <img src="${pageContext.request.contextPath}/pics/img_logo.jpg" width="30" height="30" alt="logo">
        </a>
    </c:if>

    <c:if test="${isuser}">
        <a href="controller?command=user_in" class="navbar-brand">
            <img src="${pageContext.request.contextPath}/pics/img_logo.jpg" width="30" height="30" alt="logo">
        </a>
    </c:if>

    <c:if test="${ismoderator}">
        <a href="controller?command=moderator_in" class="navbar-brand">
            <img src="${pageContext.request.contextPath}/pics/img_logo.jpg" width="30" height="30" alt="logo">
        </a>
    </c:if>

    <c:if test="${isadmin}">
        <a href="controller?command=admin_in" class="navbar-brand">
            <img src="${pageContext.request.contextPath}/pics/img_logo.jpg" width="30" height="30" alt="logo">
        </a>
    </c:if>

    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle-navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <ul class="navbar-nav mr-auto">

            <c:if test="${isguest}">

                <li class="nav-item active">
                    <a href="controller?command=guest_in" class="nav-link">
                        <fmt:message key="startpage.homebutton"/></a>
                </li>

                <li class="nav-item">
                    <a href="#" class="nav-link" data-toggle="modal" data-target="#authModal">
                        <fmt:message key="startpage.authbutton" var="authVal"/> ${authVal}</a>
                </li>

                <li class="nav-item">
                    <a href="#" class="nav-link" data-toggle="modal" data-target="#regModal">
                        <fmt:message key="startpage.registerbutton" var="regVal"/> ${regVal}</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="controller?command=all_tours" id="navbarDropdownGuest"
                       role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="label.alltours"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownGuest">
                        <a class="dropdown-item" href="controller?command=all_tours">
                            <fmt:message key="label.allcurrenttours"/></a>
                        <a class="dropdown-item" href="controller?command=type_tours">
                            <fmt:message key="label.toursbytype"/></a>
                        <a class="dropdown-item" href="controller?command=country_tours">
                            <fmt:message key="label.toursbycountry"/></a>
                        <a class="dropdown-item" href="controller?command=hot_tours">
                            <fmt:message key="label.hottours"/></a>
                    </div>
                </li>

                <li class="nav-item">
                    <a href="controller?command=change_page&targetpage=path.guest.about" class="nav-link">
                        <fmt:message key="startpage.aboutusbutton"/></a>
                </li>

            </c:if>


            <c:if test="${isadmin}">

                <li class="nav-item active">
                    <a href="controller?command=admin_in" class="nav-link">
                        <fmt:message key="startpage.homebutton"/></a>
                </li>

                <li class="nav-item">
                    <a href="controller?command=edit_users" class="nav-link"><fmt:message key="label.editusers"/></a>
                </li>

                <li class="nav-item">
                    <a href="controller?command=add_staff" class="nav-link"><fmt:message key="label.addstaff"/></a>
                </li>

            </c:if>


            <c:if test="${ismoderator}">

                <li class="nav-item active">
                    <a href="controller?command=moderator_in" class="nav-link">
                        <fmt:message key="startpage.homebutton"/></a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="controller?command=edit_tours" id="navbarDropdownModer1"
                       role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="label.alltours"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownModer1">
                        <a class="dropdown-item" href="controller?command=edit_tours">
                            <fmt:message key="label.edittours"/></a>
                        <a class="dropdown-item" href="controller?command=add_tour_page">
                            <fmt:message key="label.addtour"/></a>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="controller?command=edit_orders" id="navbarDropdownModer2"
                       role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="label.allorders"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownModer2">
                        <a class="dropdown-item" href="controller?command=edit_orders_page">
                            <fmt:message key="label.editorders"/></a>
                        <a class="dropdown-item" href="controller?command=add_order_docs_page">
                            <fmt:message key="label.addorderdoc"/></a>
                    </div>
                </li>

            </c:if>


            <c:if test="${isuser}">

                <li class="nav-item active">
                    <a href="controller?command=user_in" class="nav-link">
                        <fmt:message key="startpage.homebutton"/></a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="controller?command=all_orders" id="navbarDropdown1"
                       role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="label.allorders"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                        <a class="dropdown-item" href="controller?command=make_order_page">
                            <fmt:message key="label.makeorder"/></a>
                        <a class="dropdown-item" href="controller?command=actual_orders">
                            <fmt:message key="label.actualorders"/></a>
                        <a class="dropdown-item" href="controller?command=all_orders">
                            <fmt:message key="label.allorders"/></a>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="controller?command=all_passports" id="navbarDropdown2"
                       role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="label.allpassports"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                        <a class="dropdown-item" href="controller?command=add_passport_page">
                            <fmt:message key="label.addpassport"/></a>
                        <a class="dropdown-item" href="controller?command=all_passports">
                            <fmt:message key="label.allpassports"/></a>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="controller?command=change_page&targetpage=path.user.sheet"
                       id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="label.sheet"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                        <a class="dropdown-item" href="controller?command=change_page&targetpage=path.user.sheet">
                            <fmt:message key="label.sheet"/></a>
                        <a class="dropdown-item" href="controller?command=see_operations">
                            <fmt:message key="label.seeoperations"/></a>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="controller?command=all_tours" id="navbarDropdownUser"
                       role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="label.alltours"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownUser">
                        <a class="dropdown-item" href="controller?command=all_tours">
                            <fmt:message key="label.allcurrenttours"/></a>
                        <a class="dropdown-item" href="controller?command=type_tours">
                            <fmt:message key="label.toursbytype"/></a>
                        <a class="dropdown-item" href="controller?command=country_tours">
                            <fmt:message key="label.toursbycountry"/></a>
                        <a class="dropdown-item" href="controller?command=hot_tours">
                            <fmt:message key="label.hottours"/></a>
                    </div>
                </li>

                <li class="nav-item">
                    <a href="controller?command=change_page&targetpage=path.guest.about" class="nav-link">
                        <fmt:message key="startpage.aboutusbutton"/></a>
                </li>

            </c:if>

            <c:if test="${isuser||ismoderator||isadmin}">
                <li class="nav-item">
                    <a href="controller?command=logout" class="nav-link">
                        <fmt:message key="button.logout"/></a>
                </li>
            </c:if>

        </ul>

        <ul class="navbar-nav mr-auto" style="color: #BA4E27; font-weight: bold">
            <udt:userdata/>
        </ul>

        <form class="form-inline m-2 my-lg-0">
            <label for="language">
                <select class="btn btn-outline-success my-2 my-sm-0"
                        style="background-color: lightgreen"
                        id="language" onchange="location=value">
                    <option value="controller?command=change_lang&language=en_En"
                    ${sessionScope.language == 'en_En' ? 'selected' : ''}>
                        <fmt:message key="label.english"/></option>
                    <option value="controller?command=change_lang&language=ru_RU"
                    ${sessionScope.language == 'ru_RU' ? 'selected' : ''}>
                        <fmt:message key="label.russian"/></option>
                </select>
            </label>
        </form>

        <form class="form-inline my-2 my-lg-0" action="http://www.google.com/search" method="get">
            <fmt:message key="button.searchgoogle" var="searchgoogle"/>
            <input type="text" name="q" class="form-control mr-sm-2" placeholder="${searchgoogle}" aria-label="${searchgoogle}">
            <input type="submit" class="btn btn-outline-success my-2 my-sm-0" style="background-color: lightgreen"
                   value="${searchgoogle}">
        </form>

    </div>
</nav>

<div class="modal fade" id="authModal" tabindex="-1" role="dialog" aria-labelledby="authModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="authModalLabel">
                    ${authVal}
                </h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form name="loginForm" method="post" action="controller">
                        <input type="hidden" name="command" value="login"/>
                        <div class="form-row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="inputAuthModal">
                                        <fmt:message key="authpage.username" var="username"/>
                                        ${username}
                                    </label>
                                    <input type="text" name="user" class="form-control" id="inputAuthModal"
                                           placeholder="${username}" required
                                           pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                                    <small id="loginHelp" class="form-text text-muted">
                                        <fmt:message key="authpage.loginhelp" var="loginhelp"/>
                                    </small>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="inputPassModal">
                                        <fmt:message key="authpage.password" var="pass"/>
                                        ${pass}
                                    </label>
                                    <input type="password" name="password"
                                           class="form-control" id="inputPassModal"
                                           placeholder="${pass}" required
                                           pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                                    <small id="passHelp" class="form-text text-muted">
                                        <fmt:message key="authpage.passhelp" var="passhelp"/>
                                    </small>
                                </div>
                            </div>
                        </div>
                        <fmt:message key="button.enter" var="enter"/>
                        <input type="submit" class="btn btn-primary float-center"
                               value="${enter}">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <fmt:message key="button.close" var="close"/>
                <input type="button" class="btn btn-secondary" data-dismiss="modal"
                       value="${close}">
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="regModal" tabindex="-1" role="dialog" aria-labelledby="regModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="regModalLabel">
                    ${regVal}
                </h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form name="registerForm" method="post" action="controller">
                        <input type="hidden" name="command" value="register">
                        <div class="form-row">
                            <div class="col">
                                <div class="form-froup">
                                    <label for="inputRegModal">
                                        ${username}
                                    </label>
                                    <input type="text" name="user" class="form-control" id="inputRegModal"
                                           placeholder="${username}" required
                                           pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                                    <small id="loginHelpReg" class="form-text text-muted">
                                        ${loginhelp}
                                    </small>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-froup">
                                    <label for="inputPassRegModal">
                                        ${pass}
                                    </label>
                                    <input type="password" name="password" class="form-control"
                                           id="inputPassRegModal"
                                           placeholder="${pass}" required
                                           pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                                    <small id="passHelpReg" class="form-text text-muted">
                                        ${passhelp}
                                    </small>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-froup">
                                    <label for="inputEmailModal">
                                        <fmt:message key="regpage.email" var="emailVal"/>
                                        ${emailVal}
                                    </label>
                                    <input type="email" name="email" class="form-control" id="inputEmailModal"
                                           placeholder="${emailVal}" required
                                           pattern="[\w-\.\+!#$%&’*+\/=?`{|}~^]+@[\w-]+\.[\w]{2,6}"/>
                                    <small id="emailHelp" class="form-text text-muted">
                                        <fmt:message key="regpage.emailhelp"/>
                                    </small>
                                </div>
                            </div>
                        </div>
                        <fmt:message key="button.registerme" var="register"/>
                        <input type="submit" class="btn btn-primary float-center"
                               value="${register}">
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <input type="button" class="btn btn-secondary" data-dismiss="modal"
                       value="${close}">
            </div>
        </div>
    </div>
</div>

</body>