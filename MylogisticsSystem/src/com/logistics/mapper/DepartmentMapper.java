package com.logistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.IAnnounceMapper;
import com.logistics.idal.IDepartmentMapper;
import com.logistics.model.AnnounceInfo;
import com.logistics.model.Departmentmodel;
import com.logistics.model.StationInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class DepartmentMapper {

	// 调用我的SqlSession
		SqlSession session = MyBatisUtil.getsqlSession(true);
		IDepartmentMapper mapper=session.getMapper(IDepartmentMapper.class);
		//新建分拣中心
		//创建成功返回true
		public boolean addCenter(Departmentmodel dm){
			int count=mapper.addCenter(dm);
			
			if(count>0)
				return true;
			else
				return false;
		}
		//根据mangerid查询rangerid
		public int getrangerid(int manager_id){
			return mapper.getrangerid(manager_id);
		}
		
		//根据ID查询分拣中心信息
		public Departmentmodel getCenterById(int department_id){
			return mapper.getCenterById(department_id);
		}
		
		//更新分拣中心信息
		public boolean updateCenter(Departmentmodel di){
			int count=mapper.updateCenter(di);
			if(count>0)
				return true;
			else
				return false;
		}
		
		//删除分拣中心
		public boolean deleteDepart(int department_id){
			int count=mapper.deleteDepart(department_id);
			if(count>0)
				return true;
			else
				return false;
		}
		
		// 获取二级分拣中心信息列表列数——分页
		// 包括设置和未设置管理员账户的
		public int getCenterAndManagerListCount() {
			return mapper.getCenterAndManagerListCount();
		}

		// 获取二级分拣中心信息列表
		// 包括设置和未设置管理员账户的
		public List<Departmentmodel> getCenterAndManagerList(Map<String, Object> map) {
			return mapper.getCenterAndManagerList(
					Integer.parseInt(map.get("startIndex").toString()),
					Integer.parseInt(map.get("pageSize").toString()));
		}

		// 获取二级分拣中心信息列表——分页——优化
		// 包括设置和未设置管理员账户的
		public List<Departmentmodel> getCenterList(Map<String, Object> map) {
			return mapper.getCenterList(
					Integer.parseInt(map.get("startIndex").toString()),
					Integer.parseInt(map.get("pageSize").toString()));
		}
			
}
