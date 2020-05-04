package controller.worker;

import model.Profession;
import service.WorkerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/createWorker"})
public class NewWorkerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NewWorkerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("professions", Profession.values());
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/pages/createWorker.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WorkerService ws = new WorkerService();
        String name = request.getParameter("workerName");
        String surname = request.getParameter("workerSurname");
        int age = Integer.valueOf(request.getParameter("workerAge"));
        int salary = Integer.valueOf(request.getParameter("workerSalary"));
        Profession profession = Profession.valueOf(request.getParameter("workerProfession"));
        ws.addWorker(name, surname, age, salary, profession);
        response.sendRedirect("/workers");
    }
}
