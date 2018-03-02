package com.treasure.service.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.treasure.bean.MemberResult;
import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.bean.TokenBean;
import com.treasure.constant.StaticConstants;
import com.treasure.constant.SysConstants;
import com.treasure.dao.IncomeRecordMapper;
import com.treasure.dao.MemberMapper;
import com.treasure.dao.MobileCodeMapper;
import com.treasure.dao.SettingMapper;
import com.treasure.model.Card;
import com.treasure.model.IncomeRecord;
import com.treasure.model.Member;
import com.treasure.model.MemberExample;
import com.treasure.model.MobileCode;
import com.treasure.model.MobileCodeExample;
import com.treasure.model.Order;
import com.treasure.model.Trade;
import com.treasure.service.CardService;
import com.treasure.service.MemberService;
import com.treasure.utils.AesUtil;
import com.treasure.utils.IdcardValidator;
import com.treasure.utils.UUIDUtil;

@Service(value = "memberServiceImpl")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper mapper;
	@Autowired
	private MobileCodeMapper codeMapper;
	@Autowired
	private IncomeRecordMapper incomeMapper;
	@Autowired
	private SettingMapper settingMapper;
	@Autowired
	@Qualifier(value = "cardServiceImpl")
	private CardService cardservice;
	
	@Override
	public int add(Member member, MobileCode mobileCode) {
		codeMapper.updateByPrimaryKeySelective(mobileCode);
		return mapper.insertSelective(member);
	}

	@Override
	public void login(Member member, MemberResult result) {
		try {
			Member u = mapper.selectMemberByMobile(member.getUserName());
			if (null == u) { // 用户不存在
				result.setCode(0);
				result.setErrorCode("E0303");
				result.setMsg("您输入的用户不存在！");
			} else {
				if (u.getStatus() != null && u.getStatus() == 0) { // 用户锁定
					result.setCode(0);
					result.setErrorCode("E0304");
					result.setMsg("该用户已被锁定，不能登录！");
				} else if (!(u.getPassword()).equals(AesUtil.encrypt(member.getPassword(), SysConstants.AES_KEY))) { // 密码错误
					result.setCode(0);
					result.setErrorCode("E0305");
					result.setMsg("密码输入错误！");
				} else {
					String token = UUIDUtil.get32UUID();
					StaticConstants.tokenMap.put(token, new TokenBean(u.getId(), new Date()));
//					boolean flg = isMobile(u.getUserName());
//					System.out.println(u.getId());
//					System.out.println(u.getMobile());
//					System.out.println(flg);
//					if(flg && ("".equals(u.getMobile())||u.getMobile()==null)){
//						System.out.println("进来了");
//						int ff= mapper.updateMobile(u);
//						System.out.println(ff);
//					}
					u.setToken(token);
					result.setCode(1);
					result.setMember(u);
					result.setMsg("登录成功！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E0306");
			result.setMsg("登录异常！");
		}
	}
	
	/** 
     * 手机号验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }  

	@Override
	public void verified(Member member, MemberResult result, MobileCode mobileCode) {
		if (StringUtils.isBlank(member.getFullName())) {
			result.setCode(0);
			result.setErrorCode("E0404");
			result.setMsg("真实姓名不能为空！");
			return;
		}
		if (StringUtils.isBlank(member.getAddress())) {
			result.setCode(0);
			result.setErrorCode("E0405");
			result.setMsg("配送地址不能为空！");
			return;
		}
		if (StringUtils.isBlank(member.getBank())) {
			result.setCode(0);
			result.setErrorCode("E0411");
			result.setMsg("开户行不能为空！");
			return;
		}
		if (StringUtils.isBlank(member.getCardNumber())) {
			result.setCode(0);
			result.setErrorCode("E0414");
			result.setMsg("银行卡号不能为空！");
			return;
		}
		/*String idCard = member.getIdCard();
		if (StringUtils.isBlank(idCard) || (18 != idCard.length() && 15 != idCard.length()) || !IdcardValidator.isValidatedAllIdcard(idCard)) {
			result.setCode(0);
			result.setErrorCode("E0406");
			result.setMsg("身份证号码输入错误！");
			return;
		}
		MemberExample example = new MemberExample();
		example.createCriteria().andIdCardEqualTo(idCard);
		int count = mapper.countByExample(example);
		if (count > 0) {
			result.setCode(0);
			result.setErrorCode("E0407");
			result.setMsg("该身份已被认证过！");
			return;
		}
		if (StringUtils.isBlank(member.getPositivePhoto())) {
			result.setCode(0);
			result.setErrorCode("E0412");
			result.setMsg("请上传身份证正面照！");
			return;
		}
		if (StringUtils.isBlank(member.getNegativePhoto())) {
			result.setCode(0);
			result.setErrorCode("E0413");
			result.setMsg("请上传身份证反面照！");
			return;
		}*/
		member.setVerifiedStatus((byte) 1);
		int n = mapper.updateByPrimaryKeySelective(member);
		
		if (1 == n) {
			//codeMapper.updateByPrimaryKeySelective(mobileCode);
			member = mapper.selectMemberByMobile(member.getMobile());
			result.setCode(1);
			result.setMember(member);
			result.setMsg("实名认证通过！");
		} else {
			result.setCode(0);
			result.setErrorCode("E0408");
			result.setMsg("实名认证失败！");
		}
	}

	public int countByExample(MemberExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int addCode(MobileCode mobileCode) {
		//return codeMapper.insertSelective(mobileCode);
		try {
		
			return codeMapper.insertSelective(mobileCode);
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("异常。。");
		return codeMapper.updateByPrimaryKeySelective(mobileCode);
			
		}
		
	}

	public MobileCode selectByMobile(String mobile,String username) {
		MobileCodeExample example = new MobileCodeExample();
		example.createCriteria().andMobileEqualTo(mobile);
		MobileCode code =codeMapper.selectCode(mobile,username);
		return code;
	}

	public Member selectById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	public int update(Member member, MobileCode mobileCode) {
		codeMapper.updateByPrimaryKeySelective(mobileCode);
		return mapper.updateByPrimaryKeySelective(member);
	}

	@Override
	public Member selectMemberByMobile(String mobile) {
		return mapper.selectMemberByMobile(mobile);
	}
	
	@Override
	public Member selectMemberByUuid(String uuid) {
		return mapper.selectMemberByUUid(uuid);
	}

	@Override
	public int update(Member member) {
		return mapper.updateByPrimaryKeySelective(member);
	}

	@Override
	public List<Member> selectInviteByAdCode(String adCode) {
		return mapper.selectInviteByAdCode(adCode);
	}

	@Override
	public int addIncome(IncomeRecord incomeRecord) {
		return incomeMapper.insert(incomeRecord);
	}

	@Override
	public void addIncomes(Double dprice) {
		List<Member> memberList = mapper.selectByExample(null);
		IncomeRecord incomeRecord;
		Double integral = 0.0;
		for (Member member : memberList) {
			incomeRecord = new IncomeRecord();
			integral = member.getIntegral() + member.getFreezeIntegral();
			incomeRecord.setMemberId(member.getId());
			incomeRecord.setIntegral(integral);
			incomeRecord.setIncome(integral * dprice);
			incomeMapper.insertIncome(incomeRecord);
		}
	}

	@Override
	public Member selectById(String id) {
		return mapper.selectById(id);
	}
	
	public Member selectByZone(String id) {
		return mapper.selectByZone(id);
	}
	
	@Override
	public int addIncomeRecord(IncomeRecord income) {
		return incomeMapper.insert(income);
	}
	
	@Override
	public void updateProfit(Order order) {
		try {
			double profitPercent = 0.05;
			double profitPercent2 = 0.08;
			Member member = mapper.selectMemberByMobile(order.getMobile());
			Member recomMember = null;
			String ad = member.getAdCode();
			String recomMemberId = "";
			if (ad == null)
				return;
			// 第一步，上一级推荐人可以获得5%的现金反馈
			recomMemberId = ad.replace("ZLZC", "");
			recomMember = mapper.selectByPrimaryKey(Long.valueOf(recomMemberId));
			if (null != recomMember) {
				String ad2 = recomMember.getAdCode();
				mapper.updateRecommendMoney(order.getTotalAmount() * profitPercent, recomMember.getId());
				if (ad2 == null)
					return;
				String recomMemberId2 = ad2.replace("ZLZC", "");
				Member recomMember2 = mapper.selectByPrimaryKey(Long.valueOf(recomMemberId2));
				if (null != recomMember2) {
					// 第二步，是否达到计算2级提成的要求
					MemberExample example = new MemberExample();
					example.createCriteria().andAdCodeEqualTo("ZLZC" + recomMember2.getId());
					int count = mapper.countByExample(example);
					if (count >= 3) {// 达到二级提成的条件
						mapper.updateRecommendMoney(order.getTotalAmount() * profitPercent2, recomMember2.getId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int unfreezeIntegral(Integer unfreezeDays, Double unfreezeRate) {
		return mapper.unfreezeIntegral(unfreezeDays, unfreezeRate);
	}

	@Override
	public List<Member> selectList(Member member, PageInfo pageVo) {
		return mapper.selectList(member, pageVo);
	}

	@Override
	public int selectCount(Member member) {
		return mapper.selectCount(member);
	}

	@Override
	public int increaseIntegral(Member member) {
		return mapper.increaseIntegral(member);
	}

	@Override
	public List<Member> selectAllList() {
		return mapper.selectAllList();
	}
	
	@Override
	public List<Member> selectVipCount() {
		return mapper.selectVipCount();
	}

	@Override
	public int recharge(Member member) {
		settingMapper.updateIntegral(member.getRate());// 更新已使用数量
		Order order = new Order();
		order.setMobile(member.getMobile());
		//order.setTotalAmount(member.getRate());
		order.setIntegral(member.getRate());
		this.updateProfit(order);
		return mapper.recharge(member);
	}

	@Override
	public MobileCode selectByMemberId(Long memberId) {
		return codeMapper.selectByMemberId(memberId);
	}

	@Override
	public List<Member> selectByMemAndZone(Long memberId, String zone) {
		return mapper.selectByMemAndZone(memberId, zone);
	}

	@Override
	public int selectCountByMemAndZone(Long memberId, String zone) {
		return mapper.selectCountByMemAndZone(memberId, zone);
	}

	@Override
	public int selectCountByCode(String code, String zone) {
		return mapper.selectCountByCode(code, zone);
	}
	
	@Override
	public int selectNumByCode(String code, String zone) {
		return mapper.selectNumByCode(code, zone);
	}

	@Override
	public int updateByTrade1(Trade trade) {
		return mapper.updateByTrade1(trade);
	}
	
	@Override
	public int updateByTrade4(Trade trade) {
		return mapper.updateByTrade4(trade);
	}

	@Override
	public int updateByTrade5(Trade trade) {
		return mapper.updateByTrade5(trade);
	}

	@Override
	public int updateByTrade6(Trade trade) {
		return mapper.updateByTrade6(trade);
	}
	
	@Override
	public int updateByTrade2(Trade trade) {
		return mapper.updateByTrade2(trade);
	}

	@Override
	public List<Member> selectNumByZone(String id, String zone) {
		return mapper.selectNumByZone(id, zone);
	}

	@Override
	public List<Member> clearsSons(String id) {
		return mapper.clearsSons(id);
	}

	@Override
	public List<Member> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<IncomeRecord> findHisotry(Long id) {
		return incomeMapper.selectByPrimaryKey(id);
	}

	@Override
	public ResponseResult updatePwd(Member member) {
		ResponseResult result = new ResponseResult();
		try {
			Member m = mapper.selectByPrimaryKey(member.getId());
			if (m == null) { 
				result.setCode("false");
				result.setMsg("用户不存在!");
			} else {
//				if (!(m.getPassword()).equals(AesUtil.encrypt(member.getPasswordAgain(), SysConstants.AES_KEY))) {
//					result.setCode("false");
//					result.setMsg("原始密码错误!");
//				} else {
					m.setPassword(AesUtil.encrypt(member.getPasswordNew(), SysConstants.AES_KEY));
					int num = mapper.updateByPrimaryKeySelective(m);
					if (num > 0) {
						result.setCode("true");
						result.setMsg("密码修改成功！");
					} else {
						result.setCode("false");
						result.setMsg("密码修改失败!");
					}
				}
//			}
		} catch (Exception e) {
			result.setCode("error");
			result.setMsg("系统异常!");
		}
		return result;
	}
	
	/**
	 * 根据用户名查询
	 * */
	public Member selectFullNmae(String fullNmae) {
		
		return mapper.selectFullNmae(fullNmae);
	}
	
	public int selectMemberByMobile1(String mobile) {
		return mapper.selectMemberByMobile1(mobile);
	}

	@Override
	public int selectCodeCount(String username) {
		return mapper.selectCodeCount(username);
	}

	@Override
	public int update1(MobileCode mobileCode) {
		return codeMapper.updateByPrimaryKey1(mobileCode);
	}

	@Override
	public String findEncodeById(String memberid) {
		return mapper.findEncodeById(memberid);
	}

	@Override
	public void addEncode(String name, String memberid) {
		mapper.addEncode(name,memberid);
	}

	@Override
	public void updateTrueName(Card card) {
		mapper.updateTrueName(card);
	}

	@Override
	public String findMoneyEncodeById(String memberid) {
		return mapper.findMoneyEncodeById(memberid);
	}

	@Override
	public void addMoneyEncode(String name, String memberid) {
		mapper.addMoneyEncode(name,memberid);
	}
}
