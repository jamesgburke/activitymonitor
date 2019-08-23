<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
<title>Activity Monitor Activities_2</title>
</head>
<body>
<div>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
</div>

    <div>
        <h2 style="color:red; text-align:center;">ACTIVITIES FORM</h2>
        <br>
        <div style="align:center;">
            <form action="activityAdd" method="post" >
                <input type="hidden" id="activityadd" name="activityadd" value="add">
                <input type="submit" value="Add">
            </form>
        </div>
        <div style="align:center;">
            <form action="activityRecord" method="post" >
                <input type="hidden" id="activityrecord" name="activityrecord" value="record">
                <input type="submit" value="Record">
            </form>
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
