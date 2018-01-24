package servlet.site;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ServletUtils;
import utils.FormErrorUtils;
import utils.ApiRequestUtils;


@WebServlet(name="UserLoginServletSite", urlPatterns="/login")
public class UserLoginServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/login.jsp";
	
	private static String USERNAME_EMPTY_VALUE = "Empty Username.";
	private static String PASSWORD_EMPTY_VALUE = "Empty Password.";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(fileName).forward(request, response);
		return;
	}
	
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ApiRequestUtils api = new ApiRequestUtils();
		
		System.out.println(" -----> GET REQUEST <-----");
		api.get("/user/login");
		System.out.println(" -----> POST REQUEST <-----");
		api.post("/user/login?username=root&password=root");
		
		FormErrorUtils errors = new FormErrorUtils();
		String username = "";
		String password = "";
		// --- 1.
		if( !isParamExistNotEmpty(req, "username") ) { errors.add("username", "EMPTY_VALUE", USERNAME_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "password") ) { errors.add("password", "EMPTY_VALUE", PASSWORD_EMPTY_VALUE); }
		if( errors.isError() ) {
			// --- ERROR.
			req.setAttribute("errors", errors);
			req.getRequestDispatcher(fileName).forward(req, resp);
		} else {
			// --- SUCCESS.
			username = req.getParameter("username");
			password = req.getParameter("password");
			
			req.getRequestDispatcher(fileName).forward(req, resp);
		}
	}

}
