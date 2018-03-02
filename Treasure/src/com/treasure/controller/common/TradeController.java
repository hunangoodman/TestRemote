package com.treasure.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treasure.bean.PageInfo;
import com.treasure.bean.TradeResult;
import com.treasure.constant.SysConstants;
import com.treasure.model.Member;
import com.treasure.model.Setting;
import com.treasure.model.Trade;
import com.treasure.service.MemberService;
import com.treasure.service.SettingService;
import com.treasure.service.TradeService;
import com.treasure.utils.AesUtil;
import com.treasure.utils.TimeUtils;

@Controller
@RequestMapping(value = "/trade")
public class TradeController extends BaseController {

	@Autowired
	@Qualifier(value = "tradeServiceImpl")
	protected TradeService service;
	@Autowired
	@Qualifier(value = "memberServiceImpl")
	protected MemberService memberService;
	@Autowired
	@Qualifier(value = "settingServiceImpl")
	protected SettingService settingService;

	@RequestMapping(value = "/tradeHis")
	public @ResponseBody TradeResult tradeHis(Trade trade, PageInfo pageInfo) {
		TradeResult result = new TradeResult();
		try {
			if (null == trade) {
				result.setCode(0);
				result.setMsg("参数不能为空！");
				result.setErrorCode("E1101");
				return result;
			}
			if (null == trade.getMemberId()) {
				result.setCode(0);
				result.setMsg("用户主键不能为空！");
				result.setErrorCode("E1102");
				return result;
			}
			if (memberService.selectById(trade.getMemberId()) == null) {
				result.setCode(0);
				result.setMsg("用户不存在！");
				result.setErrorCode("E1103");
				return result;
			}
			List<Trade> list = new ArrayList<>();
			int totalRecord = 0;
			if(trade.getType()==1){//买入
				list = service.selectList2(trade, pageInfo);
				totalRecord = service.selectCount2(trade);
			}else if(trade.getType()==2){//卖出
				list = service.selectList(trade, pageInfo);
				totalRecord = service.selectCount(trade);
			}else{//互转
				if(trade.getFlag()==1){//转入
					list = service.selectList3(trade, pageInfo);
					totalRecord = service.selectCount3(trade);
				}else{//转出
					list = service.selectList4(trade, pageInfo);
					totalRecord = service.selectCount4(trade);
				}
			}
			pageInfo.setTotalRecord(totalRecord);
			result.setCode(1);
			result.setTrades(list);
			result.setPageInfo(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMsg("查询交易记录异常！");
			result.setErrorCode("E1104");
		}
		return result;
	}

	// @RequestMapping(value = "/buy")
	// public @ResponseBody TradeResult buy(Trade trade) {
	//
	// TradeResult result = new TradeResult();
	// if (trade == null) {
	// result.setCode(0);
	// result.setMsg("请求参数不正确");
	// result.setErrorCode("E0704");
	// return result;
	// }
	// try {
	// trade.setType(1);
	// trade.setStatus(Byte.parseByte("2"));
	// String validateStr = trade.validateParam(trade.getType());
	// if ("1".equals(validateStr)) {
	// if (memberService.selectById(trade.getBuyerMemberId()) == null) {
	// result.setCode(0);
	// result.setMsg("买家ID不存在或错误");
	// result.setErrorCode("E0703");
	// } else {
	// trade.setTime(TimeUtils.getCurrentTime());
	// if (service.addTradeInfo(trade) == 1) {
	// result.setCode(1);
	// } else {
	// result.setCode(0);
	// result.setMsg("数据库错误");
	// result.setErrorCode("E0704");
	// }
	// }
	// } else {
	// result.setCode(0);
	// result.setErrorCode(validateStr);
	// }
	// } catch (Exception e) {
	// result.setCode(0);
	// result.setMsg("系统错误：" + e.getMessage());
	// result.setErrorCode("E0704");
	// }
	// return result;
	// }

	@RequestMapping(value = "/sale")
	public @ResponseBody TradeResult sale(Trade trade) {
		TradeResult result = new TradeResult();
		if (trade == null) {
			result.setCode(0);
			result.setMsg("请求参数不正确");
			result.setErrorCode("E0804");
			return result;
		}
		try {
			trade.setType(2);
			trade.setTime(TimeUtils.getCurrentTime());
			result = trade.validateParam(result, trade.getType());
			if (1 != result.getCode()) {
				result.setCode(0);
				return result;
			}
			Member member = memberService.selectById(trade.getSellerMemberId());
			if (member == null) {
				result.setCode(0);
				result.setMsg("用户不存在");
				result.setErrorCode("E0803");
				return result;
			}
			if(trade.getAmount()>member.getIntegral()){
				result.setCode(0);
				result.setMsg("可卖的智联币数量不足");
				result.setErrorCode("E0803");
				return result;
			}
			
			Setting setting = settingService.querySettingInfo();
			if(setting.getIntegralPrice()<=trade.getPrice()){
				result.setCode(0);
				result.setMsg("卖出价不能高于当前大盘价");
				result.setErrorCode("E0803");
				return result;
			}
			//更新用户智联币数据(币减少，余额增加)
			memberService.updateByTrade4(trade);
			trade.setStatus((byte)1);
			if (service.addSaleInfo(trade) == 0) {
				result.setCode(0);
				result.setMsg("系统异常！");
				result.setErrorCode("E0804");
			} else {
				result.setCode(1);
				result.setMsg("卖出成功。");
				result.setTrade(trade);
			}
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMsg("价格或数量超出限制！");
			result.setErrorCode("E0805");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMsg("系统错误： " + e.getMessage());
			result.setErrorCode("E0804");
		}
		return result;
	}
	
	@RequestMapping(value = "/saleOld")
	public @ResponseBody TradeResult saleOld(Trade trade) {
		TradeResult result = new TradeResult();
		if (trade == null) {
			result.setCode(0);
			result.setMsg("请求参数不正确");
			result.setErrorCode("E0804");
			return result;
		}
		try {
			trade.setType(2);
			trade.setTime(TimeUtils.getCurrentTime());
			result = trade.validateParam(result, trade.getType());
			if (1 != result.getCode()) {
				result.setCode(0);
				return result;
			}
			Member member = memberService.selectById(trade.getSellerMemberId());
			if (member == null) {
				result.setCode(0);
				result.setMsg("用户不存在");
				result.setErrorCode("E0803");
				return result;
			}
			if(trade.getAmount()>member.getIntegral()){
				result.setCode(0);
				result.setMsg("可挂卖的智联币数量不足");
				result.setErrorCode("E0803");
				return result;
			}
			
			Setting setting = settingService.querySettingInfo();
			if(setting.getIntegralPrice()>trade.getPrice()){
				result.setCode(0);
				result.setMsg("挂卖价格不能低于大盘价格");
				result.setErrorCode("E0803");
				return result;
			}
			/*if (trade.getAmount() > member.getIntegral()) {// 挂卖数量高于用户可挂卖数量
				result.setCode(0);
				result.setMsg("可挂卖数量不足");
				result.setErrorCode("E0805");
				return result;
			}*/
			/*if (trade.getPrice() <= setting.getIntegralPrice()) {// 如果挂卖价格低于大盘价格，直接回购
				trade.setStatus((byte) 1);
			} else {
				trade.setStatus((byte) 2);
			}*/
			//更新用户智联币数据(币减少)
			memberService.updateByTrade5(trade);
			trade.setStatus((byte)2);
			if (service.addSaleInfo(trade) == 0) {
				result.setCode(0);
				result.setMsg("系统异常！");
				result.setErrorCode("E0804");
			} else {
				result.setCode(1);
				result.setMsg("挂卖成功。");
				result.setTrade(trade);
			}
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMsg("价格或数量超出限制！");
			result.setErrorCode("E0805");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMsg("系统错误： " + e.getMessage());
			result.setErrorCode("E0804");
		}
		return result;
	}

	/**
	 * 转账
	 * @param trade
	 * @return
	 */
	@RequestMapping(value = "/send")
	@Transactional
	public @ResponseBody TradeResult send(Trade trade) {
		TradeResult result = new TradeResult();
		if (trade == null) {
			result.setCode(0);
			result.setMsg("请求参数不正确");
			result.setErrorCode("E1004");
			return result;
		}
		String pwd = trade.getPasswordTwo();
		if (null == pwd || "".equals(pwd)) {
			result.setCode(0);
			result.setErrorCode("E2102");
			result.setMsg("二级密码不能为空！");
			return result;
		}
		try {
			trade.setType(3);
			trade.setPrice(0.00);
			trade.setStatus((byte) 1);
			result = trade.validateParam(result, trade.getType());
			if (1 == result.getCode()) {
				// 校验当前用户可用的智联币数量
				Member member = memberService.selectById(trade.getSellerMemberId());//转让者
				if (member == null) {
					result.setCode(0);
					result.setMsg("智联账户不正确");
					result.setErrorCode("E1002");
					return result;
				}
				if (trade.getAmount() > member.getIntegral()) {
					result.setCode(0);
					result.setMsg("转让数量超过当前账户的智联数量");
					result.setErrorCode("E1005");
					return result;
				}
				if(!member.getPasswordTwo().equals(AesUtil.encrypt(pwd, SysConstants.AES_KEY))){
					result.setCode(0);
					result.setErrorCode("E2107");
					result.setMsg("二级密码输入错误！");
					return result;
				}
				//Member member1 = memberService.selectMemberByMobile(trade.getMobile());
				Member member1 = memberService.selectMemberByUuid(trade.getUuid());//转让对象
				if (null == member1) {
					result.setCode(0);
					result.setMsg("对方账号不存在,请检查钱包码是否有误");
					result.setErrorCode("E1003");
					return result;
				}
				if (member.getId().longValue()== member1.getId().longValue()) {
					result.setCode(0);
					result.setMsg("不能转让给自己账户！");
					result.setErrorCode("E1006");
					return result;
				}
				trade.setBuyerMemberId(member1.getId());
				trade.setTime(TimeUtils.getCurrentTime());
				int send = service.addSendInfo(trade);//添加交易记录
				//修改双方的智联币数量
				Trade trade1 = new Trade();
				trade1.setSellerMemberId(member.getId());
				trade1.setAmount(trade.getAmount());
				memberService.updateByTrade5(trade1);
				Trade trade2 = new Trade();
				trade2.setSellerMemberId(member1.getId());
				trade2.setAmount(trade.getAmount());
				memberService.updateByTrade6(trade2);
				
				if (send > 0) {
					result.setCode(1);
					result.setMsg("转让成功。");
				} else {
					result.setCode(0);
					result.setMsg("系统异常，转让失败！");
					result.setErrorCode("E1004");
				}
			}
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMsg("数量超出限制！");
			result.setErrorCode("E1005");
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMsg("系统错误： " + e.getMessage());
			result.setErrorCode("E1004");
		}
		return result;
	}

	@RequestMapping(value = "/topTradeInfo")
	public @ResponseBody TradeResult topTradeInfo(Trade trade) {

		TradeResult result = new TradeResult();
		try {
			if (null == trade) {
				result.setCode(0);
				result.setMsg("参数不能为空!");
				result.setErrorCode("E1201");
				return result;
			}
			if (trade.getType() == null || (trade.getType() != 1 && trade.getType() != 2)) {
				result.setCode(0);
				result.setMsg("交易类型不在取值范围内");
				result.setErrorCode("E1202");
				return result;
			}
			List<Trade> list = service.selectTradeList(trade);
			result.setCode(1);
			result.setMsg("查询成功");
			result.setTrades(list);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMsg("系统异常");
			result.setErrorCode("E1203");
		}
		return result;
	}
	/**
	 * 查询买入卖出记录【前台】
	 * @return
	 */
	@RequestMapping(value = "/tradeInfo")
	public @ResponseBody TradeResult tradeInfo() {
		Trade trade = new Trade();
		TradeResult result = new TradeResult();
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			trade.setType(1);
			trade.setStatus((byte)1);
			List<Trade> listIn = service.selectTradeList(trade);//买入集合
			trade.setType(2);
			trade.setStatus((byte)1);
			List<Trade> listOut = service.selectTradeList(trade);//卖出集合
			map.put("listIn", listIn);
			map.put("listOut", listOut);
			result.setCode(1);
			result.setMsg("查询成功");
			result.setMap(map);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMsg("系统异常");
			result.setErrorCode("E1203");
		}
		return result;
	}
	
	/**
	 * 买入接口【前台】
	 * @return
	 */
	@RequestMapping(value = "/buyIn")
	public @ResponseBody TradeResult buyIn(Trade trade) {
		TradeResult result = new TradeResult();
		if(trade==null){
			result.setCode(0);
			result.setMsg("参数异常！");
			result.setErrorCode("E1203");
			return result;
		}
		if(trade.getPrice()==null||trade.getPrice()==0||trade.getAmount()==null||trade.getAmount()==0){
			result.setCode(0);
			result.setMsg("参数不能为空！");
			result.setErrorCode("E1203");
			return result;
		}
		Setting setting = settingService.querySettingInfo();
		if(trade.getPrice()>setting.getIntegralPrice()||trade.getPrice()<setting.getIntegralPrice()){
			result.setCode(0);
			result.setMsg("只能买入当前大盘价！");
			result.setErrorCode("E1203");
			return result;
		}
		
		//查询用户的余额是否不足
		Member member = memberService.selectById(trade.getBuyerMemberId());
		if(member==null){
			result.setCode(0);
			result.setMsg("用户不存在");
			result.setErrorCode("E1203");
			return result;
		}
		if(member.getMoney()<trade.getPrice()*trade.getAmount()){
			result.setCode(0);
			result.setMsg("余额不足");
			result.setErrorCode("E1203");
			return result;
		}
		Trade addTrade = new Trade();
		addTrade.setAmount(trade.getAmount());
		addTrade.setBuyerMemberId(trade.getBuyerMemberId());
		addTrade.setPrice(trade.getPrice());
		addTrade.setStatus((byte)1);
		addTrade.setTime(TimeUtils.getCurrentTime());
		addTrade.setType(1);
		service.addBuyInfo(addTrade);
		//更新用户智联币数据（余额减少、币增加）
		memberService.updateByTrade1(trade);
		//修改卖出记录
		result.setCode(1);
		result.setMsg("成功");
		result.setErrorCode("E1201");
		//更新大盘智联币总量
		setting.setTotalCount(setting.getTotalCount()-trade.getAmount());
		settingService.update(setting);
		return result;
	}
	
	@RequestMapping(value = "/buyInOld")
	public @ResponseBody TradeResult buyInOld(Trade trade) {
		TradeResult result = new TradeResult();
		if(trade==null){
			result.setCode(0);
			result.setMsg("参数异常！");
			result.setErrorCode("E1203");
			return result;
		}
		if(trade.getPrice()==null||trade.getPrice()==0||trade.getAmount()==null||trade.getAmount()==0){
			result.setCode(0);
			result.setMsg("参数不能为空！");
			result.setErrorCode("E1203");
			return result;
		}
		Setting setting = settingService.querySettingInfo();
		if(trade.getPrice()>setting.getIntegralPrice()){
			result.setCode(0);
			result.setMsg("买入价格不能高于大盘价！");
			result.setErrorCode("E1203");
			return result;
		}
		List<Trade> tList = service.selectInfoByPrice(trade.getPrice());
		if(tList==null||tList.size()<1){
			result.setCode(0);
			result.setMsg("该价格下没有挂卖记录！");
			result.setErrorCode("E1203");
			return result;
		}
		Double total = 0d;
		for(Trade t:tList){
			total+=t.getAmount();
		}
		Double totalCount = trade.getAmount();
		if(totalCount>total){
			result.setCode(0);
			result.setMsg("该价格下挂卖数量不足！最多可买入"+total);
			result.setErrorCode("E1203");
			return result;
		}
		
		//查询用户的余额是否不足
		Member member = memberService.selectById(trade.getBuyerMemberId());
		if(member==null){
			result.setCode(0);
			result.setMsg("用户不存在");
			result.setErrorCode("E1203");
			return result;
		}
		if(member.getMoney()<trade.getPrice()*trade.getAmount()){
			result.setCode(0);
			result.setMsg("余额不足");
			result.setErrorCode("E1203");
			return result;
		}
		
		//double price = 0;
		for(int i = 0;i<tList.size();i++){
			Trade t = tList.get(i);
			if(t.getAmount()>totalCount){
				//更新卖出者余额信息
				Member mem = memberService.selectById(t.getSellerMemberId());
				if(mem==null){
					result.setCode(0);
					result.setMsg("该挂卖记录已过期");
					result.setErrorCode("E1203");
					return result;
				}
				//更新卖出记录
				//t.setStatus((byte)1);
				t.setAmount(t.getAmount()-totalCount);//更新挂卖数量
				service.updateByPrimaryKeySelective(t);
				
				Trade addTrade = new Trade();
				addTrade.setAmount(totalCount);
				addTrade.setBuyerMemberId(trade.getBuyerMemberId());
				addTrade.setPrice(trade.getPrice());
				addTrade.setStatus((byte)1);
				addTrade.setTime(TimeUtils.getCurrentTime());
				addTrade.setType(1);
				service.addBuyInfo(addTrade);
				mem.setMoney(mem.getMoney()==null?0:mem.getMoney()+totalCount/setting.getIntegralPrice());
				memberService.update(mem);
				break;
			}
			
			Member mem = memberService.selectById(t.getSellerMemberId());
			if(mem==null){
				result.setCode(0);
				result.setMsg("该挂卖记录已过期");
				result.setErrorCode("E1203");
				return result;
			}
			//更新卖出记录
			t.setStatus((byte)1);
			//t.setBuyerMemberId(trade.getBuyerMemberId());
			service.updateByPrimaryKeySelective(t);
			//新增买入记录
			Trade addTrade = new Trade();
			addTrade.setAmount(t.getAmount());
			addTrade.setBuyerMemberId(trade.getBuyerMemberId());
			addTrade.setPrice(t.getPrice());
			addTrade.setStatus((byte)1);
			addTrade.setTime(TimeUtils.getCurrentTime());
			addTrade.setType(1);
			service.addBuyInfo(addTrade);
			totalCount = totalCount - t.getAmount();
			mem.setMoney(mem.getMoney()==null?0:mem.getMoney()+totalCount*setting.getIntegralPrice());
			memberService.update(mem);
			/*Integer amount = t.getAmount();
			if(amount!=null&&amount>0){
				if(amount<totalCount){
					//删除此挂卖记录
					//service.deleteByPrimaryKey(t.getId());
					t.setStatus((byte)1);
					service.updateByPrimaryKeySelective(t);
				}
				//新增买入记录
				trade.setAmount(amount);
				trade.setType(1);
				trade.setStatus((byte)2);
				trade.setBuyerMemberId(trade.getBuyerMemberId());
				trade.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				service.addBuyInfo(trade);
				totalCount = totalCount-amount;
			}*/
			//price = t.getPrice();
		}
		/*if(totalCount>0){//挂卖记录不足，新增买入记录
			Trade tTrade = new Trade();
			tTrade.setType(1);
			tTrade.setAmount(totalCount);
			tTrade.setPrice(price);
			tTrade.setStatus((byte)2);
			tTrade.setBuyerMemberId(trade.getBuyerMemberId());
			tTrade.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			service.addBuyInfo(tTrade);
		}*/
		/*if(amount<=0){
			result.setCode(0);
			result.setMsg("该价格下挂卖数量不足！");
			result.setErrorCode("E1203");
			return result;
		}*/
		
		
		//trade.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		//service.addBuyInfo(trade);//添加买入记录
		
		//更新用户智联币数据（余额减少、币增加）
		memberService.updateByTrade1(trade);
		//修改卖出记录
		/*TradeExample example = new TradeExample();
		example.setOrderByClause("time");
		example.createCriteria().andPriceEqualTo(trade.getPrice()).andTypeEqualTo(2);
		List<Trade> tradeList = service.selectByExample(example);
		Integer buyAccount = trade.getAmount();
		for(int i = 0;i<tradeList.size();i++){
			Trade tTrade = tradeList.get(i);
			if(tradeList.get(i).getAmount()-buyAccount>0){//如果该挂卖数量大于买入数量，更新交易记录
				tTrade.setAmount(tradeList.get(i).getAmount()-buyAccount);
				service.updateByPrimaryKey(tTrade);
				break;
			}
			service.deleteByPrimaryKey(trade.getId());//如果挂卖数量小于买入数量，删除交易记录
			buyAccount = buyAccount-tradeList.get(i).getAmount();
		}*/
		/*if (service.addSaleInfo(trade) == 0) {
			result.setCode(0);
			result.setMsg("买入失败。");
			result.setErrorCode("E0804");
		} else {
			result.setCode(1);
			result.setMsg("买入成功。");
			//result.setTrade(trade);
		}*/
		result.setCode(1);
		result.setMsg("成功");
		result.setErrorCode("E1201");
		return result;
	}
	
	/**
	 * 卖出接口【前台】
	 * @return
	 */
	@RequestMapping(value = "/buyOut")
	public @ResponseBody TradeResult buyOut() {
		Trade trade = new Trade();
		TradeResult result = new TradeResult();
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			trade.setType(1);
			List<Trade> listIn = service.selectTradeList(trade);//买入集合
			trade.setType(2);
			List<Trade> listOut = service.selectTradeList(trade);//卖出集合
			map.put("listIn", listIn);
			map.put("listOut", listOut);
			result.setCode(1);
			result.setMsg("查询成功");
			result.setMap(map);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMsg("系统异常");
			result.setErrorCode("E1203");
		}
		return result;
	}

}
