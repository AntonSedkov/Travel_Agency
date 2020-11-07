<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>

<c:if test="${sessionScope.role.equals('guest')}">
    <jsp:forward page="controller?command=guest_in"/>
</c:if>

<c:if test="${sessionScope.role.equals('user')}">
    <jsp:forward page="controller?command=user_in"/>
</c:if>

<c:if test="${sessionScope.role.equals('moderator')}">
    <jsp:forward page="controller?command=moderator_in"/>
</c:if>

<c:if test="${sessionScope.role.equals('admin')}">
    <jsp:forward page="controller?command=admin_in"/>
</c:if>

</body>
</html>