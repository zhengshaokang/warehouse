package com.hys.dal.component.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.product.IProductSpecificationsDalComp;
import com.hys.dal.db.product.IProductSpecificationsDao;
import com.hys.model.product.ProductSpecifications;

@Component
public class ProductSpecificationsDalCompImpl implements IProductSpecificationsDalComp {
	
	@Autowired
	private IProductSpecificationsDao productSpecificationsDao;

	@Override
	public Integer addProductSpecifications(ProductSpecifications productSpecifications) {
		productSpecificationsDao.addProductSpecifications(productSpecifications);
		return productSpecifications.getId();
	}

	@Override
	public boolean updateProductSpecifications(ProductSpecifications productSpecifications) {
		return JdbcUtil.isSuccess(productSpecificationsDao.updateProductSpecifications(productSpecifications));
	}

	@Override
	public boolean deleteProductSpecifications(Integer id,Integer creator) {
		return JdbcUtil.isSuccess(productSpecificationsDao.deleteProductSpecifications(id,creator));
	}

	@Override
	public ProductSpecifications queryProductSpecificationsById(Integer id) {
		return productSpecificationsDao.queryProductSpecificationsById(id);
	}

	@Override
	public List<ProductSpecifications> queryProductSpecificationsByParentId(Integer parentId) {
		return productSpecificationsDao.queryProductSpecificationsByParentId(parentId);
	}

	@Override
	public List<ProductSpecifications> queryProductSpecificationss() {
		return productSpecificationsDao.queryProductSpecificationss();
	}

	@Override
	public List<ProductSpecifications> queryProductSpecificationsOne() {
		return productSpecificationsDao.queryProductSpecificationsOne();
	}

	@Override
	public PageData<ProductSpecifications> pageQueryProductSpecifications(PageParam<ProductSpecifications> page) {
		 List<ProductSpecifications> pcs = productSpecificationsDao.pageQueryProductSpecifications(page);
         return new PageData<ProductSpecifications>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), pcs);
	}

	@Override
	public ProductSpecifications queryProductSpecificationsByName(String name,Integer creator) {
		return productSpecificationsDao.queryProductSpecificationsByName(name,creator);
	}

	@Override
	public List<ProductSpecifications> queryProductSpecificationsByParentIdAndCreator(
			Integer parentId, Integer creator) {
		return productSpecificationsDao.queryProductSpecificationsByParentIdAndCreator(parentId, creator);
	}

	@Override
	public List<ProductSpecifications> queryProductSpecificationsByCreator(
			Integer creator) {
		return productSpecificationsDao.queryProductSpecificationssByCreator(creator);
	}

	@Override
	public List<ProductSpecifications> queryProductSpecificationsOneByCreator(Integer creator) {
		return productSpecificationsDao.queryProductSpecificationsOneByCreator(creator);
	}

}
