package com.hys.dal.db.inout;

import java.util.List;

import com.hys.commons.page.PageParam;
import com.hys.model.inout.InoutRecord;

public interface IInoutRecordDao {
	public int addInoutRecord(InoutRecord inoutRecord);
	public List<InoutRecord> queryInoutRecordByProductId(Integer productId);
	public List<InoutRecord> queryInoutRecordByTime(String operateTimeStart,String operateTimeEnd);
	public List<InoutRecord> queryInoutRecords();
	public List<InoutRecord> pageQueryInoutRecord(PageParam<InoutRecord> page);
}
