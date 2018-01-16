package com.hys.service.warehouse;


import java.util.List;

import com.hys.model.warehouse.Inventory;

public interface IInventoryService {
	public boolean addInventory(Inventory inventory);
	public boolean updateInventory(Inventory inventory);
	public List<Inventory> queryInventoryByProductId(Integer productId);
	public Inventory queryInventoryByBatchNo(String batchNo);
}
