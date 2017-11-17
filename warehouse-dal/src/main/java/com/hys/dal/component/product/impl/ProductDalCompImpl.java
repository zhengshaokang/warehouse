package com.hys.dal.component.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.product.IProductDalComp;
import com.hys.dal.db.product.IProductDao;
import com.hys.model.product.Product;

@Component
public class ProductDalCompImpl implements IProductDalComp {
	
	@Autowired
	private IProductDao productDao;

	@Override
	public Integer addProduct(Product product) {
		productDao.addProduct(product);
		return product.getId();
	}

	@Override
	public boolean updateProduct(Product product) {
		return JdbcUtil.isSuccess(productDao.updateProduct(product));
	}

	@Override
	public boolean deleteProduct(Integer id) {
		return JdbcUtil.isSuccess(productDao.deleteProduct(id));
	}

	@Override
	public Product queryProductById(Integer id) {
		return productDao.queryProductById(id);
	}

	@Override
	public List<Product> queryProducts() {
		return productDao.queryProducts();
	}

	@Override
	public PageData<Product> pageQueryProduct(PageParam<Product> page) {
		 List<Product> users = productDao.pageQueryProduct(page);
         return new PageData<Product>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), users);
	}

	@Override
	public Product queryProductBySku(String sku) {
		List<Product> list = productDao.queryProductBySku(sku);
		if(null != list && list.size()>0){
			return list.get(0);
		}else{ 
			return null;
		}
	}

	@Override
	public Product queryProductBySkuAndProductionDate(String sku,
			String productionDate) {
		return productDao.queryProductBySkuAndProductionDate(sku, productionDate);
	}

}
