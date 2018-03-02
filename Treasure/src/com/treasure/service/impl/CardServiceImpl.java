package com.treasure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treasure.dao.CardMapper;
import com.treasure.dao.CoinRecordMapper;
import com.treasure.model.Card;
import com.treasure.service.CardService;
@Service
public class CardServiceImpl implements CardService{
	@Autowired
	private CardMapper cmapper;
	
	
	@Override
	public int addCard(Card card) {
		if(cmapper.findByCardId(card.getMemberId())==null){
			cmapper.addCard(card);
			return 1;
		}
		return 0;
	}
	
	public int updataCard(Card card){
		return cmapper.updataCard(card);
	}
	
	public List<Card> findAll(Long id){
		return cmapper.findAll(id);
	}

	public int delete(long id) {
		System.out.println(id);
		return cmapper.delete(id);
	}

	@Override
	public Card selectCardId(long id) {
		return cmapper.selectCardId(id);
	}
	
}
