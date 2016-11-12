package com.logistics.controller;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.UserMapper;
import com.logistics.model.UserInfo;

@Controller
@RequestMapping("/")
public class LoginController {
	UserMapper userMapper = new UserMapper();

	// 后台登录页面
	@RequestMapping("/login")
	public String login() {

		// login=login.jsp
		return "login";
	}

	// 验证登录
	@RequestMapping(value = "/validateLogin", method = RequestMethod.POST)
	public String validateLogin(String username, String password,String redirectURL, HttpServletRequest request,HttpServletResponse response) {
		// 取出记住我勾选框的值-这个值是一个数组型
		String[] rememberme = request.getParameterValues("rememberme");
		
		// 判断用户名和密码不为空
		if (StringUtils.isNotBlank(username)&& StringUtils.isNotBlank(password)) {
//			System.out.println("判断jsp页面用户名密码是否传值成功");
			// 判断输入用户名和密码是否正确
			try {
				UserInfo user = userMapper.validateLogin(username,password);
//				System.out.println("判断用户名密码是否传值");
				// 如果登录成功返回的UserInfo对象,不为空
				if (user != null) {
					
					// 当登陆成功是，将用户信息存放到session中去
					HttpSession session = request.getSession();
					session.setAttribute(MemberInterceptor.SESSION_MEMBER, user);
					
					// 判断是否选中记住用户名
					if (null != rememberme) {
						for (String s : rememberme) {
							if (s.matches("checked")) {
								// 判断cookie是否存在
								Cookie[] cs = request.getCookies();
								for (Cookie c : cs) {
									if (c.getName().matches("username")) {
										// 不做任何操作
										//System.out.println("不做任何操作!");
									} else {
										// 选中则设置Cookie
										Cookie cookie = new Cookie("username",
												username);
										cookie.setMaxAge(60 * 60 * 24 * 7);
										response.addCookie(cookie);
										//System.out.println("存入Cookie!");
									}
								}

								// System.out.println("checked!");
							}
						}
					}
				}

				if (StringUtils.isNotBlank(redirectURL)) {
					return "redirect:" + URLDecoder.decode(redirectURL);
				}
				return "redirect:/admin/index";
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
//				System.out.println("为空");
				return "redirect:/login";
			}
		} else {
			if (StringUtils.isNotBlank(redirectURL)) {
				return "redirect:/login?" + URLDecoder.decode(redirectURL);
			}
			return "redirect:/login";
		}
	}
}
