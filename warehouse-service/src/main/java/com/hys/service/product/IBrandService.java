package com.hys.service.product;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.product.Brand;

public interface IBrandService {
	public boolean addBrand(Brand brand);
	public boolean updateBrand(Brand brand);
	public boolean deleteBrand(Integer id,Integer creator);
	public Brand queryBrandById(Integer id);
	public List<Brand> queryBrands();
	public PageData<Brand> pageQueryBrand(PageParam<Brand> page);
	public Brand queryBrandByName(String name,Integer creator);
}
