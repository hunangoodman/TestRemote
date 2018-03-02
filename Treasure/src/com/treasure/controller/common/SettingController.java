package com.treasure.controller.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treasure.bean.PriceResult;
import com.treasure.bean.ResponseResult;
import com.treasure.bean.SettingResult;
import com.treasure.bean.TimeResult;
import com.treasure.constant.SysConstants;
import com.treasure.model.PriceRecord;
import com.treasure.model.Setting;
import com.treasure.service.SettingService;
import com.treasure.utils.DateUtils;

@Controller
@RequestMapping(value = "/setting")
public class SettingController extends BaseController {

	@Autowired
	@Qualifier(value = "settingServiceImpl")
	protected SettingService service;

	@RequestMapping(value = "/mgr/settingform")
	public String roleList(Model model) {
		Setting setting = service.querySettingInfo();
		DecimalFormat decimalFormat = new DecimalFormat("#.####");// 格式化设置
		setting.setTc(decimalFormat.format(setting.getTotalCount()));
		setting.setAmp(decimalFormat.format(setting.getAmplitude()));
		setting.setCamp(decimalFormat.format(setting.getCountAmplitude()));
		model.addAttribute("setting", setting);
		return "business/setting/settingform";
	}

	@RequestMapping(value = "/detail")
	public @ResponseBody SettingResult showSettingInfo() {
		SettingResult result = new SettingResult();
		try {
			result.setCode(1);
			result.setMsg("查询成功");
			result.setSetting(service.querySettingInfo1());
		} catch (Exception e) {// 异常处理
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E0909");
			result.setMsg("生成购买记录异常！");
		}
		return result;
	}

	@RequestMapping(value = "/systemTime")
	public @ResponseBody TimeResult systemTime() {
		TimeResult result = new TimeResult();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result.setCode(1);
			result.setMsg("获取系统时间成功");
			result.setSystemTime(format.format(new Date()));
		} catch (Exception e) {// 异常处理
			result.setCode(0);
			result.setMsg("获取系统时间异常！");
		}
		return result;
	}

	@RequestMapping(value = "/mgr/save", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult save(Setting setting) {
		ResponseResult result = new ResponseResult();
		try {
			int num = service.save(setting);
			if (num > 0) {
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("保存成功!");
			} else {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("保存失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;
	}

	@RequestMapping(value = "/prices")
	public @ResponseBody PriceResult prices(PriceRecord price) {
		PriceResult result = new PriceResult();
		try {
			// 默认查询近7天
			if (null == price) {
				price = new PriceRecord();
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String endDate = format.format(new Date());
			if (StringUtils.isBlank(price.getEndDate())) {
				price.setEndDate(endDate);
			}
			if (StringUtils.isBlank(price.getStartDate())) {
				price.setStartDate(DateUtils.addDayToDate(-6, price.getEndDate(), "yyyy-MM-dd"));
			}
			result.setPrices(service.selectPrices(price));
			result.setCode(1);
			result.setMsg("查询成功");
		} catch (Exception e) {// 异常处理
			result.setCode(0);
			result.setErrorCode("E2501");
			result.setMsg("查询异常！");
		}
		return result;
	}
}
