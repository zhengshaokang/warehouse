package com.hys.service.product;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.product.Product;

public interface IProductService {
	public boolean addProduct(Product product);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(Integer id,Integer creator);
	public boolean productOfflineOrUp(Integer id,Integer status);
	public Product queryProductById(Integer id);
	public Product queryProductByName(String name,Integer creator);
	public Product queryProductByCodeAndMaturityDate(String code,String maturityDate,Integer creator);
	public List<Product> queryProducts();
	public PageData<Product> pageQueryProduct(PageParam<Product> page);
	public boolean productInSubmit(Product product, Integer qty,Integer recordType,Integer operator); 
	public boolean productOutSubmit(Product product, Integer qty,Integer recordType,Integer operator); 
}
