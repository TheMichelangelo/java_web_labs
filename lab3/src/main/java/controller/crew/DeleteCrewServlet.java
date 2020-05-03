package controller.crew;

import model.Crew;
import model.Worker;
import service.CrewService;
import service.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/deleteCrew/*"})
public class DeleteCrewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteCrewServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuffer requestURL = request.getRequestURL();
        int pos = requestURL.lastIndexOf("/");
        int crewId = Integer.valueOf(requestURL.substring(pos + 1));
        CrewService cs = new CrewService();
        Crew crew = cs.getCrew(crewId);
        cs.deleteCrew(crew);
        response.sendRedirect("/crew");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
