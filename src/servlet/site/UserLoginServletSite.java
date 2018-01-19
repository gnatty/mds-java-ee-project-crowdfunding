package servlet.site;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ServletUtils;
import utils.FormErrorUtils;


@WebServlet(name="UserLoginServletSite", urlPatterns="/login")
public class UserLoginServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/login.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(fileName).forward(request, response);
		return;
	}
	
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FormErrorUtils errors = new FormErrorUtils();
		
		String username = "";
		String password = "";
		// --- 1.
		if( !isParamExistNotEmpty(req, "username") ) { errors.add("username", "EMPTY_VALUE", "_blank"); }
		if( !isParamExistNotEmpty(req, "password") ) { errors.add("password", "EMPTY_VALUE", "_blank"); }
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
