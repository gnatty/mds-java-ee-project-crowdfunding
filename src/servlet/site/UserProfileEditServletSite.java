package servlet.site;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.UserEntity;
import utils.FormErrorUtils;
import utils.ServletUtils;

@WebServlet(name="UserProfileEditServletSite", urlPatterns="/user/profile-edit")
public class UserProfileEditServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/account-profile-edit.jsp";
	
	private static String USERNAME_EMPTY_VALUE = "Empty Username.";
	private static String PASSWORD_EMPTY_VALUE = "Empty Password.";
	private static String PASSWORD_CONFIRM_EMPTY_VALUE = "Empty Password confirm.";
	private static String EMAIL_EMPTY_VALUE = "Empty Email.";
	private static String FIRSTNAME_EMPTY_VALUE = "Empty Firstname.";
	private static String LASTNAME_EMPTY_VALUE = "Empty Lastname.";
	private static String PASSWORD_NOT_MATCH = "Password not match.";
	private static String USERNAME_ALREADY_EXIST = "Username already exist";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean accDataUpdated = false;
		req.setAttribute("accDataUpdated", accDataUpdated);
		req.getRequestDispatcher(fileName).forward(req, resp);
		return;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FormErrorUtils errors = new FormErrorUtils();
		String username = "";
		String password = null;
		String passwordConfirm = null;
		String firstname = "";
		String lastname = "";
		String email = "";
		boolean accDataUpdated = false;
		
		// --- 1.
		if( !isParamExistNotEmpty(req, "frmEmail") ) { errors.add("email", "EMPTY_VALUE", EMAIL_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmFirstname") ) { errors.add("firstname", "EMPTY_VALUE", FIRSTNAME_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmLastname") ) { errors.add("lastname", "EMPTY_VALUE", LASTNAME_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmUsername") ) { errors.add("username", "EMPTY_VALUE", USERNAME_EMPTY_VALUE); }
		
		if(errors.isError() ) {
			// --- ERROR.
			req.setAttribute("errors", errors);
			req.getRequestDispatcher(fileName).forward(req, resp);
			return;
		} else {
			// --- get current user data.
			HttpSession sess = req.getSession();
			String userToken = (String) sess.getAttribute("token");
			UserDAO userDAO = new UserDAO();
			UserEntity user = userDAO.getUserByToken(userToken);
			
			username = req.getParameter("frmUsername");
			firstname = req.getParameter("frmFirstname");
			lastname = req.getParameter("frmLastname");
			email = req.getParameter("frmEmail");
			
			// --- check if different UserName.
			if(!username.equals(user.getUsername())) {
				if(userDAO.isUsernameExist(username)) {
					errors.add("profileUpdate", "USERNAME_ALREADY_EXIST", USERNAME_ALREADY_EXIST);
					System.out.println("username already exist");
				}
			}
			
			// --- check password.
			if(isParamExistNotEmpty(req, "frmPassword")) {
				password = req.getParameter("frmPassword");
			}
			if(isParamExistNotEmpty(req, "frmPasswordConfirm")) {
				passwordConfirm = req.getParameter("frmPasswordConfirm");
			}
			
			if(password != null || passwordConfirm != null) {
				if(!password.equals(passwordConfirm)) {
					errors.add("profileUpdate", "PASSWORD_NOT_MATCH", PASSWORD_NOT_MATCH);
				} else {
					user.setPassword(password);
				}
			}
			
			if(errors.isError() ) {
				// --- ERROR.
				req.setAttribute("errors", errors);
				req.getRequestDispatcher(fileName).forward(req, resp);
				return;
			}
			
			user.setUsername(username);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setMail(email);
			
			// --- update user data.
			userDAO.update(user);
			accDataUpdated = true;
			req.setAttribute("accDataUpdated", accDataUpdated);
			req.setAttribute("userLogged", user);
			req.getRequestDispatcher(fileName).forward(req, resp);
		}
		
	}
	
}

