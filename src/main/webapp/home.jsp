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
        <c:forEach items="${posts}" var="p">
            <div class="post">
                <div class="postHeader">
                    <a href="/Codegram/profile/${p.korisnik.username}" class="profile-link">
                        <label>${p.korisnik.username}</label>
                    </a>
                </div>
                <div class="postBody" onclick="location.href='/Codegram/post/${p.id}'">
                    <pre class="postText">
                    <code>
                        <c:out value="${p.opis}" escapeXml="true" />
                    </code>
                    </pre>
                    <c:if test="${!empty p.slika}">
                        <img src="data:image/jpeg;base64,${images[p.id]}" alt="Post Image"/>
                    </c:if>
                </div>
                <div class="postActions">
                    <button class="action-button">Like</button>
                    <button class="action-button">Comment</button>
                    <button class="action-button">Save</button>
<%--                    <c:if test="${korisnik.id == p.userid.id}">--%>
<%--                        <button class="action-button">Delete</button>--%>
<%--                    </c:if>--%>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@ include file="utils/footer.jsp" %>
</body>
</html>