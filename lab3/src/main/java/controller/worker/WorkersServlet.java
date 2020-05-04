package controller.worker;

import model.Worker;
import service.WorkerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/workers"})
public class WorkersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public WorkersServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WorkerService ws = new WorkerService();
        List<Worker> workers = ws.getAllWorkers();
        request.setAttribute("workers", workers);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/pages/workers.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
