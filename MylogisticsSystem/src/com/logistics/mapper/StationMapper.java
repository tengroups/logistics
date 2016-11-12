package com.logistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;



import com.logistics.idal.IStationMapper;
import com.logistics.model.Departmentmodel;
import com.logistics.model.StationInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class StationMapper {
	SqlSession session = MyBatisUtil.getsqlSession(true);
	IStationMapper mapper=session.getMapper(IStationMapper.class);
	//新建分拣站
	//创建成功返回true
	public boolean addStation(StationInfo si){
		int count=mapper.addStation(si);
		System.out.println(si.getStreet_name());
		if(count>0)
			return true;
		else
			return false;
	}

	// 获取二级分拣中心信息列表列数——分页
	// 包括设置和未设置管理员账户的
	public int getStationAndManagerListCount() {
		return mapper.getStationAndManagerListCount();
	}

	// 获取二级分拣中心信息列表
	// 包括设置和未设置管理员账户的
	public List<StationInfo> getstationAndManagerList(Map<String, Object> map) {
		return mapper.getStationAndManagerList(
				Integer.parseInt(map.get("startIndex").toString()),
				Integer.parseInt(map.get("pageSize").toString()));
	}
	
	// 获取二级分拣中心信息列表——分页——优化
			// 包括设置和未设置管理员账户的
			public List<StationInfo> getStationList(Map<String, Object> map) {
				return mapper.getStationList(
						Integer.parseInt(map.get("startIndex").toString()),
						Integer.parseInt(map.get("pageSize").toString()));
			}
			
	//根据ID查询分拣站信息
	public StationInfo getStationById(int station_id){
		return mapper.getStationById(station_id);
	}
//	//根据mangerid查询rangerid
//	public int getStationRangerId(int manager_id){
//		return mapper.getstationrangerId(manager_id);
//	}
	
	//更新分拣中心信息
	public boolean updateStation(StationInfo si){
		int count=mapper.updateStation(si);
		if(count>0)
			return true;
		else
			return false;
	}
//	
	//删除分拣中心
	public boolean deleteStationById(int station_id){
		int count=mapper.deletestation(station_id);
		if(count>0)
			return true;
		else
			return false;
	}
//	
}
