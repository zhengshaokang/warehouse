package com.hys.dal.component.product;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.product.ProductClassification;

public interface IProductClassificationDalComp {
	public Integer addProductClassification(ProductClassification productClassification);
	public boolean updateProductClassification(ProductClassification productClassification);
	public boolean deleteProductClassification(Integer id,Integer creator);
	public ProductClassification queryProductClassificationById(Integer id);
	public List<ProductClassification> queryProductClassificationByParentId(Integer parentId);
	public List<ProductClassification> queryProductClassificationByParentIdAndCreator(Integer parentId,Integer creator);
	public List<ProductClassification> queryProductClassifications();
	public List<ProductClassification> queryProductClassificationsByCreator(Integer creator);
	public List<ProductClassification> queryProductClassificationOne();
	public List<ProductClassification> queryProductClassificationOneByCreator(Integer creator);
	public PageData<ProductClassification> pageQueryProductClassification(PageParam<ProductClassification> page);
	public ProductClassification queryProductClassificationByName(String name,Integer creator);
}
