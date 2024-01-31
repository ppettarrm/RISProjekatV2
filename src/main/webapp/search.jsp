<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/posts.css" />">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="pocetna">
    <%@ include file="utils/header.jsp" %>
    <div class="posts">
        <c:if test="${empty users}">
            <h1>No user found!</h1><br/>
        </c:if>
        <c:if test="${!empty users}">
            <h1>Users:</h1><br/>
        </c:if>
        <c:forEach items="${users}" var="k">
            <div class="post">
                <div class="postHeader">
                    <a href="/Codegram/profile/${k.username}" class="profile-link">
                        <label>${k.username}</label>
                    </a>
                </div>
            </div>
        </c:forEach>
        <c:if test="${!empty posts}">
            <h1>Posts:</h1><br/>
        </c:if>
        <c:if test="${empty posts}">
            <h1>No post found!</h1><br/>
        </c:if>
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
                        <button class="action-button">Like <i class="fa fa-thumbs-up"></i></button>
                    </form>
                    <button class="action-button">Comment</button>
                    <form action="/Codegram/postAction/save?post_id=${p.id}" method="post" target="hiddenframe${p.id}">
                        <button class="action-button">Save <i class="fa fa-save"></i></button>
                    </form>
<%--                    <c:if test="${korisnik.id == p.userid.id}">--%>
<%--                        <button class="action-button">Delete</button>--%>
<%--                    </c:if>--%>
                </div>
                </sec:authorize>
            </div>
        </c:forEach>
    </div>
</div>
<%@ include file="utils/footer.jsp" %>
</body>
</html>