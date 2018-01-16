package com.hys.dal.component.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.product.IProductUnitDalComp;
import com.hys.dal.db.product.IProductUnitDao;
import com.hys.model.product.ProductUnit;

@Component
public class ProductUnitDalCompImpl implements IProductUnitDalComp {
	
	@Autowired
	private IProductUnitDao productUnitDao;

	@Override
	public Integer addProductUnit(ProductUnit productSpecifications) {
		productUnitDao.addProductUnit(productSpecifications);
		return productSpecifications.getId();
	}

	@Override
	public boolean updateProductUnit(ProductUnit productSpecifications) {
		return JdbcUtil.isSuccess(productUnitDao.updateProductUnit(productSpecifications));
	}

	@Override
	public boolean deleteProductUnit(Integer id,Integer creator) {
		return JdbcUtil.isSuccess(productUnitDao.deleteProductUnit(id,creator));
	}

	@Override
	public ProductUnit queryProductUnitById(Integer id) {
		return productUnitDao.queryProductUnitById(id);
	}


	@Override
	public List<ProductUnit> queryProductUnits() {
		return productUnitDao.queryProductUnits();
	}


	@Override
	public PageData<ProductUnit> pageQueryProductUnit(PageParam<ProductUnit> page) {
		 List<ProductUnit> pcs = productUnitDao.pageQueryProductUnit(page);
         return new PageData<ProductUnit>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), pcs);
	}

	@Override
	public ProductUnit queryProductUnitByName(String name,Integer creator) {
		return productUnitDao.queryProductUnitByName(name,creator);
	}

	@Override
	public List<ProductUnit> queryProductUnitsByCreator(Integer creator) {
		return productUnitDao.queryProductUnitsByCreator(creator);
	}

}
