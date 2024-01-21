<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css" />">
</head>
<body>
<sec:authorize access="!isAuthenticated()">
<div class='prijava'>
    <label class="title">Log in</label>
    <form action="/Codegram/auth/login" method="post">
        <label class="loginlabel">Username:</label>
        <input class="logininput" type="text" min="6" max="20" required name="username"/>
        <label class="loginlabel">Password:</label>
        <input class="logininput" type="password" min="6" max="50" required name="password"/>
        <input class="logininput" type="submit" value="Log in"/>
    </form>
    <div class="button-container">
        <a href="/Codegram/auth/registerPage" class="logininput signup-button">Sign up</a>
    </div>
    <div class="button-container">
        <a href="/Codegram/home" class="logininput signup-button">Continue as guest</a>
    </div>
    <div class="button-container">
        <label class="loginlabel">
            <a href="/Codegram/oauth/forgotPassword">Forgot your password?</a>
        </label>
    </div>
    <c:if test="${!empty message}">
        <label class="loginlabel">${message}</label>
    </c:if>
    </sec:authorize>
</div>
<sec:authorize access="isAuthenticated()">
<div class='prijava'>
    <label class="title">You are already logged in!</label>
    <button class="logininput" type="submit" onclick="location.href='/Codegram/home'">Continue to home</button>
</div>
</sec:authorize>
</body>
</html>
