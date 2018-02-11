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

import utils.ServletUtils;
import utils.FormErrorUtils;
import utils.ApiRequestUtils;
import entity.CategoryEntity;

@WebServlet(name="ProjectCreateServletSite", urlPatterns="/project/create")
public class ProjectCreateServletSite extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
	private static String fileName = "/views/project-create.jsp";
	
	private static String NAME_EMPTY_VALUE = "Empty Name.";
	private static String DESCRIPTION_EMPTY_VALUE = "Empty Description.";
	private static String AMOUNT_EMPTY_VALUE = "Empty Amount.";
	private static String CATEGORY_EMPTY_VALUE = "Empty Category.";
	
	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ApiRequestUtils api = new ApiRequestUtils("get", "/category/list");
		api.addParameter("exec", "yes");
		api.run();
		Gson gson = new Gson();
		LinkedTreeMap result = gson.fromJson(api.getResponseData() , LinkedTreeMap.class);
		result = (LinkedTreeMap) result.get("success");
		System.out.print(result.get("data"));
		
		List<CategoryEntity> cat = (List<CategoryEntity>) result.get("data");
		req.setAttribute("catList", cat);
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
			req.getRequestDispatcher(fileName).forward(req, resp);
			return;
		} else {
			name = req.getParameter("frmName");
			description = req.getParameter("frmDescription");
			amount = req.getParameter("frmAmount");
			category = req.getParameter("frmCategory");
			
			HttpSession sess = req.getSession();
			String token = (String) sess.getAttribute("token");
			
			ApiRequestUtils api = new ApiRequestUtils("post", "/project/create");
			api.addParameter("token", token);
			api.addParameter("name", name);
			api.addParameter("description", description);
			api.addParameter("amount", amount);
			api.addParameter("category", category);
			api.run();
			
			System.out.println(name);
			System.out.println(description);
			System.out.println(amount);
			System.out.println("category : " + category);
			System.out.println(token);
			System.out.println(api.getResponseData());
			
			req.getRequestDispatcher(fileName).forward(req, resp);
		}
	}

}
