<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.content"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css">
<html lang="${language}">
<head>
    <title><fmt:message key="loginpage.title"/></title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>

<form>

    <label for="language"><select id="language" name="language" onchange="submit()">
        <option><fmt:message key="label.language"/></option>
        <option value="en_US" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select></label>
</form>

<section class="login-block">
    <div class="container">
        <div class="row">
            <div class="col-md-4 login-sec">
                <h2 class="text-center"><fmt:message key="loginpage.formtitle"/></h2>
                <form name="loginForm" method="post" action="controller/">
                    <input type="hidden" name="command" value="login"/>
                    <div class="form-group">
                        <label class="text-uppercase"><fmt:message key="loginpage.login"/></label>
                        <label>
                            <input type="text" name="login" class="form-control" placeholder="User" required
                                   pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="text-uppercase"><fmt:message key="loginpage.password"/></label>
                        <label>
                            <input type="password" name="password" class="form-control" placeholder="Pass"
                                   required pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                        </label>
                    </div>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input type="checkbox" class="form-check-input"/>
                            <small><fmt:message key="loginpage.rememberme"/></small>
                        </label>
                    </div>
                    <fmt:message key="label.submit" var="submitValue"/>
                    <input type="submit" class="btn btn-login float-right" value="${submitValue}"/>
                    <div>
                        ${loginerror}<br/>
                        ${wrongaction}<br/>
                        ${nullpage}<br/>
                    </div>
                </form>
                <br/>


                <h2 class="text-center">Register form</h2>
                <form name="registerForm" method="post" action="controller/">
                    <input type="hidden" name="command" value="register">
                    <div class="form-group">
                        <label class="text-uppercase">Login:</label>
                        <label>
                            <input type="text" name="login" placeholder="Login" required
                                   pattern="^(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="text-uppercase">Password: </label>
                        <label>
                            <input type="password" name="password" placeholder="Pass" required
                                   pattern="^(?=.*?\d)(?=.*?[A-Z])(?=.*?[a-z])[\w]{6,16}$"/>
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="text-uppercase">Email:</label>
                        <label>
                            <input type="email" name="email" placeholder="Em@il.me" required
                                   pattern="[\w-\.\+!#$%&’*+\/=?`{|}~^]+@[\w-]+\.[\w]{2,6}"/>
                        </label>
                    </div>
                    <input type="submit" class="btn btn-login float-center" value="Go"/>
                    <div>
                        ${registererror}<br/>
                    </div>
                </form>

                <form>
                    <div>
                        <a href="controller?command=change_page&targetpage=path.page.register"
                           class="btn btn-login float-right">To Register</a>
                    </div>
                </form>


                <div class="copy-text">Created with <i class="fa fa-heart"></i> from Antonius :)</div>
            </div>
            <div class="col-md-8 banner-sec">
                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    </ol>
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active">
                            <img class="d-block img-fluid" src="https://static.pexels.com/photos/33972/pexels-photo.jpg"
                                 alt="First slide">
                            <div class="carousel-caption d-none d-md-block">
                                <div class="banner-text">
                                    <h2>This is Heaven</h2>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                                        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                                        nostrud exercitation</p>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img class="d-block img-fluid"
                                 src="https://images.pexels.com/photos/7097/people-coffee-tea-meeting.jpg"
                                 alt="First slide">
                            <div class="carousel-caption d-none d-md-block">
                                <div class="banner-text">
                                    <h2>This is Heaven</h2>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                                        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                                        nostrud exercitation</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>
</html>