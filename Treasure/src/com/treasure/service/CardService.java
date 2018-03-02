package com.treasure.service;

import java.util.List;

import com.treasure.model.Card;

public interface CardService {
	
	public int addCard(Card card);
	
	public List<Card> findAll(Long id);
	
	public int delete(long id);
	
	public int updataCard(Card card);
	
	public Card selectCardId(long id);
}
