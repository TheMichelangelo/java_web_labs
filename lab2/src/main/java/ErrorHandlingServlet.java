import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "BooksServlet",
        urlPatterns = "/book")
//sqlexception
//class not found exception
public class ErrorHandlingServlet extends HttpServlet {
}
