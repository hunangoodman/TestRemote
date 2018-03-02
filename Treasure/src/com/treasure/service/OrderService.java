package com.treasure.service;

import java.util.List;

import com.treasure.bean.OrderResult;
import com.treasure.bean.PageInfo;
import com.treasure.model.Order;
import com.treasure.model.PayRecord;

public interface OrderService {

	void buy(Order order, OrderResult result);

	List<Order> selectList(Order order, PageInfo pageInfo);

	int selectCount(Order order);

	Order selectByKey(Long id);

	int update(Order order);

	int updatePayOrderNo(Order order);

	int addPayRecord(PayRecord record);

	int deleteByKeys(List<Long> keyes);

	Order selectByOrderNo(String orderNo);

}
