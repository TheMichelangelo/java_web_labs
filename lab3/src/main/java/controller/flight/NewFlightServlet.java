package controller.flight;

import service.FlightService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/createFlight"})
public class NewFlightServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NewFlightServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/pages/createFlight.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FlightService fs = new FlightService();

        String locationTo = request.getParameter("locationTo");
        String locatingFrom = request.getParameter("locationFrom");
        int planeNo = Integer.valueOf(request.getParameter("planeNo"));

        String timeArrivalString = request.getParameter("timeArrival");
        String timeStartsString = request.getParameter("timeFlightStarts");

        fs.addFlight(planeNo, locationTo, locatingFrom, timeArrivalString, timeStartsString);
        response.sendRedirect("/flights");
    }
}
