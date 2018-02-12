package servlet.site;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserTokenDAO;
import entity.UserEntity;
import entity.UserTokenEntity;
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
			
			System.out.println(" -----> REQUEST <-----");
			UserDAO userDAO = new UserDAO();
			List<UserEntity> res = userDAO.login(username, password);
			if(res.size() == 0) {
				errors.add("login", "WRONG_CREDENTIALS", WRONG_CREDENTIALS);
				req.setAttribute("errors", errors);
				req.getRequestDispatcher(fileName).forward(req, resp);
				return;
			} else {
				UserTokenDAO userTokenDAO = new UserTokenDAO();
				UserTokenEntity token = userTokenDAO.create(res.get(0).getId(), res.get(0).getUsername());
				HttpSession session = req.getSession();
				session.setAttribute("token", token.getKey());
				resp.sendRedirect(req.getContextPath() + "/");
				return;
			}
		}
	}

}
