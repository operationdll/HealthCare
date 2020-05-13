package com.spring.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dto.UserDto;

/**
 * 自定义拦截器
 *
 */
@Configuration
public class MyAdapter extends WebMvcConfigurerAdapter {

	public static Logger log = Logger.getLogger(MyAdapter.class);

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptor handlerInterceptor = new HandlerInterceptor() {
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				HttpSession session = request.getSession();
				UserDto userDto = (UserDto) session.getAttribute("user");
				if (userDto == null) {
					String url = request.getRequestURL().toString();
					if (getPermission(url)) {
						response.sendRedirect("/");
					}
				}
				return true;
			}

			private boolean getPermission(String url) {
				boolean bol = false;
				String lastUrl = url.substring(url.lastIndexOf("/") + 1);
				if (!"login.do".equals(lastUrl) && !"getPatientByHid.do".equals(lastUrl)
						&& !"getIdImg.do".equals(lastUrl)) {
					bol = true;
				}
				// API不拦截
				lastUrl = url.substring(0, url.lastIndexOf("/"));
				lastUrl = lastUrl.substring(lastUrl.lastIndexOf("/") + 1);
				if ("dataManage".equals(lastUrl)) {
					bol = false;
				}
				return bol;
			}

			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
					ModelAndView modelAndView) throws Exception {
				// System.out.println("postHandle");
			}

			public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
					Exception ex) throws Exception {
				// System.out.println("afterCompletion");
			}

		};
		registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
	}
}
