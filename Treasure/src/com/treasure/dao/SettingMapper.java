package com.treasure.dao;

import com.treasure.model.Setting;
import com.treasure.model.SettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SettingMapper {
	int countByExample(SettingExample example);

	int deleteByExample(SettingExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Setting record);

	int insertSelective(Setting record);

	List<Setting> selectByExample(SettingExample example);

	Setting selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Setting record, @Param("example") SettingExample example);

	int updateByExample(@Param("record") Setting record, @Param("example") SettingExample example);

	int updateByPrimaryKeySelective(Setting record);

	int updateByPrimaryKey(Setting record);

	Setting selectSettingInfo();

	int updateIntegral(@Param("integral") Double integral);

	Setting querySettingInfo1();
}