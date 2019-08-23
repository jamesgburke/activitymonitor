<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>Activity Monitor Login</title>
</head>
<body>
<div>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
</div>

<div>
    <h2 style="color:red; text-align:center;">LOGIN FORM</h2>
    <br>
    <div style="align:center;">
        <form:form action="loginUser" method="post" modelAttribute="sUser"> <%-- modelAttribute="userKey"> --%>
            <table style="align:center;">
                <tr>
                    <td><label>User name:&nbsp;</label></td>
                    <td>
                        <form:input type="text" path="username" placeholder="enter User Name"/>
                        <form:errors path="username"/>
                    </td>
                </tr>
                <tr>
                    <td><label>Password:&nbsp;</label></td>
                    <td><form:input type="password" path="password" placeholder="enter Password"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Save"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form:form>
    </div>
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
