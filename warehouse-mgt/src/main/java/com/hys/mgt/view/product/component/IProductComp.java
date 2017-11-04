package com.hys.mgt.view.product.component;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.vo.ProductVo;

public interface IProductComp {
	public ResultPrompt addProduct(ProductVo product);
	public ResultPrompt updateProduct(ProductVo product);
	public ResultPrompt deleteProduct(Integer id);
	public ProductVo queryProductById(Integer id);
	public ProductVo queryProductBySku(String sku);
	public List<ProductVo> queryProducts();
	public PageData<ProductVo> pageQueryProduct(ProductVo p);
	public ResultPrompt productInSubmit(Integer productId, Integer qty,Integer recordType,Integer operator); 
	public ResultPrompt productOutSubmit(Integer productId, Integer qty,Integer recordType,Integer operator); 
}
