package com.hys.mgt.view.product.component;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.vo.ProductClassificationVo;

public interface IProductClassificationComp {
	public ResultPrompt addProductClassification(ProductClassificationVo productClassificationVo);
	public ResultPrompt updateProductClassification(ProductClassificationVo productClassificationVo);
	public ResultPrompt deleteProductClassification(Integer id,Integer creator);
	public ProductClassificationVo queryProductClassificationById(Integer id);
	public List<ProductClassificationVo> queryProductClassificationByParentId(Integer parentId);
	public List<ProductClassificationVo> queryProductClassifications();
	public List<ProductClassificationVo> queryProductClassificationOne();
	public PageData<ProductClassificationVo> pageQueryProductClassification(ProductClassificationVo p);
}
