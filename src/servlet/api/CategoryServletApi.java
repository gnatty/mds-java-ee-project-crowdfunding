package servlet.api;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import utils.ServletUtils;
import dao.CategoryDAO;
import entity.CategoryEntity;

@WebServlet(
		name="CategoryServletApi", 
		urlPatterns="/api/category/*"
)
public class CategoryServletApi extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = getUrlParam(req);
	    switch(key) {
	    case "list":
	    	list(req, resp);
	    	return;
	    }
	}
	
	public void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		CategoryDAO catDAO = new CategoryDAO();
		List<CategoryEntity> res = catDAO.list();
		returnGsonResponse(resp, listToJsonElement(res), 1, 200);
	}

}
