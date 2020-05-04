package controller.crew;

import model.Flight;
import model.Worker;
import service.FlightService;
import service.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/removeFlight/*"})
public class RemoveFlightServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RemoveFlightServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        StringBuffer requestURL = request.getRequestURL();
        int pos = requestURL.lastIndexOf("/");
        int flightId = Integer.valueOf(requestURL.substring(pos + 1));
        FlightService fs = new FlightService();
        Flight flight = fs.getFlight(flightId);
        response.sendRedirect("/editCrew/" + String.valueOf(flight.getCrewId()));*/
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuffer requestURL = request.getRequestURL();
        int pos = requestURL.lastIndexOf("/");
        int flightId = Integer.valueOf(requestURL.substring(pos + 1));
        FlightService fs = new FlightService();
        Flight flight = fs.getFlight(flightId);
        long crewId = flight.getCrewId();
        fs.unassignCrewFromFlight(flight);
        response.sendRedirect("/editCrew/" + String.valueOf(crewId));
    }
}