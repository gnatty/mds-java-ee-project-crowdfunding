package servlet.site;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ServletUtils;

@WebServlet(name="errorPageNotFoundServletSite", urlPatterns="/error-page-not-found")
public class errorPageNotFoundServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/error-page-not-found.jsp";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(fileName).forward(req, resp);
		return;
	}

}