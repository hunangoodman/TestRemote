package com.treasure.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.bean.Result;
import com.treasure.bean.WithdrawResult;
import com.treasure.constant.SysConstants;
import com.treasure.model.Member;
import com.treasure.model.Setting;
import com.treasure.model.Withdraw;
import com.treasure.model.WithdrawExample;
import com.treasure.service.MemberService;
import com.treasure.service.SettingService;
import com.treasure.service.WithdrawService;
import com.treasure.utils.AesUtil;

@Controller
@RequestMapping(value = "/withdraw")
public class WithdrawController extends BaseController {

	@Autowired
	@Qualifier(value = "withdrawServiceImpl")
	protected WithdrawService service;
	@Autowired
	@Qualifier(value = "memberServiceImpl")
	protected MemberService memberService;
	@Autowired
	@Qualifier(value = "settingServiceImpl")
	protected SettingService settingService;

	@RequestMapping(value = "/mgr/withdrawList")
	public String withdrawList() {
		return "business/withdraw/withdrawList";
	}

	@RequestMapping(value = "/mgr/withdrawform")
	public String withdrawform() {
		return "business/withdraw/withdrawform";
	}

	// 显示所有订单
	@RequestMapping(value = "/mgr/showList")
	public @ResponseBody ResponseResult showList(Withdraw withdraw, PageInfo pageInfo) {
		ResponseResult result = new ResponseResult();
		try {
			if (pageInfo == null) {
				pageInfo = new PageInfo();
			}
			List<Withdraw> list = service.selectList(withdraw, pageInfo);// 查询符合条件的数据
			int totalRecord = service.selectCount(withdraw);
			pageInfo.setTotalRecord(totalRecord);
			result.setCode(SysConstants.STATUS_TRUE);
			result.setMsg("分页查询订单列表成功。");
			result.setList(list);
			result.setPageInfo(pageInfo);
		} catch (Exception e) {// 异常处理
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("分页查询订单列表异常！");
		}
		return result;
	}

	@RequestMapping(value = "/mgr/showUpdate", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showUpdate(@RequestParam(required = false, value = "key") Long id) {
		ResponseResult result = new ResponseResult();
		try {
			if (id != null) {
				Withdraw withdraw = service.selectByKey(id);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				withdraw.setApplyTime1(format.format(withdraw.getApplyTime()));
				result.setCode(SysConstants.STATUS_TRUE);
				result.setObject(withdraw);
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
			result.setObject(null);
		}
		return result;
	}

	@RequestMapping(value = "/mgr/check", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult check(Withdraw withdraw) {
		System.out.println("with = "+withdraw);
		ResponseResult result = new ResponseResult();
		try {
			withdraw.setCheckTime(new Date());
			int n = service.check(withdraw);
			//修改用户余额信息
			/*Member me = new Member();
			me.setId(memberId);
			me.setMoney(member.getMoney()-withdraw.getAmount());
			me.setConsumeBean(member.getConsumeBean()+withdraw.getAmount() * setting.getConsumeBeanRate());
			memberService.update(me);*/
			
			if (1 == n) {
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("审核成功。");
			} else {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("审核失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
			result.setObject(null);
		}
		return result;
	}

	@RequestMapping(value = "/apply", produces = "application/json;charset=utf-8")
	public @ResponseBody Result apply(Withdraw withdraw) {
		Result result = new Result();
		try {
			if (null == withdraw) {
				result.setCode(0);
				result.setErrorCode("E2101");
				result.setMsg("申请信息不能为空！");
				return result;
			}
			Long memberId = withdraw.getMemberId();
			if (null == memberId) {
				result.setCode(0);
				result.setErrorCode("E2102");
				result.setMsg("用户不能为空！");
				return result;
			}
			String pwd = withdraw.getPasswordTwo();
			if (null == pwd || "".equals(pwd)) {
				result.setCode(0);
				result.setErrorCode("E2102");
				result.setMsg("二级密码不能为空！");
				return result;
			}
			WithdrawExample example = new WithdrawExample();
			example.createCriteria().andMemberIdEqualTo(memberId).andCheckStatusEqualTo((byte) 0);
			int count = service.countByExample(example);
			if (count > 0) {
				result.setCode(0);
				result.setErrorCode("E2110");
				result.setMsg("您有提现申请未完成审核，不可重复提交申请！");
				return result;
			}
			Member member = memberService.selectById(memberId);
			if (null == member) {
				result.setCode(0);
				result.setErrorCode("E2103");
				result.setMsg("用户信息不存在！");
				return result;
			}
			if (1 != member.getVerifiedStatus()) {
				result.setCode(0);
				result.setErrorCode("E2111");
				result.setMsg("您还没有通过实名认证，不可进行提现操作喔！");
				return result;
			}
			if (null == member.getMoney() || member.getMoney() < 100) {
				result.setCode(0);
				result.setErrorCode("E2104");
				result.setMsg("账户余额小于100，不可申请提现！");
				return result;
			}
			if (null == withdraw.getAmount() || withdraw.getAmount() <= 0) {
				result.setCode(0);
				result.setErrorCode("E2105");
				result.setMsg("提现金额必须大于0！");
				return result;
			}
			if (member.getMoney() < withdraw.getAmount()) {
				result.setCode(0);
				result.setErrorCode("E2106");
				result.setMsg("申请提现金额不能大于账户余额！");
				return result;
			}
			if(!member.getPasswordTwo().equals(AesUtil.encrypt(pwd, SysConstants.AES_KEY))){
				result.setCode(0);
				result.setErrorCode("E2107");
				result.setMsg("二级密码输入错误！");
				return result;
			}
			/*
			 * if (StringUtils.isBlank(withdraw.getCardNumber())) {
			 * result.setCode(0); result.setErrorCode("E2107");
			 * result.setMsg("支付宝账号不能为空！"); return result; }
			 */
			Setting setting = settingService.querySettingInfo();
			Double rate = setting.getWithdrawRate();
			rate = null == rate || 0 == rate ? 0.01 : rate;
			withdraw.setCardNumber(member.getCardNumber());
			withdraw.setProcedureRates(rate);
			withdraw.setProcedureMoney(withdraw.getAmount() * rate);
			withdraw.setRealityMoney(withdraw.getAmount() - withdraw.getProcedureMoney() - withdraw.getAmount() * setting.getConsumeBeanRate());
			int n = service.add(withdraw);
			//修改用户余额信息
			Member me = new Member();
			me.setId(memberId);
			me.setMoney(member.getMoney()-withdraw.getAmount());
			me.setConsumeBean(member.getConsumeBean()+withdraw.getAmount() * setting.getConsumeBeanRate());
			memberService.update(me);
			if (1 == n) {
				result.setCode(1);
				result.setMsg("申请提现提交成功。");
			} else {
				result.setCode(0);
				result.setErrorCode("E2108");
				result.setMsg("申请提现提交失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E2109");
			result.setMsg("申请提现提交异常！");
		}
		return result;
	}

	// 显示所有订单
	@RequestMapping(value = "/history")
	public @ResponseBody WithdrawResult history(Withdraw withdraw, PageInfo pageInfo) {
		WithdrawResult result = new WithdrawResult();
		try {
			if (pageInfo == null) {
				pageInfo = new PageInfo();
			}
			if (null == withdraw || null == withdraw.getMemberId()) {
				result.setCode(0);
				result.setErrorCode("E4001");
				result.setMsg("用户主键不能为空！");
			}
			List<Withdraw> list = service.selectList(withdraw, pageInfo);// 查询符合条件的数据
			int totalRecord = service.selectCount(withdraw);
			pageInfo.setTotalRecord(totalRecord);
			result.setPageInfo(pageInfo);
			result.setCode(1);
			result.setMsg("分页查询订单列表成功。");
			result.setWithdraws(list);
		} catch (Exception e) {// 异常处理
			result.setCode(0);
			result.setErrorCode("E4002");
			result.setMsg("分页查询订单列表异常！");
		}
		return result;
	}
}
