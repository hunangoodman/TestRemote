package com.treasure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.treasure.model.Card;

public interface CardMapper {
public void addCard(Card card);
	public List<Card> findAll(Long id);
	public int delete(long id);
	public String findByCardNumber(String cardNumber);
	public String findByCardId(long memberId);
	public int updataCard(@Param("record")Card card);
	public Card selectCardId(long memberId);
	
}
