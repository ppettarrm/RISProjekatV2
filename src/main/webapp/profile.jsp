<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Codegram: ${user.username}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/posts.css" />">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="pocetna">

    <%@ include file="utils/header.jsp" %>

    <div class="profile-info">
        <div class="profile-picture">
            <c:if test="${!empty base64Image}">
                <img src="data:image/jpeg;base64,${base64Image}" alt="Profile Picture">
            </c:if>
            <c:if test="${empty base64Image}">
                <img src="<c:url value="/utils/profilePicture.png" />" alt="Profile Picture">
            </c:if>
        </div>
        <div class="profile-username">
            ${user.username}
        </div>
        <div style="display: flex;">
            <div style="display: inline">
                <h3>${followers}</h3>
                <h5>Followers</h5>
            </div>
            <div style="width: 10px"></div>
            <div style="display: inline">
                <h3>${following}</h3>
                <h5>Following</h5>
            </div>
        </div>
        <iframe name="hiddenframe" hidden></iframe>
        <form action="/Codegram/follow?username=${user.username}" method="post" target="hiddenframe">
            <button class="action-button">Follow</button>
        </form>
    </div>


    <div class="posts">
        <c:if test="${empty posts}">
            <h1>User does not have any posts.</h1>
        </c:if>
        <c:if test="${!empty posts}">
        <c:forEach items="${posts}" var="p">
            <iframe name="hiddenframe${p.id}" hidden></iframe>
            <div class="post">
                <div class="postHeader">
                    <a href="/Codegram/profile/${p.korisnik.username}" class="profile-link">
                        <label>${p.korisnik.username}</label>
                    </a>
                </div>
                <div class="postBody" onclick="location.href='/Codegram/post/${p.id}'">
                    <c:if test="${!empty p.slika}">
                        <img src="data:image/jpeg;base64,${images[p.id]}" alt="Post Image"/>
                    </c:if>
                    <pre class="postText"><code><c:out value="${p.opis}" escapeXml="true" /></code></pre>
                </div>
                <sec:authorize access="isAuthenticated()">
                    <div class="postActions">
                        <form action="/Codegram/postAction/like?post_id=${p.id}" method="post" target="hiddenframe${p.id}">
                            <button class="action-button"><i class="fa fa-thumbs-up"></i></button>
                        </form>
                        <button class="action-button">Comment</button>
                        <button class="action-button">Save</button>
                            <%--                    <c:if test="${korisnik.id == p.userid.id}">--%>
                            <%--                        <button class="action-button">Delete</button>--%>
                            <%--                    </c:if>--%>
                    </div>
                </sec:authorize>
            </div>
        </c:forEach>
        </c:if>
    </div>
</div>
<%@ include file="utils/footer.jsp" %>
</body>
</html>