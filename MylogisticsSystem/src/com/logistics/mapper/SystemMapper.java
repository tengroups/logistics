package com.logistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.ISystemMapper;
import com.logistics.model.AnnounceInfo;
import com.logistics.model.SystemInfo;
import com.logistics.model.UserInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class SystemMapper {
	// 调用我的SqlSession
		SqlSession session = MyBatisUtil.getsqlSession(true);
		ISystemMapper mapper = session.getMapper(ISystemMapper.class);
		
		
		//根据ID查询我们的系统信息
		public SystemInfo getSystemInfo(int company_id){
			return mapper.getSystemInfo(company_id);
		}
		
		//更新系统信息
		public boolean updateSystem(SystemInfo si){
			int count=mapper.updateSystem(si);
			if(count>0)
				return true;
			else
				return false;
		}

			public boolean getpasswordOk(String manager_name,String manager_password){
				int count1=mapper.getpasswordOk(manager_name,manager_password);
				if (count1>0) {
					return true;
				}else {
					return false;
				}
			}
		//更新当前密码
		public boolean updatepassword(String manager_password,String manager_name){
			int count=mapper.updatepassword(manager_password,manager_name);
			if(count>0)
				return true;
			else
				return false;
		}
		//获取权限数据行数
		public int getvalidateCount(){
			return mapper.getvalidateCount();
		}
		// 获取权限列表信息
		public List<UserInfo> getvalidationInfo(Map<String,Object> map) {
	         return mapper.getvalidationInfo(Integer.parseInt(map.get("startIndex").toString()),Integer.parseInt(map.get("pageSize").toString()));
		}
		
		//新增权限用户
		//创建成功返回true
		public boolean InserValidater(UserInfo ui){
			int count=mapper.InserValidater(ui);
			
			if(count>0)
				return true;
			else
				return false;
		}
		//根据id查询权限用户
		public UserInfo getManagerById(int manager_id){
			return mapper.getManagerById(manager_id);
		}
		//更新权限信息
				public boolean updatevalidate(UserInfo ui){
					int count=mapper.updatevalidate(ui);
					if(count>0)
						return true;
					else
						return false;
				}
				//删除用户
				public boolean deletevalidater(int manager_id){
					int count=mapper.deletevalidater(manager_id);
					if(count>0)
						return true;
					else
						return false;
				}
				
}
