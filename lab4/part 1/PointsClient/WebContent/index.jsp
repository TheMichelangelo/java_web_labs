<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.List" %>
<%@page import="chnu.javaeelabs.PointWSStub.GetPoints"  %>
<%@page import="chnu.javaeelabs.PointWSStub.Point"  %>
<%@page import="chnu.javaeelabs.PointWSStub.GetPointsWithMaxDistance"  %>
<%@page import="chnu.javaeelabs.PointWSStub.GetPointsWithMaxDistanceResponse"  %>
<html>
<head>
<meta charset="UTF-8">
<title>Знайти дві найвіддаленіші точки</title>
</head>
<body>
	<table>
		<tr><th>Лабораторна робота №4</th></tr>
		<tr><th>
			<ol>
				<%
					List points = (List)request.getAttribute("points"); 
					if(points!=null)
					{
						for(int i=0;i<points.size();i++)
						{
							out.println("<li>"+points.get(i)+"</li>");
						}
					}
				%>
			</ol>  
		</th></tr>
	</table>
	<form action="PointServlet" method="GET">
		<label>Яку кількість перших точок потрібно розглянути?</label>
		<input type="number" id="limit" name="limit" min="2">
	</form>
	<% 
		Point pointA = (Point)request.getAttribute("pointA");
		Point pointB = (Point)request.getAttribute("pointB");
		if(pointA!=null)
		{
			out.println("<h3>Точка А<h3>");
			out.println("Назва точки : "+pointA.getName());
			out.println("Координати x: "+pointA.getCoord_x()+" y:"+pointA.getCoord_y()+" z:"+pointA.getCoord_z() );
			out.println("Маса x: "+pointA.getM() );
			out.println("<h3>Точка B<h3>");
			out.println("Назва точки : "+pointB.getName());
			out.println("Координати x: "+pointB.getCoord_x()+" y:"+pointB.getCoord_y()+" z:"+pointB.getCoord_z() );
			out.println("Маса x: "+pointB.getM() );
		}
	%>
</body>
</html>