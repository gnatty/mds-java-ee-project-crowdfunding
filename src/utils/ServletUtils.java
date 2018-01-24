package utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ServletUtils extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public String getUrlParam(HttpServletRequest request) {
		if( request.getPathInfo().equals(null) ) {
			return "";
		}
		return request.getPathInfo().substring(1);
	}
	
	@SuppressWarnings("rawtypes")
	public JsonElement listToJsonElement(List l) {
		GsonBuilder gsonBuilder = new GsonBuilder();
	    Gson gson = gsonBuilder.create();
	    return gson.toJsonTree(l);
	}
	
	public String returnGsonString(JsonElement data, int state, int code) {
	    String stateVal = (state==1)?"success":"error";
	    JsonObject jo = new JsonObject();
	    JsonObject joData = new JsonObject();
	    joData.addProperty("code", code);
	    joData.add("data", data);
	    jo.add(stateVal, joData);
	    return jo.toString();
	}
	
	public HttpServletResponse returnGsonResponse(HttpServletResponse resp, JsonElement data, int state, int code) throws IOException {
		String respData = returnGsonString(data, state, code);
		
		resp.setContentType("application/json");
		HttpServletResponse hsr = (HttpServletResponse) resp;
	    hsr.setStatus(code);
		resp.getWriter().append(respData);
		return resp;
	}

	public boolean isParamExist(HttpServletRequest req, String key) {
		return req.getParameterMap().containsKey(key);
	}
	
	public boolean isParamExistNotEmpty(HttpServletRequest req, String key) {
		return req.getParameterMap().containsKey(key) && !req.getParameter(key).equals("");
	}
	
}
