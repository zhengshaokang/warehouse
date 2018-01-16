package com.hys.dal.db.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.product.ProductInPrice;

public interface IProductInPriceDao {
	public int addProductInPrice(ProductInPrice productInPrice);
	public int updateProductInPrice(ProductInPrice productInPrice);
	public List<ProductInPrice> pageQueryProductInPrice(PageParam<ProductInPrice> page);
	public List<ProductInPrice> queryProductInPriceByProductId(Integer productId);
	public ProductInPrice queryProductInPriceByProductIdAndDate(@Param("productId") Integer productId,@Param("inDate") String inDate);
}
