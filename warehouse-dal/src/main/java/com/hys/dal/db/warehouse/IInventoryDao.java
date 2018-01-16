package com.hys.dal.db.warehouse;


import java.util.List;

import com.hys.model.warehouse.Inventory;

public interface IInventoryDao {
	public int addInventory(Inventory inventory);
	public int updateInventory(Inventory inventory);
	public List<Inventory> queryInventoryByProductId(Integer productId);
	public Inventory queryInventoryByBatchNo(String batchNo);
}
