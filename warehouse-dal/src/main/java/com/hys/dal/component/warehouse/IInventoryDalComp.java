package com.hys.dal.component.warehouse;


import java.util.List;

import com.hys.model.warehouse.Inventory;

public interface IInventoryDalComp {
	public boolean addInventory(Inventory inventory);
	public boolean updateInventory(Inventory inventory);
	public List<Inventory> queryInventoryByProductId(Integer productId);
	public Inventory queryInventoryByBatchNo(String batchNo);
}
