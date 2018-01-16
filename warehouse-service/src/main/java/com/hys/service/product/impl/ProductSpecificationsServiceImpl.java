package com.hys.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.product.IProductSpecificationsDalComp;
import com.hys.model.product.ProductSpecifications;
import com.hys.service.product.IProductSpecificationsService;

@Service
public class ProductSpecificationsServiceImpl implements IProductSpecificationsService {

	@Autowired
	private IProductSpecificationsDalComp ProductSpecificationsDalComp;
	
	@Override
	public boolean addProductSpecifications(ProductSpecifications ProductSpecifications) {
		if(null == ProductSpecifications){
			return false;
		}
		Integer id = ProductSpecificationsDalComp.addProductSpecifications(ProductSpecifications);
		if(id != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProductSpecifications(ProductSpecifications ProductSpecifications) {
		if(null == ProductSpecifications){
			return false;
		}
		return ProductSpecificationsDalComp.updateProductSpecifications(ProductSpecifications);
	}

	@Override
	public boolean deleteProductSpecifications(Integer id,Integer creator) {
		if(id == null) {
			return false;
		}
		return ProductSpecificationsDalComp.deleteProductSpecifications(id,creator);
	}

	@Override
	public ProductSpecifications queryProductSpecificationsById(Integer id) {
		if(id == null) {
			return null;
		}
		return ProductSpecificationsDalComp.queryProductSpecificationsById(id);
	}

	@Override
	public List<ProductSpecifications> queryProductSpecificationsByParentId(Integer parentId) {
		if(parentId == null) {
			return null;
		}
		return ProductSpecificationsDalComp.queryProductSpecificationsByParentId(parentId);
	}

	@Override
	public List<ProductSpecifications> queryProductSpecificationss() {
		return ProductSpecificationsDalComp.queryProductSpecificationss();
	}

	@Override
	public List<ProductSpecifications> queryProductSpecificationsOne() {
		return ProductSpecificationsDalComp.queryProductSpecificationsOne();
	}

	@Override
	public PageData<ProductSpecifications> pageQueryProductSpecifications(
			PageParam<ProductSpecifications> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return ProductSpecificationsDalComp.pageQueryProductSpecifications(page);
	}

	@Override
	public ProductSpecifications queryProductSpecificationsByName(String name,Integer creator) {
		if (LogicUtil.isNull(name)){
            return null;
        }
		return ProductSpecificationsDalComp.queryProductSpecificationsByName(name, creator);
	}

}
