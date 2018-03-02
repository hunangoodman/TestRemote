package com.treasure.model;

public class Card {

	private Long id;
	private Long memberId;
	private String fullName;
	private String cardNumber;
	private String cardInfo;
	/*
	 * 	memberId:memberId,
		fullName:name.value,
		cardNumber:account.value,
		cardInfo:info.value,
		token:token
	 */
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}
	
	
}
