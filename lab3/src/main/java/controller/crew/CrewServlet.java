package controller.crew;

import model.Crew;
import service.CrewService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/crew"})
public class CrewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CrewServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CrewService cs = new CrewService();
        List<Crew> crew = cs.getAllCrews();
        request.setAttribute("crew", crew);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/pages/crew.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
