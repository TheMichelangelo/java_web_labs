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

@WebServlet(urlPatterns = {"/flights"})
public class FlightsServlet extends HttpServlet {
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
    private static final long serialVersionUID = 1L;

    public FlightsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FlightService fs = new FlightService();
        List<Flight> flights = fs.getAllFlights();
        request.setAttribute("flights", flights);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/pages/flights.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
}
