package controller.worker;

import model.Crew;
import model.Profession;
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

@WebServlet(urlPatterns = {"/editWorker/*"})
public class EditWorkerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditWorkerServlet() {
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
        CrewService cs = new CrewService();
        List<Crew> crewList = cs.getAllCrews();
        request.setAttribute("worker", worker);
        request.setAttribute("crewList", crewList);
        request.setAttribute("professions", Profession.values());
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/pages/editWorker.jsp");
        dispatcher.forward(request, response);
    }

    //TODO test
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WorkerService ws = new WorkerService();
        StringBuffer requestURL = request.getRequestURL();
        int pos = requestURL.lastIndexOf("/");
        long workerId = Integer.valueOf(requestURL.substring(pos + 1));
        Worker worker = ws.getWorker(workerId);

        String name = request.getParameter("workerName");
        String surname = request.getParameter("workerSurname");
        int age = Integer.valueOf(request.getParameter("workerAge"));
        int salary = Integer.valueOf(request.getParameter("workerSalary"));
        worker.setName(name);
        worker.setSurname(surname);
        worker.setAge(age);
        worker.setSalary(salary);
        if(worker.getCrewId()==0)
        {
            long crewId = Integer.valueOf(request.getParameter("workerCrew"));
            worker.setCrewId(crewId);
            Profession profession = Profession.valueOf(request.getParameter("workerProfession"));
            worker.setProfession(profession);
        }
        ws.updateWorker(worker);
        response.sendRedirect("/workers");
    }
}
