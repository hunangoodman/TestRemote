package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.bean.PageInfo;
import com.treasure.model.Card;
import com.treasure.model.Member;
import com.treasure.model.MemberExample;
import com.treasure.model.Trade;

public interface MemberMapper {
	int countByExample(MemberExample example);

	int deleteByExample(MemberExample example);

	int deleteByPrimaryKey(Long id);

	int insert(Member record);

	int insertSelective(Member record);

	List<Member> selectByExample(MemberExample example);

	Member selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);
	
	int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

	int updateByPrimaryKeySelective(Member record);

	int updateByPrimaryKey(Member record);

	Member selectMemberByMobile(@Param("mobile") String mobile);
	int selectMemberByMobile1(@Param("mobile") String mobile);
	
	Member selectMemberByUUid(@Param("uuid") String uuid);

	int updateIntegral(@Param("memberId") Long memberId, @Param("integral") Double integral);

	List<Member> selectInviteByAdCode(@Param("adCode") String adCode);

	int updateByTrade1(@Param("trade") Trade trade);

	int updateByTrade2(@Param("trade") Trade trade);

	int updateByTrade3(@Param("trade") Trade trade);
	
	int updateByTrade4(@Param("trade") Trade trade);
	
	int updateByTrade5(@Param("trade") Trade trade);
	
	int updateByTrade6(@Param("trade") Trade trade);

	int updateRecommendMoney(@Param("money") Double money, @Param("id") Long id);

	Member selectById(@Param("id") String id);

	int unfreezeIntegral(@Param("unfreezeDays") Integer unfreezeDays, @Param("unfreezeRate") Double unfreezeRate);

	List<Member> selectList(@Param("record") Member member, @Param("pageVo") PageInfo pageVo);

	int selectCount(@Param("record") Member member);

	int updateWithdraw(@Param("money") Double money, @Param("consumeBean") Double consumeBean, @Param("id") Long memberId);

	int increaseIntegral(@Param("member") Member member);

	List<Member> selectAllList();

	int recharge(@Param("member") Member member);

	int updateThawDate(@Param("memberId") Long memberId);
	
    List<Member> selectByMemAndZone(@Param("memId") Long memberId,@Param("zone") String zone);
	
	int selectCountByMemAndZone(@Param("memId") Long memberId,@Param("zone") String zone);
	
	int selectCountByCode(@Param("code") String code,@Param("zone") String zone);
	
	int selectNumByCode(@Param("code") String code,@Param("zone") String zone);
	
	List<Member> selectVipCount();
	
	Member selectByZone(@Param("id")String id);
	
	List<Member> selectNumByZone(@Param("id") String code,@Param("zone") String zone);
	
	List<Member> clearsSons(@Param("id") String code);
	
	List<Member> selectAll();

	Member selectFullNmae(String fullNmae);

	int selectCodeCount(String username);
	
	Member selectMobile(String username);
	int updateMobile(@Param("member") Member member);

	String findEncodeById(String memberid);

	void addEncode(@Param("name")String name, @Param("mid")String memberid);

	void updateTrueName(Card card);

	String findMoneyEncodeById(String memberid);

	void addMoneyEncode(@Param("name")String name, @Param("mid")String memberid);
}