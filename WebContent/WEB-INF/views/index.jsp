<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- --------------------------------------- -->
<!-- Required meta tags -->
<!-- Bootstrap CSS -->
<%@ include file="boot_strap_css.html" %>
<!-- END: Boostrap CSS and Required meta tags -->
<!-- --------------------------------------- -->
<!-- ----- DOES NOT WORK THE WAY I WANT! (specificity preference rule breakdown!:~()
<link rel="stylesheet" href="../css/activitymonitor.css">
<link rel="stylesheet" href="<c:url value="/css/activitymonitor.css" />" > 
--!>

<!-- --------------------------------------- -->
<meta charset="ISO-8859-1">
<title>Activity Monitor Home</title>

<style type="text/css">
.jgb_custom {
    color: red;
    text-align: center;
}
</style>
</head>
<body>
<div>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
</div>

    <h1 class="jgb_custom">
<!--     <h1 style="color:red; text-align:center;"> -->
        Welcome to Activity Monitor
    </h1>
    <h4 style="color:blue; font-style: italic; text-align:center;">
        Exercise your body, your mind, or both!
    </h4>
    <h4 style="color:green; font-style: italic; text-align:center;">
        Set goals, track your activities, then monitor your progress.
    </h4>
    <h4 style="color:purple; font-style: italic; text-align:center;">
        Fully configurable to track ANY activity!
    </h4>
    <hr style="border-width: .2em; border-style: groove; color: red;">
    <div class="container">
        <span>Existing users please click Login.<br>New users please click Register to create an account.<br></span>
    </div>
    <div class="container">
        <form action="login" method="post"><table><tr><td><input type="submit" name="login" value="Login"/></td></tr></table></form>
        <form action="register" method="post"><table><tr><td><input type="submit" name="register" value="Register"/></td></tr></table></form>
    </div>

<div>
<%@ include file="footer.jsp" %>
</div>
<!-- --------------------------------------- -->
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<%@ include file="boot_strap_js.html" %>
</body>
</html>
