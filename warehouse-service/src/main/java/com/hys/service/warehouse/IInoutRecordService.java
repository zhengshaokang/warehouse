package com.hys.service.warehouse;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.warehouse.InoutRecord;

public interface IInoutRecordService {
	public boolean addInoutRecord(InoutRecord inoutRecord);
	public List<InoutRecord> queryInoutRecordByProductId(Integer productId);
	public List<InoutRecord> queryInoutRecordByTime(String operateTimeStart,String operateTimeEnd);
	public List<InoutRecord> queryInoutRecords();
	public PageData<InoutRecord> pageQueryInoutRecord(PageParam<InoutRecord> page);
}
