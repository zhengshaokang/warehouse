package com.hys.mgt.view.product.component;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.vo.ProductUnitVo;

public interface IProductUnitComp {
	public ResultPrompt addProductUnit(ProductUnitVo productUnitVo);
	public ResultPrompt updateProductUnit(ProductUnitVo productUnitVo);
	public ResultPrompt deleteProductUnit(Integer id,Integer creator);
	public ProductUnitVo queryProductUnitById(Integer id);
	public List<ProductUnitVo> queryProductUnitByParentId(Integer parentId);
	public List<ProductUnitVo> queryProductUnits();
	public List<ProductUnitVo> queryProductUnitOne();
	public PageData<ProductUnitVo> pageQueryProductUnit(ProductUnitVo p);
}
