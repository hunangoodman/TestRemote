package com.treasure.dao;

import org.apache.ibatis.annotations.Param;

import com.treasure.bean.PageInfo;
import com.treasure.model.BtoolsConsult;

import java.util.List;
import java.util.Map;


/**
 * @class 咨询
 * @author 吴健 E-mail:1582406829@qq.com
 * @date 2017-12-14
 */


public interface BtoolsConsultMapper{

	public int insert(@Param("item") BtoolsConsult btoolsConsult);
   	
   	public int batchInsert(@Param("list") List<BtoolsConsult> btoolsConsultList);
   	
	public int updateByMap(Map<String, Object> modifyMap);
	
	public int updateByModel(@Param("record")BtoolsConsult btoolsConsult);
	
	public int deleteByIds(@Param("list") List<Long> list);
	
	public int deleteById(Long conId);

	public int deleteByModel(@Param("record") BtoolsConsult btoolsConsult);
	

	public List<BtoolsConsult> selectByModel(@Param("record") BtoolsConsult btoolsConsult);
	
	public int  selectTotalRecord(@Param("record") BtoolsConsult btoolsConsult);
	
	public BtoolsConsult  selectById(Long conId);
	
	public BtoolsConsult  selectForUpdate(Long conId);
	
	public List<BtoolsConsult> selectInPage(@Param("record") BtoolsConsult btoolsConsult, @Param("pageInfo") PageInfo pageInfo);
	
}