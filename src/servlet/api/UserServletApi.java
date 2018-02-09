package servlet.api;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import org.json.simple.ItemList;
import utils.ServletUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dao.UserDAO;
import entity.UserEntity;

@WebServlet(
		name="UserServletApi", 
		urlPatterns="/api/user/*"
)
public class UserServletApi extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = getUrlParam(req);
	    switch(key) {
	    case "":
	    	return;
	    case "login":
	    	returnGsonResponse(resp, null, 1, 200);
	    	return;
	    case "logout":
	    	logout(req, resp);
	    	return;
	    case "register":
	    	returnGsonResponse(resp, null, 1, 200);
	    	return;
	    case "list":
	    	list(req, resp);
	    	return;
	    }
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = getUrlParam(req);
	    switch(key) {
	    case "":
	    	returnGsonResponse(resp, null, 1, 200);
	    	return;
	    case "login":
	    	login(req, resp);
	    	return;
	    case "logout":
	    	returnGsonResponse(resp, null, 1, 200);
	    	return;
	    case "register":
	    	returnGsonResponse(resp, null, 1, 200);
	    	return;
	    }
	}
	
	public void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserDAO userDAO = new UserDAO();
		List<UserEntity> res = userDAO.list();
		returnGsonResponse(resp, listToJsonElement(res), 1, 200);
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println(Base64.getDecoder().decode(req.getParameter("data")));
	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = "";
		String password = "";
		
		System.out.println(req.getParameter("data"));
		
		if( isParamExistNotEmpty(req, "username") && isParamExistNotEmpty(req, "password")) {
			username = req.getParameter("username");
			password = req.getParameter("password");
			
			UserDAO userDAO = new UserDAO();
			List<UserEntity> res = userDAO.login(username, password);
			returnGsonResponse(resp, listToJsonElement(res), 1, 200);
		} else {
			returnGsonResponse(resp, null, 0, 400);
		}
	}
	
}
