package controller.flight;

import model.Crew;
import model.Flight;
import model.Worker;
import service.CrewService;
import service.FlightService;
import service.WorkerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/editFlight/*"})
public class EditFlightServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditFlightServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuffer requestURL = request.getRequestURL();
        int pos = requestURL.lastIndexOf("/");
        int flightId = Integer.valueOf(requestURL.substring(pos + 1));
        FlightService fs = new FlightService();
        Flight flight = fs.getFlight(flightId);

        CrewService cs = new CrewService();
        List<Crew> crew = cs.getAllCrews();
        List<Crew> crewToSelect = new ArrayList<>();
        for(Crew cr:crew)
        {
            if(cs.checkCrewIsComplete(cr))
            {
                crewToSelect.add(cr);
            }
        }
        request.setAttribute("crewToSelect",crewToSelect);
        request.setAttribute("flight",flight);
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/pages/editFlight.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FlightService fs = new FlightService();
        StringBuffer requestURL = request.getRequestURL();
        int pos = requestURL.lastIndexOf("/");
        long workerId = Integer.valueOf(requestURL.substring(pos + 1));
        Flight flight = fs.getFlight(workerId);

        String locationTo = request.getParameter("locationTo");
        String locatingFrom = request.getParameter("locationFrom");
        int planeNo = Integer.valueOf(request.getParameter("planeNo"));
        String timeArrivalString = request.getParameter("timeArrival");
        String timeStartsString = request.getParameter("timeFlightStarts");

        flight.setTimeFlightStarts(timeStartsString);
        flight.setTimeArrival(timeArrivalString);
        flight.setPlaneNo(planeNo);
        flight.setLocationTo(locationTo);
        flight.setLocationFrom(locatingFrom);

        fs.updateFlight(flight);
        response.sendRedirect("/flights");
    }
}
