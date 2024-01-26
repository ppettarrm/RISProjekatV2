<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Feed</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/posts.css" />">
</head>
<body>
<div class="pocetna">

    <%@ include file="utils/header.jsp" %>
    <div class="posts">
        <c:if test="${!empty post}">
            <div class="post">
                <div class="postHeader">
                    <a href="/Codegram/profile/${post.korisnik.username}" class="profile-link">
                        <label>${post.korisnik.username}</label>
                    </a>
                </div>
                <div class="postBody">
                    <c:if test="${!empty post.slika}">
                        <img src="data:image/jpeg;base64,${images}" alt="Post Image"/>
                    </c:if>
                    <pre class="postText" style="color: white">
                        <code>
                            <c:out value="${post.opis}" escapeXml="true"/>
                        </code>
                    </pre>
                </div>
                <div class="postActions">
                    <button class="action-button">Like</button>
                    <button class="action-button">Comment</button>
                    <button class="action-button">Save</button>
                    <c:if test="${korisnik.id == post.korisnik.id}">
                        <button class="action-button">Delete</button>
                    </c:if>
                </div>
            </div>
        </c:if>
        <c:if test="${empty post}">
            <h1>Post does not exists!</h1>
        </c:if>
    </div>
</div>
<%@ include file="utils/footer.jsp" %>
</body>
</html>