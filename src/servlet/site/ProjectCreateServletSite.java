package servlet.site;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import dao.CategoryDAO;
import dao.ProjectDAO;
import dao.UserDAO;
import utils.ServletUtils;
import utils.FormErrorUtils;
import utils.ApiRequestUtils;
import entity.CategoryEntity;
import entity.UserEntity;

@WebServlet(name="ProjectCreateServletSite", urlPatterns="/project/create")
public class ProjectCreateServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/project-create.jsp";
	
	private static String NAME_EMPTY_VALUE = "Empty Name.";
	private static String DESCRIPTION_EMPTY_VALUE = "Empty Description.";
	private static String AMOUNT_EMPTY_VALUE = "Empty Amount.";
	private static String CATEGORY_EMPTY_VALUE = "Empty Category.";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// --- retrieve categories.
		CategoryDAO catDAO = new CategoryDAO();
		List<CategoryEntity> categories = catDAO.list();
		
		req.setAttribute("catList", categories);
		req.getRequestDispatcher(fileName).forward(req, resp);
		return;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FormErrorUtils errors = new FormErrorUtils();
		String name = "";
		String description = "";
		String amount = "";
		String category = "";
		
		if( !isParamExistNotEmpty(req, "frmName") ) { errors.add("name", "EMPTY_VALUE", NAME_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmDescription") ) { errors.add("description", "EMPTY_VALUE", DESCRIPTION_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmAmount") ) { errors.add("amount", "EMPTY_VALUE", AMOUNT_EMPTY_VALUE); }
		if( !isParamExistNotEmpty(req, "frmCategory") ) { errors.add("category", "EMPTY_VALUE", CATEGORY_EMPTY_VALUE); }
		
		if( errors.isError() ) {
			// --- ERROR.
			req.setAttribute("errors", errors);
			this.doGet(req, resp);
			return;
		} else {
			HttpSession sess = req.getSession();
			String token = (String) sess.getAttribute("token");
			System.out.println(token);
			UserDAO userDAO = new UserDAO();
			UserEntity curUser = userDAO.getUserByToken(token);
			
			if(curUser == null) {
				// --- throw error ?.
				resp.sendRedirect(req.getContextPath() + "/");
				return;
			} else {
				// insert new project.
				name = req.getParameter("frmName");
				description = req.getParameter("frmDescription");
				amount = req.getParameter("frmAmount");
				category = req.getParameter("frmCategory");
				int userId = curUser.getId();
				int catId = Integer.parseInt(category);
				String createdAt = this.getCurrentTimestamp();
				String endAt = "864000";
				
				// --- check if category id exist.
				CategoryDAO catDAO = new CategoryDAO();
				if(catDAO.isExistById(catId) == false) {
					// throw error ?.
					System.out.println("category do not exist");
					resp.sendRedirect(req.getContextPath() + "/");
					return;
				}

				ProjectDAO projectDAO = new ProjectDAO();
				projectDAO.create(userId, catId, name, description, amount, createdAt, endAt);
				resp.sendRedirect(req.getContextPath() + "/");
				return;
			}
		}
	}

}
