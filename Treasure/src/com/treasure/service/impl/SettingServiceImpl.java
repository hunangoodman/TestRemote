package com.treasure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treasure.dao.MemberMapper;
import com.treasure.dao.PriceRecordMapper;
import com.treasure.dao.SettingMapper;
import com.treasure.dao.TradeMapper;
import com.treasure.model.PriceRecord;
import com.treasure.model.Setting;
import com.treasure.model.Trade;
import com.treasure.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	private SettingMapper settingMapper;
	@Autowired
	private PriceRecordMapper priceMapper;
	@Autowired
	private TradeMapper tradeMapper;
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Setting querySettingInfo() {
		return settingMapper.selectSettingInfo();
	}

	@Override
	public int save(Setting setting) {
		try {
			return settingMapper.insertSelective(setting);
		} catch (Exception e) {
			return settingMapper.updateByPrimaryKeySelective(setting);
		}
	}

	@Override
	public List<PriceRecord> selectPrices(PriceRecord price) {
		return priceMapper.selectPrices(price);
	}

	@Override
	public int update(Setting setting, PriceRecord record) {
		return settingMapper.updateByPrimaryKeySelective(setting) + priceMapper.insertSelective(record);
	}
	
	@Override
	public int update(Setting setting) {
		return settingMapper.updateByPrimaryKeySelective(setting);
	}

	@Override
	public int repurchase(Double price) {
		int n = 0;
		List<Trade> trades = tradeMapper.selectByPrice(price);
		if (null != trades && trades.size() > 0) {
			for (Trade trade : trades) {
				trade.setStatus((byte) 1);
				n += tradeMapper.updateByPrimaryKeySelective(trade);
				n += memberMapper.updateByTrade3(trade);
			}
		}
		return n;
	}

	@Override
	public Setting querySettingInfo1() {
		return settingMapper.querySettingInfo1();
	}

}
