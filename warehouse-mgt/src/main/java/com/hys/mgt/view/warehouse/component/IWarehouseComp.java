package com.hys.mgt.view.warehouse.component;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.warehouse.vo.WarehouseVo;

public interface IWarehouseComp {
	public ResultPrompt addWarehouse(WarehouseVo warehouseVo);
	public ResultPrompt updateWarehouse(WarehouseVo warehouseVo);
	public ResultPrompt deleteWarehouse(Integer id);
	public WarehouseVo queryWarehouseById(Integer id);
	public List<WarehouseVo> queryWarehouses();
	public PageData<WarehouseVo> pageQueryWarehouse(WarehouseVo p);
}
