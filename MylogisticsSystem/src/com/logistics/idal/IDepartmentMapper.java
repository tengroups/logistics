package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.AnnounceInfo;
import com.logistics.model.Departmentmodel;
import com.logistics.model.StationInfo;

public interface IDepartmentMapper {
	//新增二级分拣中心
		@Insert("CALL createCenter(#{department_name},#{city_name},#{department_code},#{district_name},#{department_address},#{department_manager},#{manager_id},#{department_tel},#{department_mobile})")
		@Options(statementType = StatementType.CALLABLE)
		public int addCenter(Departmentmodel dm);
		
		//根据id查询分拣中心信息
		@Select("CALL getCenterById(#{0})")
		@Options(statementType = StatementType.CALLABLE)
		public Departmentmodel getCenterById(int department_id);
		
		//更新分拣中心信息
		@Update("CALL updateCenter(#{department_name},#{city_name},#{department_code},#{district_name},#{department_address},#{department_manager},#{manager_id},#{department_tel},#{department_mobile},#{department_id},#{rangerId})")
		@Options(statementType = StatementType.CALLABLE)
		public int updateCenter(Departmentmodel dm);
		
		///根据managerid查找rangerid
		@Select("CALL getRangerId(#{manager_id})")
		@Options(statementType = StatementType.CALLABLE)
		public int getrangerid(int manager_id);
		
		//删除二级分拣信息
		@Delete("CALL deleteDepartmentById(#{id})")
		@Options(statementType = StatementType.CALLABLE)
		public int deleteDepart(int department_id);
		
		// 获取二级分拣中心信息列表列数——分页
		// 包括设置和未设置管理员账户的
		@Select("CALL getCenterAndManagerListCount()")
		@Options(statementType = StatementType.CALLABLE)
		public int getCenterAndManagerListCount();
		
		// 获取二级分拣中心信息列表——分页
		// 包括设置和未设置管理员账户的
		@Select("CALL getCenterAndManagerList(#{0},#{1})")
		@Options(statementType = StatementType.CALLABLE)
		public List<Departmentmodel> getCenterAndManagerList(int startIndex,
				int pageSize);

		// 获取二级分拣中心信息列表——分页——优化
		// 包括设置和未设置管理员账户的
		@Select("CALL getMyCenterList(#{0},#{1})")
		@Options(statementType = StatementType.CALLABLE)
		public List<Departmentmodel> getCenterList(int startIndex,
				int pageSize);
		
	
}
