import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorHandlingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable)
                request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)
                request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)
                request.getAttribute("javax.servlet.error.servlet_name");

        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String)
                request.getAttribute("javax.servlet.error.request_uri");

        if (requestUri == null) {
            requestUri = "Unknown";
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!doctype html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Lab 2</title>\n" +
                "    <meta name=\"Lab2\" content=\"Lab2\">\n" +
                "    <meta name=\"Mike Pastula\" content=\"lab\">\n" +
                "</head>\n" +
                "<body  style=\"background-color:grey;\">\n" +
                "<table width=\"720\" align=\"center\">\n" +
                "    <tr  align=\"center\"><th colspan=\"2\">Лабораторна робота №2</th></tr>\n" +
                "    <tr  align=\"center\"><th colspan=\"2\">Варіант №13</th></tr>\n" +
                "    <tr  align=\"center\"><th colspan=\"2\"><a href=\"http://localhost:8080\">Back to the main page</a></th></tr>\n" +
                "    <tr  align=\"center\"><th colspan=\"2\">");

        if (throwable == null && statusCode == null) {
            out.println("Error information is missing</th></tr>\n</table>");
        } else if (statusCode != null) {
            switch (statusCode) {
                case 404:
                    out.println("Page not found</th></tr></table>");
                    break;
                default:
                    out.println("The status code : " + statusCode + "</th></tr>\n</table>");
                    break;
            }
            out.println();
        } else {
            out.println("Error information</th></tr>\n");
            out.println("<tr><th>Servlet Name : " + servletName + "</th></tr>\n");
            out.println("<tr><th>Exception Type : " + throwable.getClass().getName() + "</th></tr>\n");
            out.println("<tr><th>The request URI: " + requestUri + "</th></tr>\n");
            out.println("<tr><th>The exception message: " + throwable.getMessage() + "</th></tr>\n</table>");
        }
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
