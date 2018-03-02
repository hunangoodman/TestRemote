package com.treasure.controller.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.constant.SysConstants;
import com.treasure.model.CoinRecord;
import com.treasure.model.Member;
import com.treasure.service.CoinRecordService;

@Controller
@RequestMapping(value = "coin")
public class CoinRecordController extends BaseController{
	private static final Logger log = Logger.getLogger(CoinRecordController.class);
	@Autowired
	@Qualifier(value = "coinRecordServiceImpl")
	private CoinRecordService service;
	/**
	 * 
	 * @Description:显示拨币记录
	 * @author:李广林
	 * @param 
	 * @return
	 * @返回类型:ResponseResult
	 * @date:2018年1月26日
	 */
	@RequestMapping(value = "/coinlist")
	public String showPage() {
		return "business/coinrecode/coinrecodelist";
	}
	
	
	@RequestMapping(value = "/coins/showRecord")
	public @ResponseBody ResponseResult showRecord(CoinRecord cord, PageInfo pageVo) {
		if (pageVo == null) {
			pageVo = new PageInfo();
		}
		System.out.println("moble"+cord.getMobile());
		List<CoinRecord> list = service.findAll(cord,pageVo);// 查询符合条件的数据
		int totalRecord = service.selectCount(cord);
		pageVo.setTotalRecord(totalRecord);
		ResponseResult result = new ResponseResult();
		result.setList(list);
		result.setCode(SysConstants.STATUS_TRUE);
		return result;
	}
}
