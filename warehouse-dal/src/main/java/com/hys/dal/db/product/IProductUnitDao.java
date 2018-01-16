package com.hys.dal.db.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.product.ProductUnit;

public interface IProductUnitDao {
	public int addProductUnit(ProductUnit productUnit);
	public int updateProductUnit(ProductUnit productUnit);
	public int deleteProductUnit(@Param("id") Integer id,@Param("creator") Integer creator);
	public ProductUnit queryProductUnitById(Integer id);
	public List<ProductUnit> queryProductUnits();
	public List<ProductUnit> queryProductUnitsByCreator(@Param("creator") Integer creator);
	public List<ProductUnit> pageQueryProductUnit(PageParam<ProductUnit> page);
	public ProductUnit queryProductUnitByName(@Param("name") String name,@Param("creator") Integer creator);
}
