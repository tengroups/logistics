package com.logistics.idal;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.logistics.model.CompanyInfo;

public interface ICompanyMapper {

	//获取公司信息
	@Select("CALL getCompanyInfo()")
	@Options(statementType = StatementType.CALLABLE)
	public CompanyInfo getCompanyInfo();
	//更新公司信息
		@Update("CALL updateCompanyInfo(#{company_name},#{company_code},#{company_license},#{company_tel},#{company_address},#{company_content})")
		@Options(statementType = StatementType.CALLABLE)
		public int updateCompanyInfo(CompanyInfo ci);
}
