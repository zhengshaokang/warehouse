package com.hys.service.warehouse.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.warehouse.IInventoryDalComp;
import com.hys.dal.component.warehouse.IWarehouseDalComp;
import com.hys.model.warehouse.Inventory;
import com.hys.model.warehouse.Warehouse;
import com.hys.service.warehouse.IInventoryService;
import com.hys.service.warehouse.IWarehouseService;

@Service
public class InventoryServiceImpl implements IInventoryService {

	@Autowired
	private IInventoryDalComp inventoryDalComp;
	
	@Override
	public boolean addInventory(Inventory inventory) {
		if(null == inventory){
			return false;
		}
		return inventoryDalComp.addInventory(inventory);
	}

	@Override
	public boolean updateInventory(Inventory inventory) {
		if(null == inventory){
			return false;
		}
		return inventoryDalComp.updateInventory(inventory);
	}

	@Override
	public List<Inventory> queryInventoryByProductId(Integer productId) {
		if(null == productId){
			return null;
		}
		return inventoryDalComp.queryInventoryByProductId(productId);
	}

	@Override
	public Inventory queryInventoryByBatchNo(String batchNo) {
		if(null == batchNo){
			return null;
		}
		return inventoryDalComp.queryInventoryByBatchNo(batchNo);
	}

}
