<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="udt" uri="customtags" %>

    <body>

        <nav class="navbar navbar-expand-lg navbar-dark sticky-top" style="background-color: cornflowerblue">

            <c:if test="${role.equals('guest')}" var="guesthomepage"/>
            <c:if test="${role.equals('user')}" var="userhomepage"/>
            <c:if test="${role.equals('moderator')}" var="moderatorhomepage"/>
            <c:if test="${role.equals('admin')}" var="adminhomepage"/>

            <c:if test="${guesthomepage}">
                <a href="controller?command=guest_in" class="navbar-brand">
                    <img src="${pageContext.request.contextPath}/pics/img_logo.jpg" width="30" height="30" alt="logo">
                </a>
            </c:if>

            <c:if test="${userhomepage}">
                <a href="controller?command=user_in" class="navbar-brand">
                    <img src="${pageContext.request.contextPath}/pics/img_logo.jpg" width="30" height="30" alt="logo">
                </a>
            </c:if>

            <c:if test="${moderatorhomepage}">
                <a href="controller?command=moderator_in" class="navbar-brand">
                    <img src="${pageContext.request.contextPath}/pics/img_logo.jpg" width="30" height="30" alt="logo">
                </a>
            </c:if>

            <c:if test="${adminhomepage}">
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

                    <c:if test="${guesthomepage}">
                        <li class="nav-item active">
                            <a href="controller?command=guest_in" class="nav-link">
                                <fmt:message key="startpage.homebutton"/></a>
                        </li>
                    </c:if>

                    <c:if test="${userhomepage}">
                        <li class="nav-item active">
                            <a href="controller?command=user_in" class="nav-link">
                                <fmt:message key="startpage.homebutton"/></a>
                        </li>
                    </c:if>

                    <c:if test="${moderatorhomepage}">
                        <li class="nav-item active">
                            <a href="controller?command=moderator_in" class="nav-link">
                                <fmt:message key="startpage.homebutton"/></a>
                        </li>
                    </c:if>

                    <c:if test="${adminhomepage}">
                        <li class="nav-item active">
                            <a href="controller?command=admin_in" class="nav-link">
                                <fmt:message key="startpage.homebutton"/></a>
                        </li>
                    </c:if>

                    <c:if test="${guesthomepage}">
                        <li class="nav-item">
                            <a href="#" class="nav-link" data-toggle="modal" data-target="#authModal">
                                <fmt:message key="startpage.authbutton" var="authVal"/> ${authVal}</a>
                        </li>
                    </c:if>

                    <c:if test="${guesthomepage}">
                        <li class="nav-item">
                            <a href="#" class="nav-link" data-toggle="modal" data-target="#regModal">
                                <fmt:message key="startpage.registerbutton" var="regVal"/> ${regVal}</a>
                        </li>
                    </c:if>

                    <c:if test="${guesthomepage||userhomepage}">
                        <li class="nav-item">
                            <a href="controller?command=change_page&targetpage=path.guest.about" class="nav-link">
                                <fmt:message key="startpage.aboutusbutton"/></a>
                        </li>
                    </c:if>

                    <c:if test="${userhomepage||moderatorhomepage||adminhomepage}">
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
                                    ${language == 'en_En' ? 'selected' : ''}>
                                <fmt:message key="label.english"/></option>
                            <option value="controller?command=change_lang&language=ru_RU"
                                    ${language == 'ru_RU' ? 'selected' : ''}>
                                <fmt:message key="label.russian"/></option>
                        </select>
                    </label>
                </form>

                <form class="form-inline my-2 my-lg-0" action="http://www.google.com/search" method="get">
                    <fmt:message key="button.searchgoogle" var="search"/>
                    <input type="text" name="q" class="form-control mr-sm-2" placeholder="${search}" aria-label="${search}">
                    <input type="submit" class="btn btn-outline-success my-2 my-sm-0" style="background-color: lightgreen"
                           value="${search}">
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
                            <form name="loginForm" method="post" action="controller/">
                                <input type="hidden" name="command" value="login"/>
                                <div class="form-row">
                                    <div class="col">
                                        <div class="form-group">
                                            <label for="exampleInputAuth">
                                                <fmt:message key="authpage.username" var="username"/>
                                                ${username}
                                            </label>
                                            <input type="text" name="user" class="form-control" id="exampleInputAuth"
                                                   placeholder="${username}" required
                                                   pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                                            <small id="loginHelp" class="form-text text-muted">
                                                <fmt:message key="authpage.loginhelp" var="loginhelp"/>
                                            </small>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="form-group">
                                            <label for="exampleInputAuth">
                                                <fmt:message key="authpage.password" var="pass"/>
                                                ${pass}
                                            </label>
                                            <input type="password" name="password"
                                                   class="form-control" id="exampleInputPass"
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
                            <form name="registerForm" method="post" action="controller/">
                                <input type="hidden" name="command" value="register">
                                <div class="form-row">
                                    <div class="col">
                                        <div class="form-froup">
                                            <label for="exampleInputReg">
                                                ${username}
                                            </label>
                                            <input type="text" name="user" class="form-control" id="exampleInputReg"
                                                   placeholder="${username}" required
                                                   pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                                            <small id="loginHelpReg" class="form-text text-muted">
                                                ${loginhelp}
                                            </small>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="form-froup">
                                            <label for="exampleInputReg">
                                                ${pass}
                                            </label>
                                            <input type="password" name="password" class="form-control"
                                                   id="exampleInputPassReg"
                                                   placeholder="${pass}" required
                                                   pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                                            <small id="passHelpReg" class="form-text text-muted">
                                                ${passhelp}
                                            </small>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="form-froup">
                                            <label for="exampleInputReg">
                                                <fmt:message key="regpage.email" var="emailVal"/>
                                                ${emailVal}
                                            </label>
                                            <input type="email" name="email" class="form-control" id="exampleInputEmail"
                                                   placeholder="${emailVal}" required
                                                   pattern="[\w-\.\+!#$%&’*+\/=?`{|}~^]+@[\w-]+\.[\w]{2,6}"/>
                                            <small id="emailHelp" class="form-text text-muted">
                                                <fmt:message key="regpage.emailhelp"/>
                                            </small>
                                        </div>
                                    </div>
                                </div>
                                <fmt:message key="button.register" var="register"/>
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