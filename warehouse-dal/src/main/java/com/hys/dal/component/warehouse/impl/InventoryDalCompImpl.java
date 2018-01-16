package com.hys.dal.component.warehouse.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.dal.component.warehouse.IInventoryDalComp;
import com.hys.dal.db.warehouse.IInventoryDao;
import com.hys.model.warehouse.Inventory;

@Component
public class InventoryDalCompImpl implements IInventoryDalComp{
	@Autowired
	private IInventoryDao inventoryDao;

	@Override
	public boolean addInventory(Inventory inventory) {
		return JdbcUtil.isSuccess(inventoryDao.addInventory(inventory));
	}


	@Override
	public boolean updateInventory(Inventory inventory) {
		
		return JdbcUtil.isSuccess(inventoryDao.updateInventory(inventory));
	}

	@Override
	public List<Inventory> queryInventoryByProductId(Integer productId) {
		return inventoryDao.queryInventoryByProductId(productId);
	}

	@Override
	public Inventory queryInventoryByBatchNo(String batchNo) {
		return inventoryDao.queryInventoryByBatchNo(batchNo);
	}


}
