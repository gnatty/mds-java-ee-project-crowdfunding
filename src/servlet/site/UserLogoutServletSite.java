package servlet.site;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ServletUtils;;

@WebServlet(name="UserLogoutServletSite", urlPatterns="/logout")
public class UserLogoutServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		sess.removeAttribute("token");
		resp.sendRedirect(req.getContextPath() + "/");
		return;
	}

}

