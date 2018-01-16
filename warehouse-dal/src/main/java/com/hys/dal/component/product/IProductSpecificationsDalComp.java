package com.hys.dal.component.product;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.product.ProductSpecifications;

public interface IProductSpecificationsDalComp {
	public Integer addProductSpecifications(ProductSpecifications productSpecifications);
	public boolean updateProductSpecifications(ProductSpecifications productSpecifications);
	public boolean deleteProductSpecifications(Integer id,Integer creator);
	public ProductSpecifications queryProductSpecificationsById(Integer id);
	public List<ProductSpecifications> queryProductSpecificationsByParentId(Integer parentId);
	public List<ProductSpecifications> queryProductSpecificationss();
	public List<ProductSpecifications> queryProductSpecificationsOne();
	public List<ProductSpecifications> queryProductSpecificationsByParentIdAndCreator(Integer parentId,Integer creator);
	public List<ProductSpecifications> queryProductSpecificationsByCreator(Integer creator);
	public List<ProductSpecifications> queryProductSpecificationsOneByCreator(Integer creator);
	public PageData<ProductSpecifications> pageQueryProductSpecifications(PageParam<ProductSpecifications> page);
	public ProductSpecifications queryProductSpecificationsByName(String name,Integer creator);
}
