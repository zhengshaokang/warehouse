package com.hys.mgt.view.product.component;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.vo.SupplierVo;

public interface ISupplierComp {
	public ResultPrompt addSupplier(SupplierVo supplierVo);
	public ResultPrompt updateSupplier(SupplierVo supplierVo);
	public ResultPrompt deleteSupplier(Integer id,Integer creator);
	public SupplierVo querySupplierById(Integer id);
	public List<SupplierVo> querySuppliers();
	public PageData<SupplierVo> pageQuerySupplier(SupplierVo p);
}
