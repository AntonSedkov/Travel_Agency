<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css">
<head>
    <title>Register Page</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<h1>Register form</h1>
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
        <label>Email:</label>
        <label>
            <input type="email" name="email" placeholder="Em@il.me" required
            <%--pattern="[\w-\.\+!#$%&’*+\/=?`{|}~^]+@[\w-]+\.[\w]{2,6}"--%>/>
        </label>
    </div>
    <input type="submit" class="btn btn-login float-center" value="Go"/>
</form>

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
