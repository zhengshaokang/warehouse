package com.hys.dal.component.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.dal.component.product.IProductAttributeDalComp;
import com.hys.dal.db.product.IProductAttributeDao;
import com.hys.model.product.ProductAttribute;

@Component
public class ProdcutAttributeDalCompImpl implements IProductAttributeDalComp {
	
	@Autowired
	private IProductAttributeDao productAttributeDao;

	@Override
	public boolean addProductAttribute(ProductAttribute productAttribute) {
		return JdbcUtil.isSuccess(productAttributeDao.addProductAttribute(productAttribute));
	}

	@Override
	public boolean updateProductAttribute(ProductAttribute productAttribute) {
		return JdbcUtil.isSuccess(productAttributeDao.updateProductAttribute(productAttribute));
	}

	@Override
	public boolean deleteProductAttribute(Integer id) {
		return JdbcUtil.isSuccess(productAttributeDao.deleteProductAttribute(id));
	}

	@Override
	public boolean deleteProductAttributeByProductId(Integer productId) {
		return JdbcUtil.isSuccess(productAttributeDao.deleteProductAttributeByProductId(productId));
	}

	@Override
	public ProductAttribute queryProductAttributeById(Integer id) {
		return productAttributeDao.queryProductAttributeById(id);
	}

	@Override
	public List<ProductAttribute> queryProductAttributeByProductId(
			Integer productId) {
		return productAttributeDao.queryProductAttributeByProductId(productId);
	}

	@Override
	public List<ProductAttribute> queryProductAttributes() {
		return productAttributeDao.queryProductAttributes();
	}

}
