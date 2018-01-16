package com.hys.mgt.view.product.component;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.vo.ProductInPriceVo;
import com.hys.mgt.view.product.vo.ProductInVo;
import com.hys.mgt.view.product.vo.ProductOutVo;
import com.hys.mgt.view.product.vo.ProductVo;
import com.hys.mgt.view.warehouse.vo.InventoryVo;

public interface IProductComp {
	public ResultPrompt addProduct(ProductVo product);
	public ResultPrompt updateProduct(ProductVo product);
	public ResultPrompt deleteProduct(Integer id,Integer creator);
	public ResultPrompt productOfflineOrUp(Integer id,Integer status);
	public ProductVo queryProductById(Integer id);
	public ProductVo queryProductByCode(String code,Integer creator);
	public List<ProductVo> queryProducts();
	public PageData<ProductVo> pageQueryProduct(ProductVo p);
	public ResultPrompt productInSubmit(ProductInVo productInVo,Integer operator); 
	public ResultPrompt productOutSubmit(ProductOutVo productOutVo,Integer operator); 
//	public ResultPrompt productOutSubmit(String sku, Integer qty,Integer recordType,Integer operator); 
	public List<InventoryVo> queryInventoryByProductId(Integer productId);
	public List<ProductInPriceVo> queryPriceByProductId(Integer productId);
}
