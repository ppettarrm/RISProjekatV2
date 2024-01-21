<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css" />">
</head>
<body>
<div class='prijava'>
    <label class="title">Register</label>
    <c:if test="${empty code}">
    <form action="/Codegram/auth/register" method="POST" enctype="multipart/form-data">
        <label class="loginlabel">Email:</label>
        <input class="logininput" type="email" name="mail" required/>
        <label class="loginlabel">Username:</label>
        <input class="logininput" type="text" name="username" required/>
        <label class="loginlabel">Password:</label>
        <input class="logininput" type="password" name="password" required/>
        <label class="loginlabel">Confirm Password:</label>
        <input class="logininput" type="password" name="confirmPassword" required/>
        <label class="loginlabel">Profile picture:</label>
        <input class="logininput" type="file" name="userImage" accept="image/*" required/>
        <div class="button-container">
            <input class="logininput" type="submit" value="Sign up"/>
            <a href="/Codegram/auth/loginPage" class="logininput signup-button">Log in</a>
            <a href="/Codegram/home" class="logininput signup-button">Continue as guest</a>
        </div>
    </form>
    <c:if test="${!empty message}">
        <label class="loginlabel">${message}</label>
    </c:if>
    </c:if>
    <c:if test="${!empty code}">
        <form action="/Codegram/oauth/confirm" method="POST" enctype="multipart/form-data">
            <label class="loginlabel">Code:</label>
            <input class="logininput" type="number" name="code" min="1000" max="9999" required/>
            <input class="logininput" type="submit" value="Confirm"/>
        </form>
    </c:if>
</div>
</body>
</html>
