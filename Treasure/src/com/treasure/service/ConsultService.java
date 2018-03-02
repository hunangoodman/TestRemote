package com.treasure.service;

import java.util.List;

import com.treasure.bean.PageInfo;
import com.treasure.model.BtoolsConsult;
import com.treasure.model.Module;

public interface ConsultService {
	/**
	 * 新增BtoolsConsult
	 * 
	 */
	public int add(BtoolsConsult btoolsConsult);
   	
   	/**
	 * 批量新增BtoolsConsult
	 * 
	 */
	public int batchAdd(List<BtoolsConsult>  btoolsConsultList);
   	
   	/**
	 * 根据map键值对 更新BtoolsConsult
	 * 
	 */
	public int modifyByMap(BtoolsConsult oldBtoolsConsult ,BtoolsConsult newBtoolsConsult);
	
	/**
	 * 根据对象 更新BtoolsConsult
	 * 
	 */
	public int modifyByModel(BtoolsConsult btoolsConsult);
	
	/**
	 * 批量删除BtoolsConsult
	 * 
	 */
	public int remove(List<Long> list);

	/**
	 * 根据id删除BtoolsConsult
	 * 
	 */
	public int removeById(Long conId);
	
	/**
	 * 根据对象删除BtoolsConsult
	 * 
	 */
	public int removeByModel(BtoolsConsult btoolsConsult);
	
	
	/**
	 * 根据对象查询BtoolsConsult
	 * 
	 */
	public List<BtoolsConsult> findByModel(BtoolsConsult btoolsConsult);
	
	/**
	 * 统计记录数BtoolsConsult
	 * 
	 */
	public int  findTotal(BtoolsConsult btoolsConsult);
	
	/**
	 * 根据id查询BtoolsConsult
	 * 
	 */
	public BtoolsConsult  findById(Long conId);
	
	public List<BtoolsConsult> selectAllList();
	
	public List<BtoolsConsult> findInPage(BtoolsConsult btoolsConsult,  PageInfo pageInfo);
}
