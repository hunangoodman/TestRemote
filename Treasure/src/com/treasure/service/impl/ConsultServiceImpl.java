package com.treasure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treasure.bean.PageInfo;
import com.treasure.dao.BtoolsConsultMapper;
import com.treasure.model.BtoolsConsult;
import com.treasure.service.ConsultService;

@Service
public class ConsultServiceImpl implements  ConsultService{

	@Autowired
	private BtoolsConsultMapper btoolsConsultDao;
	
	public int add(BtoolsConsult btoolsConsult) {
		if(btoolsConsult.getStatus()==null){
			btoolsConsult.setStatus(0);
		}
		return btoolsConsultDao.insert(btoolsConsult);
	}

	@Override
	public int batchAdd(List<BtoolsConsult> btoolsConsultList) {
		int num = 0;

		int c = 10000;
		int size = btoolsConsultList.size()/c + 1;
		for(int i=0; i<size; i++) {
			int begin = i*c;
			int end = (i+1)*c;
			end = end >= btoolsConsultList.size() ? btoolsConsultList.size() : end;
			List<BtoolsConsult> insertList = btoolsConsultList.subList(begin, end);
			num += btoolsConsultDao.batchInsert(insertList);
		}
		return num;
	}

	@Override
	public int modifyByMap(BtoolsConsult oldBtoolsConsult,BtoolsConsult newBtoolsConsult) {
		return 0;
	}

	@Override
	public int modifyByModel(BtoolsConsult btoolsConsult) {
		return btoolsConsultDao.updateByModel(btoolsConsult);
	}

	@Override
	public int remove(List<Long> list) {
		return btoolsConsultDao.deleteByIds(list);
	}

	@Override
	public int removeById(Long conId) {
		return btoolsConsultDao.deleteById(conId);
	}

	@Override
	public int removeByModel(BtoolsConsult btoolsConsult) {
		return btoolsConsultDao.deleteByModel(btoolsConsult);
	}


	@Override
	public int findTotal(BtoolsConsult btoolsConsult) {
		return btoolsConsultDao.selectTotalRecord(btoolsConsult);
	}

	@Override
	public BtoolsConsult findById(Long conId) {
		System.out.println(conId);
		return btoolsConsultDao.selectById(conId);
		
	}


	@Override
	public List<BtoolsConsult> findByModel(BtoolsConsult btoolsConsult) {
		
		return btoolsConsultDao.selectByModel(btoolsConsult);
	}

	@Override
	public List<BtoolsConsult> selectAllList() {
		BtoolsConsult consult = new BtoolsConsult();
		consult.setStatus(2);
		return btoolsConsultDao.selectByModel(consult);
	}
	
	@Override
	public List<BtoolsConsult> findInPage(BtoolsConsult btoolsConsult,  PageInfo pageInfo){
	
		return btoolsConsultDao.selectInPage(btoolsConsult , pageInfo);
	
	}
	
}
