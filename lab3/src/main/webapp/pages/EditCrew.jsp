<%--
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
    <title>Airline-crew-edit</title>
    <style>
        body {
            margin: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h3>Edit crew page</h3>

<table align="center">
    <tr>
        <th width="400" colspan="5" align="left">Crew no: ${crew.no}</th>
    </tr>
    <tr>
        <th bgcolor="aqua" align="left">Workers</th>
    </tr>
    <tr>
        <td>
            <table>
                <tr>
                    <form method="POST" action="/assignWorker/${crew.id}">
                        <td colspan="3">
                            <select name="workerId" id="workerId">
                                <co:forEach items="${freeWorkers}" var="worker">
                                    <option value="${worker.id}">${worker.name} ${worker.profession} ${worker.salary}</option>
                                </co:forEach>
                            </select>
                        </td>
                        <td colspan="2" align="center"><input type="submit" value= "Assign" /></td>
                    </form>
                </tr>
                <c:forEach items="${crew.staff}" var="worker">
                    <tr align="center">
                        <td>${worker.name}</td>
                        <td>${worker.surname}</td>
                        <td>${worker.salary}</td>
                        <td>${worker.age}</td>
                        <td>${worker.profession}</td>
                        <c:if test="${crew.flights.size()==0}">
                            <th width="100"><a href="/unassign/${worker.id}">ANASSIGN WORKER</a></th>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th bgcolor="#dc143c" align="left">Flights</th>
    </tr>
    <tr>
        <td>
            <table>
                <c:forEach items="${crew.flights}" var="flight">
                    <tr align="center">
                        <td>${flight.planeNo}</td>
                        <td>${flight.locationFrom}</td>
                        <td>${flight.locationTo}</td>
                        <td>${flight.timeFlightStarts}</td>
                        <td>${flight.timeArrival}</td>
                        <th width="100"><a href="/removeFlight/${flight.id}">REMOVE FLIGHT</a></th>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <td align="center">
            <a href="/crew">Cancel</a>
        </td>
    </tr>
</table>


<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
