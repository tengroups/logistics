package com.singletest;

import org.junit.Test;

import com.logistics.mapper.MenuMapper;

public class validateMenu {
@Test
public void test(){
	MenuMapper mm=new MenuMapper();
	if (mm.validateMenu(1,"admin/index")) {
		System.out.println("有访问权限");
	}else {
		System.out.println("无访问权限");
	}
}
}
