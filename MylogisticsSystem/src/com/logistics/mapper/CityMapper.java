package com.logistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.ICityMapper;
import com.logistics.model.CityInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class CityMapper {
	// 调用我的SqlSession
		SqlSession session = MyBatisUtil.getsqlSession(true);
		// 得到ICityMapper接口的实现类对象，
	//ICityMapper接口的实现类对象由sqlSession.getMapper(ICityMapper.class)动态构建出来
		ICityMapper mapper = session.getMapper(ICityMapper.class);
	//获取城市信息
	public List<CityInfo> getCityList(){
		return mapper.getCityList();
	}
	//获取区县信息
	public List<CityInfo> getAreaByCityId(int city_id) {
		return mapper.getAreaByCityId(city_id);
	}
	//获取区号
	public List<CityInfo> getCodeByCityId(int city_id){
		return mapper.getCodeByCityId(city_id);
	}
	//获取街道名称
	public List<CityInfo> getStreetByCityId(int parent_id){
		return mapper.getStreetByCityId(parent_id);
		
	}
	
	//测试方法，计算total总数
		public int getCityTotal(){
			return mapper.getCityTotal();
		}
		
		//此项查询的是选择哪一页数据
		public List<CityInfo> getCityMap(Map<String,Object> map){
			//String s=(String)map.get("startIndex");
			//String p=(String)map.get("pageSize");
			return mapper.getCityPager(Integer.parseInt(map.get("startIndex").toString()),Integer.parseInt(map.get("pageSize").toString()));
		}
	}