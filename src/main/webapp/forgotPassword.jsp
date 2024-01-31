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
    <form action="/Codegram/auth/reset" method="post">
        <label class="loginlabel">Mail:</label>
        <input class="logininput" type="email" required name="mail"/>
        <input class="logininput" type="submit" value="Reset password"/>
    </form>
    <div class="button-container">
        <a href="/Codegram/auth/loginPage" class="logininput signup-button">Cancel</a>
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
