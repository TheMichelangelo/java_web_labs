package controller.crew;

import service.CrewService;
import service.FlightService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/createCrew"})
public class NewCrewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NewCrewServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CrewService cs = new CrewService();
        int crewNumber = Integer.valueOf(request.getParameter("crewNumber"));
        cs.addCrew(crewNumber);
        response.sendRedirect("/crew");
    }
}