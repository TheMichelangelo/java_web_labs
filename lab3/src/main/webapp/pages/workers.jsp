<%@ page import="model.Role" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26.04.2020
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="e" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Airline-worker</title>
    <style>
        body {
            margin: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h3>Worker page</h3>

<%
    User user = (User) session.getAttribute("loginedUser");
%>
<% if (user != null && user.getRole() == Role.MANAGER) { %>
<p style="color:blue;"><a href="/createWorker">CREATE WORKER</a></p>
<% } else { %>
<%} %>

<table border="3" cellpadding="0" cellspacing="0" align="center" height="75" weidth="300">
    <tr align="center">
        <th>Name</th>
        <th>Surname</th>
        <th>Salary</th>
        <th>Age</th>
        <th>Profession</th>
        <% if (user != null && user.getRole() == Role.MANAGER) { %>
        <th colspan="2">Actions</th>
        <% } else { %>
        <%} %>
    </tr>
    <c:forEach items="${workers}" var="worker">
        <tr align="center">
            <td>${worker.name}</td>
            <td>${worker.surname}</td>
            <td>${worker.salary}</td>
            <td>${worker.age}</td>
            <td>${worker.profession}</td>
            <c:if test = "${worker.crewId==0}">
                <% if (user != null && user.getRole() == Role.MANAGER) { %>
                <th width="100"><a href="/editWorker/${worker.id}">EDIT/VIEW DETAILS</a></th>
                <th width="100"><a href="/deleteWorker/${worker.id}">DELETE</a></th>
                <% } else { %>
                <%} %>
            </c:if>
        </tr>
    </c:forEach>
</table>
</table>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
