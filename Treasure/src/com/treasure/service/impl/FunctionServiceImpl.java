package com.treasure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.treasure.bean.PageInfo;
import com.treasure.dao.FunctionMapper;
import com.treasure.model.Function;
import com.treasure.service.FunctionService;

/**
 * @project House-Intro-Web-Service
 * @package com.xinhai.houseintro.web.service.impl
 * @class FunctionServiceImpl.java
 * @author jiagui E-mail:1257896208@qq.com
 * @date 2015年10月25日 下午4:39:48
 * @description function模块service层实现类
 */
@Service(value = "functionServiceImpl")
public class FunctionServiceImpl implements FunctionService {
	@Autowired
	@Qualifier(value = "functionMapper")
	private FunctionMapper functionMapper;

	public List<Function> selectList(Function function, PageInfo pageVo) {
		return functionMapper.selectList(function, pageVo);
	}

	public int selectTotalRecord(Function function) {
		return functionMapper.selectTotalRecord(function);
	}

	public int addFunction(Function function) {
		return functionMapper.insertSelective(function);
	}

	public int updateFunction(Function function) {
		return functionMapper.updateByPrimaryKeySelective(function);
	}

	public Function selectByKey(Integer functionId) {
		return functionMapper.selectByPrimaryKey(functionId);
	}

	public int deleteFunctionByKey(List<?> list) {
		return functionMapper.deleteFunctionByKey(list);
	}

	public List<Function> showAllList() {
		return functionMapper.selectByExample(null);
	}
}
