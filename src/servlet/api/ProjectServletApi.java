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
		name="ProjectServletApi", 
		urlPatterns="/api/project/*"
)
public class ProjectServletApi extends ServletUtils {

	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	returnGsonResponse(resp, null, 1, 200);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = getUrlParam(req);
	    switch(key) {
	    case "create":
	    	create(req, resp);
	    	return;
	    }
	}
	
	public void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		JsonObject jo = new JsonObject();
		jo.addProperty("valid", "yes");
		returnGsonResponse(resp, jo.getAsJsonObject(), 1, 200);
	}
}
