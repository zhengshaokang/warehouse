package com.hys.dal.component.product;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.product.Product;

public interface IProductDalComp {
	public Integer addProduct(Product product);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(Integer id);
	public Product queryProductById(Integer id);
	public Product queryProductBySku(String sku);
	public List<Product> queryProducts();
	public PageData<Product> pageQueryProduct(PageParam<Product> page);
}
