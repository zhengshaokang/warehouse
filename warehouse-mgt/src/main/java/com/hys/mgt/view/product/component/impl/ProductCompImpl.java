package com.hys.mgt.view.product.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.commons.util.SysRemark;
import com.hys.dal.select.Users;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.common.ProductConverter;
import com.hys.mgt.view.product.component.IProductComp;
import com.hys.mgt.view.product.vo.ProductVo;
import com.hys.model.product.Product;
import com.hys.service.product.IProductService;

@Component
public class ProductCompImpl implements IProductComp{
	
	private final static Logger log = LogProxy.getLogger(ProductCompImpl.class);
	@Autowired
	private IProductService productService;
	@Autowired 
	private Users users;

	@Override
	public ResultPrompt addProduct(ProductVo product) {
		ResultPrompt rp = new ResultPrompt();
		try {
			
			Product cp = productService.queryProductBySku(product.getSku());
			
			if(LogicUtil.isNotNull(cp)) {
				 rp.setStatusCode("300");
				 rp.setMessage("sku已经存在，请重新填写！");
				 // rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 // rp.setNavTabId("product/list"); // 要刷新的tab页id
				 return rp;
			} 
			
			Product p = ProductConverter.convert2Do(product);
			p.setSysRemark(SysRemark.append("", "于"+DateUtil.getCurrentDateTimeAsString()+"由"+users.getOptions().get(product.getCreator().toString())+"入库，数量为:"+p.getInventoryAvailable()));
			boolean b = productService.addProduct(p);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
		    	 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	        rp.setNavTabId("product/list"); // 要刷新的tab页id
		}
		
		return rp;
	}

	@Override
	public ResultPrompt updateProduct(ProductVo product) {
		ResultPrompt rp = new ResultPrompt();
		try {
			
			Product cp = productService.queryProductBySku(product.getSku());
			
			if(LogicUtil.isNotNull(cp) && cp.getId() != product.getId()) { //要排除查询到的是当前的商品
				 rp.setStatusCode("300");
				 rp.setMessage("sku已经存在，请重新填写！");
				 // rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 // rp.setNavTabId("product/list"); // 要刷新的tab页id
				 return rp;
			} 
			Product productold = productService.queryProductById(product.getId());
			
			product.setStatus(productold.getStatus());
			product.setCreator(productold.getCreator());
			if(!productold.getInventoryAvailable().equals(product.getInventoryAvailable())) { 
				 rp.setStatusCode("300");
				 rp.setMessage("不能直接修改库存，只能通过出库或者入库操作完成！");
				 return rp;
			}
			
			//当修改剩余库存后，需要对总库存和盘点库存重新计算，保证数据准确
//			if(productold.getInventoryAvailable() > product.getInventoryAvailable()) {
//				int newInventoryAvailable = productold.getInventoryAvailable() - product.getInventoryAvailable();
//				int newInventoryCheck = productold.getInventoryCheck() - newInventoryAvailable;
//				int newInventoryTotal = productold.getInventoryTotal() - newInventoryAvailable;
//				product.setInventoryCheck(newInventoryCheck);
//				product.setInventoryTotal(newInventoryTotal);
//			} else if(productold.getInventoryAvailable() < product.getInventoryAvailable()) {
//				int newInventoryAvailable = product.getInventoryAvailable() - productold.getInventoryAvailable();
//				int newInventoryCheck = productold.getInventoryCheck() + newInventoryAvailable;
//				int newInventoryTotal = productold.getInventoryTotal() + newInventoryAvailable;
//				product.setInventoryCheck(newInventoryCheck);
//				product.setInventoryTotal(newInventoryTotal);
//			} else {
				product.setInventoryCheck(productold.getInventoryCheck());
				product.setInventoryTotal(productold.getInventoryTotal());
//			}
			
			product.setCheckDate(productold.getCheckDate());
			product.setSysRemark(SysRemark.append(productold.getSysRemark(), "于"+DateUtil.getCurrentDateTimeAsString()+"由"+users.getOptions().get(product.getUpdator().toString())+"修改！"));
			
			Product p = ProductConverter.convert2Do(product);
			
			boolean b = productService.updateProduct(p);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
		    	 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	        rp.setNavTabId("product/list"); // 要刷新的tab页id
		}
		
		return rp;
	}

	@Override
	public ResultPrompt deleteProduct(Integer id) {
		ResultPrompt rp = new ResultPrompt();
		try {
			boolean b = productService.deleteProduct(id);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
		    rp.setMessage("操作失败！");
	        rp.setNavTabId("product/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ProductVo queryProductById(Integer id) {
		Product cp = productService.queryProductById(id);
		return ProductConverter.convert2Vo(cp);
	}

	@Override
	public ProductVo queryProductBySku(String sku) {
		Product cp = productService.queryProductBySku(sku);
		return ProductConverter.convert2Vo(cp);
	}

	@Override
	public List<ProductVo> queryProducts() {
		List<Product> pl = productService.queryProducts();
		List<ProductVo> vl = new ArrayList<ProductVo>();
		if(LogicUtil.isNotNullAndEmpty(pl)){
			for (Product product : pl) {
				vl.add(ProductConverter.convert2Vo(product));
			}
		}
		return vl;
	}

	@Override
	public PageData<ProductVo> pageQueryProduct(ProductVo v) {
		 PageData<ProductVo> pageVo = null;
	        try {
	            Product p = (Product) ProductConverter.convert2Do(v);  
	            PageParam<Product> page = new PageParam<Product>();
	            page.setP(p);
	            page.setPageNo(v.getPageNum());
	            page.setPageSize(v.getNumPerPage());
	            PageData<Product> pd = productService.pageQueryProduct(page);

	            List<Product> list = pd.getPageData();
	            List<ProductVo> listvo = new ArrayList<>();
	            if (LogicUtil.isNotNull(list)) {
	                for (Product product : list) {
	                    listvo.add((ProductVo) ProductConverter.convert2Vo(product));
	                }
	            }
	            pageVo = new PageData<ProductVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
	        } catch (Exception e){
	        	e.printStackTrace();
	            log.debug("pageQueryProduct exception=>" + e);
	        }
	        return pageVo;
	}

	@Override
	public ResultPrompt productInSubmit(Integer productId, Integer qty, Integer recordType ,Integer operator) {
		ResultPrompt rp = new ResultPrompt();
		try {
			Product p = productService.queryProductById(productId);
			boolean b = productService.productInSubmit(p, qty, recordType,operator);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
		    rp.setMessage("操作失败！");
	        rp.setNavTabId("product/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt productOutSubmit(Integer productId, Integer qty, Integer recordType, Integer operator) {
		ResultPrompt rp = new ResultPrompt();
		try {
			Product p = productService.queryProductById(productId);
			boolean b = productService.productOutSubmit(p, qty, recordType,operator);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
		    rp.setMessage("操作失败！");
	        rp.setNavTabId("product/list"); // 要刷新的tab页id
		}
		return rp;
	}

}
