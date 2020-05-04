package controller.crew;

import model.Crew;
import model.Worker;
import service.CrewService;
import service.WorkerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/editCrew/*"})
public class EditCrewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditCrewServlet() {
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
        request.setAttribute("crew", crew);

        WorkerService ws = new WorkerService();
        List<Worker> workers = ws.getWorkersWithoutCrew();
        request.setAttribute("freeWorkers",workers);
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/pages/editCrew.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
