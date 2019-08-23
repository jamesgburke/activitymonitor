<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.SpringMVC05.entities.User" %>
<!DOCTYPE html>
<html>
<head>
<!-- --------------------------------------- -->
<!-- Required meta tags -->
<!-- Bootstrap CSS -->
<%@ include file="boot_strap_css.html" %>
<!-- END: Boostrap CSS and Required meta tags -->
<!-- --------------------------------------- -->

<meta charset="ISO-8859-1">
<title>User</title>
</head>
<%-- 
    User user = (User) request.getAttribute("sUser");
--%>
<body>
    <div>
        <h1>SPRING MVC & JPA APP 5.0</h1>
        <hr>
        <table>
            <tr><td><a href="index">Home</a></td></tr>
            <tr><td><a href="login">Login User</a></td></tr>
            <tr><td><a href="register">Register User</a></td>
            <tr><td><a href="showUsers">All Users</a></td></tr>
        </table>
        <hr>
    </div>
    <div>
        <h2>User Info</h2>
    </div>
    <div>
        <hr>
        <span>here's where the user info goes</span>
        <br>
        <table>
            <tr>
                <td><label>ID:&nbsp;</label></td>
                <td><input type="text" name="id" value="${sUser.id}" readonly="readonly"></td>
            </tr>
            <tr>
                <td><label>User name:&nbsp;</label></td>
                <td><input type="text" name="username" value="${sUser.username}" readonly="readonly"></td>
            </tr>
            <tr>
                <td><label>Password:&nbsp;</label></td>
                <td><input type="text" name="password" value="${sUser.password}" readonly="readonly"></td>
            </tr>
            <tr>
                <td><label>Email:&nbsp;</label></td>
                <td><input type="text" name="email" value="${sUser.email}" readonly="readonly"></td>
            </tr>
        </table>
        <hr>
    </div>
    
<!-- --------------------------------------- -->
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<%@ include file="boot_strap_js.html" %>
</body>
</html>