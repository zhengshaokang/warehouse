package com.hys.dal.component.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.product.IBrandDalComp;
import com.hys.dal.db.product.IBrandDao;
import com.hys.model.product.Brand;

@Component
public class BrandDalCompImpl implements IBrandDalComp {
	
	@Autowired
	private IBrandDao brandDao;

	@Override
	public Integer addBrand(Brand brand) {
		brandDao.addBrand(brand);
		return brand.getId();
	}

	@Override
	public boolean updateBrand(Brand brand) {
		return JdbcUtil.isSuccess(brandDao.updateBrand(brand));
	}

	@Override
	public boolean deleteBrand(Integer id,Integer creator) {
		return JdbcUtil.isSuccess(brandDao.deleteBrand(id,creator));
	}

	@Override
	public Brand queryBrandById(Integer id) {
		return brandDao.queryBrandById(id);
	}


	@Override
	public List<Brand> queryBrands() {
		return brandDao.queryBrands();
	}
	
	@Override
	public List<Brand> queryBrandsByCreator(Integer creator) {
		return brandDao.queryBrandsByCreator(creator);
	}

	@Override
	public PageData<Brand> pageQueryBrand(PageParam<Brand> page) {
		 List<Brand> pcs = brandDao.pageQueryBrand(page);
         return new PageData<Brand>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), pcs);
	}

	@Override
	public Brand queryBrandByName(String name,Integer creator) {
		return brandDao.queryBrandByName(name,creator);
	}

}
