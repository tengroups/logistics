package com.logistics.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.IUserMapper;
import com.logistics.model.UserInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class UserMapper {
	//调用我的sqlsession执行完sql语句后自动提交事物
		SqlSession session=MyBatisUtil.getsqlSession(true);
		//得到IUserMapper接口的实现类对象，IUserMapper接口的实现类对象由sqlSession.getmapper()
		IUserMapper mapper =session.getMapper(IUserMapper.class);
		
		public UserInfo validateLogin(String username,String password){
			
			//在此处添加密码加密

			
			
			//执行查询操作，将结果自动封装成menuinfo的list形式返回
					UserInfo u=mapper.validateLogin(username,password);
					// 执行完关闭session
					SqlSession session = MyBatisUtil.getsqlSession(true);
					session.close();
					return u;
		}
		//获取用户列表
		
		public List<UserInfo> getUserList(){
			List<UserInfo> userlist=mapper.getUserList();
			return userlist;
		}
		//条形码生成路径上传到数据库
		public boolean inseruser(String rootpath){
			//上传图片路径到数据库
			boolean list=mapper.inseruser(rootpath);
			SqlSession session = MyBatisUtil.getsqlSession(true);
			session.close();
			return true;
//			
		}
		
		
		// 获取未分配二级分拣中心管理权限的二级管理员列表
		public List<UserInfo> getNoSetCenterManagerList() {
	        return mapper.getNoSetCenterManagerList();
		}
		// 获取未分配三级分拣站管理权限的二级管理员列表
				public List<UserInfo>getNotStationManagerList(){
			        return mapper.getNotStationManagerList();
				}
				// 获取未分配三级分拣站管理权限的二级管理员列表
				public List<UserInfo>getDepartManager(int role_id){
			        return mapper.getDepartManager(role_id);
				}
				
}
