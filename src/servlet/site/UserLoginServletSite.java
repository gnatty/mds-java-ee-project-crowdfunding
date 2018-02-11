package servlet.site;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.ServletUtils;
import utils.FormErrorUtils;
import utils.ApiRequestUtils;

@WebServlet(name="UserLoginServletSite", urlPatterns="/login")
public class UserLoginServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/login.jsp";
	
	private static String USERNAME_EMPTY_VALUE = "Empty Username.";
	private static String PASSWORD_EMPTY_VALUE = "Empty Password.";
	private static String WRONG_CREDENTIALS = "Wrong username or password.";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// HttpSession session = req.getSession();
		// System.out.println(session.getAttribute("token"));
		req.getRequestDispatcher(fileName).forward(req, resp);
		return;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FormErrorUtils errors = new FormErrorUtils();
		String username = "";
		String password = "";
		// --- 1.
		if( !isParamExistNotEmpty(req, "frmUsername") ) { errors.add("username", "EMPTY_VALUE", USERNAME_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmPassword") ) { errors.add("password", "EMPTY_VALUE", PASSWORD_EMPTY_VALUE); }
		if( errors.isError() ) {
			// --- ERROR.
			req.setAttribute("errors", errors);
			req.getRequestDispatcher(fileName).forward(req, resp);
		} else {
			// --- SUCCESS.
			username = req.getParameter("frmUsername");
			password = req.getParameter("frmPassword");
			
			System.out.println(" -----> POST REQUEST <-----");
			ApiRequestUtils api = new ApiRequestUtils("post", "/user/login");
			api.addParameter("username", username);
			api.addParameter("password", password);
			api.run();
			
			switch(api.getResponseCode()) {
			case 200:
				String token = (String) api.getResult().get("token");
				HttpSession session = req.getSession();
				session.setAttribute("token", token);
				resp.sendRedirect(req.getContextPath() + "/");
				break;
			case 400:
				errors.add("login", "WRONG_CREDENTIALS", WRONG_CREDENTIALS);
				req.setAttribute("errors", errors);
				req.getRequestDispatcher(fileName).forward(req, resp);
				break;
			}
		}
	}

}
