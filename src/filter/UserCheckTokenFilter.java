package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ApiRequestUtils;

@WebFilter(urlPatterns="/*")
public class UserCheckTokenFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// --- cast
		HttpServletRequest req = (HttpServletRequest) request;
		ServletContext sc = req.getServletContext();
		HttpSession sess = req.getSession();
		HttpServletResponse resp = (HttpServletResponse) response;
		sc.setAttribute("projetDir", req.getContextPath());
		
		String reqPath = req.getRequestURI().substring(sc.getContextPath().length());
		String apiPath = "/api";
		String loginPage = "/login";
		String registerPage = "/register";
		String projectCreatePage = "/project/create";
		
		boolean isUserLogged = false;
		System.out.println("request path : " + reqPath);
		
		sc.setAttribute("projetDir", req.getContextPath());

		String userToken = (String) sess.getAttribute("token");
		if(userToken != null) {
			ApiRequestUtils api = new ApiRequestUtils("post", "/user/check_token");
			api.addParameter("token", userToken);
			api.run();
			if(api.getResult().get("isTokenValid").equals("yes")) {
				isUserLogged = true;
			}
			// --- debug check token.
			System.out.print(api.getResponseData());
		}
		sc.setAttribute("isUserLogged", isUserLogged);
		
		if(reqPath.contains(apiPath)) {
			chain.doFilter(request, response);
			return;
		} else {
			List<String> checkPage = Arrays.asList(loginPage, registerPage);
			List<String> checkLoggedPage = Arrays.asList(projectCreatePage);
			
			if(checkPage.contains(reqPath) && isUserLogged == false) {
				chain.doFilter(request, response);
				return;
			} else if(checkPage.contains(reqPath) && isUserLogged == true){
				resp.sendRedirect(req.getContextPath() + "/");
				return;
			} else if(checkLoggedPage.contains(reqPath) && isUserLogged == false){
				resp.sendRedirect(req.getContextPath() + "/");
				return;
			} else {
				chain.doFilter(request, response);
				return;
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
