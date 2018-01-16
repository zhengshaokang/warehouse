package com.hys.mgt.view.product.component;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.vo.ProductSpecificationsVo;

public interface IProductSpecificationsComp {
	public ResultPrompt addProductSpecifications(ProductSpecificationsVo ProductSpecificationsVo);
	public ResultPrompt updateProductSpecifications(ProductSpecificationsVo ProductSpecificationsVo);
	public ResultPrompt deleteProductSpecifications(Integer id,Integer creator);
	public ProductSpecificationsVo queryProductSpecificationsById(Integer id);
	public List<ProductSpecificationsVo> queryProductSpecificationsByParentId(Integer parentId);
	public List<ProductSpecificationsVo> queryProductSpecificationss();
	public List<ProductSpecificationsVo> queryProductSpecificationsOne();
	public PageData<ProductSpecificationsVo> pageQueryProductSpecifications(ProductSpecificationsVo p);
}
