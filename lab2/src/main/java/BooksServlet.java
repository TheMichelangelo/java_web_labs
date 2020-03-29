import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "BooksServlet",
        urlPatterns = "/book")
public class BooksServlet extends HttpServlet  {
    private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
    }

    public BooksServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        try{
            response.setContentType("text/html;charset=UTF-8");
            ArrayList<Book> books = new ArrayList<Book>();
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            Connection con=DriverManager.getConnection("'jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine");
            String radioButtonValue = request.getParameter("search");
            ResultSet rs = null;
            if("name".equals(radioButtonValue))
            {
                String authorName =request.getParameter("authorName");
                rs = con.createStatement().executeQuery("Select id,book_name,author,print_date,price " +
                        "from book where author like '"+authorName+"'");
            }
            else if("name_length".equals(radioButtonValue))
            {
                rs=con.createStatement().executeQuery("Select id,book_name,author,print_date,price,max(len(book_name)) as price from book");
            }
            else if("time".equals(radioButtonValue))
            {
                rs = con.createStatement().executeQuery("Select min(print_date),max(print_date)from book");
                if(rs.next())
                {
                    Date max= rs.getDate(2);
                    Date min= rs.getDate(1);
                    min.setTime(min.getTime()+24*60*60*1000);
                    rs=con.createStatement().executeQuery("Select * from book where " +
                            "print_date='"+max+"' or print_date='"+min+"'");
                }
            }

            while(rs.next()){
                Book book = new Book(rs.getInt("id"),rs.getString("book_name"),
                        rs.getString("author"),rs.getInt("price"),rs.getDate("print_date"));
                books.add(book);
            }

            rs.close();
            con.close();
            PrintWriter out = response.getWriter();
            printPageHeader(out);
            out.println("<br><br><br><table width=\"720\" align=\"center\">\n");
            if(0==books.size())
            {
                out.println("<tr><th>No books were found by your request</th></tr>\n");
            }else{
                out.println("<tr><th colspan=4>Books found by yours request:</th></tr>\n");
                out.println("<tr><th>Name</th><th>Author</th><th>Date</th><th>Price</th></tr>\n");

                for(Book b:books)
                {
                    out.println("<tr align=\"center\"><td>"+b.getName()+"</td><td>"+b.getAuthor()
                            +"</td><td>"+b.getDate()+"</td><td>"+b.getPrice()+"</td></tr>\n");
                }
            }
            out.println("</table>");
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    void printPageHeader(PrintWriter out)
    {
        out.println("<!doctype html>\n"+
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Lab 2</title>\n" +
                "    <meta name=\"Lab2\" content=\"Lab2\">\n" +
                "    <meta name=\"Mike Pastula\" content=\"lab\">\n" +
                "</head>\n" +
                "<body  style=\"background-color:grey;\">\n" +
                "<table width=\"720\" align=\"center\">\n" +
                "    <tr  align=\"center\"><th colspan=\"2\">Лабораторна робота №2</th></tr>\n"+
                "    <tr  align=\"center\"><th colspan=\"2\">Варіант №13</th></tr>\n" +
                "    <tr  align=\"center\"><th colspan=\"2\"><a href=\"http://localhost:8080\">Back to the main page</a></th></tr>\n"+
                "</table>");
    }
}
