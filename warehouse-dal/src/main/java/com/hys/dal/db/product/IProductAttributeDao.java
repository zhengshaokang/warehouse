package com.hys.dal.db.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.model.product.ProductAttribute;

public interface IProductAttributeDao {
	public int addProductAttribute(ProductAttribute productAttribute);
	public int updateProductAttribute(ProductAttribute productAttribute);
	public int deleteProductAttribute(@Param("id") Integer id);
	public int deleteProductAttributeByProductId(@Param("productId") Integer productId);
	public ProductAttribute queryProductAttributeById(@Param("id") Integer id);
	public List<ProductAttribute> queryProductAttributeByProductId(@Param("productId") Integer productId);
	public List<ProductAttribute> queryProductAttributes();
}
