package servlet.site;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="UserLoginServletSite", urlPatterns="/login")
public class UserLoginServletSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/login.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(fileName).forward(request, response);
		return;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = "";
		String password = "";
		// --- 1.
		if ( !req.getParameterMap().containsKey("username") || !req.getParameterMap().containsKey("password") ) {
			HttpServletResponse re = (HttpServletResponse) resp;
			re.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		} else {
			System.out.println("ok");
			username = req.getParameter("username");
			password = req.getParameter("password");
		}

		// --- 2.
		if( username.equals("") ) { req.setAttribute("errorUsername", true); }
		if( password.equals("") ) { req.setAttribute("errorPassword", true); }
		
		// --- 3.
		System.out.println("username : " + username + " password : " + password);
		req.getRequestDispatcher(fileName).forward(req, resp);
		return;
	}

}
