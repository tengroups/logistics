package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.MenuInfo;
import com.logistics.model.UserInfo;

public interface IMenuMapper {

	//根据对应的权限检索出顶部导航标题
	//注解调用存储过程
	@Select("CALL getTopMenu(#{role_id})")
	//设置类型为CALL可用
	@Options(statementType=StatementType.CALLABLE)
	public List<MenuInfo> getTopMenu(int role_id);

	//二级管理目录
	//管理首页下
	@Select("CALL getSecondMenu(#{0},#{1})")
	@Options(statementType=StatementType.CALLABLE)
	public List<MenuInfo> getSecondMenu(int role_id,int upMenuId);
	
	//	目录权限验证
	//	参数两个：
	//role_id角色 
	//validateMenuPath验证的路径地址
	//返回count，在实现里做true或者false判断
	//或者写存储过程返回true或者false--mysql不支持布尔型
	//所以选择第一种方法解决
	@Select("CALL validateMenu(#{0},#{1})")
	@Options(statementType=StatementType.CALLABLE)
	public int validateMenu(int role_id,String validateMenuPath);
	
	
	
	
}
