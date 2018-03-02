package com.treasure.model;

import java.io.Serializable;
import java.util.Date;

/**
 *  @name 吴健
 *  @date 2018-1-26
 * */
public class TransferRecord implements Serializable{
	
		private Long id;

		private String fullName;

		private String mobile;

		private String password;
		private String passwordAgain;
		private String passwordNew;
		
		private String passwordTwo;
		private String passwordTwoAgain;
		
		private String connection;//连接点

		private String adCode;

		private Double money;

		private Double integral;

		private Double freezeIntegral;

		private Double silver;

		private Double consumeBean;

		private Byte status;

		private Byte verifiedStatus;

		private String address;

		private String cardNumber;

		private String idCard;

		private String head;

		private String positivePhoto;

		private String negativePhoto;

		private Date addTime;

		private Byte activateStatus;

		private String remark;

		private Date thawDate;

		private String bank;

		private Double recommendMoney;
		// 无映射属性
		private String code;
		private String token;
		private Double yesterdayIncome;
		private Double totalIncome;
		private Double totalIntegral;// 银多宝总额
		private Double rate;
		private Integer recommendNum;// 推荐人数
		private Double recommMoney;//推荐奖金
		
		private String zone;//推荐分区
		
		private String uuid;//用户钱包码
		
		private Integer total;//会员持币数
		
		private Integer dyTotal;//推荐会员持币数
		
		private Integer quantity;//用户贝西币数
		
		private Double staIncome;
		
		private Double dyIncome;
		
		private Integer aNum;
		
		private Integer bNum;
		
		private Double aCount;
		
		private Double bCount;
		
		private String mycode;
		
		private Integer type;
		
		private String time;
		
		private double amount;
		
		
		

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		private String userName;
		
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getPasswordNew() {
			return passwordNew;
		}

		public void setPasswordNew(String passwordNew) {
			this.passwordNew = passwordNew;
		}

		public String getMycode() {
			return mycode;
		}

		public void setMycode(String mycode) {
			this.mycode = mycode;
		}

		public String getPasswordAgain() {
			return passwordAgain;
		}

		public void setPasswordAgain(String passwordAgain) {
			this.passwordAgain = passwordAgain;
		}

		public String getPasswordTwoAgain() {
			return passwordTwoAgain;
		}

		public void setPasswordTwoAgain(String passwordTwoAgain) {
			this.passwordTwoAgain = passwordTwoAgain;
		}

		public String getConnection() {
			return connection;
		}

		public void setConnection(String connection) {
			this.connection = connection;
		}

		public String getPasswordTwo() {
			return passwordTwo;
		}

		public void setPasswordTwo(String passwordTwo) {
			this.passwordTwo = passwordTwo;
		}

		public Integer getaNum() {
			return aNum;
		}

		public void setaNum(Integer aNum) {
			this.aNum = aNum;
		}

		public Integer getbNum() {
			return bNum;
		}

		public void setbNum(Integer bNum) {
			this.bNum = bNum;
		}

		

		public Double getaCount() {
			return aCount;
		}

		public void setaCount(Double aCount) {
			this.aCount = aCount;
		}

		public Double getbCount() {
			return bCount;
		}

		public void setbCount(Double bCount) {
			this.bCount = bCount;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public Double getStaIncome() {
			return staIncome;
		}

		public void setStaIncome(Double staIncome) {
			this.staIncome = staIncome;
		}

		public Double getDyIncome() {
			return dyIncome;
		}

		public void setDyIncome(Double dyIncome) {
			this.dyIncome = dyIncome;
		}

		public Integer getDyTotal() {
			return dyTotal;
		}

		public void setDyTotal(Integer dyTotal) {
			this.dyTotal = dyTotal;
		}

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getZone() {
			return zone;
		}

		public void setZone(String zone) {
			this.zone = zone;
		}

		public Double getRecommMoney() {
			return recommMoney;
		}

		public void setRecommMoney(Double recommMoney) {
			this.recommMoney = recommMoney;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public Double getYesterdayIncome() {
			return yesterdayIncome;
		}

		public void setYesterdayIncome(Double yesterdayIncome) {
			this.yesterdayIncome = yesterdayIncome;
		}

		public Double getTotalIncome() {
			return totalIncome;
		}

		public void setTotalIncome(Double totalIncome) {
			this.totalIncome = totalIncome;
		}

		public Double getTotalIntegral() {
			return totalIntegral;
		}

		public void setTotalIntegral(Double totalIntegral) {
			this.totalIntegral = totalIntegral;
		}

		public Double getRate() {
			return rate;
		}

		public void setRate(Double rate) {
			this.rate = rate;
		}

		public Integer getRecommendNum() {
			return recommendNum;
		}

		public void setRecommendNum(Integer recommendNum) {
			this.recommendNum = recommendNum;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAdCode() {
			return adCode;
		}

		public void setAdCode(String adCode) {
			this.adCode = adCode;
		}

		public Double getMoney() {
			return money;
		}

		public void setMoney(Double money) {
			this.money = money;
		}

		public Double getIntegral() {
			return integral;
		}

		public void setIntegral(Double integral) {
			this.integral = integral;
		}

		public Double getFreezeIntegral() {
			return freezeIntegral;
		}

		public void setFreezeIntegral(Double freezeIntegral) {
			this.freezeIntegral = freezeIntegral;
		}

		public Double getSilver() {
			return silver;
		}

		public void setSilver(Double silver) {
			this.silver = silver;
		}

		public Double getConsumeBean() {
			return consumeBean;
		}

		public void setConsumeBean(Double consumeBean) {
			this.consumeBean = consumeBean;
		}

		public Byte getStatus() {
			return status;
		}

		public void setStatus(Byte status) {
			this.status = status;
		}

		public Byte getVerifiedStatus() {
			return verifiedStatus;
		}

		public void setVerifiedStatus(Byte verifiedStatus) {
			this.verifiedStatus = verifiedStatus;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCardNumber() {
			return cardNumber;
		}

		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}

		public String getIdCard() {
			return idCard;
		}

		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}

		public String getHead() {
			return head;
		}

		public void setHead(String head) {
			this.head = head;
		}

		public String getPositivePhoto() {
			return positivePhoto;
		}

		public void setPositivePhoto(String positivePhoto) {
			this.positivePhoto = positivePhoto;
		}

		public String getNegativePhoto() {
			return negativePhoto;
		}

		public void setNegativePhoto(String negativePhoto) {
			this.negativePhoto = negativePhoto;
		}

		public Date getAddTime() {
			return addTime;
		}

		public void setAddTime(Date addTime) {
			this.addTime = addTime;
		}

		public Byte getActivateStatus() {
			return activateStatus;
		}

		public void setActivateStatus(Byte activateStatus) {
			this.activateStatus = activateStatus;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public Date getThawDate() {
			return thawDate;
		}

		public void setThawDate(Date thawDate) {
			this.thawDate = thawDate;
		}

		public String getBank() {
			return bank;
		}

		public void setBank(String bank) {
			this.bank = bank;
		}

		public Double getRecommendMoney() {
			return recommendMoney;
		}

		public void setRecommendMoney(Double recommendMoney) {
			this.recommendMoney = recommendMoney;
		}
		
		
		
		public String toString() {
			return "Member [id=" + id + ", fullName=" + fullName + ", mobile="
					+ mobile + ", password=" + password + ", passwordAgain="
					+ passwordAgain + ", passwordNew=" + passwordNew
					+ ", passwordTwo=" + passwordTwo + ", passwordTwoAgain="
					+ passwordTwoAgain + ", connection=" + connection + ", adCode="
					+ adCode + ", money=" + money + ", integral=" + integral
					+ ", freezeIntegral=" + freezeIntegral + ", silver=" + silver
					+ ", consumeBean=" + consumeBean + ", status=" + status
					+ ", verifiedStatus=" + verifiedStatus + ", address=" + address
					+ ", cardNumber=" + cardNumber + ", idCard=" + idCard
					+ ", head=" + head + ", positivePhoto=" + positivePhoto
					+ ", negativePhoto=" + negativePhoto + ", addTime=" + addTime
					+ ", activateStatus=" + activateStatus + ", remark=" + remark
					+ ", thawDate=" + thawDate + ", bank=" + bank
					+ ", recommendMoney=" + recommendMoney + ", code=" + code
					+ ", token=" + token + ", yesterdayIncome=" + yesterdayIncome
					+ ", totalIncome=" + totalIncome + ", totalIntegral="
					+ totalIntegral + ", rate=" + rate + ", recommendNum="
					+ recommendNum + ", recommMoney=" + recommMoney + ", zone="
					+ zone + ", uuid=" + uuid + ", total=" + total + ", dyTotal="
					+ dyTotal + ", quantity=" + quantity + ", staIncome="
					+ staIncome + ", dyIncome=" + dyIncome + ", aNum=" + aNum
					+ ", bNum=" + bNum + ", aCount=" + aCount + ", bCount="
					+ bCount + ", mycode=" + mycode + "]";
		}

	
}
