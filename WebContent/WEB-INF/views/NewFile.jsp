<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container">
    <form:form action="userInfo" method="post" modelAttribute="sUser">
        <table>
            <tr>
                <td class="sub_field"><label>ID</label></td>
                <td>
                    <form:input type="hidden" id="userId" path="id"/>
                </td>
            </tr>
            <tr>
                <td class="sub_field"><label>User Name</label></td>
                <td>
                    <form:input type="text" id="username" path="username"/>
                    <form:errors path="username"/>
                </td>
            </tr>
            <tr>
                <td class="sub_field"><label>Password</label></td>
                <td><input type="text" id="password" name="password"/></td>
            </tr>
            <tr>
                <td class="sub_field"><label>Email</label></td>
                <td>
                    <form:input type="text" id="email" path="email"/>
                    <form:errors path="email"/>
                </td>
            </tr>
            <tr class="btn">
                <td><input type="submit" name="submit" value="Submit Info"/></td>
            </tr>
        </table>            
    </form:form>
</div>
<div >
    <h2>Welcome, ${loginResultMsg}</h2>
    <p>test user from session object: ${sUser.username}</p>
    <p>test user from copy of session object: ${testUser.username}</p>
</div>

</body>
</html>