<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin panel</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/posts.css" />">
    <style>
        /* Add the styles from styles/posts.css here */

        /* body styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background: linear-gradient(to bottom, #1e1e1e, #333) no-repeat;
            color: #fff;
            align-items: center;
            justify-content: center;
            text-align: center;
        }

        /* .header styles */
        .header {
            width: 100%;
            background-color: #333;
            color: #fff;
            padding: 10px;
            display: flex;
            position: fixed;
            justify-content: space-between;
            align-items: center;
        }

        /* .header img styles */
        .header img {
            border-radius: 50%;
            margin-right: 10px;
        }

        /* Add other styles similarly */

    </style>
    <style>
        /* Additional styles for your table */
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px; /* Adjust margin as needed */
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            color: black;
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<%@ include file="utils/header.jsp" %>
<h2>User Information</h2>
<div class="posts">
    <table>
        <tr>
            <th>Username</th>
            <th>Number of Posts</th>
            <th>Number of Followers</th>
            <th>Profile Link</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td>${postovi[user.id]}</td>
                <td>${following[user.id]}</td>
                <td><a href="/Codegram/profile/${user.username}">Profile</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="utils/footer.jsp" %>
</body>
</html>
