<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css" />">
</head>
<body>
<div class='prijava'>
<sec:authorize access="isAuthenticated()">
    <label class="title">Add post</label>
    <form action="/Codegram/addPost/" method="post" enctype="multipart/form-data">
        <label class="loginlabel">Description: *</label>
        <label>
            <textarea class="logininput" type="" required name="description" minlength="10"></textarea>
        </label>
        <label class="loginlabel">Image:</label>
        <input class="logininput" type="file" name="image"/>
        <div class="button-container">
            <input class="logininput" type="submit" value="Add post"/> <a
                href="/Codegram/home" class="logininput signup-button">Cancel</a>
        </div>
    </form>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <label class="title">Log in to add post!</label>
    <button class="logininput" onclick="location.href='/Codegram/auth/loginPage'">Log in</button>
</sec:authorize>
</div>
</body>
</html>



