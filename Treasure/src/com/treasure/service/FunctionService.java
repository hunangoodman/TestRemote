package com.treasure.service;

import java.util.List;

import com.treasure.bean.PageInfo;
import com.treasure.model.Function;
public interface FunctionService {
	List<Function> showAllList() ;

	List<Function> selectList(Function function, PageInfo pageVo) ;

	int selectTotalRecord(Function function) ;

	int addFunction(Function function) ;

	int updateFunction(Function function) ;

	Function selectByKey(Integer functionId) ;

	int deleteFunctionByKey(List<?> list) ;

}
