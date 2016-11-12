package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.StationInfo;
import com.logistics.model.UserInfo;

public interface IUserMapper {
	//判断用户登录，返回登陆成功的用户对象
		//注解调用存储过程
		@Select("CALL validateLogin(#{0},#{1})")
		//设置类型为CALL可用
		@Options(statementType=StatementType.CALLABLE)
		public UserInfo validateLogin(String username,String password);

		//获取用户列表
		@Select("CALL getUserList()")
		@Options(statementType=StatementType.CALLABLE)
		public List<UserInfo> getUserList();
		
		//插入条码路径到数据库
		@Insert("CALL Insertpath(#{0})")
		public boolean inseruser(String rootpath);
		
		//获取未分配二级分拣中心管理权限的二级管理员列表
		@Select("CALL getNoSetCenterManagerList()")
		@Options(statementType = StatementType.CALLABLE)
		public List<UserInfo> getNoSetCenterManagerList();

	// 获取未分配二级分拣中心管理权限的二级管理员列表
	@Select("CALL getNotStationmanager()")
	@Options(statementType = StatementType.CALLABLE)
	public List<UserInfo> getNotStationManagerList();
	
	// 根据id查询分拣中心信息
			@Select("CALL getDepartManager(#{0})")
			@Options(statementType = StatementType.CALLABLE)
			public List<UserInfo> getDepartManager(int role_id);
}
