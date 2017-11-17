package com.hys.dal.db.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.product.Product;

public interface IProductDao {
	public int addProduct(Product Product);
	public int updateProduct(Product Product);
	public int deleteProduct(Integer id);
	public Product queryProductById(Integer id);
	public List<Product> queryProductBySku(String sku);
	public Product queryProductBySkuAndProductionDate(@Param("sku") String sku,@Param("productionDate") String productionDate);
	public List<Product> queryProducts();
	public List<Product> pageQueryProduct(PageParam<Product> page);
}
