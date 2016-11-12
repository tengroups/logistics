package com.singletest;

import java.util.List;

import org.junit.Test;

import com.logistics.mapper.MenuMapper;
import com.logistics.model.MenuInfo;


public class menuTest {
	@Test
	public void test(){
		MenuMapper mm=new MenuMapper();
		//单元测试默认设置1为管理员，我们手动添加的，来测试程序是否运行正常
		List<MenuInfo> mi=mm.getTopMenu(1);
		for (MenuInfo m : mi) {
			System.out.println(m.getMenuContent());
		}
	}
	
}
