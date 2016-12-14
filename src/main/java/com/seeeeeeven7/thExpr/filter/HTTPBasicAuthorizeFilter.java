package com.seeeeeeven7.thExpr.filter;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seeeeeeven7.thExpr.models.User;
import com.seeeeeeven7.thExpr.models.UserRepository;
public class HTTPBasicAuthorizeFilter implements Filter {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		Boolean valid = checkHTTPBasicAuthorize(request);
		if (valid == false) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("application/json; charset=utf-8");
			httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

			ObjectMapper mapper = new ObjectMapper();

			httpResponse.getWriter().write(mapper.writeValueAsString(""));
			return;
		} 
		else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	private Boolean checkHTTPBasicAuthorize(ServletRequest request) {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String auth = httpRequest.getHeader("Authorization");
			if ((auth != null) && (auth.length() > 6)) {
				String HeadStr = auth.substring(0, 5).toLowerCase();
				if (HeadStr.compareTo("basic") == 0) {
					auth = auth.substring(6, auth.length());
			        String decodedAuth = new String(Base64.getDecoder().decode(auth), Charset.forName("UTF-8"));
					if (decodedAuth != null) {
						String[] UserArray = decodedAuth.split(":");
						if (UserArray != null && UserArray.length == 2) {
							String Name = UserArray[0];
							String Password = UserArray[1];
							User user = userRepository.findByName(Name);
							MessageDigest md = MessageDigest.getInstance("MD5");
							md.update(Password.getBytes());
							if (user.getPasswordMD5().compareTo(new BigInteger(1, md.digest()).toString(16)) == 0) {
								return true;
							}
						}
					}
				}
			}
			return false;
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
