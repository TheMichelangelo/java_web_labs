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
    <title>Airline-flight-edit</title>
    <style>
        body {
            margin: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h3>Edit flight page</h3>

<form method="POST" action="/editFlight/${flight.id}">
    <table align="center">
        <tr>
            <th width="200"><label for="planeNo">Plane number:</label></th>
            <td><input type="number" value="${flight.planeNo}" name="planeNo" id="planeNo" min="0" required></td>
        </tr>
        <tr>
            <th><label for="locationFrom">Location from:</label></th>
            <td><input type="text" value="${flight.locationFrom}" name="locationFrom" id="locationFrom" required></td>
        </tr>
        <tr>
            <th><label for="locationTo">Location to:</label></th>
            <td><input type="text" value="${flight.locationTo}" name="locationTo" id="locationTo" required></td>
        </tr>

        <tr>
            <th><label for="timeFlightStarts">Time flight starts :</label></th>
            <td><input type="time" id="timeFlightStarts" name="timeFlightStarts" value="${flight.timeFlightStarts}" required></td>
        </tr>
        <tr>
            <th><label for="timeArrival">Arrival time:</label></th>
            <td><input type="time" id="timeArrival" name="timeArrival" value="${flight.timeArrival}" required></td>
        </tr>
        <tr>
            <th><label for="crewId">Crew no:</label></th>
            <td>
                <select name="crewId" id="crewId">
                    <option value="0">No crew</option>
                    <co:forEach items="${crewToSelect}" var="crew">
                        <c:if test="${flight.crewId==crew.id}">
                            <option selected value="${crew.id}">${crew.no}</option>
                        </c:if>
                        <c:if test="${flight.crewId!=crew.id}">
                            <option value="${crew.id}">${crew.no}</option>
                        </c:if>
                    </co:forEach>
                </select>
            </td>
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
