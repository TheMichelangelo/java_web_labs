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
<%@ taglib prefix="co" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Airline-flight-new</title>
    <style>
        body {
            margin: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h3>Create flight page</h3>

<form method="POST" action="/createFlight">
    <table align="center">
        <tr>
            <th width="100"><label for="planeNo">Plane number:</label></th>
            <td><input type="number" value="" name="planeNo" id="planeNo" min="0" required></td>
        </tr>
        <tr>
            <th><label for="locationFrom">Location from:</label></th>
            <td><input type="text" value="" name="locationFrom" id="locationFrom" required></td>
        </tr>
        <tr>
            <th><label for="locationTo">Location to:</label></th>
            <td><input type="text" value="" name="locationTo" id="locationTo" required></td>
        </tr>

        <tr>
            <th><label for="timeFlightStarts">Time flight starts :</label></th>
            <td><input type="time" id="timeFlightStarts" name="timeFlightStarts" value="00:00" required></td>
        </tr>
        <tr>
            <th><label for="timeArrival">Arrival time:</label></th>
            <td><input type="time" id="timeArrival" name="timeArrival" value="00:00" required></td>
        </tr>
        <tr>
            <td colspan ="2" align="center">
                <input type="submit" value= "Submit" />
                <a href="/flights">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
