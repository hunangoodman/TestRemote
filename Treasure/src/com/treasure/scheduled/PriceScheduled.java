package com.treasure.scheduled;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.treasure.model.IncomeRecord;
import com.treasure.model.Member;
import com.treasure.model.PriceRecord;
import com.treasure.model.Setting;
import com.treasure.service.MemberService;
import com.treasure.service.OrderService;
import com.treasure.service.SettingService;

@Component
public class PriceScheduled {
	private final static Logger log = Logger.getLogger(PriceScheduled.class);
	@Autowired
	@Qualifier(value = "settingServiceImpl")
	private SettingService service;
	@Value("${unfreeze.integral.days}")
	private Integer unfreezeDays;
	@Value("${unfreeze.integral.rate}")
	private Double unfreezeRate;
	@Autowired
	@Qualifier(value = "memberServiceImpl")
	private MemberService memberService;
	@Autowired
	@Qualifier(value = "orderServiceImpl")
	private OrderService orderService;
	

	@Scheduled(cron = "${price.time}")
//	@Scheduled(cron = "0 0/1 * * * ?")
	public void run() {
		log.info("价格自行涨跌任务执行开始...");
		try {
			Setting setting = service.querySettingInfo();
			if (null != setting) {
				Double bprice = setting.getIntegralPrice();// 之前价格
				// 价格增减量
				Double dprice = bprice * new Random().nextDouble() * setting.getAmplitude();
				// 价格增幅
				Double amplitude = dprice / bprice;
				// 之后价格
				Double aprice = bprice + amplitude;
				setting.setIntegralPrice(aprice);
				Double totalCount = setting.getTotalCount();// 之前数量
				// 数量增减量
				Double dcount = totalCount * new Random().nextDouble() * setting.getCountAmplitude();
				setting.setTotalCount(totalCount + dcount);
				PriceRecord record = new PriceRecord();
				record.setPrice(aprice);
				record.setAmplitude(amplitude);
				record.setRecordDate(new Date());
				int n = service.update(setting, record);
				service.repurchase(aprice);
				/*if (n > 0) {// 回购价格低于当前大盘价格的银多宝
					memberService.addIncomes(dprice);// 更新所有用户的昨日收益
				}*/
				
				
				Double statRate = (double)0;
				Double dynRate = (double)0;
				//List<Member> memList = memberService.selectVipCount();
				List<Member> memList = memberService.selectAll();
				if(memList!=null&&memList.size()>0){
					for(int i = 0;i<memList.size();i++){
						
						//Member member = memberService.selectByZone(memList.get(i).getId()+"");
						Member member = memList.get(i);
						//查询A区所有直属下级
						List<Member> aList = memberService.selectNumByZone(memList.get(i).getId()+"","A");
						//Double aCount = member.getaCount();//A区用户持币数（购买币个数）
						//Double bCount = member.getbCount();//B区用户持币数（购买币个数）
						double aCount = 0;
						double bCount = 0;
						Integer aNum = 0;
						Integer bNum = 0;
						//计算A区业绩
						if(aList!=null&&aList.size()>0){
							for(int k = 0;k<aList.size();k++){
								List<Member> tList = memberService.clearsSons(aList.get(k).getId()+"");
								if(tList!=null&&tList.size()>0){
									aNum+=tList.size();
									for(int j = 0;j<tList.size();j++){
										aCount = aCount+tList.get(j).getIntegral();
									}
								}
							}
						}
						member.setaCount(aCount);
						member.setaNum(aNum);
						//查询B区所有直属下级
						List<Member> bList = memberService.selectNumByZone(memList.get(i).getId()+"","B");
						//计算B区业绩
						if(bList!=null&&bList.size()>0){
							for(int k = 0;k<bList.size();k++){
								List<Member> tList = memberService.clearsSons(bList.get(k).getId()+"");
								if(tList!=null&&tList.size()>0){
									bNum+=tList.size();
									for(int j = 0;j<tList.size();j++){
										bCount = bCount+tList.get(j).getIntegral();
									}
								}
							}
						}
						member.setbCount(bCount);
						member.setbNum(bNum);
						//Member member = memList.get(i);
						//用户静态持币算力
						double stateCount = member.getIntegral();//用户持币数（购买币个数）
						if(stateCount>=0&&stateCount<300){
							statRate = setting.getRate1();
						}else if(stateCount>=300&&stateCount<1000){
							statRate = setting.getRate2();
						}else if(stateCount>=1000&&stateCount<5000){
							statRate = setting.getRate3();
						}else{
							statRate = setting.getRate4();
						}
						member.setStaIncome(stateCount*statRate);
						double rCount = 0;
						if(aCount>bCount){
							if(bCount>=0&&bCount<500){
								dynRate = (double)0;
							}else if(bCount>=500&&bCount<1000){
								dynRate = setting.getRate5();
							}else if(bCount>=1000&&bCount<5000){
								dynRate = setting.getRate6();
							}else{
								dynRate = setting.getRate7();
							}
							rCount = bCount*dynRate;
							if(rCount>5000){
								member.setDyIncome((double)5000);
							}else{
								if(rCount>member.getIntegral()*0.5){
									member.setDyIncome(member.getIntegral()*0.5);
								}else{
									member.setDyIncome(rCount);
								}
							}
						}else{
							if(aCount>=0&&aCount<500){
								dynRate = (double)0;
							}else if(aCount>=500&&aCount<1000){
								dynRate = setting.getRate5();
							}else if(aCount>=1000&&aCount<5000){
								dynRate = setting.getRate6();
							}else{
								dynRate = setting.getRate7();
							}
							rCount = aCount*dynRate;
							if(rCount>5000){
								member.setDyIncome((double)5000);
							}else{
								if(rCount>member.getIntegral()*0.5){
									member.setDyIncome(member.getIntegral()*0.5);
								}else{
									member.setDyIncome(rCount);
								}
							}
						}
						
						
						
						//动态持币数
						/*Integer dynCount = member.getDyTotal();//用户持币数（购买币个数）
						if(dynCount>=0&&dynCount<500){
							dynRate = (double)0;
						}else if(dynCount>=500&&dynCount<1000){
							dynRate = setting.getRate5();
						}else if(dynCount>=1000&&dynCount<5000){
							dynRate = setting.getRate6();
						}else{
							dynRate = setting.getRate7();
						}*/
						Double oldPrice = member.getIntegral();
						Double newPrice = oldPrice+stateCount*statRate+member.getDyIncome();//余额+静态收益+动态收益
						
						System.out.println("old ======== "+oldPrice+"   new ========="+newPrice);
						member.setIntegral(newPrice);
						memberService.update(member);
						
						IncomeRecord incomeRecord = new IncomeRecord();
						//Double integral = oldPrice +stateCount+rCount;
						incomeRecord.setMemberId(member.getId());
						incomeRecord.setIntegral(stateCount*statRate+member.getDyIncome());
						incomeRecord.setIncome(dprice*(stateCount*statRate+member.getDyIncome()));
						incomeRecord.setRecordDate(new Date());
						memberService.addIncomeRecord(incomeRecord);// 更新所有用户的昨日收益
					}
				}
				//更新用户的持币数及收益
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 解冻银多宝
			if (null == unfreezeDays || 0 == unfreezeDays) {
				unfreezeDays = 10;
			}
			if (null == unfreezeRate || 0 == unfreezeRate) {
				unfreezeRate = 0.1;
			}
			memberService.unfreezeIntegral(unfreezeDays, unfreezeRate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 用户大盘量增长
		/*List<Member> members = memberService.selectAllList();
		if (null != members && members.size() > 0) {
			for (Member member : members) {
				memberService.increaseIntegral(member);
			}
		}*/
		log.info("价格自行涨跌任务执行结束...");
	}
}