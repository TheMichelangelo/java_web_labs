package chnu.javaeelabs;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import chnu.javaeelabs.PointWSStub.GetPoints;

/**
 * Servlet Filter implementation class PointsFilter
 */
public class PointsFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PointsFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		PointWSStub stub = new PointWSStub("http://localhost:8080/PointsService/services/PointWS?wsdl");
		GetPoints gp = new GetPoints(); 
		String[] points = stub.getPoints(gp).get_return();
		for(Object pointm:points) {
			System.out.println(pointm);
		}
		request.setAttribute("points",Arrays.asList(points));
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
