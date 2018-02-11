package servlet.site;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ServletUtils;

@WebServlet(name="UserProfileServletSite", urlPatterns="/account/profile")
public class UserProfileServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/account-profile.jsp";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(fileName).forward(req, resp);
		return;
	}

}