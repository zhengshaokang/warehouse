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
import com.hys.mgt.view.product.common.ProductUnitConverter;
import com.hys.mgt.view.product.component.IProductUnitComp;
import com.hys.mgt.view.product.vo.ProductUnitVo;
import com.hys.model.product.ProductUnit;
import com.hys.service.product.IProductUnitService;
@Component
public class ProductUnitCompImpl implements IProductUnitComp{
	private final static Logger log = LogProxy.getLogger(ProductUnitCompImpl.class);
	
	@Autowired
	private IProductUnitService productUnitService;

	@Override
	public ResultPrompt addProductUnit (ProductUnitVo ProductUnitVo){
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			//验证名称是否存在，不能重复
			
			ProductUnit  pc = productUnitService.queryProductUnitByName(ProductUnitVo.getName(),ProductUnitVo.getCreator());
			if(null != pc) {
				 rp.setStatusCode("200");
				 rp.setMessage("该分类名称已经存在！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/unitlist"); // 要刷新的tab页id
				 return rp;
			}
			boolean b = productUnitService.addProductUnit(ProductUnitConverter.convert2Do(ProductUnitVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/unitlist"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/unitlist"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	        rp.setNavTabId("product/unitlist"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt updateProductUnit(ProductUnitVo productUnitVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			boolean b = productUnitService.updateProductUnit(ProductUnitConverter.convert2Do(productUnitVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/unitlist"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/unitlist"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	        rp.setNavTabId("product/unitlist"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt deleteProductUnit(Integer id,Integer creator) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			boolean b = productUnitService.deleteProductUnit(id,creator);
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setNavTabId("product/unitlist"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setNavTabId("product/unitlist"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	        rp.setNavTabId("product/unitlist"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ProductUnitVo queryProductUnitById(Integer id) {
		return ProductUnitConverter.convert2Vo(productUnitService.queryProductUnitById(id));
	}

	@Override
	public List<ProductUnitVo> queryProductUnitByParentId(
			Integer parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductUnitVo> queryProductUnits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductUnitVo> queryProductUnitOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageData<ProductUnitVo> pageQueryProductUnit(ProductUnitVo p) {
		PageData<ProductUnitVo> pageVo = null;
        try {
        	ProductUnit pc = (ProductUnit) ProductUnitConverter.convert2Do(p);  
            PageParam<ProductUnit> page = new PageParam<ProductUnit>();
            page.setP(pc);
            page.setPageNo(p.getPageNum());
            page.setPageSize(p.getNumPerPage());
            PageData<ProductUnit> pd = productUnitService.pageQueryProductUnit(page);

            List<ProductUnit> list = pd.getPageData();
            List<ProductUnitVo> listvo = new ArrayList<>();
            if (LogicUtil.isNotNull(list)) {
                for (ProductUnit pcl : list) {
                    listvo.add((ProductUnitVo) ProductUnitConverter.convert2Vo(pcl));
                }
            }
            pageVo = new PageData<ProductUnitVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
        } catch (Exception e){
        	e.printStackTrace();
            log.debug("pageQueryProductUnit exception=>" + e);
        }
        return pageVo;
	}

}
