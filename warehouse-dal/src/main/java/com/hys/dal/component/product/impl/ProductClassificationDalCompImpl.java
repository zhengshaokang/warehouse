package com.hys.dal.component.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.product.IProductClassificationDalComp;
import com.hys.dal.db.product.IProductClassificationDao;
import com.hys.model.product.ProductClassification;

@Component
public class ProductClassificationDalCompImpl implements IProductClassificationDalComp {
	
	@Autowired
	private IProductClassificationDao productClassificationDao;

	@Override
	public Integer addProductClassification(ProductClassification productClassification) {
		productClassificationDao.addProductClassification(productClassification);
		return productClassification.getId();
	}

	@Override
	public boolean updateProductClassification(ProductClassification productClassification) {
		return JdbcUtil.isSuccess(productClassificationDao.updateProductClassification(productClassification));
	}

	@Override
	public boolean deleteProductClassification(Integer id,Integer creator) {
		return JdbcUtil.isSuccess(productClassificationDao.deleteProductClassification(id,creator));
	}

	@Override
	public ProductClassification queryProductClassificationById(Integer id) {
		return productClassificationDao.queryProductClassificationById(id);
	}

	@Override
	public List<ProductClassification> queryProductClassificationByParentId(Integer parentId) {
		return productClassificationDao.queryProductClassificationByParentId(parentId);
	}

	@Override
	public List<ProductClassification> queryProductClassifications() {
		return productClassificationDao.queryProductClassifications();
	}

	@Override
	public List<ProductClassification> queryProductClassificationOne() {
		return productClassificationDao.queryProductClassificationOne();
	}

	@Override
	public PageData<ProductClassification> pageQueryProductClassification(PageParam<ProductClassification> page) {
		 List<ProductClassification> pcs = productClassificationDao.pageQueryProductClassification(page);
         return new PageData<ProductClassification>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), pcs);
	}

	@Override
	public ProductClassification queryProductClassificationByName(String name,Integer creator) {
		return productClassificationDao.queryProductClassificationByName(name,creator);
	}

	@Override
	public List<ProductClassification> queryProductClassificationByParentIdAndCreator(Integer parentId, Integer creator) {
		return productClassificationDao.queryProductClassificationByParentIdAndCreator(parentId, creator);
	}

	@Override
	public List<ProductClassification> queryProductClassificationOneByCreator(
			Integer creator) {
		return productClassificationDao.queryProductClassificationOneByCreator(creator);
	}

	@Override
	public List<ProductClassification> queryProductClassificationsByCreator(
			Integer creator) {
		return productClassificationDao.queryProductClassificationsByCreator(creator);
	}

}
