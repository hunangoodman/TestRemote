package com.treasure.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.treasure.bean.OrderResult;
import com.treasure.bean.PageInfo;
import com.treasure.dao.MemberMapper;
import com.treasure.dao.OrderMapper;
import com.treasure.dao.PayRecordMapper;
import com.treasure.dao.SettingMapper;
import com.treasure.model.Member;
import com.treasure.model.Order;
import com.treasure.model.OrderExample;
import com.treasure.model.PayRecord;
import com.treasure.model.Setting;
import com.treasure.service.MemberService;
import com.treasure.service.OrderService;
import com.treasure.utils.UUIDUtil;

@Service(value = "orderServiceImpl")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper mapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private SettingMapper settingMapper;
	@Autowired
	private PayRecordMapper payRecordMapper;
	@Autowired
	@Qualifier(value = "memberServiceImpl")
	protected MemberService memberService;

	@Override
	public void buy(Order order, OrderResult result) {
		if (null == order) {
			result.setCode(0);
			result.setErrorCode("E0501");
			result.setMsg("订单信息不能为空！");
			return;
		}
		Long memberId = order.getMemberId();
		if (null == memberId) {
			result.setCode(0);
			result.setErrorCode("E0502");
			result.setMsg("会员ID不能为空！");
			return;
		}
		/*if (null == order.getPayType()) {
			result.setCode(0);
			result.setErrorCode("E0503");
			result.setMsg("支付类型不能为空！");
			return;
		}*/
		Member member = memberMapper.selectByPrimaryKey(memberId);
		if (null == member) {
			result.setCode(0);
			result.setErrorCode("E0504");
			result.setMsg("用户信息错误！");
			return;
		}
		if (1 != member.getVerifiedStatus()) {
			result.setCode(0);
			result.setErrorCode("E0512");
			result.setMsg("用户未通过实名认证，不可购买银币！");
			return;
		}
		if (null == order.getQuantity() || order.getQuantity() <= 0) {
			result.setCode(0);
			result.setErrorCode("E0505");
			result.setMsg("购买数量必须大于0！");
			return;
		}
		order.setFullName(member.getFullName());
		// if (StringUtils.isBlank(fullName) ||
		// !member.getFullName().equals(fullName)) {
		// result.setCode(0);
		// result.setErrorCode("E0506");
		// result.setMsg("汇款人姓名错误！");
		// return;
		// }
		/*if (StringUtils.isBlank(order.getPayNumber())) {
			result.setCode(0);
			result.setErrorCode("E0508");
			result.setMsg("支付账号不能为空！");
			return;
		}*/
		Setting setting = settingMapper.selectSettingInfo();
		if (null == setting) {
			result.setCode(0);
			result.setErrorCode("E0509");
			result.setMsg("当前大盘信息异常！");
			return;
		}
		if (order.getQuantity() > setting.getMaxBuyCount()) {
			result.setCode(0);
			result.setErrorCode("E0514");
			result.setMsg("购买数量大于单次可购买数量！");
			return;
		}
		order.setTotalAmount(setting.getSilverPrice() * order.getQuantity());
		/*if (null == order.getTotalAmount() || order.getTotalAmount() < 50) {
			result.setCode(0);
			result.setErrorCode("E0507");
			result.setMsg("充值金额需大于50！");
			return;
		}*/
		/*if (order.getTotalAmount() / setting.getIntegralPrice() > setting.getTotalCount() - setting.getUsedCount()) {
			result.setCode(0);
			result.setErrorCode("E0515");
			result.setMsg("系统可用大盘数量低于购买量！");
			return;
		}*/
		order.setPrice(setting.getSilverPrice());
		order.setMobile(member.getMobile());
		order.setOrderNo(UUIDUtil.get32UUID());
		int n = mapper.insertSelective(order);
		if (1 == n) {
			result.setCode(1);
			result.setOrder(order);
			result.setMsg("成功生成购买记录，等待后台审核！");
		} else {
			result.setCode(0);
			result.setErrorCode("E0510");
			result.setMsg("生成购买记录失败！");
		}
	}

	@Override
	public List<Order> selectList(Order order, PageInfo pageInfo) {
		return mapper.selectList(order, pageInfo);
	}

	@Override
	public int selectCount(Order order) {
		return mapper.selectCount(order);
	}

	@Override
	public Order selectByKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(Order order) {
		if (null != order.getIntegral() && order.getIntegral() > 0) {
			Long memberId = order.getMemberId();
			//memberMapper.updateIntegral(memberId, order.getIntegral());
			settingMapper.updateIntegral(order.getIntegral());
			memberMapper.updateThawDate(memberId);
			Member member = memberMapper.selectByPrimaryKey(memberId);
			memberService.updateProfit(order);
			if (0 == member.getActivateStatus()) {// 审核通过，激活用户
				Member m = new Member();
				m.setId(memberId);
				m.setActivateStatus((byte) 1);
				memberMapper.updateByPrimaryKeySelective(m);
			}
			if(member.getMoney()!=null){
				member.setMoney(member.getMoney()+order.getQuantity());
			}else{
				member.setMoney((double)0+order.getQuantity());
			}
			memberMapper.updateByPrimaryKey(member);
		}
		if (StringUtils.isBlank(order.getPayOrderNo())) {
			order.setPayOrderNo(null);
		}
		return mapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public int updatePayOrderNo(Order order) {
		return mapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public int addPayRecord(PayRecord record) {
		OrderExample example = new OrderExample();
		Order order = new Order();
		order.setPayOrderNo(record.getTradeNo());
		example.createCriteria().andOrderNoEqualTo(record.getOrderNo());
		mapper.updateByExampleSelective(order, example);
		return payRecordMapper.insertSelective(record);
	}

	@Override
	public int deleteByKeys(List<Long> keyes) {
		OrderExample example = new OrderExample();
		example.createCriteria().andIdIn(keyes);
		return mapper.deleteByExample(example);
	}

	@Override
	public Order selectByOrderNo(String orderNo) {
		return mapper.selectByOrderNo(orderNo);
	}
}
