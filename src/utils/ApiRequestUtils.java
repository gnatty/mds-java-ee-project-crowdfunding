package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ApiRequestUtils {
	
	private static String endpoint = "http://localhost:8080/MdsCrowdProject/api";
	
	public ApiRequestUtils() {
		
	}
	
	public void get(String action, String data) throws IOException {
		
		HttpClient client = HttpClientBuilder.create().build();
		
	
        HttpGet get = new HttpGet(endpoint + action + "?data=" + base64Encode(data));
        HttpResponse response = client.execute(get);
        System.out.println(EntityUtils.toString(response.getEntity()));
       
	}
	
	public void post(String action, String data) throws IOException {

		HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(endpoint + action);

        List<NameValuePair> arguments = new ArrayList<>(1);
        arguments.add(new BasicNameValuePair("data", data));
        
        post.setEntity(new UrlEncodedFormEntity(arguments));
        HttpResponse response = client.execute(post);
        
        System.out.println(EntityUtils.toString(response.getEntity()));   
	}
	
	public JsonObject setData() {
		return new JsonObject();
	}
	
	public String base64Encode(String data) {
		byte[] bytesEncoded = Base64.encodeBase64(data.getBytes());
		return new String(bytesEncoded);
	}
	
	public String base64Decode(String data) {
		byte[] valueDecoded = Base64.decodeBase64(data);
		return new String(valueDecoded);
	}

	
	
}