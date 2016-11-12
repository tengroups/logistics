package com.singletest;

import org.junit.Test;

import com.logistics.mapper.UserMapper;
import com.logistics.model.UserInfo;

public class validateLogin {
	@Test
	public void test(){
		UserMapper mm=new UserMapper();
		//单元测试默认设置1为管理员，我们手动添加的，来测试程序是否运行正常
		UserInfo u=mm.validateLogin("admin","admin");
		if (u!=null) {
			System.out.println(u.getRole_id());
		}
	}
}
