package com.hys.mgt.view.warehouse.component;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.warehouse.vo.InoutRecordVo;

public interface IInoutRecordComp {
	public boolean addInoutRecord(InoutRecordVo inoutRecord);
	public List<InoutRecordVo> queryInoutRecordByProductId(Integer productId);
	public List<InoutRecordVo> queryInoutRecordByTime(String operateTimeStart,String operateTimeEnd);
	public List<InoutRecordVo> queryInoutRecords();
	public PageData<InoutRecordVo> pageQueryInoutRecord(InoutRecordVo inout);
	public ResultPrompt returninSubmit(String sku,String productionDate, Integer qty,Integer recordType,Integer operator);
	public ResultPrompt returnstockSubmit(String sku,String productionDate, Integer qty,Integer recordType,Integer operator);
//	public ResultPrompt discardoutSubmit(String sku, Integer qty,Integer recordType,Integer operator);
}
