<%@ page import="model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26.04.2020
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<div style="background: blue; height: 75px; padding: 5px;">
    <div style="float: left; text-align: center;">
        <h1>Airline website</h1>
    </div>

    <%
        User user=(User)session.getAttribute("loginedUser");
    %>
    <% if(user!=null){ %>
    <div style="float: right; padding: 25px; text-align: right;"><b><%=user.getName()%></b></div>
    <% }else{ %>
    <%} %>
</div>

<div style="padding: 5px; text-align:left; face:3">

    <a href="/">Flights page</a>
    <% if(user!=null){ %>
    |
    <a href="/crew">Crew List</a>
    |
    <a href="/workers">Workers List</a>
    |
    <a href="/logout">Log out</a>
    <% }else{ %>
    |
    <a href="/login">Login</a>
    <%} %>


</div>

