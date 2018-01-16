package com.hys.dal.db.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.product.Brand;

public interface IBrandDao {
	public int addBrand(Brand brand);
	public int updateBrand(Brand brand);
	public int deleteBrand(@Param("id") Integer id,@Param("creator") Integer creator);
	public Brand queryBrandById(Integer id);
	public List<Brand> queryBrands();
	public List<Brand> queryBrandsByCreator(@Param("creator") Integer creator);
	public List<Brand> pageQueryBrand(PageParam<Brand> page);
	public Brand queryBrandByName(@Param("name") String name,@Param("creator") Integer creator);
}
