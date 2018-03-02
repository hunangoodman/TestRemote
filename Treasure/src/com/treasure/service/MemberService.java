package com.treasure.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.bean.MemberResult;
import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.model.Card;
import com.treasure.model.IncomeRecord;
import com.treasure.model.Member;
import com.treasure.model.MemberExample;
import com.treasure.model.MobileCode;
import com.treasure.model.Order;
import com.treasure.model.Trade;

public interface MemberService {

	int add(Member member, MobileCode mobileCode);

	void login(Member member, MemberResult result);

	void verified(Member member, MemberResult result, MobileCode mobileCode);

	int countByExample(MemberExample example);

	int addCode(MobileCode mobileCode);

	MobileCode selectByMobile(String mobile,String username);

	Member selectMemberByMobile(String mobile);

	Member selectById(Long id);

	int update(Member member, MobileCode mobileCode);
	int update1(MobileCode mobileCode);

	int update(Member member);

	List<Member> selectInviteByAdCode(String adCode);

	int addIncome(IncomeRecord incomeRecord);

	void addIncomes(Double dprice);

	Member selectById(String id);

	void updateProfit(Order order);

	int unfreezeIntegral(Integer unfreezeDays, Double unfreezeRate);

	List<Member> selectList(Member member, PageInfo pageVo);

	int selectCount(Member member);

	int increaseIntegral(Member member);

	List<Member> selectAllList();

	int recharge(Member member);

	MobileCode selectByMemberId(Long memberId);
	
	List<Member> selectByMemAndZone(Long memberId, String zone);
	
	int selectCountByMemAndZone(Long memberId, String zone);
	
	int selectCountByCode(String code, String zone);
	
	public List<Member> selectVipCount();
	
	public int addIncomeRecord(IncomeRecord income);
	
	int updateByTrade1(Trade trade);
	
	public int selectNumByCode(String code, String zone);
	
	public int updateByTrade4(Trade trade);
	
	public Member selectMemberByUuid(String uuid);
	
	public int updateByTrade5(Trade trade);
	
	public int updateByTrade6(Trade trade);

	public int updateByTrade2(Trade trade);
	
	public Member selectByZone(String id);
	
	public List<Member> selectNumByZone(String id,String zone);
	
	public List<Member> clearsSons(String id);
	
	public List<Member> selectAll();

	List<IncomeRecord> findHisotry(Long id);

	ResponseResult updatePwd(Member member);
	
	public Member selectFullNmae(String fullNmae);
	
	public int selectMemberByMobile1(String mobile);

	int selectCodeCount(String username);

	String findEncodeById(String memberid);

	void addEncode(String name, String memberid);

	void updateTrueName(Card card);

	String findMoneyEncodeById(String memberid);

	void addMoneyEncode(String name, String memberid);
	
	
}
