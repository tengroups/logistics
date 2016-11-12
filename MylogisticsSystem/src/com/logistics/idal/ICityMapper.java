package com.logistics.idal;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import com.logistics.model.CityInfo;

public interface ICityMapper {
	//获取城市信息
@Select("CALL getCityList()")
@Options(statementType=StatementType.CALLABLE)
public List<CityInfo> getCityList();
//根据城市查询对应的区县
@Select("CALL getAreaByCityId(#{select_id})")
@Options(statementType=StatementType.CALLABLE)
public List<CityInfo> getAreaByCityId(int city_id);
//根据城市查询对应区县的编号
@Select("CALL getCodeByCityId(#{select_id})")
@Options(statementType=StatementType.CALLABLE)
public List<CityInfo> getCodeByCityId(int city_id);
//根据区县查询对应的街道
@Select("CALL getStreetByParentId(#{select_id})")
@Options(statementType=StatementType.CALLABLE)
public List<CityInfo> getStreetByCityId(int parent_id);

//测试方法，计算total总数
@Select("CALL getCityTotal()")
@Options(statementType = StatementType.CALLABLE)
public int getCityTotal();

/*
 * 参数：
 * 第一个：当前页面数据的起始id
 * 第二个：当前页面显示的数据内容
 * 解释：假如数据有17条，每页显示5条，
 * 第一页显示1-5
 * 第二页显示6-10
 * 第三页显示11-15
 * 第四页显示16-17
 * 如何取这里不管，这里只是分页查询
 * */
@Select("CALL getCityPager(#{0},#{1})")
//@Options(statementType = StatementType.CALLABLE)
public List<CityInfo> getCityPager(int startIndex,int pageSize);
}


