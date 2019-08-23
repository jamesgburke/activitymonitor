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
<title>Activity Monitor - Add Activities</title>
</head>
<body>
<div>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
</div>

<div>
    <h2 style="color:red; text-align:center;">NEW ACTIVITY FORM</h2>
    <br>
    <div style="align:center;">
        <form:form action="newActivity" method="POST" modelAttribute="sActivity">
            <table id="newActivityName" style="align:center;">
                <tr>
                    <td><label>Activity Name:&nbsp;</label></td>
                    <td><form:input type="text" path="name" placeholder="enter Activity Name" /></td>
                </tr>
            </table>
            <table id="newActivityType" style="align:center;">
                <tr>
                    <td><label>Type:&nbsp;</label></td>
                    <td>
                        <form:select type="text" path="type.name" modelAttribute="sActivityType" method="POST" placeholder="Select Activity Type">
                        <form:option value="none" disabled="disabled" style="color: gray;">Select a Type</form:option>
                            <form:option itemValue="type" value="Exercise">Exercise</form:option>
                            <form:option itemValue="type" value="Sport">Sport</form:option>
                            <form:option itemValue="type" value="Cardio">Cardio</form:option>
                            <form:option itemValue="type" value="Weight-bearing">Weight-bearing</form:option>
                        </form:select>
                    </td>
                </tr>
            </table>
            <br><br>
            <input type="submit" value="Save">
            <input type="reset" value="Cancel">
        </form:form>
    </div>
    <br><br>
</div>

<div>
<%@ include file="footer.jsp" %>
</div>

<!-- --------------------------------------- -->
<!-- Optional JavaScript -->
<%@ include file="jgb_js.html" %>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<%@ include file="boot_strap_js.html" %>
</body>
</html>