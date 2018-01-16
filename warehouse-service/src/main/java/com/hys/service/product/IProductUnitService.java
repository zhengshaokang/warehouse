package com.hys.service.product;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.product.ProductUnit;

public interface IProductUnitService {
	public boolean addProductUnit(ProductUnit productUnit);
	public boolean updateProductUnit(ProductUnit productUnit);
	public boolean deleteProductUnit(Integer id,Integer creator);
	public ProductUnit queryProductUnitById(Integer id);
	public List<ProductUnit> queryProductUnits();
	public PageData<ProductUnit> pageQueryProductUnit(PageParam<ProductUnit> page);
	public ProductUnit queryProductUnitByName(String name,Integer creator);
}
