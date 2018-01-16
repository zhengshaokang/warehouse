package com.hys.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.product.IProductDalComp;
import com.hys.dal.component.warehouse.IInoutRecordDalComp;
import com.hys.dal.select.Users;
import com.hys.model.product.Product;
import com.hys.service.product.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private IProductDalComp productDalComp;
	@Autowired
	private IInoutRecordDalComp inoutRecordDalComp;
	@Autowired 
	private Users users;

	@Override
	public boolean addProduct(Product product) {
		if(null == product){
			return false;
		}
		Integer id = productDalComp.addProduct(product);
		if(id != null) {
			return true;
		} 
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		if(null == product || null == product.getId()){
			return false;
		}
		
		boolean b2 = productDalComp.updateProduct(product);
		if(b2) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteProduct(Integer id,Integer creator) {
		if(null == id) {
			return false;
		}
		boolean b1 = productDalComp.deleteProduct(id,creator);
		if(b1) {
			return true;
		} 
		return false;
	}

	@Override
	public Product queryProductById(Integer id) {
		if(null == id) {
			return null;
		}
		Product product = productDalComp.queryProductById(id);
		return product;
	}

	@Override
	public Product queryProductByName(String name,Integer creator) {
		if(null == name || "".equals(name)) {
			return null;
		}
		Product product = productDalComp.queryProductByName(name,creator);
		return product;
	}

	@Override
	public List<Product> queryProducts() {
		List<Product> productlist = productDalComp.queryProducts();
		return productlist;
	}

	@Override
	public PageData<Product> pageQueryProduct(PageParam<Product> page) {
	    if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		PageData<Product> pageDate = productDalComp.pageQueryProduct(page);
		return pageDate;
	}

	@Override
	public boolean productInSubmit(Product product, Integer qty,Integer recordType,Integer operator) {
		
		return false;
	}

	@Override
	public boolean productOutSubmit(Product product, Integer qty, Integer recordType ,Integer operator) {
		return false;
	}

	@Override
	public Product queryProductByCodeAndMaturityDate(String code, String maturityDate,Integer creator) {
		if(null == code || "".equals(code)) {
			return null;
		}
		if(null == maturityDate || "".equals(maturityDate)) {
			return null;
		}
		Product product = productDalComp.queryProductByCodeAndMaturityDate(code, maturityDate,creator);
		return product;
	}

	@Override
	public boolean productOfflineOrUp(Integer id,Integer status) {
		Product p = queryProductById(id);
		p.setStatus(status);
		return updateProduct(p);
	}

}
