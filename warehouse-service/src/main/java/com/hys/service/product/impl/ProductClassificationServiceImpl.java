package com.hys.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.product.IProductClassificationDalComp;
import com.hys.model.product.ProductClassification;
import com.hys.service.product.IProductClassificationService;

@Service
public class ProductClassificationServiceImpl implements IProductClassificationService {

	@Autowired
	private IProductClassificationDalComp productClassificationDalComp;
	
	@Override
	public boolean addProductClassification(ProductClassification productClassification) {
		if(null == productClassification){
			return false;
		}
		Integer id = productClassificationDalComp.addProductClassification(productClassification);
		if(id != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProductClassification(ProductClassification productClassification) {
		if(null == productClassification){
			return false;
		}
		return productClassificationDalComp.updateProductClassification(productClassification);
	}

	@Override
	public boolean deleteProductClassification(Integer id ,Integer creator) {
		if(id == null) {
			return false;
		}
		return productClassificationDalComp.deleteProductClassification(id ,creator);
	}

	@Override
	public ProductClassification queryProductClassificationById(Integer id) {
		if(id == null) {
			return null;
		}
		return productClassificationDalComp.queryProductClassificationById(id);
	}

	@Override
	public List<ProductClassification> queryProductClassificationByParentId(Integer parentId) {
		if(parentId == null) {
			return null;
		}
		return productClassificationDalComp.queryProductClassificationByParentId(parentId);
	}

	@Override
	public List<ProductClassification> queryProductClassifications() {
		return productClassificationDalComp.queryProductClassifications();
	}

	@Override
	public List<ProductClassification> queryProductClassificationOne() {
		return productClassificationDalComp.queryProductClassificationOne();
	}

	@Override
	public PageData<ProductClassification> pageQueryProductClassification(
			PageParam<ProductClassification> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return productClassificationDalComp.pageQueryProductClassification(page);
	}

	@Override
	public ProductClassification queryProductClassificationByName(String name,Integer creator) {
		if (LogicUtil.isNull(name)){
            return null;
        }
		return productClassificationDalComp.queryProductClassificationByName(name, creator);
	}

}
