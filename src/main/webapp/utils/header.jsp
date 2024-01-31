<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Codegram</title>
    <link rel="stylesheet" href="<c:url value="/css/posts.css" />" type="text/css">
</head>
<body>
<header>
    <div class="header">
<%--        <a href="/Codegram/home"><img src="" alt="Logo" class="logo" width="50" height="50"/></a>--%>
        <div>
            <form action="/Codegram/search/" method="post">
                <input type="text" class="logininput" name="search">
                <input type="submit" class="action-button" value="Search">
            </form>
        </div>
        <div style="align-items: baseline">
            <button onclick="location.href='/Codegram/home'" class="logininput" style="display: inline-block;">Home</button>
            <sec:authorize access="isAuthenticated()">
                <button onclick="location.href='/Codegram/addPost/'" class="logininput" style="display: inline-block;">Add post</button>
                <button onclick="location.href='/Codegram/saved/'" class="logininput" style="display: inline-block;">Saved posts</button>
                <button onclick="location.href='/Codegram/auth/logout'" class="logininput">Log out</button>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <button onclick="location.href='/Codegram/auth/loginPage'" class="logininput" style="display: inline-block;">Log in</button>
            </sec:authorize>
        </div>
    </div>

</header>
</body>
</html>