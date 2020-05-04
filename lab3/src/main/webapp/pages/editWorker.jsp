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
    <title>Airline-worker-edit</title>
    <style>
        body {
            margin: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h3>Edit worker page</h3>

<form method="POST" action="/editWorker/${worker.id}">
    <table align="center">
        <tr>
            <th width="100">Name:</th>
            <td><input type="text" value="${worker.name}" name="workerName" id="workerName" required></td>
        </tr>
        <tr>
            <th>Surname:</th>
            <td><input type="text" value="${worker.surname}" name="workerSurname" id="workerSurname" required></td>
        </tr>
        <tr>
            <th>Age:</th>
            <td><input type="number" value="${worker.age}" min="18" max="81" name="workerAge" id="workerAge"></td>
        </tr>
        <tr>
            <th>Salary:</th>
            <td><input type="number" value="${worker.salary}" min="100" max="10000" name="workerSalary" id="workerSalary"></td>
        </tr>
        <c:if test = "${worker.crewId!=0}">
            <tr>
                <th>Profession:</th>
                <td><input type="text" value=""  name="workerProfession" id="workerProfession" readonly></td>
            </tr>
        </c:if>

        <c:if test = "${worker.crewId==0}">
            <tr>
                <th>Profession:</th>
                <td>
                    <select name="workerProfession" id="workerProfession">
                        <co:forEach items="${professions}" var="profession">
                            <c:if test="${worker.profession==profession}">
                                <option selected>${profession}</option>
                            </c:if>
                            <c:if test="${worker.profession!=profession}">
                                <option>${profession}</option>
                            </c:if>
                        </co:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Crew:</th>
                <td>
                    <select name="workerCrew" id="workerCrew">
                        <option value="0">No crew</option>
                        <co:forEach items="${crewList}" var="crew">
                            <option value="${crew.id}">${crew.no}</option>
                        </co:forEach>
                    </select>
                </td>
            </tr>
        </c:if>


        <tr>
            <td colspan ="2" align="center">
                <input type="submit" value= "Submit" />
                <a href="/workers">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
