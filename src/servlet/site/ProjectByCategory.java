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
import entity.ProjectEntity;
import entity.UserEntity;

@WebServlet(name="ProjectByCategory", urlPatterns="/project/by-category/*")
public class ProjectByCategory extends ServletUtils {

	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/project-bycategory.jsp";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// retrieve all categories.
		CategoryDAO catDAO = new CategoryDAO();
		List<CategoryEntity> categories = catDAO.list();
		List<ProjectEntity> projectList;
		ProjectDAO projectDAO = new ProjectDAO();
		
		String catLabel = req.getPathInfo();
		
		if(catLabel == null || catLabel.equals("/")) {
			// -- retrieve all project
			projectList = projectDAO.list();
		} else {
			// -- Get project by category.
			catLabel = catLabel.replace("/", "");
			CategoryEntity cat = catDAO.getByLabel(catLabel);
			if(cat == null) {
				// -- throw error ?
				resp.sendRedirect(req.getContextPath() + "/error-page-not-found");
				return;
			} else {
				projectList = projectDAO.getByCatId(cat.getId());
			}
		}
		
		req.setAttribute("projectList", projectList);
		req.setAttribute("catList", categories);
		req.getRequestDispatcher(fileName).forward(req, resp);
		return;
	}
	
}
