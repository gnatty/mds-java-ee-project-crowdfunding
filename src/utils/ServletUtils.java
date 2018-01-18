package utils;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

public class ServletUtils extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String returnJson(Object data, int state, int code) {
		String stateVal = (state==1)?"success":"error";
		
		JSONObject objContent = new JSONObject();
		objContent.put("code", code);
		objContent.put("data", data);
		JSONObject obj = new JSONObject();
	    obj.put(stateVal, objContent);
	    
	    return obj.toJSONString();
	}
	
	public HttpServletResponse returnJsonReponse(HttpServletResponse resp, String data) throws IOException {
		resp.setContentType("application/json");
		resp.getWriter().append(data);
		return resp;
	}

}
