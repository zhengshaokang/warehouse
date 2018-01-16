package com.hys.service.product;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.product.ProductSpecifications;

public interface IProductSpecificationsService {
	public boolean addProductSpecifications(ProductSpecifications ProductSpecifications);
	public boolean updateProductSpecifications(ProductSpecifications ProductSpecifications);
	public boolean deleteProductSpecifications(Integer id,Integer creator);
	public ProductSpecifications queryProductSpecificationsById(Integer id);
	public List<ProductSpecifications> queryProductSpecificationsByParentId(Integer parentId);
	public List<ProductSpecifications> queryProductSpecificationss();
	public List<ProductSpecifications> queryProductSpecificationsOne();
	public PageData<ProductSpecifications> pageQueryProductSpecifications(PageParam<ProductSpecifications> page);
	public ProductSpecifications queryProductSpecificationsByName(String name,Integer creator);
}
