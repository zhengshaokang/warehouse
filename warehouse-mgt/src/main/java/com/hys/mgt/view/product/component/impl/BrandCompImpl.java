package com.hys.mgt.view.product.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.common.BrandConverter;
import com.hys.mgt.view.product.component.IBrandComp;
import com.hys.mgt.view.product.vo.BrandVo;
import com.hys.model.product.Brand;
import com.hys.service.product.IBrandService;
@Component
public class BrandCompImpl implements IBrandComp{
	private final static Logger log = LogProxy.getLogger(BrandCompImpl.class);
	
	@Autowired
	private IBrandService BrandService;

	@Override
	public ResultPrompt addBrand (BrandVo brandVo){
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			//验证名称是否存在，不能重复
			
			Brand  pc = BrandService.queryBrandByName(brandVo.getName(),brandVo.getCreator());
			if(null != pc) {
				 rp.setStatusCode("200");
				 rp.setMessage("该品牌已经存在！");
				 //rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("brand/list"); // 要刷新的tab页id
				 return rp;
			}
			boolean b = BrandService.addBrand(BrandConverter.convert2Do(brandVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("brand/list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("brand/list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	    	 rp.setNavTabId("brand/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt updateBrand(BrandVo brandVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			boolean b = BrandService.updateBrand(BrandConverter.convert2Do(brandVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("brand/list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("brand/list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	    	 rp.setNavTabId("brand/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt deleteBrand(Integer id,Integer creator) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			boolean b = BrandService.deleteBrand(id,creator);
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setNavTabId("brand/list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setNavTabId("brand/list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	        rp.setNavTabId("brand/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public BrandVo queryBrandById(Integer id) {
		return BrandConverter.convert2Vo(BrandService.queryBrandById(id));
	}

	@Override
	public List<BrandVo> queryBrandByParentId(
			Integer parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandVo> queryBrands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandVo> queryBrandOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageData<BrandVo> pageQueryBrand(BrandVo p) {
		PageData<BrandVo> pageVo = null;
        try {
        	Brand pc = (Brand) BrandConverter.convert2Do(p);  
            PageParam<Brand> page = new PageParam<Brand>();
            page.setP(pc);
            page.setPageNo(p.getPageNum());
            page.setPageSize(p.getNumPerPage());
            PageData<Brand> pd = BrandService.pageQueryBrand(page);

            List<Brand> list = pd.getPageData();
            List<BrandVo> listvo = new ArrayList<>();
            if (LogicUtil.isNotNull(list)) {
                for (Brand pcl : list) {
                    listvo.add((BrandVo) BrandConverter.convert2Vo(pcl));
                }
            }
            pageVo = new PageData<BrandVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
        } catch (Exception e){
        	e.printStackTrace();
            log.debug("pageQueryBrand exception=>" + e);
        }
        return pageVo;
	}

}
