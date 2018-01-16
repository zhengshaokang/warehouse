package com.hys.mgt.view.product.component.impl;

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
import com.hys.mgt.view.product.common.SupplierConverter;
import com.hys.mgt.view.product.component.ISupplierComp;
import com.hys.mgt.view.product.vo.SupplierVo;
import com.hys.model.purchase.Supplier;
import com.hys.service.purchase.ISupplierService;
@Component
public class SupplierCompImpl implements ISupplierComp{
	private final static Logger log = LogProxy.getLogger(SupplierCompImpl.class);
	
	@Autowired
	private ISupplierService supplierService;

	@Override
	public ResultPrompt addSupplier (SupplierVo supplierVo){
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			//验证名称是否存在，不能重复
			
			Supplier  pc = supplierService.querySupplierByName(supplierVo.getName(),supplierVo.getCreator());
			if(null != pc) {
				 rp.setStatusCode("200");
				 rp.setMessage("该分类名称已经存在！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/supplierlist"); // 要刷新的tab页id
				 return rp;
			}
			boolean b = supplierService.addSupplier(SupplierConverter.convert2Do(supplierVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/supplierlist"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/supplierlist"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	        rp.setNavTabId("product/supplierlist"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt updateSupplier(SupplierVo supplierVo){
			ResultPrompt rp = new ResultPrompt();
			try {
				
				boolean b = supplierService.updateSupplier(SupplierConverter.convert2Do(supplierVo));
				
				if(b) {	
					 rp.setStatusCode("200");
					 rp.setMessage("操作成功！");
					 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
					 rp.setNavTabId("product/supplierlist"); // 要刷新的tab页id
				 } else {
					 rp.setStatusCode("300");
					 rp.setMessage("操作失败！");
					 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
					 rp.setNavTabId("product/supplierlist"); // 要刷新的tab页id
				 }
			} catch (Exception e) {
				e.printStackTrace();
			    rp.setStatusCode("300");
		    	rp.setMessage("操作失败！");
		    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
		        rp.setNavTabId("product/supplierlist"); // 要刷新的tab页id
			}
			return rp;
	}

	@Override
	public ResultPrompt deleteSupplier(Integer id,Integer creator) {
		ResultPrompt rp = new ResultPrompt();
		try {
			
			//验证名称是否存在，不能重复
			
			Boolean  b = supplierService.deleteSupplier(id,creator);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setNavTabId("product/supplierlist"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setNavTabId("product/supplierlist"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	        rp.setNavTabId("product/supplierlist"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public SupplierVo querySupplierById(Integer id) {
		return SupplierConverter.convert2Vo(supplierService.querySupplierById(id));
	}


	@Override
	public List<SupplierVo> querySuppliers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageData<SupplierVo> pageQuerySupplier(SupplierVo p) {
		PageData<SupplierVo> pageVo = null;
        try {
        	Supplier pc = (Supplier) SupplierConverter.convert2Do(p);  
            PageParam<Supplier> page = new PageParam<Supplier>();
            page.setP(pc);
            page.setPageNo(p.getPageNum());
            page.setPageSize(p.getNumPerPage());
            PageData<Supplier> pd = supplierService.pageQuerySupplier(page);

            List<Supplier> list = pd.getPageData();
            List<SupplierVo> listvo = new ArrayList<>();
            if (LogicUtil.isNotNull(list)) {
                for (Supplier pcl : list) {
                    listvo.add((SupplierVo) SupplierConverter.convert2Vo(pcl));
                }
            }
            pageVo = new PageData<SupplierVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
        } catch (Exception e){
        	e.printStackTrace();
            log.debug("pageQuerySupplier exception=>" + e);
        }
        return pageVo;
	}

}
