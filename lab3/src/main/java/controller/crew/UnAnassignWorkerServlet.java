package controller.crew;

import model.Worker;
import service.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/unassign/*"})
public class UnAnassignWorkerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UnAnassignWorkerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*StringBuffer requestURL = request.getRequestURL();
        int pos = requestURL.lastIndexOf("/");
        int workerId = Integer.valueOf(requestURL.substring(pos + 1));
        WorkerService ws = new WorkerService();
        Worker worker = ws.getWorker(workerId);
        response.sendRedirect("/editCrew/" + String.valueOf(worker.getCrewId()));*/
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuffer requestURL = request.getRequestURL();
        int pos = requestURL.lastIndexOf("/");
        int workerId = Integer.valueOf(requestURL.substring(pos + 1));
        WorkerService ws = new WorkerService();
        Worker worker = ws.getWorker(workerId);
        long crewId = worker.getCrewId();
        ws.unassignWorkerFromCrew(worker);
        response.sendRedirect("/editCrew/" + String.valueOf(crewId));
    }
}