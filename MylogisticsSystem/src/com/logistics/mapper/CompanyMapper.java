package com.logistics.mapper;

import org.apache.ibatis.session.SqlSession;

import com.logistics.idal.ICompanyMapper;
import com.logistics.model.CompanyInfo;
import com.logistics.mybatisutil.MyBatisUtil;

public class CompanyMapper {
	// �����ҵ�SqlSession
	SqlSession session = MyBatisUtil.getsqlSession(true);
	ICompanyMapper mapper = session.getMapper(ICompanyMapper.class);

	// ʵ�ַ���
	public CompanyInfo getCompanyInfo() {
		return mapper.getCompanyInfo();
	}
	
	public boolean updateCompanyInfo(CompanyInfo ci) {
		int count=mapper.updateCompanyInfo(ci);
		if(count>0)
			return true;
		else
			return false;
	}
}
