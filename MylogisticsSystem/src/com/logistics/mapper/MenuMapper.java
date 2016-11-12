package com.logistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.IMenuMapper;
import com.logistics.model.MenuInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class MenuMapper {
//调用我的sqlsession执行完sql语句后自动提交事物
	SqlSession session=MyBatisUtil.getsqlSession(true);
	//得到IUserMapper接口的实现类对象，IUserMapper接口的实现类对象由sqlSession.getmapper()
	IMenuMapper mapper =session.getMapper(IMenuMapper.class);
	
	public List<MenuInfo> getTopMenu(int role_id){
		//执行查询操作，将结果自动封装成menuinfo的list形式返回
				List<MenuInfo> menulist=mapper.getTopMenu(role_id);
				//调用我的sqlsession执行完sql语句后自动提交事物
				SqlSession session=MyBatisUtil.getsqlSession(true);
				//执行完关闭
				session.close();
				return menulist;
	}
	
	//二级管理目录
	//管理首页
	public List<MenuInfo> getSecondMenu(int role_id,int upMenuId){
		List<MenuInfo> menulist=mapper.getSecondMenu(role_id, upMenuId);
		return menulist;
	}
	
//	目录权限验证
	//	参数两个：
	//role_id角色 
	//validateMenuPath验证的路径地址
	//返回count，在实现里做true或者false判断
	//或者写存储过程返回true或者false--mysql不支持布尔型
	//所以选择第一种方法解决
	
	public boolean validateMenu(int role_id,String validateMenuPath){
		int result=mapper.validateMenu(role_id, validateMenuPath);
		if (result>0) 
		return true;
		else 
			return false;
		
		
	}
	
}
