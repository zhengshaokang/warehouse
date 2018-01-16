package com.hys.dal.component.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.product.ProductUnit;

public interface IProductUnitDalComp {
	public Integer addProductUnit(ProductUnit productUnit);
	public boolean updateProductUnit(ProductUnit productUnit);
	public boolean deleteProductUnit(Integer id,Integer creator);
	public ProductUnit queryProductUnitById(Integer id);
	public List<ProductUnit> queryProductUnits();
	public List<ProductUnit> queryProductUnitsByCreator(Integer creator);
	public PageData<ProductUnit> pageQueryProductUnit(PageParam<ProductUnit> page);
	public ProductUnit queryProductUnitByName(String name,Integer creator);
}
