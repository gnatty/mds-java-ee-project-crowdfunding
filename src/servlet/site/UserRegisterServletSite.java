package servlet.site;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;

import com.google.gson.JsonObject;

import dao.UserDAO;
import utils.ServletUtils;
import utils.FormErrorUtils;
import utils.ApiRequestUtils;


@WebServlet(name="UserRegisterServletSite", urlPatterns="/register")
public class UserRegisterServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/register.jsp";
	
	private static String USERNAME_EMPTY_VALUE = "Empty Username.";
	private static String PASSWORD_EMPTY_VALUE = "Empty Password.";
	private static String PASSWORD_CONFIRM_EMPTY_VALUE = "Empty Password confirm.";
	private static String EMAIL_EMPTY_VALUE = "Empty Email.";
	private static String FIRSTNAME_EMPTY_VALUE = "Empty Firstname.";
	private static String LASTNAME_EMPTY_VALUE = "Empty Lastname.";
	private static String PASSWORD_NOT_MATCH = "Password not match.";
	private static String USERNAME_ALREADY_EXIST = "Username already exist";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(fileName).forward(request, response);
		return;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FormErrorUtils errors = new FormErrorUtils();
		String username = "";
		String password = "";
		String passwordConfirm = "";
		String firstname = "";
		String lastname = "";
		String email = "";
		
		// --- 1.
		if( !isParamExistNotEmpty(req, "frmEmail") ) { errors.add("email", "EMPTY_VALUE", EMAIL_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmFirstname") ) { errors.add("firstname", "EMPTY_VALUE", FIRSTNAME_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmLastname") ) { errors.add("lastname", "EMPTY_VALUE", LASTNAME_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmUsername") ) { errors.add("username", "EMPTY_VALUE", USERNAME_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmPassword") ) { errors.add("password", "EMPTY_VALUE", PASSWORD_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmPasswordConfirm") ) { errors.add("passwordConfirm", "EMPTY_VALUE", PASSWORD_CONFIRM_EMPTY_VALUE); }
		
		if( errors.isError() ) {
			// --- ERROR.
			req.setAttribute("errors", errors);
			req.getRequestDispatcher(fileName).forward(req, resp);
			return;
		} else {
			// --- SUCCESS.
			username = req.getParameter("frmUsername");
			password = req.getParameter("frmPassword");
			passwordConfirm = req.getParameter("frmPasswordConfirm");
			firstname = req.getParameter("frmFirstname");
			lastname = req.getParameter("frmLastname");
			email = req.getParameter("frmEmail");
			
			if(!password.equals(passwordConfirm)) {
				errors.add("register", "PASSWORD_NOT_MATCH", PASSWORD_NOT_MATCH);
				req.setAttribute("errors", errors);
				req.getRequestDispatcher(fileName).forward(req, resp);
				return;
			}
			
			System.out.println(" -----> REQUEST <-----");
			UserDAO userDAO = new UserDAO();
			String res = userDAO.register(username, password, email, firstname, lastname);
			switch(res) {
			case "USERNAME_ALREADY_EXIST":
				errors.add("register", "USERNAME_ALREADY_EXIST", USERNAME_ALREADY_EXIST);
				req.setAttribute("errors", errors);
				req.getRequestDispatcher(fileName).forward(req, resp);
				break;
			case "ACCOUNT_CREATED":
				resp.sendRedirect(req.getContextPath() + "/");
				break;
			}
		}
	}

}
