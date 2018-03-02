package com.treasure.service;

import java.util.List;

import com.treasure.model.PriceRecord;
import com.treasure.model.Setting;

public interface SettingService {
	
	Setting querySettingInfo();

	int save(Setting setting);

	List<PriceRecord> selectPrices(PriceRecord price);

	int update(Setting setting, PriceRecord record);

	int repurchase(Double aprice);

	Setting querySettingInfo1();
	
	int update(Setting setting);
	
}
