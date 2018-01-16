package com.hys.dal.db.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.product.ProductSpecifications;

public interface IProductSpecificationsDao {
	public int addProductSpecifications(ProductSpecifications productSpecifications);
	public int updateProductSpecifications(ProductSpecifications productClassification);
	public int deleteProductSpecifications(@Param("id") Integer id,@Param("creator")Integer creator);
	public ProductSpecifications queryProductSpecificationsById(Integer id);
	public List<ProductSpecifications> queryProductSpecificationsByParentId(Integer parentId);
	public List<ProductSpecifications> queryProductSpecificationss();
	public List<ProductSpecifications> queryProductSpecificationsOne();
	public List<ProductSpecifications> queryProductSpecificationsByParentIdAndCreator(@Param("parentId") Integer parentId,@Param("creator") Integer creator);
	public List<ProductSpecifications> queryProductSpecificationssByCreator(@Param("creator") Integer creator);
	public List<ProductSpecifications> queryProductSpecificationsOneByCreator(@Param("creator") Integer creator);
	public List<ProductSpecifications> pageQueryProductSpecifications(PageParam<ProductSpecifications> page);
	public ProductSpecifications queryProductSpecificationsByName(@Param("name") String name,@Param("creator") Integer creator);
}
