package com.hys.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.product.IProductInPriceDalComp;
import com.hys.model.product.ProductInPrice;
import com.hys.service.product.IProductInPriceService;

@Service
public class ProductInPriceServiceImpl implements IProductInPriceService {

	@Autowired
	private IProductInPriceDalComp productInPriceDalComp;
	
	@Override
	public boolean addProductInPrice(ProductInPrice productInPrice) {
		if(null == productInPrice){
			return false;
		}
		Integer id = productInPriceDalComp.addProductInPrice(productInPrice);
		if(id != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<ProductInPrice> queryProductInPriceByProductId(Integer productId) {
		if(productId == null) {
			return null;
		}
		return productInPriceDalComp.queryProductInPriceByProductId(productId);
	}

	@Override
	public PageData<ProductInPrice> pageQueryProductInPrice(
			PageParam<ProductInPrice> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return productInPriceDalComp.pageQueryProductInPrice(page);
	}

	@Override
	public ProductInPrice queryProductInPriceByProductIdAndDate(Integer productId, String inDate) {
		if(productId == null || inDate == null) {
			return null;
		}
		return productInPriceDalComp.queryProductInPriceByProductIdAndDate(productId, inDate);
	}

	@Override
	public boolean updateProductInPrice(ProductInPrice productInPrice) {
		if(null == productInPrice){
			return false;
		}
		return productInPriceDalComp.updateProductInPrice(productInPrice);
	}

}
