package chnu.javaeelabs;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chnu.javaeelabs.PointWSStub.GetPointsWithMaxDistance;
import chnu.javaeelabs.PointWSStub.Point;

public class PointServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public PointServlet() {
		super();
    }

    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	
    	int limit = Integer.valueOf(request.getParameter("limit")).intValue();
    	PointWSStub stub = new PointWSStub("http://localhost:8080/PointsService/services/PointWS?wsdl");
    	GetPointsWithMaxDistance gp = new GetPointsWithMaxDistance();
    	gp.setLimit(limit);
    	Point[] point = stub.getPointsWithMaxDistance(gp).get_return();
    	request.setAttribute("pointA",point[0]);
    	request.setAttribute("pointB",point[1]);
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
    	dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}
