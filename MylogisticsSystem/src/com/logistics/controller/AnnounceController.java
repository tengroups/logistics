package com.logistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logistics.interceptor.MemberInterceptor;
import com.logistics.mapper.AnnounceMapper;
import com.logistics.mapper.MenuMapper;
import com.logistics.model.AnnounceInfo;
import com.logistics.model.MenuInfo;
import com.logistics.model.UserInfo;

@Controller
@RequestMapping("/announce")
public class AnnounceController extends BaseController {
	// 引入常用的方法对象并实例化
	MenuMapper menuMapper = new MenuMapper();
	AnnounceMapper am = new AnnounceMapper();

	// 发布公告
	@RequestMapping("/create")
	public String createAnnounce(HttpServletRequest request) {

		String view = initMenu(request, "announce/create");

		return view;
	}

	public String initMenu(HttpServletRequest request, String pager) {
		// 获取session内的用户信息
		HttpSession session = request.getSession();
		UserInfo u = (UserInfo) session
				.getAttribute(MemberInterceptor.SESSION_MEMBER);
		// 登录管理用户名
		String logname = u.getManager_name();
		int role_id = u.getRole_id();
		int manager_id = u.getManager_id();
		// ModelAndView view;
		// 权限判断
		if (!menuMapper.validateMenu(role_id, "admin/index")) {
			// 无权访问
			// view =new ModelAndView("error/503");
			return "redirect:/error/503";
		}

		// 测试生成菜单，默认权限为1-总管理员
		List<MenuInfo> menulist = menuMapper.getTopMenu(role_id);
		List<MenuInfo> siderlist = menuMapper.getSecondMenu(role_id, 1);
		// 将取到的数据传递到前端页面，使用jstl调用
		request.setAttribute("menulist", menulist);
		request.setAttribute("siderlist", siderlist);
		// 登录的用户名放入这个对象中，传递给前端
		request.setAttribute("welcome", logname);
		request.setAttribute("manager_id", manager_id);

		return pager;
	}

	//公告管理列表
	@RequestMapping("/manager")
	public String managerAnnounce(HttpServletRequest request, Model model,
			@RequestParam(required = false) String searchInfo,
			@RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize) {
		// 初始化菜单信息
		String view = initMenu(request, "announce/manager");
		// 添加分页相关信息
		// 创建Map，来存放我们的数据，
		Map<String, Object> map = new HashMap<String, Object>();
		// 这里的searchInfo是查询方法 在这里用不到，比如我们需要根据条件来查询我们的数据，这个就需要了
		// 其实这个searchInfo就是我们动态查询时的查询条件，这里无用
		map.put("searchInfo", searchInfo);

		// 取出数据总数，
		Integer totalCount = am.getAnnounceCount();
		// 初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
		this.initPage(map, pageNum, pageSize, totalCount);
		// list为我们的需要显示的数据List ，获取的返回值是我们常用的List<实体类>形式
		List list = am.getAnnounceList(map);
		// list为我们的需要显示的数据List
		// 初始化结果
		this.initResult(model, list, map);

		return view;
	}

	// 查看公告列表
	@RequestMapping("/list")
	public String announcelist(HttpServletRequest request, Model model,
			@RequestParam(required = false) String searchInfo,
			@RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize) {
	// 初始化菜单信息
	String view = initMenu(request, "announce/list");
	// 添加分页相关信息
	// 创建Map，来存放我们的数据，
	Map<String, Object> map = new HashMap<String, Object>();
	// 这里的searchInfo是查询方法 在这里用不到，比如我们需要根据条件来查询我们的数据，这个就需要了
	// 其实这个searchInfo就是我们动态查询时的查询条件，这里无用
	map.put("searchInfo", searchInfo);

	// 取出数据总数，
	Integer totalCount = am.getAnnounceCount();
	// 初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
	this.initPage(map, pageNum, pageSize, totalCount);
	// list为我们的需要显示的数据List ，获取的返回值是我们常用的List<实体类>形式
	List list = am.getAnnounceList(map);
	// list为我们的需要显示的数据List
	// 初始化结果
	this.initResult(model, list, map);

	return view;
	}

	// 根据ID查询公告
	@RequestMapping("/details/{id}")
	public String showAnnounce(@PathVariable int id, HttpServletRequest request) {
		AnnounceInfo ai = am.getAnnounceById(id);
		request.setAttribute("announceInfo", ai);

		return "announce/details";
	}

	// 发布公告
	@RequestMapping("/insertData")
	public String insertAnnounce(AnnounceInfo ai) {

		String view="";

		if (am.addAnnounce(ai)) {
			// 发布成功执行下面内容
			// 添加成功
			JOptionPane.showMessageDialog(null, "添加成功!");
			view = "redirect:/announce/manager";
		} else {
			// 发布失败执行下面内容
			// 添加成功
			JOptionPane.showMessageDialog(null, "添加失败!");
			view = "redirect:/error/shibai";
		}

		return view;
	}
	
	//根据id更新页面
	@RequestMapping("/update/{id}")
	public String update(HttpServletRequest request,@PathVariable int id){
		//初始化菜单
		String view=initMenu(request, "announce/update");
		//加载我的更新的数据
		AnnounceInfo ai = am.getAnnounceById(id);
		request.setAttribute("announceInfo", ai);
		
		return view;
	}
	
	//执行更新方法
	@RequestMapping("/updateData")
	public String updateData(AnnounceInfo ai){
		String view="";
		//执行更新方法
		if(am.updateAnnounce(ai)){
			//更新成功执行操作
			JOptionPane.showMessageDialog(null, "更新成功!");
			view="redirect:/announce/manager";
		}
		else
		{
			JOptionPane.showMessageDialog(null, "更新失败!");
			view="redirect:/announce/manager";
		}
		return view;
	}
	
	
	//删除公告，根据id
	@RequestMapping("/delData/{id}")
	public String delAnnounce(@PathVariable int id){
		String view="";
		//执行删除操作
		if(am.deleteAnnounce(id)){
			JOptionPane.showMessageDialog(null, "删除成功!");
			view="redirect:/announce/manager";
		}
		else
		{
			JOptionPane.showMessageDialog(null, "删除失败!");
			view="redirect:/announce/manager";
		}
		
		return view;
	}

}
