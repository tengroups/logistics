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
import com.logistics.mapper.CityMapper;
import com.logistics.mapper.DepartmentMapper;
import com.logistics.mapper.MenuMapper;
import com.logistics.mapper.StationMapper;
import com.logistics.mapper.UserMapper;
import com.logistics.model.AnnounceInfo;
import com.logistics.model.CityInfo;
import com.logistics.model.Departmentmodel;
import com.logistics.model.MenuInfo;
import com.logistics.model.StationInfo;
import com.logistics.model.UserInfo;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController{
	//引用常用的方法对象并实例化
			MenuMapper menuMapper=new MenuMapper();
			CityMapper cm=new CityMapper();
			DepartmentMapper dm=new DepartmentMapper();
			UserMapper um=new UserMapper();
			StationMapper sm=new StationMapper();
		    //二级分拣中心首页
			@RequestMapping("/")
			public String Index(){
				//注意这里的index.jsp在admin文件夹下的
				return "redirect:/department/index";
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
				//权限判断
				if (!menuMapper.validateMenu(role_id,"admin/index")) {
					return "redirect:/error/503";
				}
				//注意这里的index.jsp在admin文件夹下
				//测试生成菜单，默认权限为1总管理员
				List<MenuInfo> menulist=menuMapper.getTopMenu(role_id);
				List<MenuInfo> siderlist=menuMapper.getSecondMenu(role_id,7);
				
				//将取到的数据传递到前端页面，使用jstl调用
				request.setAttribute("menulist",menulist);
				request.setAttribute("siderlist",siderlist);
				//登陆的用户名放入这个对象中，传递给前端
				request.setAttribute("welcome",logname);
				return pager;	
			}
			//新建二级分拣中心
			@RequestMapping("/addcenter")
			public String addcenter(HttpServletRequest request) {
				List<CityInfo> citylist=cm.getCityList();
				List<CityInfo> arealist=cm.getAreaByCityId(1);
				List<UserInfo> userlist=um.getNoSetCenterManagerList();
			
				request.setAttribute("citylist", citylist);
				request.setAttribute("arealist", arealist);
				request.setAttribute("userlist", userlist);
		
				String view = index(request, "department/addcenter");
				return view;
			}
			//新增二级分拣中心提交
			@RequestMapping("/insertData")
			public String addcenter(Departmentmodel dl) {
		
				// 创建成功后跳转的页面地址
				String view = "redirect:/department/managercenter";
				 
				if (dm.addCenter(dl)) {
					// 发布成功执行下面内容
					// 添加成功
					JOptionPane.showMessageDialog(null, "添加成功!");
				} else {
					// 发布失败执行下面内容
					// 添加成功
					JOptionPane.showMessageDialog(null, "添加失败!");
				}

				return view;
			}
			
			// 管理二级分拣中心
			@RequestMapping("/managercenter")
			public String managercenter(HttpServletRequest request, Model model,
					@RequestParam(required = false) String searchInfo,
					@RequestParam(required = false) Integer pageNum,
					@RequestParam(required = false) Integer pageSize) {
				// 初始化菜单
				String view =index(request, "department/managercenter");
				// 添加分页相关信息
				// 创建Map，来存放我们的数据，
				Map<String, Object> map = new HashMap<String, Object>();
				// 其实这个searchInfo就是我们动态查询时的查询条件，这里无用
				map.put("searchInfo", searchInfo);
				// 取出数据总数，
				Integer totalCount = dm.getCenterAndManagerListCount();
				// 初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
				this.initPage(map, pageNum, pageSize, totalCount);
				// list为我们的需要显示的数据List ，获取的返回值是我们常用的List<实体类>形式
				List list = dm.getCenterList(map);//getCenterAndManagerList(map);
				
				// list为我们的需要显示的数据List
				// 初始化结果
				this.initResult(model, list, map);
				return view;
			}
			
			// 根据ID查询分拣中心信息
			@RequestMapping("/details/{id}")
			public String showDepartment(@PathVariable int id, HttpServletRequest request) {
				Departmentmodel di=dm.getCenterById(id);
				request.setAttribute("departInfo",di);
				return "department/details";
			}
			
			//根据id更新页面
			@RequestMapping("/update/{id}")
			public String update(HttpServletRequest request,@PathVariable int id){
				//初始化菜单
				List<CityInfo> citylist=cm.getCityList();
				List<CityInfo> arealist=cm.getAreaByCityId(1);
				List<UserInfo> userlist=um.getNoSetCenterManagerList();
				request.setAttribute("citylist", citylist);
				request.setAttribute("arealist", arealist);
				request.setAttribute("userlist", userlist);
				String view=index(request, "department/update");
				//加载我的更新的数据
				Departmentmodel di=dm.getCenterById(id);
				int managerid=di.getManager_id();
				int rangerid=dm.getrangerid(managerid);
				di.setRangerId(rangerid);
				request.setAttribute("departinfo", di);
				return view;
			}
			//执行更新方法
			@RequestMapping("/updateData")
			public String updateData(Departmentmodel di){
				String view="";
				//执行更新方法
				if(dm.updateCenter(di)){
					//更新成功执行操作
					JOptionPane.showMessageDialog(null, "更新成功!");
					view="redirect:/department/managercenter";
				}
				else
				{
					JOptionPane.showMessageDialog(null, "更新失败!");
					view="redirect:/department/update";
				}
				return view;
			}
			
			//删除公告，根据id
			@RequestMapping("/delData/{id}")
			public String delDepart(@PathVariable int id){
				String view="";
				//执行删除操作
				if(dm.deleteDepart(id)){
					JOptionPane.showMessageDialog(null, "删除成功!");
					view="redirect:/department/managercenter";
				}
				else
				{
					JOptionPane.showMessageDialog(null, "删除失败!");
					view="redirect:/department/managercenter";
				}
				
				return view;
			}
		//三级分拣站
			//新建三级分拣站
			@RequestMapping("/addstation")
			public String addstation(HttpServletRequest request) {
				List<UserInfo> userlist=um.getNotStationManagerList();
				List<CityInfo> citylist=cm.getCityList();
				List<CityInfo> arealist=cm.getAreaByCityId(1);
				List<UserInfo> managerlist=um.getDepartManager(2);
				request.setAttribute("userlist1", managerlist);
				request.setAttribute("citylist",citylist);
				request.setAttribute("arealist",arealist);
				request.setAttribute("userlist", userlist);
				String view = index(request, "department/addstation");
				return view;
			}
			//新增三级分拣站
			@RequestMapping("/insertstation")
			public String addstation(StationInfo si,HttpServletRequest request) {
				// 创建成功后跳转的页面地址
				String view = "redirect:/department/managerstation";
				System.out.println(si.getDepartment_manager());
				 
				if (sm.addStation(si)) {
					// 发布成功执行下面内容
					// 添加成功
					JOptionPane.showMessageDialog(null, "添加成功!");
				} else {
					// 发布失败执行下面内容
					// 添加成功
					JOptionPane.showMessageDialog(null, "添加失败!");
				}

				return view;
			}
			
			// 管理三级分拣站
			@RequestMapping("/managerstation")
			public String managerstation(HttpServletRequest request, Model model,
					@RequestParam(required = false) String searchInfo,
					@RequestParam(required = false) Integer pageNum,
					@RequestParam(required = false) Integer pageSize) {
				// 初始化菜单
				String view =index(request, "department/managerstation");
				// 添加分页相关信息
				// 创建Map，来存放我们的数据，
				Map<String, Object> map = new HashMap<String, Object>();
				// 其实这个searchInfo就是我们动态查询时的查询条件，这里无用
				map.put("searchInfo", searchInfo);
				// 取出数据总数，
				Integer totalCount =sm.getStationAndManagerListCount();
				// 初始化分页数据，map对应的model，pageNum对应当前页，pageSize为每页显示的数据，totalCount为数据总行数
				this.initPage(map, pageNum, pageSize, totalCount);
				// list为我们的需要显示的数据List ，获取的返回值是我们常用的List<实体类>形式
				List list = sm.getStationList(map);//getCenterAndManagerList(map);
				// 初始化结果
				this.initResult(model, list, map);
				return view;
			}
			
			// 根据ID查询分拣中心信息
			@RequestMapping("/stationdetails/{id}")
			public String showStation(@PathVariable int id, HttpServletRequest request) {
				String view =index(request, "department/stationdetails");
				StationInfo si=sm.getStationById(id);
				request.setAttribute("stationInfo",si);
				return view;
			}
			
			//根据id更新页面
			@RequestMapping("/updatestation/{id}")
			public String updatestation(HttpServletRequest request,@PathVariable int id){
				//初始化菜单
				List<UserInfo> userlist=um.getNotStationManagerList();
				List<CityInfo> citylist=cm.getCityList();
				List<CityInfo> arealist=cm.getAreaByCityId(1);
				List<UserInfo> managerlist=um.getDepartManager(2);
				request.setAttribute("userlist1", managerlist);
				request.setAttribute("citylist",citylist);
				request.setAttribute("arealist",arealist);
				request.setAttribute("userlist", userlist);
			
				String view=index(request, "department/updatestation");
				//加载我的更新的数据
				StationInfo si=sm.getStationById(id);
				request.setAttribute("stationinfo",si);
				return view;
			}
			//执行更新方法
			@RequestMapping("/updatestationData")
			public String updatestationData(StationInfo si){
				System.out.println(si.getCity_name()+"城市,"+si.getDepartment_manager()+"二级管理,"+si.getManager_id()+"管理id"+si.getStation_address()+"地址,");
				String view="";
				//执行更新方法
				if(sm.updateStation(si)){
					//更新成功执行操作
					JOptionPane.showMessageDialog(null, "更新成功!");
					view="redirect:/department/managerstation";
				}
				else
				{
					JOptionPane.showMessageDialog(null, "更新失败!");
					view="redirect:/department/updatestation";
				}
				return view;
			}
			
			//删除公告，根据id
			@RequestMapping("/delstationData/{id}")
			public String delstation(@PathVariable int id){
				String view="";
				//执行删除操作
				if(sm.deleteStationById(id)){
					JOptionPane.showMessageDialog(null, "删除成功!");
					view="redirect:/department/managerstation";
				}
				else
				{
					JOptionPane.showMessageDialog(null, "删除失败!");
					view="redirect:/department/managerstation";
				}
				
				return view;
			}
}
