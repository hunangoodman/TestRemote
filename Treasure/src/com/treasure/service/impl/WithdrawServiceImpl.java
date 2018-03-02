package com.treasure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treasure.bean.PageInfo;
import com.treasure.dao.MemberMapper;
import com.treasure.dao.SettingMapper;
import com.treasure.dao.WithdrawMapper;
import com.treasure.model.Member;
import com.treasure.model.Setting;
import com.treasure.model.Withdraw;
import com.treasure.model.WithdrawExample;
import com.treasure.service.WithdrawService;

@Service(value = "withdrawServiceImpl")
public class WithdrawServiceImpl implements WithdrawService {
	@Autowired
	private WithdrawMapper mapper;
	@Autowired
	private MemberMapper membermapper;
	@Autowired
	private SettingMapper settingMapper;

	@Override
	public int add(Withdraw withdraw) {
		return mapper.insertSelective(withdraw);
	}

	@Override
	public int selectCount(Withdraw withdraw) {
		return mapper.selectCount(withdraw);
	}

	@Override
	public List<Withdraw> selectList(Withdraw withdraw, PageInfo pageInfo) {
		return mapper.selectList(withdraw, pageInfo);
	}

	@Override
	public Withdraw selectByKey(Long id) {
		return mapper.selectByKey(id);
	}

	@Override
	public int check(Withdraw withdraw) {
		if (1 == withdraw.getCheckStatus()) {
			Setting setting = settingMapper.selectSettingInfo();
			Double consumeBean = withdraw.getAmount() * setting.getConsumeBeanRate();
			membermapper.updateWithdraw(withdraw.getAmount(), consumeBean, withdraw.getMemberId());
		}else if(2 == withdraw.getCheckStatus()){//如果提现失败，返还提现金额到余额
			Member member = membermapper.selectByPrimaryKey(withdraw.getMemberId());
			member.setMoney(member.getMoney()+withdraw.getAmount());
			membermapper.updateByPrimaryKeySelective(member);
		}
		return mapper.updateByPrimaryKeySelective(withdraw);
	}

	@Override
	public int countByExample(WithdrawExample example) {
		return mapper.countByExample(example);
	}

}
