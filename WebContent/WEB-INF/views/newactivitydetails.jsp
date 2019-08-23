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
<title>Activity Monitor - Add Activity Details</title>
</head>
<body>
<div>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
</div>

    <div>
        <h2 style="color:red; text-align:center;">NEW ACTIVITY DETAILS FORM</h2>
        <br>
        <div style="align:center;">
            <form:form action="activitydetails" method="POST" modelAttribute="sActivityDetails">
                <table id="newActivityName" style="align:center;">
                    <tr>
                        <td><label>Activity Name:&nbsp;</label></td>
                        <td><form:input type="text" path="detailName" placeholder="enter Activity Name" /></td>
                    </tr>
                </table>
                <table id="newActivityTable" style="align:center;">
                    <tr>
                        <td><label>Detail Name1:&nbsp;</label></td>
                        <td><form:input type="text" path="detailName" placeholder="enter Detail Name" /></td>
                    </tr>
                </table>
                <button type="button" onclick="addActivityDetail()">Add Detail Name</button>
                <button type="button" onclick="removeActivityDetail()">Remove Detail Name</button>
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

<script>
var detailCount = 2;
function addActivityDetail() {
    //var table = document.getElementById("jjj");
    var table = document.getElementById("newActivityTable");
    //var row = table.insertRow(0);
    var row = table.insertRow(-1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = "Detail Name" + detailCount;
    cell2.innerHTML = "<form:input type='text' path='detail" + (detailCount++) + "'/>";
}

function removeActivityDetail() {
    //var table = document.getElementById("jjj");
    var table = document.getElementById("newActivityTable");
    //var row = table.insertRow(0);
    var row = table.deleteRow(-1);
    //var cell1 = row.insertCell(0);
    //var cell2 = row.insertCell(1);
    //cell1.innerHTML = "Detail Name" + detailCount++;
    //cell2.innerHTML = "<input type='text'>";
    detailCount--;
}

</script>
<!-- --------------------------------------- -->
<!-- Optional JavaScript -->
<%@ include file="jgb_js.html" %>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<%@ include file="boot_strap_js.html" %>
</body>
</html>