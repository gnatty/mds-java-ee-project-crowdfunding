package servlet.api;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import utils.ApiRequestUtils;
import utils.ServletUtils;
import dao.UserDAO;
import dao.UserTokenDAO;
import entity.UserEntity;
import entity.UserTokenEntity;

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
	    	returnGsonResponse(resp, null, 1, 200);
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
	    	register(req, resp);
	    case "check_token":
	    	checkToken(req, resp);
	    	return;
	    }
	}
	
	public void checkToken(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ApiRequestUtils api = new ApiRequestUtils();
		api.setData(req.getParameter("data"));
		
		if(api.isParamExist("token")) {
			UserTokenDAO userTokenDAO = new UserTokenDAO();
			if(userTokenDAO.check(api.getParameter("token"))) {
				JsonObject jo = new JsonObject();
				jo.addProperty("isTokenValid", "yes");
				returnGsonResponse(resp, jo.getAsJsonObject(), 1, 200);
			} else {
				JsonObject jo = new JsonObject();
				jo.addProperty("isTokenValid", "no");
				returnGsonResponse(resp, jo.getAsJsonObject(), 0, 400);
			}
		} else {
			JsonObject jo = new JsonObject();
			jo.addProperty("no_token", "NO_TOKEN");
			returnGsonResponse(resp, jo.getAsJsonObject(), 0, 400);
		}
	}
	
	public void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserDAO userDAO = new UserDAO();
		List<UserEntity> res = userDAO.list();
		returnGsonResponse(resp, listToJsonElement(res), 1, 200);
	}
	
	public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ApiRequestUtils api = new ApiRequestUtils();
		api.setData(req.getParameter("data"));
		
		if(api.isParamExist("username") && api.isParamExist("password") 
				&& api.isParamExist("firstname") && api.isParamExist("lastname") && api.isParamExist("email")) {
			String username = api.getParameter("username");
			String password = api.getParameter("password");
			String firstname = api.getParameter("firstname");
			String lastname = api.getParameter("lastname");
			String email = api.getParameter("email");
			
			System.out.println("===> creating user");
			System.out.println(username);
			System.out.println(password);
			System.out.println(firstname);
			System.out.println(lastname);
			System.out.println(email);
			
			UserDAO userDAO = new UserDAO();
			String res = userDAO.register(username, password, email, firstname, lastname);
			
			if(res == "USERNAME_ALREADY_EXIST") {
				JsonObject jo = new JsonObject();
				jo.addProperty("account", "USERNAME_ALREADY_EXIST");
				returnGsonResponse(resp, jo.getAsJsonObject(), 0, 400);
			} else {
				JsonObject jo = new JsonObject();
				jo.addProperty("account", "CREATED");
				returnGsonResponse(resp, jo.getAsJsonObject(), 1, 200);
			}
		} else {
			JsonObject jo = new JsonObject();
			jo.addProperty("param", "ERROR_PARAM");
			returnGsonResponse(resp, jo.getAsJsonObject(), 0, 400);
		}
	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ApiRequestUtils api = new ApiRequestUtils();
		api.setData(req.getParameter("data"));
		
		if(api.isParamExist("username") && api.isParamExist("password")) {
			String username = api.getParameter("username");
			String password = api.getParameter("password");
			
			UserDAO userDAO = new UserDAO();
			List<UserEntity> res = userDAO.login(username, password);
			
			if(res.size() == 0) {
				JsonObject jo = new JsonObject();
				jo.addProperty("login", "WRONG_CREDENTIALS");
				returnGsonResponse(resp, jo.getAsJsonObject(), 0, 400);
			} else {
				UserTokenDAO userTokenDAO = new UserTokenDAO();
				UserTokenEntity token = userTokenDAO.create(res.get(0).getId(), res.get(0).getUsername());
				
				JsonObject jo = new JsonObject();
				jo.addProperty("token", token.getKey());
				returnGsonResponse(resp, jo.getAsJsonObject(), 1, 200);
			}
		} else {
			JsonObject jo = new JsonObject();
			jo.addProperty("erorr", "FATAL_ERROR");
			returnGsonResponse(resp, jo.getAsJsonObject(), 0, 400);
		}
	}
	
}
