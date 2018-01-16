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
import com.hys.mgt.view.product.common.ProductClassificationConverter;
import com.hys.mgt.view.product.component.IProductClassificationComp;
import com.hys.mgt.view.product.vo.ProductClassificationVo;
import com.hys.model.product.ProductClassification;
import com.hys.service.product.IProductClassificationService;
@Component
public class ProductClassificationCompImpl implements IProductClassificationComp{
	private final static Logger log = LogProxy.getLogger(ProductClassificationCompImpl.class);
	
	@Autowired
	private IProductClassificationService productClassificationService;

	@Override
	public ResultPrompt addProductClassification (ProductClassificationVo productClassificationVo){
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			//验证名称是否存在，不能重复
			
			ProductClassification  pc = productClassificationService.queryProductClassificationByName(productClassificationVo.getName(),productClassificationVo.getCreator());
			if(null != pc) {
				 rp.setStatusCode("200");
				 rp.setMessage("该分类名称已经存在！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/classificationlist"); // 要刷新的tab页id
				 return rp;
			}
			boolean b = productClassificationService.addProductClassification(ProductClassificationConverter.convert2Do(productClassificationVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/classificationlist"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/classificationlist"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	        rp.setNavTabId("product/classificationlist"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt updateProductClassification(ProductClassificationVo productClassificationVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			boolean b = productClassificationService.updateProductClassification(ProductClassificationConverter.convert2Do(productClassificationVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/classificationlist"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/classificationlist"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	        rp.setNavTabId("product/classificationlist"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt deleteProductClassification(Integer id,Integer creator) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			boolean b = productClassificationService.deleteProductClassification(id,creator);
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setNavTabId("product/classificationlist"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setNavTabId("product/classificationlist"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	        rp.setNavTabId("product/classificationlist"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ProductClassificationVo queryProductClassificationById(Integer id) {
		return ProductClassificationConverter.convert2Vo(productClassificationService.queryProductClassificationById(id));
	}

	@Override
	public List<ProductClassificationVo> queryProductClassificationByParentId(
			Integer parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductClassificationVo> queryProductClassifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductClassificationVo> queryProductClassificationOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageData<ProductClassificationVo> pageQueryProductClassification(ProductClassificationVo p) {
		PageData<ProductClassificationVo> pageVo = null;
        try {
        	ProductClassification pc = (ProductClassification) ProductClassificationConverter.convert2Do(p);  
            PageParam<ProductClassification> page = new PageParam<ProductClassification>();
            page.setP(pc);
            page.setPageNo(p.getPageNum());
            page.setPageSize(p.getNumPerPage());
            PageData<ProductClassification> pd = productClassificationService.pageQueryProductClassification(page);

            List<ProductClassification> list = pd.getPageData();
            List<ProductClassificationVo> listvo = new ArrayList<>();
            if (LogicUtil.isNotNull(list)) {
                for (ProductClassification pcl : list) {
                    listvo.add((ProductClassificationVo) ProductClassificationConverter.convert2Vo(pcl));
                }
            }
            pageVo = new PageData<ProductClassificationVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
        } catch (Exception e){
        	e.printStackTrace();
            log.debug("pageQueryProductClassification exception=>" + e);
        }
        return pageVo;
	}

}
