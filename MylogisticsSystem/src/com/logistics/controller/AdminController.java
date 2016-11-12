package com.logistics.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.MenuMapper;
import com.logistics.mapper.UserMapper;
import com.logistics.model.MenuInfo;
import com.logistics.model.UserInfo;
import com.logistics.util.BarcodeHelper;



@Controller
@RequestMapping("/admin") 
//显示action默认在admin下，下面的action定义，就可以不用写了
public class AdminController {
	//引用常用的方法对象并实例化
	MenuMapper menuMapper=new MenuMapper();
	UserMapper userMapper=new UserMapper();

    //后台管理首页
	@RequestMapping("/")
	public String Index(){
		
		//注意这里的index.jsp在admin文件夹下的
		return "redirect:/admin/index";
	}
	//后台管理首页
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request){
		//获取session内的用户信息
		HttpSession session=request.getSession();
		UserInfo u=(UserInfo) session.getAttribute(MemberInterceptor.SESSION_MEMBER);
		//登陆管理用户名
		String logname=u.getManager_name();
		int role_id=u.getRole_id();
	
		//ModelAndView view;
		//权限判断
		if (!menuMapper.validateMenu(role_id,"admin/index")) {
			//view=new ModelAndView("error/503");
			return "redirect:/error/503";
		}
		
		//注意这里的index.jsp在admin文件夹下
		//view=new ModelAndView("admin/index");
		//测试生成菜单，默认权限为1总管理员
		List<MenuInfo> menulist=menuMapper.getTopMenu(role_id);
		List<MenuInfo> siderlist=menuMapper.getSecondMenu(role_id,1);
		//将取到的数据传递到前端页面，使用jstl调用
		request.setAttribute("menulist",menulist);
		request.setAttribute("siderlist",siderlist);
		//view.addObject("menulist",menulist);
		//登陆的用户名放入这个对象中，传递给前端
//		view.addObject("welcome",logname);
		request.setAttribute("welcome",logname);
		return "admin/index";
		
	}
	//条形码实现
	@RequestMapping("/testcode")
	public String testcode(HttpServletRequest request) throws IOException{
		
		//ModelAndView view=new ModelAndView("admin/codetest");
//		List<FileItem> formItems = upload.parseRequest(request);
		//条形码
		String myCode="TR0220101X090001";
		//服务器存储文件夹
		String mypath="upload\\code";
		//生成路径
		String path=BarcodeHelper.createBarCode15(request, mypath, myCode);
	
		//打印输出
//		System.out.println(path);
		//传递图片路径到数据库
		List<String> list=new ArrayList<String>();
		list.add(path);

	for (String p : list) {
	 
		userMapper.inseruser(p);
		//通过表达是将图片路劲传递到前端页面
		request.setAttribute("message","图片上传成功");
		request.setAttribute("file",p);
		System.out.println(p);
	}
		return "admin/codetest";
	}
	

	
}
