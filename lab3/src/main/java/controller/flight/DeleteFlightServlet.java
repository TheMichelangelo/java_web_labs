package controller.flight;

import model.Flight;
import service.FlightService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/deleteFlight/*"})
public class DeleteFlightServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteFlightServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuffer requestURL = request.getRequestURL();
        int pos = requestURL.lastIndexOf("/");
        int flightId = Integer.valueOf(requestURL.substring(pos+1));
        FlightService fs = new FlightService();
        Flight flight = fs.getFlight(flightId);
        fs.deleteFlight(flight);
        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
}
