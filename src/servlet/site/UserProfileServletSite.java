package servlet.site;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.ProjectEntity;
import entity.UserEntity;
import utils.ServletUtils;

@WebServlet(name="UserProfileServletSite", urlPatterns="/user/profile/*")
public class UserProfileServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/account-profile.jsp";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userId = req.getPathInfo();
		UserEntity user;
		
		try {
			if(userId == null || userId.equals("/")) {
				// --- throw user not found.
				throw new Exception();
			}
			
			String tmpUserId = req.getPathInfo().replace("/", "");
			int _userId = Integer.parseInt(tmpUserId);
			
			System.out.println("user id : " + _userId);
			
			UserDAO userDAO = new UserDAO();
			user = userDAO.getUserById(_userId);
			
			if(user == null) {
				// --- throw user not found.
				throw new Exception();
			}

		} catch(Exception e) {
			// --- throw project not found.
			resp.sendRedirect(req.getContextPath() + "/error-page-not-found");
			return;
		}
		
		req.setAttribute("user", user);
		req.getRequestDispatcher(fileName).forward(req, resp);
		return;
	}

}