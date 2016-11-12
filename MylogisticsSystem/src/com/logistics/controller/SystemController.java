package com.logistics.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.ldap.ManageReferralControl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.aspectj.weaver.ast.And;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.MenuMapper;
import com.logistics.mapper.SystemMapper;
import com.logistics.mapper.UserMapper;
import com.logistics.model.AnnounceInfo;
import com.logistics.model.MenuInfo;
import com.logistics.model.SystemInfo;
import com.logistics.model.UserInfo;


@Controller
@RequestMapping("/system")
public class SystemController extends BaseController{
	//引用常用的方法对象并实例化
		MenuMapper menuMapper=new MenuMapper();
		SystemMapper sm=new SystemMapper();
		UserMapper um=new UserMapper();
	    //后台管理首页
		@RequestMapping("/")
		public String Index(){
			
			//注意这里的index.jsp在admin文件夹下的
			return "redirect:/system/index";
		}
		//后台管理首页
		@RequestMapping(value="/index")
		public String index(HttpServletRequest request,String pager){
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
			List<MenuInfo> siderlist=menuMapper.getSecondMenu(role_id,22);
			//将取到的数据传递到前端页面，使用jstl调用
			request.setAttribute("menulist",menulist);
			request.setAttribute("siderlist",siderlist);
			//view.addObject("menulist",menulist);
			//登陆的用户名放入这个对象中，传递给前端
//			view.addObject("welcome",logname);
			request.setAttribute("welcome",logname);
			return pager;	
		}
		
	
		//根据id更新页面
		@RequestMapping("/maintain")
		public String update(HttpServletRequest request){
			
		//初始化菜单
			String view=index(request, "system/maintain");
			//加载我的更新的数据
			SystemInfo si = sm.getSystemInfo(1);
			request.setAttribute("systeminfo",si);
			return view;
		}
		
		
		//执行更新方法
		@RequestMapping(value="/updateData",method=RequestMethod.POST)
		public String updateData(SystemInfo si,HttpServletRequest request){
			String view="";
			//执行更新方法
			if(sm.updateSystem(si)){
				//更新成功执行操作
				
				JOptionPane.showMessageDialog(null, "更新成功!");
				view="redirect:/system/index";
			}
			else
			{
				JOptionPane.showMessageDialog(null, "更新失败!");
				view="redirect:/system/maintain";
			}
			return view;
		}
		
		
		//根据id更新页面
		@RequestMapping("/editpassword")
		public String updatepassword(HttpServletRequest request){
		//初始化菜单
			//获取session内的用户信息
			HttpSession session=request.getSession();
			UserInfo u=(UserInfo) session.getAttribute(MemberInterceptor.SESSION_MEMBER);
			
			request.setAttribute("user",u);
			String view=index(request, "system/editpassword");
	
			return view;
		}
		//执行更新方法
		@RequestMapping(value="/updatepassword",method=RequestMethod.POST)
		public String updatepassword(HttpServletRequest request,HttpServletResponse response){
			String oldpassword=request.getParameter("oldpassword");
			String newpassword=request.getParameter("password");
			//获取session内的用户信息
			HttpSession session=request.getSession();
			UserInfo u=(UserInfo) session.getAttribute(MemberInterceptor.SESSION_MEMBER);
			request.setAttribute("user",u);
			String manager_name=u.getManager_name();
			String view=index(request, "system/editpassword");
			System.out.println(oldpassword+','+newpassword);
			//验证用户旧密码是否正确
			if (sm.getpasswordOk(manager_name,oldpassword)) {
				System.out.println(manager_name+','+newpassword);
				//修改密码的操作
				if (sm.updatepassword(newpassword,manager_name)) {
					JOptionPane.showMessageDialog(null,"密码修改成功");
					view="redirect:/system/index";
				}else {
					JOptionPane.showMessageDialog(null,"密码修改失败");
					view="redirect:/system/editpassword";
				} 
			}else{
				JOptionPane.showMessageDialog(null,"您输入的密码有误！");
				view="redirect:/system/editpassword";
			}
			return view;
		}
		
		//权限管理列表
		@RequestMapping("/validationmanager")
		public String managerValidate(HttpServletRequest request, Model model,
				@RequestParam(required = false) String searchInfo,
				@RequestParam(required = false) Integer pageNum,
				@RequestParam(required = false) Integer pageSize) {
			// 初始化菜单信息
			String view = index(request, "system/validationmanager");
			// 添加分页相关信息
			// 创建Map，来存放我们的数据，
			Map<String, Object> map = new HashMap<String, Object>();
			// 这里的searchInfo是查询方法 在这里用不到，比如我们需要根据条件来查询我们的数据，这个就需要了
			// 其实这个searchInfo就是我们动态查询时的查询条件，这里无用
			map.put("searchInfo", searchInfo);

			// 取出数据总数，
			Integer totalCount =sm.getvalidateCount();
			// 初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
			this.initPage(map, pageNum, pageSize, totalCount);
			// list为我们的需要显示的数据List ，获取的返回值是我们常用的List<实体类>形式
			List list =sm.getvalidationInfo(map);
			// list为我们的需要显示的数据List
			// 初始化结果
			this.initResult(model, list, map);

			return view;
		}
		
		// 新增用户
		@RequestMapping("/insertvalidater")
		public String createAnnounce(HttpServletRequest request) {
			String view = index(request, "system/insertvalidater");
			return view;
		}
		// 执行新增方法
		@RequestMapping("/insertData")
		public String insertValidater(UserInfo ui) {
			String username=ui.getManager_name();
			String password=ui.getManager_password();
			int validate=ui.getRole_id();		
			String view="redirect:/system/validationmanager";
			if (StringUtils.isNotBlank(username)&& StringUtils.isNotBlank(password) && validate==0) {
				if (sm.InserValidater(ui)) {
					
					// 发布成功执行下面内容
					// 添加成功
					JOptionPane.showMessageDialog(null, "添加成功!");
					
				}
			
			
		 else {
				// 发布失败执行下面内容
				// 添加成功
				JOptionPane.showMessageDialog(null, "添加失败!");
			}
			}else {
				JOptionPane.showMessageDialog(null, "请补充完整内容！");
			}
			return view;
		}
		//根据id更新页面
		@RequestMapping("/update/{id}")
		public String update(HttpServletRequest request,@PathVariable int id){
			//初始化菜单
			String view=index(request, "system/update");
			//加载我的更新的数据
			UserInfo u=sm.getManagerById(id);
			request.setAttribute("user",u);
			
			return view;
		}
		//执行更新方法
		@RequestMapping("/updatevalidate")
		public String updateData(UserInfo ui){
			String view="";
			//执行更新方法
			if(sm.updatevalidate(ui)){
				//更新成功执行操作
				JOptionPane.showMessageDialog(null, "更新成功!");
				view="redirect:/system/validationmanager";
			}
			else
			{
				JOptionPane.showMessageDialog(null, "更新失败!");
				view="redirect:/system/validationmanager";
			}
			return view;
		}
		
		@RequestMapping("/delData/{id}")
		public String delvalidater(@PathVariable int id){
			String view="";
			//执行删除操作
			if(sm.deletevalidater(id)){
				JOptionPane.showMessageDialog(null, "删除成功!");
				view="redirect:/system/validationmanager";
			}
			else
			{
				JOptionPane.showMessageDialog(null, "删除失败!");
				view="redirect:/system/validationmanager";
			}
			
			return view;
		}
}
