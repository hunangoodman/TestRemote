package com.treasure.dao;

import com.treasure.model.MobileCode;
import com.treasure.model.MobileCodeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MobileCodeMapper {
	int countByExample(MobileCodeExample example);

	int deleteByExample(MobileCodeExample example);

	int deleteByPrimaryKey(String mobile);

	int insert(MobileCode record);

	int insertSelective(MobileCode record);

	List<MobileCode> selectByExample(MobileCodeExample example);

	MobileCode selectByPrimaryKey(String mobile);

	int updateByExampleSelective(@Param("record") MobileCode record, @Param("example") MobileCodeExample example);

	int updateByExample(@Param("record") MobileCode record, @Param("example") MobileCodeExample example);

	int updateByPrimaryKeySelective(MobileCode record);

	int updateByPrimaryKey(MobileCode record);
	//根据用户名电话号码修改状态
	int updateByPrimaryKey1(MobileCode record);

	MobileCode selectByMemberId(@Param("memberId") Long memberId);

	List<MobileCode> selectByExample(MobileCodeExample example, String username);

	byte selectStatu(@Param("mobile")String mobile, @Param("username")String username);

	MobileCode selectCode(@Param("mobile")String mobile, @Param("userName")String username);
}