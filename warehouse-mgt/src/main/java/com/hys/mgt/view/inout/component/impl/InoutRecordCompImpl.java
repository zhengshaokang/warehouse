package com.hys.mgt.view.inout.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.inout.common.InoutRecordConverter;
import com.hys.mgt.view.inout.component.IInoutRecordComp;
import com.hys.mgt.view.inout.vo.InoutRecordVo;
import com.hys.model.inout.InoutRecord;
import com.hys.model.product.Product;
import com.hys.service.inout.IInoutRecordService;
import com.hys.service.product.IProductService;

@Component
public class InoutRecordCompImpl implements IInoutRecordComp{
	
	private final static Logger log = LogProxy.getLogger(InoutRecordCompImpl.class);
	
	@Autowired
	private IInoutRecordService inoutRecordService;
	@Autowired
	private IProductService productService;

	@Override
	public boolean addInoutRecord(InoutRecordVo inoutRecord) {
		return false;
	}

	@Override
	public List<InoutRecordVo> queryInoutRecordByProductId(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InoutRecordVo> queryInoutRecordByTime(String operateTimeStart,
			String operateTimeEnd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InoutRecordVo> queryInoutRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageData<InoutRecordVo> pageQueryInoutRecord(InoutRecordVo inoutVo) {
		 PageData<InoutRecordVo> pageVo = null;
	        try {
	        	InoutRecord p = (InoutRecord) InoutRecordConverter.convert2Do(inoutVo);  
	            PageParam<InoutRecord> page = new PageParam<InoutRecord>();
	            page.setP(p);
	            page.setPageNo(inoutVo.getPageNum());
	            page.setPageSize(inoutVo.getNumPerPage());
	            PageData<InoutRecord> pd = inoutRecordService.pageQueryInoutRecord(page);

	            List<InoutRecord> list = pd.getPageData();
	            List<InoutRecordVo> listvo = new ArrayList<>();
	            if (LogicUtil.isNotNull(list)) {
	                for (InoutRecord inout : list) {
	                    listvo.add((InoutRecordVo) InoutRecordConverter.convert2Vo(inout));
	                }
	            }
	            pageVo = new PageData<InoutRecordVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
	        } catch (Exception e){
	        	e.printStackTrace();
	            log.debug("pageQueryProduct exception=>" + e);
	        }
	       return pageVo;
	}

	@Override
	public ResultPrompt returninSubmit(String sku, Integer qty, Integer recordType, Integer operator) {
		ResultPrompt rp = new ResultPrompt();
		try {
			Product p = productService.queryProductBySku(sku);
			boolean b = productService.productInSubmit(p, qty, recordType,operator);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("inout/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 rp.setNavTabId("inout/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
		    rp.setMessage("操作失败！");
	        rp.setNavTabId("inout/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt returnstockSubmit(String sku, Integer qty,
			Integer recordType, Integer operator) {
		ResultPrompt rp = new ResultPrompt();
		try {
			Product p = productService.queryProductBySku(sku);
			boolean b = productService.productInSubmit(p, qty, recordType,operator);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("inout/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 rp.setNavTabId("inout/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
		    rp.setMessage("操作失败！");
	        rp.setNavTabId("inout/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt discardoutSubmit(String sku, Integer qty,
			Integer recordType, Integer operator) {
		ResultPrompt rp = new ResultPrompt();
		try {
			Product p = productService.queryProductBySku(sku);
			boolean b = productService.productOutSubmit(p, qty, recordType,operator);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("inout/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 rp.setNavTabId("inout/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
		    rp.setMessage("操作失败！");
	        rp.setNavTabId("inout/list"); // 要刷新的tab页id
		}
		return rp;
	}

}
