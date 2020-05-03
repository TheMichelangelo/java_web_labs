package controller.worker;

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

@WebServlet(urlPatterns = {"/deleteWorker/*"})
public class DeleteWorkerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteWorkerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuffer requestURL = request.getRequestURL();
        int pos = requestURL.lastIndexOf("/");
        int workerId = Integer.valueOf(requestURL.substring(pos + 1));
        WorkerService ws = new WorkerService();
        Worker worker = ws.getWorker(workerId);
        ws.deleteWorker(worker);
        response.sendRedirect("/workers");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
