package com.hys.mgt.view.warehouse.component.impl;

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
import com.hys.mgt.view.warehouse.common.WarehouseConverter;
import com.hys.mgt.view.warehouse.component.IWarehouseComp;
import com.hys.mgt.view.warehouse.vo.WarehouseVo;
import com.hys.model.warehouse.Warehouse;
import com.hys.service.warehouse.IWarehouseService;
@Component
public class WarehouseCompImpl implements IWarehouseComp{
	private final static Logger log = LogProxy.getLogger(WarehouseCompImpl.class);
	
	@Autowired
	private IWarehouseService warehouseService;

	@Override
	public ResultPrompt addWarehouse (WarehouseVo warehouseVo){
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			//验证名称是否存在，不能重复
			
			Warehouse  pc = warehouseService.queryWarehouseByName(warehouseVo.getName(),warehouseVo.getCreator());
			if(null != pc) {
				 rp.setStatusCode("200");
				 rp.setMessage("该仓库名称已经存在！");
				 rp.setNavTabId("warehouse/list"); // 要刷新的tab页id
				 return rp;
			}
			
			Warehouse  pc1 = warehouseService.queryWarehouseByCode(warehouseVo.getCode());
			if(null != pc1) {
				 rp.setStatusCode("200");
				 rp.setMessage("该仓库编码已经存在！");
				 rp.setNavTabId("warehouse/list"); // 要刷新的tab页id
				 return rp;
			}
			
			boolean b = warehouseService.addWarehouse(WarehouseConverter.convert2Do(warehouseVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("warehouse/list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("warehouse/list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	    	rp.setNavTabId("warehouse/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt updateWarehouse(WarehouseVo warehouseVo){
			ResultPrompt rp = new ResultPrompt();
			try {
				
				boolean b = warehouseService.updateWarehouse(WarehouseConverter.convert2Do(warehouseVo));
				
				if(b) {	
					 rp.setStatusCode("200");
					 rp.setMessage("操作成功！");
					 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
					 rp.setNavTabId("warehouse/list"); // 要刷新的tab页id
				 } else {
					 rp.setStatusCode("300");
					 rp.setMessage("操作失败！");
					 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
					 rp.setNavTabId("warehouse/list"); // 要刷新的tab页id
				 }
			} catch (Exception e) {
				e.printStackTrace();
			    rp.setStatusCode("300");
		    	rp.setMessage("操作失败！");
		    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
		    	rp.setNavTabId("warehouse/list"); // 要刷新的tab页id
			}
			return rp;
	}

	@Override
	public ResultPrompt deleteWarehouse(Integer id) {
		ResultPrompt rp = new ResultPrompt();
		try {
			
			//验证名称是否存在，不能重复
			
			Boolean  b = warehouseService.deleteWarehouse(id);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setNavTabId("warehouse/list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setNavTabId("warehouse/list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setNavTabId("warehouse/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public WarehouseVo queryWarehouseById(Integer id) {
		return WarehouseConverter.convert2Vo(warehouseService.queryWarehouseById(id));
	}


	@Override
	public List<WarehouseVo> queryWarehouses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageData<WarehouseVo> pageQueryWarehouse(WarehouseVo p) {
		PageData<WarehouseVo> pageVo = null;
        try {
        	Warehouse pc = (Warehouse) WarehouseConverter.convert2Do(p);  
            PageParam<Warehouse> page = new PageParam<Warehouse>();
            page.setP(pc);
            page.setPageNo(p.getPageNum());
            page.setPageSize(p.getNumPerPage());
            PageData<Warehouse> pd = warehouseService.pageQueryWarehouse(page);

            List<Warehouse> list = pd.getPageData();
            List<WarehouseVo> listvo = new ArrayList<>();
            if (LogicUtil.isNotNull(list)) {
                for (Warehouse pcl : list) {
                    listvo.add((WarehouseVo) WarehouseConverter.convert2Vo(pcl));
                }
            }
            pageVo = new PageData<WarehouseVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
        } catch (Exception e){
        	e.printStackTrace();
            log.debug("pageQueryWarehouse exception=>" + e);
        }
        return pageVo;
	}

}
