package com.treasure.scheduled;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.treasure.model.InestmentIncome;
import com.treasure.model.MoneyManagement;
import com.treasure.model.Setting;
import com.treasure.service.IncomeService;
import com.treasure.service.MoneyManagementService;
import com.treasure.service.SettingService;
@Component
public class MoneyManageCheduled {
	private final static Logger log = Logger.getLogger(PriceScheduled.class);
	@Autowired
	@Qualifier(value = "settingServiceImpl")
	private SettingService service;
	
	@Autowired
	@Qualifier(value = "moneyManagementServiceImpl")
	private MoneyManagementService moneyManagementService;
	
	@Autowired
	@Qualifier(value = "incomeServiceImpl")
	private IncomeService incomeService;
	
	@Value("${unfreeze.integral.days}")
	private Integer unfreezeDays;
	@Value("${unfreeze.integral.rate}")
	private Double unfreezeRate;
	
//	@Scheduled(cron = "${price.time}")
//	@Scheduled(cron = "0 0/1 * * * ?")
	public void run(){
		log.info("收益计算执行开始...");
		try {
			//大盘收益率
			Setting setting = service.querySettingInfo();
			//投资收益的项目
			List<MoneyManagement> list=moneyManagementService.selectAll();
			log.info("收益的个数"+list.size());
			for(int i=0;i<list.size();i++){
				//个人收益
				InestmentIncome income = new InestmentIncome();
				//收益id
				income.setInvestmentId(list.get(i).getManagementId());
				//收益状态：0:收益中 1：已取出  2:存入
				income.setIncomeType(list.get(i).getType());
				list.get(i).getFinancialManagement().equals("0");
				log.info("mangagementid==="+list.get(i).getManagementId());
				//收益类别  0 活期  1 定期
				if(list.get(i).getFinancialManagement().equals("0")){
					List<InestmentIncome> incomeList =incomeService.selectimcomeId(income);
					//当天的总额investmentId
					double tollMoney=0;
					double money=0;
					log.info("当天收益"+incomeList.size());
					//当前的收益  为什么每次id不同都会加呢
					if(incomeList.size()<1){
						tollMoney = list.get(i).getMoney()+list.get(i).getMoney()*setting.getInterestrate();
						money = list.get(i).getMoney()*setting.getInterestrate();
					}else{
						log.info("收益了");
						tollMoney=incomeList.get(incomeList.size()-1).getTotalMoney()+list.get(incomeList.size()-1).getMoney()*setting.getInterestrate();
						money =incomeList.get(incomeList.size()-1).getTotalMoney()*setting.getInterestrate();
					} 
					//历史的收益金额
					for(int n=1;n<incomeList.size();n++){
						tollMoney+=incomeList.get(n).getIncomeMoney();
					}
					//存入到收益记录表
					income.setTotalMoney(tollMoney);
					income.setIncomeMoney(money);
					incomeService.insertIncome(income);
					//dingyice zssouyaee 
				}else if(list.get(i).getFinancialManagement().equals("1")){
					List<InestmentIncome> incomeList =incomeService.selectimcomeId(income);
					//当天的总额investmentId
					double tollMoney=0;
					double money=0;
					log.info("当天收益"+incomeList.size());
					//当前的收益  为什么每次id不同都会加呢
					if(incomeList.size()<1){
						tollMoney = list.get(i).getMoney()+list.get(i).getMoney()*setting.getInterestrate();
						money = list.get(i).getMoney()*setting.getInterestrate();
					}else{
						log.info("收益了");
						tollMoney=incomeList.get(incomeList.size()-1).getTotalMoney()+list.get(incomeList.size()-1).getMoney()*setting.getInterestrate();
						money =incomeList.get(incomeList.size()-1).getTotalMoney()*setting.getInterestrate();
					} 
					//历史的收益金额
					for(int n=1;n<incomeList.size();n++){
						tollMoney+=incomeList.get(n).getIncomeMoney();
					}
				}
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
