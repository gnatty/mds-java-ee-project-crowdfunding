package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("rawtypes")
public class ApiRequestUtils {

	private static String endpoint = "http://localhost:8080/MdsCrowdProject/api";
	private JsonObject data;
	private String action;
	private String method;
	private String responseData;
	private int responseCode;
	private LinkedTreeMap result;
	
	public ApiRequestUtils(String _method, String _action) {
		this.data = new JsonObject();
		this.method = _method;
		this.action = _action;
	}
	
	public ApiRequestUtils() {
	}
	
	public JsonObject getData() {
		return this.data;
	}
	
	public String getResponseData() {
		return this.responseData;
	}
	
	public int getResponseCode() {
		return this.responseCode;
	}
	
	public LinkedTreeMap getResult() {
		return this.result;
	}
	
	public void addParameter(String name, String value) {
		this.data.addProperty(name, value);
	}
	
	public String getParameter(String name) {
		return this.data.get(name).getAsString();
	}
	
	public boolean isParamExist(String name) {
		try {
			this.getParameter(name);
			return true;
		} catch(NullPointerException e) {
			return false;
		}
	}
	
	public String dataToString() {
		return data.toString();
	}
	
	public String dataToBase64() {
		return this.base64Encode(this.dataToString());
	}
	
	public JsonObject setData() {
		return new JsonObject();
	}
	
	public String base64Encode(String data) {
		return Base64.getEncoder().encodeToString(data.getBytes());
	}
	
	public String base64Decode(String data) {
		byte[] decoded = Base64.getDecoder().decode(data);
		return new String(decoded);
	}
	
	public void run() throws IOException {
		switch(this.method) {
		case "get":
			this.get();
			break;
		case "post":
			this.post();
			break;
		}
	}
	
	public void setData(String data) {
		try {
			String tmpData = this.base64Decode(data);
			this.data = new JsonParser().parse(tmpData).getAsJsonObject();
		} catch(JsonSyntaxException e) {
			this.data = new JsonObject();
		}
	}
	
	private void setDataFromResponse(String data) {
		Gson gson = new Gson();
		LinkedTreeMap result = gson.fromJson(data , LinkedTreeMap.class);
		if(this.getResponseCode() == 200) {
			result = (LinkedTreeMap) result.get("success");
			try {
				this.result = (LinkedTreeMap) result.get("data");
			} catch(ClassCastException e) {
				// none.
			}
		} else {
			result = (LinkedTreeMap) result.get("error");
			try {
				this.result = (LinkedTreeMap) result.get("data");
			} catch(ClassCastException e) {
				// none.
			}
		}
	}
	
	private void get() throws IOException {
		// --- Create request with data parameter.
		HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(endpoint + this.action + "?data=" + this.dataToBase64());
        // --- Execute query.
        HttpResponse response = client.execute(get);
        // --- Get Response.
        this.responseData = EntityUtils.toString(response.getEntity());
        this.responseCode = response.getStatusLine().getStatusCode();
        this.setDataFromResponse(this.responseData);
	}
	
	private void post() throws IOException {
		// --- Create request.
		HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(endpoint + this.action);
        // --- Insert data post.
        List<NameValuePair> arguments = new ArrayList<>(1);
        arguments.add(new BasicNameValuePair("data", this.dataToBase64()));
        // --- Execute query.
        post.setEntity(new UrlEncodedFormEntity(arguments));
        HttpResponse response = client.execute(post);
        // --- Get response.
        this.responseData = EntityUtils.toString(response.getEntity());
        this.responseCode = response.getStatusLine().getStatusCode();
        this.setDataFromResponse(this.responseData);
	}

}