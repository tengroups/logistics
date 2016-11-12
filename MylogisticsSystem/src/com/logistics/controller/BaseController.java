package com.logistics.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.ui.Model;

import com.logistics.util.Pager;

public class BaseController {
//初始化分页信息
/*	参数
 * 第一个：map为压入的数据，数据为我们分页相关数据
 * 第二个：当前页码
 * 第三个：每页显示行数
 * 第四个：总的数据行数
 */
	protected void initPage(Map<String,Object> map,Integer pageNum,Integer pageSize,Integer totalCount){
		if (null==pageSize|| pageSize.equals("")) {
			pageSize=5;
		}
		//这里做了限制，每页最多显示50行
		if (pageSize>50) {
			pageSize=50;
			
		}
		//计算总页数
//		（总行数+每页显示行数-1）/每页行数
		Integer totalPage=(totalCount+pageSize-1)/pageSize;
//		当前页码为空，默认设置为第一页
		if (null==pageNum) {
			pageNum=1;
		}else if(pageNum>totalPage){
		//如果当前页码大于实际总页码，则等于总页码	
			pageNum=totalPage;
		}
		/*当各个参数压入map中
		 * 分别对应：起始页、当前页、总页、每页行数、总行数
		 * */
		map.put("startIndex",Pager.getStartIndex(pageNum, pageSize));
		map.put("pageNum",pageNum);
		map.put("totalPage",totalPage);
		map.put("pageSize",pageSize);
		map.put("totalCount",totalCount);
	}
	//将相关数据放入model
	protected void initResult(Model model,List<Object> list,Map<String,Object> map){
		//定义属性list存放
		model.addAttribute("list",list);
		//Interator是迭代器，map定义为HashMap,存放的是我们分页的数据信息
		Iterator it=map.entrySet().iterator();
		//获得map的迭代器，用作遍历map中的每一个键值对
		while (it.hasNext()) {
			Map.Entry m=(Entry) it.next();
			//将取到的键值对存入model
			model.addAttribute(m.getKey().toString(),m.getValue());
			
		}
	}
}
