package com.hys.dal.component.product;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.product.ProductAttribute;

public interface IProductAttributeDalComp {
	public boolean addProductAttribute(ProductAttribute productAttribute);
	public boolean updateProductAttribute(ProductAttribute productAttribute);
	public boolean deleteProductAttribute(Integer id);
	public boolean deleteProductAttributeByProductId(Integer productId);
	public ProductAttribute queryProductAttributeById(Integer id);
	public List<ProductAttribute> queryProductAttributeByProductId(Integer productId);
	public List<ProductAttribute> queryProductAttributes();
}
