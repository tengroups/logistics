package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.AnnounceInfo;
import com.logistics.model.SystemInfo;
import com.logistics.model.UserInfo;


public interface ISystemMapper {
	//根据ID查询我们的系统信息
		@Select("CALL getSystemInfo(#{0})")
		@Options(statementType = StatementType.CALLABLE)
		public SystemInfo getSystemInfo(int company_id);
		
		//更新系统信息
		@Update("CALL updateSystemInfo(#{company_name},#{company_code},#{company_license},#{company_address},#{company_tel},#{company_content})")
		@Options(statementType = StatementType.CALLABLE)
		public int updateSystem(SystemInfo si);
		
		//判断用户名密码是否正确
		@Select("CALL getPasswordOk(#{0},#{1})")
		@Options(statementType = StatementType.CALLABLE)
		public int getpasswordOk(String manager_name,String manager_password);
		//更新密码
		@Update("CALL updatepassword(#{0},#{1})")
		@Options(statementType = StatementType.CALLABLE)
		public int updatepassword(String manager_password,String manager_name);
		
		//获取权限数据行数
		@Select("CALL getValidateCount()")
		@Options(statementType = StatementType.CALLABLE)
		public int getvalidateCount();
		
		
		//获取权限列表信息
		@Select("CALL getValidationInfo(#{0},#{1})")
		@Options(statementType = StatementType.CALLABLE)
		public List<UserInfo> getvalidationInfo(int startIndex,int pageSize);
		
		//新增权限用户
		@Insert("CALL InserValidater(#{manager_name},#{manager_password},#{role_id},#{department_id})")
		@Options(statementType = StatementType.CALLABLE)
		public int InserValidater(UserInfo ui);
		
		//根据id查询权限信息
		@Select("CALL getManagerInfoById(#{0})")
		@Options(statementType = StatementType.CALLABLE)
		public UserInfo getManagerById(int manager_id);
		//更新公告
		@Update("CALL updateValidationById(#{manager_name},#{manager_password},#{role_id},#{manager_id})")
		@Options(statementType = StatementType.CALLABLE)
		public int updatevalidate(UserInfo ui);
	
		//删除公告
		@Delete("CALL deleteValidater(#{id})")
		@Options(statementType = StatementType.CALLABLE)
		public int deletevalidater(int manager_id);
		
}
