<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body>
    <div class="container-fluid navbar-expand-lg navbar-dark" style="background-color: cornflowerblue">
        <div class="form-row">
                <div class="col m-2" id="contact">
                    <h3><fmt:message key="startpage.contact"/></h3>
                    <p><fmt:message key="startpage.address"/></p>
                </div>
                <div class="col m-2" id="social">
                    <h3><fmt:message key="startpage.social"/></h3>
                    <a href="http://facebook.com" target="_blank" class="navbar-brand">
                        <img src="${pageContext.request.contextPath}/pics/fb.png" alt="facebook account"/>
                    </a>
                    <a href="http://vk.com" target="_blank" class="navbar-brand">
                        <img src="${pageContext.request.contextPath}/pics/vk.png" alt="vk account"/>
                    </a>
                    <a href="http://twitter.com" target="_blank" class="navbar-brand">
                        <img src="${pageContext.request.contextPath}/pics/tw.png" alt="twitter account"/>
                    </a>
                </div>
                <div class="col m-2">
                    <fmt:message key="startpage.phone"/><br/>
                    <fmt:message key="startpage.skype"/><br/>
                    <fmt:message key="startpage.emailaddress" var="emailaddress"/>
                    <a href="mailto:${emailaddress}">${emailaddress}</a><br/>
                    <p>
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script>
                        <fmt:message key="startpage.aftercopyright"/><p>
                </div>
            </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/travel_agency.js"></script>

</body>