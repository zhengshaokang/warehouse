package com.hys.dal.component.inout.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.inout.IInoutRecordDalComp;
import com.hys.dal.db.inout.IInoutRecordDao;
import com.hys.model.inout.InoutRecord;

@Component
public class InoutRecordDalCompImpl implements IInoutRecordDalComp{
	@Autowired
	private IInoutRecordDao inoutRecordDao;

	@Override
	public boolean addInoutRecord(InoutRecord inoutRecord) {
		return JdbcUtil.isSuccess(inoutRecordDao.addInoutRecord(inoutRecord));
	}

	@Override
	public List<InoutRecord> queryInoutRecordByProductId(Integer productId) {
		return inoutRecordDao.queryInoutRecordByProductId(productId);
	}

	@Override
	public List<InoutRecord> queryInoutRecordByTime(String operateTimeStart,
			String operateTimeEnd) {
		return inoutRecordDao.queryInoutRecordByTime(operateTimeStart, operateTimeEnd);
	}

	@Override
	public List<InoutRecord> queryInoutRecords() {
		return inoutRecordDao.queryInoutRecords();
	}

	@Override
	public PageData<InoutRecord> pageQueryInoutRecord(
			PageParam<InoutRecord> page) {
		 List<InoutRecord> inoutRecords = inoutRecordDao.pageQueryInoutRecord(page);
         return new PageData<InoutRecord>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), inoutRecords);
	}

}
