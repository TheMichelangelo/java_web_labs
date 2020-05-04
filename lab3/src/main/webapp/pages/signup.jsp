<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26.04.2020
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Airline-sign up</title>
    <style>
        body{
            margin:20px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h3>Sign up page</h3>
<form method="POST" action="/signUp">
    <table border="0">
        <tr>
            <td>User Name</td>
            <td><input type="text" name="email" value= "" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value= "" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="/login">Cancel</a>
            </td>
        </tr>
    </table>
</form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
