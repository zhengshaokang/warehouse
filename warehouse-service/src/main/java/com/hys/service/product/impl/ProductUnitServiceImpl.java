package com.hys.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.product.IProductUnitDalComp;
import com.hys.model.product.ProductUnit;
import com.hys.service.product.IProductUnitService;

@Service
public class ProductUnitServiceImpl implements IProductUnitService {

	@Autowired
	private IProductUnitDalComp productUnitDalComp;
	
	@Override
	public boolean addProductUnit(ProductUnit productUnit) {
		if(null == productUnit){
			return false;
		}
		Integer id = productUnitDalComp.addProductUnit(productUnit);
		if(id != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProductUnit(ProductUnit productUnit) {
		if(null == productUnit){
			return false;
		}
		return productUnitDalComp.updateProductUnit(productUnit);
	}

	@Override
	public boolean deleteProductUnit(Integer id,Integer creator) {
		if(id == null) {
			return false;
		}
		return productUnitDalComp.deleteProductUnit(id,creator);
	}

	@Override
	public ProductUnit queryProductUnitById(Integer id) {
		if(id == null) {
			return null;
		}
		return productUnitDalComp.queryProductUnitById(id);
	}

	@Override
	public List<ProductUnit> queryProductUnits() {
		return productUnitDalComp.queryProductUnits();
	}

	@Override
	public PageData<ProductUnit> pageQueryProductUnit(
			PageParam<ProductUnit> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return productUnitDalComp.pageQueryProductUnit(page);
	}

	@Override
	public ProductUnit queryProductUnitByName(String name,Integer creator) {
		if (LogicUtil.isNull(name)){
            return null;
        }
		return productUnitDalComp.queryProductUnitByName(name, creator);
	}

}
