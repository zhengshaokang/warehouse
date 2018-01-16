package com.hys.dal.component.product;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.product.ProductInPrice;

public interface IProductInPriceDalComp {
	public Integer addProductInPrice(ProductInPrice productInPrice);
	public boolean updateProductInPrice(ProductInPrice productInPrice);
	public PageData<ProductInPrice> pageQueryProductInPrice(PageParam<ProductInPrice> page);
	public List<ProductInPrice> queryProductInPriceByProductId(Integer productId);
	public ProductInPrice queryProductInPriceByProductIdAndDate(Integer productId,String inDate);
}
