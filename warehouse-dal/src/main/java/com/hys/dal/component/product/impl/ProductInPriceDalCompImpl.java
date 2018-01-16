package com.hys.dal.component.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.product.IProductInPriceDalComp;
import com.hys.dal.db.product.IProductInPriceDao;
import com.hys.model.product.ProductInPrice;

@Component
public class ProductInPriceDalCompImpl implements IProductInPriceDalComp {
	
	@Autowired
	private IProductInPriceDao productInPriceDao;

	@Override
	public Integer addProductInPrice(ProductInPrice productInPrice) {
		productInPriceDao.addProductInPrice(productInPrice);
		return productInPrice.getId();
	}

	@Override
	public List<ProductInPrice> queryProductInPriceByProductId(Integer productId) {
		return productInPriceDao.queryProductInPriceByProductId(productId);
	}

	@Override
	public PageData<ProductInPrice> pageQueryProductInPrice(PageParam<ProductInPrice> page) {
		 List<ProductInPrice> pcs = productInPriceDao.pageQueryProductInPrice(page);
         return new PageData<ProductInPrice>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), pcs);
	}

	@Override
	public ProductInPrice queryProductInPriceByProductIdAndDate(Integer productId, String inDate) {
		return productInPriceDao.queryProductInPriceByProductIdAndDate(productId, inDate);
	}

	@Override
	public boolean updateProductInPrice(ProductInPrice productInPrice) {
		return JdbcUtil.isSuccess(productInPriceDao.updateProductInPrice(productInPrice));
	}


}
