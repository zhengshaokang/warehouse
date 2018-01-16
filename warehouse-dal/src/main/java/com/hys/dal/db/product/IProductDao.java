package com.hys.dal.db.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.product.Product;

public interface IProductDao {
	public int addProduct(Product Product);
	public int updateProduct(Product Product);
	public int deleteProduct(@Param("id") Integer id,@Param("creator") Integer creator);
	public Product queryProductById(Integer id);
	public List<Product> queryProductByName(@Param("name")String name,@Param("creator") Integer creator);
	public Product queryProductByCodeAndMaturityDate(@Param("code") String code,@Param("maturityDate") String maturityDate,@Param("creator") Integer creator);
	public List<Product> queryProducts();
	public List<Product> pageQueryProduct(PageParam<Product> page);
}
