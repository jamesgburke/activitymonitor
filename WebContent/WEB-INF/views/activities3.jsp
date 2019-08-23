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
<title>Activity Monitor Activities</title>
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
            <form action="loginUser" method="post" >
                <table style="align:center;">
                    <tr>
                        <td><label>Available Activities:&nbsp;</label></td>
                        <td><input type="text" name="activity" placeholder="enter Activity"></td>
                    </tr>
                    <tr>
                        <td><label>Date Completed:&nbsp;</label></td>
                        <td><input type="text" name="datecompleted" placeholder="enter Date Completed"></td>
                    </tr>
                    <tr>
                        <td><label>Duration:&nbsp;</label></td>
                        <td><input type="time" name="duration" placeholder="enter duration"></td>
                    </tr>
                    <tr>
                        <td><label>Start Time:&nbsp;</label></td>
                        <td><input type="time" name="starttime" placeholder="enter Start Time"></td>
                    </tr>
                    <tr>
                        <td><label>End Time:&nbsp;</label></td>
                        <td><input type="time" name="endtime" placeholder="enter End Time"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Save"></td>
                        <td><input type="reset" value="Cancel"></td>
                    </tr>
                </table>
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
