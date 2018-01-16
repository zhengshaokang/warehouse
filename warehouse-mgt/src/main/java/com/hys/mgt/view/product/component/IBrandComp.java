package com.hys.mgt.view.product.component;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.vo.BrandVo;

public interface IBrandComp {
	public ResultPrompt addBrand(BrandVo brandVo);
	public ResultPrompt updateBrand(BrandVo brandVo);
	public ResultPrompt deleteBrand(Integer id,Integer creator);
	public BrandVo queryBrandById(Integer id);
	public List<BrandVo> queryBrandByParentId(Integer parentId);
	public List<BrandVo> queryBrands();
	public List<BrandVo> queryBrandOne();
	public PageData<BrandVo> pageQueryBrand(BrandVo p);
}
