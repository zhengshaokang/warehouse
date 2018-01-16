package com.hys.service.warehouse.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.warehouse.IInoutRecordDalComp;
import com.hys.model.warehouse.InoutRecord;
import com.hys.service.warehouse.IInoutRecordService;

@Service
public class InoutRecotdServiceImpl implements IInoutRecordService{
	
	@Autowired
	private IInoutRecordDalComp inoutRecordDalComp;

	@Override
	public boolean addInoutRecord(InoutRecord inoutRecord) {
		if(LogicUtil.isNull(inoutRecord)) {
			return false;
		}
		return inoutRecordDalComp.addInoutRecord(inoutRecord);
	}

	@Override
	public List<InoutRecord> queryInoutRecordByProductId(Integer productId) {
		if(LogicUtil.isNull(productId)) {
			return null;
		}
		return inoutRecordDalComp.queryInoutRecordByProductId(productId);
	}

	@Override
	public List<InoutRecord> queryInoutRecordByTime(String operateTimeStart,
			String operateTimeEnd) {
		if(LogicUtil.isNullOrEmpty(operateTimeStart) || LogicUtil.isNullOrEmpty(operateTimeEnd)) {
			return null;
		}
		return inoutRecordDalComp.queryInoutRecordByTime(operateTimeStart, operateTimeEnd);
	}

	@Override
	public List<InoutRecord> queryInoutRecords() {
		return inoutRecordDalComp.queryInoutRecords();
	}

	@Override
	public PageData<InoutRecord> pageQueryInoutRecord(
			PageParam<InoutRecord> page) {
	    if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
             return null;
        }
		return inoutRecordDalComp.pageQueryInoutRecord(page);
	}

}
