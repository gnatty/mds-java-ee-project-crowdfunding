package servlet.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import utils.ServletUtils;

@WebServlet(
		name="UserServletApi", 
		urlPatterns="/api/user/*"
)
public class UserServletApi extends ServletUtils {
	
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( request.getPathInfo().equals(null) ) {
			return;
		}
		String key = request.getPathInfo().substring(1);
		
		returnJsonReponse(response, returnJson(key, 1, 200));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getPathInfo());
	}
	
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
