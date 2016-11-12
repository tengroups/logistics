package com.logistics.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.IAnnounceMapper;
import com.logistics.model.AnnounceInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class AnnounceMapper {
	// 调用我的SqlSession
	SqlSession session = MyBatisUtil.getsqlSession(true);
	IAnnounceMapper mapper = session.getMapper(IAnnounceMapper.class);

	// 获取公告列表信息
	public List<AnnounceInfo> getAnnounceList(Map<String,Object> map) {
         return mapper.getAnnounceList(Integer.parseInt(map.get("startIndex").toString()),Integer.parseInt(map.get("pageSize").toString()));
	}
	
	//获取公告数据行数
	public int getAnnounceCount(){
		return mapper.getAnnounceCount();
	}
	
	//根据ID查询我们的公告
	public AnnounceInfo getAnnounceById(int announce_id){
		return mapper.getAnnounceById(announce_id);
	}
	
	//发布公告
	//创建成功返回true
	public boolean addAnnounce(AnnounceInfo ai){
		int count=mapper.addAnnounce(ai);
		
		if(count>0)
			return true;
		else
			return false;
	}
	
	//更新公告
	public boolean updateAnnounce(AnnounceInfo ai){
		int count=mapper.updateAnnounce(ai);
		if(count>0)
			return true;
		else
			return false;
	}
	
	//删除公告
	public boolean deleteAnnounce(int announce_id){
		int count=mapper.deleteAnnounce(announce_id);
		if(count>0)
			return true;
		else
			return false;
	}
}
