package com.hys.dal.component.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.product.Product;

public interface IProductDalComp {
	public Integer addProduct(Product product);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(Integer id,Integer creator);
	public Product queryProductById(Integer id);
	public Product queryProductByName(String name,Integer creator);
	public Product queryProductByCodeAndMaturityDate(String code,String maturityDate,Integer creator);
	public List<Product> queryProducts();
	public PageData<Product> pageQueryProduct(PageParam<Product> page);
}
