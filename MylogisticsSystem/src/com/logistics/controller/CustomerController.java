package com.logistics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.MenuMapper;
import com.logistics.model.MenuInfo;
import com.logistics.model.UserInfo;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	// 引入常用的方法对象并实例化
	MenuMapper menuMapper = new MenuMapper();

	// 首页
	@RequestMapping("/")
	public String Index() {
		return "redirect:/customer/index";
	}

	// 管理首页
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		// 获取session内的用户信息
		HttpSession session = request.getSession();
		UserInfo u = (UserInfo) session
				.getAttribute(MemberInterceptor.SESSION_MEMBER);
		String logname = u.getManager_name();
		int role_id = u.getRole_id();
		// 注意这里的index.jsp在admin文件夹下的
		ModelAndView view = new ModelAndView("customer/index");
		// 测试生成菜单，默认权限为1-总管理员
		List<MenuInfo> menulist = menuMapper.getTopMenu(role_id);
		List<MenuInfo> siderlist=menuMapper.getSecondMenu(role_id,2);
		// 将取到的数据传递到前端页面，使用jstl调用
		view.addObject("menulist", menulist);
		view.addObject("siderlist", siderlist);
		// 登录的用户名放入这个对象中，传递给前端
		view.addObject("welcome", logname);

		return view;
	}
}
