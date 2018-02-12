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
import dao.ProjectContributionDAO;
import dao.ProjectDAO;
import dao.UserDAO;
import utils.ServletUtils;
import utils.FormErrorUtils;
import utils.ApiRequestUtils;
import entity.CategoryEntity;
import entity.ProjectContributionEntity;
import entity.ProjectEntity;
import entity.UserEntity;

@WebServlet(name="ProjectDetailServletSite", urlPatterns="/project/detail/*")
public class ProjectDetailServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/project-detail.jsp";
	
	private static String AMOUNT_EMPTY_VALUE = "Empty Amount.";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String projectID = req.getPathInfo();
		int projectId;
		ProjectEntity project;
		
		try {
			if(projectID == null || projectID.equals("/")) {
				// --- throw project not found.
				throw new Exception();
			}
			String tpmProjectId = req.getPathInfo().replace("/", "");
			projectId = Integer.parseInt(tpmProjectId);
			
			ProjectDAO projectDAO = new ProjectDAO();
			project = projectDAO.getById(projectId);
			
			if(project == null) {
				// --- throw project not found.
				throw new Exception();
			}
		} catch(Exception e) {
			// --- throw project not found.
			resp.sendRedirect(req.getContextPath() + "/error-page-not-found");
			return;
		}
		
		req.setAttribute("project", project);
		req.getRequestDispatcher(fileName).forward(req, resp);
		return;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("project detail post");
		
		FormErrorUtils errors = new FormErrorUtils();
		if( !isParamExistNotEmpty(req, "frmAmount") ) { errors.add("amount", "EMPTY_VALUE", AMOUNT_EMPTY_VALUE); }
		
		if( errors.isError() ) {
			// --- ERROR.
			System.out.println("project detail post error");
			req.setAttribute("errors", errors);
			this.doGet(req, resp);
			return;
		}
		
		// --- check user.
		HttpSession sess = req.getSession();
		String token = (String) sess.getAttribute("token");
		System.out.println(token);
		UserDAO userDAO = new UserDAO();
		UserEntity curUser = userDAO.getUserByToken(token);
		
		if(curUser == null) {
			// --- throw error ?.
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}
		
		int userId = curUser.getId();
		int projectId = Integer.parseInt(req.getPathInfo().replace("/", ""));
		String amount = req.getParameter("frmAmount");
		
		// -- check if project exist by id before.
		ProjectContributionDAO contriDAO = new ProjectContributionDAO();
		ProjectContributionEntity contri = contriDAO.create(userId, projectId, amount);
		
		
		
		this.doGet(req, resp);
		return;
	}

}
