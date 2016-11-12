package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.Departmentmodel;
import com.logistics.model.StationInfo;

public interface IStationMapper {
	//新增三级分拣站
	@Insert("CALL createStation(#{department_manager},#{station_name},#{station_manager},#{station_tel},#{station_address},#{city_name},#{district_name},#{street_name},#{manager_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int addStation(StationInfo si);
	
		// 获取二级分拣中心信息列表列数——分页
		// 包括设置和未设置管理员账户的
		@Select("CALL getStationAndManagerListCount()")
		@Options(statementType = StatementType.CALLABLE)
		public int getStationAndManagerListCount();
		
		// 获取二级分拣中心信息列表——分页
		// 包括设置和未设置管理员账户的
		@Select("CALL getstationAndManagerList(#{0},#{1})")
		@Options(statementType = StatementType.CALLABLE)
		public List<StationInfo> getStationAndManagerList(int startIndex,
				int pageSize);

		// 获取二级分拣中心信息列表——分页——优化
				// 包括设置和未设置管理员账户的
				@Select("CALL getMyStationList(#{0},#{1})")
				@Options(statementType = StatementType.CALLABLE)
				public List<StationInfo> getStationList(int startIndex,
						int pageSize);
				
		// 根据id查询分拣站信息
		@Select("CALL getStationById(#{0})")
		@Options(statementType = StatementType.CALLABLE)
		public StationInfo getStationById(int station_id);
		
		// 更新分拣站信息
		@Update("CALL updateStation(#{department_manager},#{station_name},#{station_manager},#{station_tel},#{station_address},#{city_name},#{district_name},#{street_name},#{manager_id},#{station_id})")
		@Options(statementType = StatementType.CALLABLE)
		public int updateStation(StationInfo si);
		
//		///根据managerid查找rangerid
//		@Select("CALL getStationRangerId(#{manager_id})")
//		@Options(statementType = StatementType.CALLABLE)
//		public int getstationrangerId(int manager_id);
//		
		//删除二级分拣信息
				@Delete("CALL deleteStationById(#{id})")
				@Options(statementType = StatementType.CALLABLE)
				public int deletestation(int station_id);
			
}
