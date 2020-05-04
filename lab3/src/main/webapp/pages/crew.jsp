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
    <title>Airline-crew</title>
    <style>
        body {
            margin: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h3>Crew page</h3>

<%
    User user = (User) session.getAttribute("loginedUser");
%>
<% if (user != null && user.getRole() == Role.ADMIN) { %>
<p style="color:blue;"><a href="/createCrew">CREATE CREW</a></p>
<% } else { %>
<%} %>

<form method="POST" action="/createCrew">
    <table align="left">
        <tr>
            <th colspan="2">ADD NEW CREW</th>
        </tr>
        <tr>
            <td width="100">
                Crew no:
            </td>
            <td>
                <input type="number" value="19" min="1" max="1000" name="crewNumber" id="crewNumber">
            </td>
        </tr>
        <tr align="center">
            <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form>

<table border="3" cellpadding="0" cellspacing="0" align="center" height="75" weidth="300">
    <tr align="center">
        <th>Crew info</th>
        <% if (user != null && user.getRole() == Role.MANAGER) { %>
        <th colspan="2">Actions</th>
        <% } else { %>
        <%} %>
    </tr>
    <c:forEach items="${crew}" var="cr">
        <tr align="center">
            <td>
                <table>
                    <tr>
                        <td width="450" colspan="5" align="center" bgcolor="#ffebcd">Crew no: ${cr.no}</td>
                    </tr>
                    <tr>
                        <th bgcolor="aqua" colspan="5">Workers</th>
                    </tr>
                    <c:forEach items="${cr.staff}" var="worker">
                        <tr align="center">
                            <td>${worker.name}</td>
                            <td>${worker.surname}</td>
                            <td>${worker.salary}</td>
                            <td>${worker.age}</td>
                            <td>${worker.profession}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th bgcolor="#dc143c" colspan="5">Flights</th>
                    </tr>
                    <c:forEach items="${cr.flights}" var="flight">
                        <tr align="center">
                            <td>${flight.planeNo}</td>
                            <td>${flight.locationFrom}</td>
                            <td>${flight.locationTo}</td>
                            <td>${flight.timeFlightStarts}</td>
                            <td>${flight.timeArrival}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <% if (user != null && user.getRole() == Role.MANAGER) { %>
            <th width="100"><a href="/editCrew/${cr.id}">EDIT/VIEW DETAILS</a></th>
            <c:if test="${cr.flights.size()==0}">
                <th width="100"><a href="/deleteCrew/${cr.id}">DELETE</a></th>
            </c:if>
            <% } else { %>
            <%} %>

        </tr>
    </c:forEach>
</table>
</table>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
