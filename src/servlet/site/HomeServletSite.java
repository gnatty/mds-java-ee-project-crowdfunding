package servlet.site;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProjectDAO;
import entity.ProjectEntity;
import utils.ServletUtils;

@WebServlet(name="HomeServletSite", urlPatterns="")
public class HomeServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/home.jsp";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// --- retrieve last 10 project;
		ProjectDAO projectDAO = new ProjectDAO();
		List<ProjectEntity> projectList = projectDAO.list();
		
		req.setAttribute("projectList", projectList);
		req.getRequestDispatcher(fileName).forward(req, resp);
		return;
	}

}
