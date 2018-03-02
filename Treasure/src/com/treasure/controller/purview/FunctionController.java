package com.treasure.controller.purview;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.constant.SysConstants;
import com.treasure.controller.common.BaseController;
import com.treasure.model.Function;
import com.treasure.service.FunctionService;

/**
 * @project House-Intro-Web
 * @package com.xinhai.houseintro.web.purview
 * @class FunctionController.java
 * @author jiagui E-mail:1257896208@qq.com
 * @date 2015年10月25日 下午4:35:40
 * @description 页面功能按钮模块
 */
@Controller(value = "FunctionController")
@RequestMapping(value = "/mgr/function")
public class FunctionController extends BaseController {
	@Autowired
	@Qualifier(value = "functionServiceImpl")
	private FunctionService functionService;
	private static final Logger log = Logger.getLogger(FunctionController.class);

	// 进入页面
	@RequestMapping(value = "/functionlist")
	public String initPage() {
		log.info("进入功能模块页面...");
		return "purview/function/functionlist";
	}

	// 进入功能选择页面
	@RequestMapping(value = "/functionselect")
	public String functionselect() {
		return "purview/function/functionselect";
	}

	@RequestMapping(value = "/functionform")
	public String functionform() {
		return "purview/function/functionform";
	}

	// 查询function数据
	@RequestMapping(value = "/showList", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showList(Function function, PageInfo pageVo) {
		log.info("查询function数据");
		ResponseResult result = new ResponseResult();
		try {
			if (pageVo == null) {
				pageVo = new PageInfo();
			}
			List<Function> list = functionService.selectList(function, pageVo);// 查询符合条件的数据
			int totalRecord = functionService.selectTotalRecord(function);
			pageVo.setTotalRecord(totalRecord);
			result.setCode(SysConstants.STATUS_TRUE);
			result.setList(list);
			result.setPageInfo(pageVo);
		} catch (Exception e) {// 异常处理
			log.info(e.getMessage());
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	// 查询function数据
	@RequestMapping(value = "/showAllList", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showAllList() throws Exception {
		log.info("查询function数据");
		ResponseResult result = new ResponseResult();
		try {
			List<Function> list = functionService.showAllList();// 查询所有
			result.setCode(SysConstants.STATUS_TRUE);
			result.setList(list);
		} catch (Exception e) {// 异常处理
			log.info(e.getMessage());
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 增加
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addFunction", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult addFunction(Function function) {
		int num = 0;
		ResponseResult result = new ResponseResult();
		try {
			if (function.getFunctionId() == null) {
				num = functionService.addFunction(function);
				if (num > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("添加成功~");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("添加失败!");
				}
			} else {
				num = functionService.updateFunction(function);
				if (num > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("修改成功~");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("修改失败！");
				}
			}
		} catch (DuplicateKeyException e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("code已经存在！");
			result.setObject(function);
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg(e.getMessage());
			result.setObject(function);
		}
		return result;
	}

	/**
	 * 资源修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showUpdate", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showUpdate(@RequestParam(required = false, value = "key") Integer functionId) {
		ResponseResult result = new ResponseResult();
		try {
			if (functionId != null) {
				Function function = functionService.selectByKey(functionId);
				result.setCode(SysConstants.STATUS_TRUE);
				result.setObject(function);
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg(e.getMessage());
			result.setObject(null);
		}
		return result;
	}

	/**
	 * 批量删除
	 * 
	 * @param response
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteFunction", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult deleteFunction(@RequestParam(required = false, value = "keys") String keys) {
		log.info("主键：" + keys);
		ResponseResult result = new ResponseResult();
		try {
			if (keys != null && !keys.equals("")) {
				List<String> keyes = new ArrayList<String>();
				String[] objs = keys.split(",");
				for (String k : objs) {
					keyes.add(k);
				}
				int num = functionService.deleteFunctionByKey(keyes);
				if (num > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("成功删除" + num + "条数据");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("删除失败！");
				}
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg(e.getMessage());
		}
		return result;
	}
}