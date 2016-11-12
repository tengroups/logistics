package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.AnnounceInfo;


public interface IAnnounceMapper {
	
	//获取公告数据行数
	@Select("CALL getAnnounceCount()")
	@Options(statementType = StatementType.CALLABLE)
	public int getAnnounceCount();
	
	// 获取公告列表信息
	@Select("CALL getAnnounceList(#{0},#{1})")
	@Options(statementType = StatementType.CALLABLE)
	public List<AnnounceInfo> getAnnounceList(int startIndex,int pageSize);
	
	//根据ID查询我们的公告
	@Select("CALL getAnnounceById(#{0})")
	@Options(statementType = StatementType.CALLABLE)
	public AnnounceInfo getAnnounceById(int announce_id);
	
	//发布公告
	@Insert("CALL createAnnounce(#{announce_Title},#{announce_Content},#{manager_id})")
	@Options(statementType = StatementType.CALLABLE)
	public int addAnnounce(AnnounceInfo ai);
	
	//更新公告
	@Update("CALL updateAnnounce(#{announce_Title},#{announce_Content},#{manager_id},#{announce_Id})")
	@Options(statementType = StatementType.CALLABLE)
	public int updateAnnounce(AnnounceInfo ai);
	
	//删除公告
	@Delete("CALL deleteAnnounce(#{id})")
	@Options(statementType = StatementType.CALLABLE)
	public int deleteAnnounce(int announce_id);
	
}
