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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ta_login.css" type="text/css">

<html lang="${language}">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="startpage.title"/></title>
</head>

<body style="background-color: burlywood">

<nav class="navbar navbar-expand-lg navbar-dark sticky-top" style="background-color: cornflowerblue">
    <a href="${pageContext.request.contextPath}/index.jsp" class="navbar-brand">
        <img src="${pageContext.request.contextPath}/pics/img_logo.jpg" width="30" height="30" alt="logo">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle-navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a href="${pageContext.request.contextPath}/index.jsp" class="nav-link">
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
            <li class="nav-item">
                <a href="controller?command=change_page&targetpage=path.page.about" class="nav-link">
                    <fmt:message key="startpage.aboutusbutton"/></a>
            </li>
        </ul>
        <form class="form-inline m-2 my-lg-0">
            <label for="language"><select class="btn btn-outline-success my-2 my-sm-0"
                                          style="background-color: lightgreen"
                                          id="language" name="language" onchange="submit()">
                <option><fmt:message key="label.language"/></option>
                <option value="en_US" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru_RU" ${language == 'ru' ? 'selected' : ''}>Русский</option>
            </select></label>
        </form>

        <form class="form-inline my-2 my-lg-0" action="http://www.google.com/search" method="get">
            <fmt:message key="button.search" var="search"/>
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
                                        <fmt:message key="authpage.login" var="login"/>
                                        ${login}
                                    </label>
                                    <input type="text" name="login" class="form-control" id="exampleInputAuth"
                                           placeholder="${login}" required
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
                        <div class="text-uppercase">
                            ${loginerror}<br/>
                        </div>
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
                                        ${login}
                                    </label>
                                    <input type="text" name="login" class="form-control" id="exampleInputReg"
                                           placeholder="${login}" required
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
                        <div class="text-uppercase">
                            ${registererror}<br/>
                        </div>
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

<div class="container-fluid p-0">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li class="active" data-target="#carouselExampleIndicators" data-slide-to="0"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath}/pics/carouselOne.jpg" alt="" class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/pics/carouselTwo.jpg" alt="" class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/pics/carouselThree.jpg" alt="" class="d-block w-100">
            </div>
        </div>
        <a href="#carouselExampleIndicators" class="carousel-control-prev" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">
                <fmt:message key="button.previous"/>
            </span>
        </a>
        <a href="#carouselExampleIndicators" class="carousel-control-next" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">
                <fmt:message key="button.next"/>
            </span>
        </a>
    </div>
</div>

<div class="container-fluid">
    <div class="container">
        <div class="row text-center justify-content-center m-3 p-1">
            <h3><fmt:message key="startpage.maincontext"/></h3>
        </div>
    </div>
</div>


<div class="container-fluid mt-5" style="background-color:lightseagreen">
    <div class="container p-5">
        <div class="card-deck">
            <div class="card text-center" style="width: 20rem;">
                <img src="${pageContext.request.contextPath}/pics/default.jpg" alt="" class="card-img-top">
                <div class="card-body">
                    <h4 class="card-title">Title</h4>
                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Culpa incidunt
                        ipsam
                        laboriosam quasi. Accusantium, aspernatur distinctio doloremque ea est, eum facere fuga
                        inventore
                        laborum nihil non, omnis quas sit voluptatum.</p>
                    <a href="#" class="btn btn-primary">Submit</a>
                </div>
            </div>
            <div class="card text-center" style="width: 20rem;">
                <img src="${pageContext.request.contextPath}/pics/default.jpg" alt="" class="card-img-top">
                <div class="card-body">
                    <h4 class="card-title">Title</h4>
                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Culpa incidunt
                        ipsam
                        laboriosam quasi. Accusantium, aspernatur distinctio doloremque ea est, eum facere fuga
                        inventore
                        laborum nihil non, omnis quas sit voluptatum.</p>
                    <a href="#" class="btn btn-primary">Submit</a>
                </div>
            </div>
            <div class="card text-center" style="width: 20rem;">
                <img src="${pageContext.request.contextPath}/pics/default.jpg" alt="" class="card-img-top">
                <div class="card-body">
                    <h4 class="card-title">Title</h4>
                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Culpa incidunt
                        ipsam
                        laboriosam quasi. Accusantium, aspernatur distinctio doloremque ea est, eum facere fuga
                        inventore
                        laborum nihil non, omnis quas sit voluptatum.</p>
                    <a href="#" class="btn btn-primary">Submit</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="contaner p-5">
    <div class="row">
        <div class="col">
            <div class="embed-responsive embed-responsive-16by9">
                <iframe src="https://www.youtube.com/embed/Tc0LU7y3btU"
                        class="embed-responsive-item" allowfullscreen></iframe>
            </div>
        </div>
        <div class="col">
            <div class="embed-responsive embed-responsive-16by9">
                <iframe src="https://www.youtube.com/embed/QNdYybI3Pgs"
                        class="embed-responsive-item" allowfullscreen></iframe>
            </div>
        </div>
        <div class="col">
            <div class="embed-responsive embed-responsive-16by9">
                <iframe src="https://www.youtube.com/embed/yZc9vCXLPAo"
                        class="embed-responsive-item" allowfullscreen></iframe>
            </div>
        </div>
    </div>
</div>

<div class="footer row-fluid" style="background-color: cornflowerblue">
    <div class="container-fluid">
        <div class="form-row">
            <div class="col m-2" id="contact">
                <h3><fmt:message key="startpage.contact"/></h3>
                <p><fmt:message key="startpage.address"/></p>
            </div>
            <div class="col m-2" id="social">
                <h3><fmt:message key="startpage.social"/></h3>
                <a href="http://facebook.com" target="_blank" class="navbar-brand">
                    <img src="${pageContext.request.contextPath}/pics/fb.png" alt=""/>
                </a>
                <a href="http://vk.com" target="_blank" class="navbar-brand">
                    <img src="${pageContext.request.contextPath}/pics/vk.png" alt=""/>
                </a>
                <a href="http://twitter.com" target="_blank" class="navbar-brand">
                    <img src="${pageContext.request.contextPath}/pics/tw.png" alt=""/>
                </a>
            </div>
            <div class="col m-2">
                <fmt:message key="startpage.phone"/><br/>
                <fmt:message key="startpage.skype"/><br/>
                <fmt:message key="startpage.emailaddress" var="emailaddress"/>
                <a href="mailto:${emailaddress}">${emailaddress}</a><br/>
                <p><fmt:message key="startpage.copyright"/><p>
            </div>
        </div>
    </div>
</div>

</body>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/ta_script.js"></script>
</html>
