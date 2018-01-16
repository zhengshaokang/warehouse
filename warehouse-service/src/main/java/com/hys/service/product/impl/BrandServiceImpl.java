package com.hys.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.product.IBrandDalComp;
import com.hys.model.product.Brand;
import com.hys.service.product.IBrandService;

@Service
public class BrandServiceImpl implements IBrandService {

	@Autowired
	private IBrandDalComp brandDalComp;
	
	@Override
	public boolean addBrand(Brand brand) {
		if(null == brand){
			return false;
		}
		Integer id = brandDalComp.addBrand(brand);
		if(id != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateBrand(Brand brand) {
		if(null == brand){
			return false;
		}
		return brandDalComp.updateBrand(brand);
	}

	@Override
	public boolean deleteBrand(Integer id,Integer creator) {
		if(id == null) {
			return false;
		}
		return brandDalComp.deleteBrand(id,creator);
	}

	@Override
	public Brand queryBrandById(Integer id) {
		if(id == null) {
			return null;
		}
		return brandDalComp.queryBrandById(id);
	}

	@Override
	public List<Brand> queryBrands() {
		return brandDalComp.queryBrands();
	}

	@Override
	public PageData<Brand> pageQueryBrand(
			PageParam<Brand> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return brandDalComp.pageQueryBrand(page);
	}

	@Override
	public Brand queryBrandByName(String name,Integer creator) {
		if (LogicUtil.isNull(name)){
            return null;
        }
		return brandDalComp.queryBrandByName(name,creator);
	}

}
