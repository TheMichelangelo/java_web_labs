<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26.04.2020
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body{
            margin:20px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<h3>Login Page</h3>

<% String errorMessage = (String) request.getAttribute("errorString"); %>
<% if(errorMessage!=null){ %>
<p style="color: red;">errorMessage</p>
<% }else{ %>

<%} %>

<form method="POST" action="/login">
    <table border="0">
        <tr>
            <td>User Name</td>
            <td><input type="text" name="userName" value= "" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value= "" /> </td>
        </tr>
        <tr>
            <td>Remember me</td>
            <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<p style="color:blue;">Do not have an account? <a href="/signUp">SIGN UP</a></p>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
