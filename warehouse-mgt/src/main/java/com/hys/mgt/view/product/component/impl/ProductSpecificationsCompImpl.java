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
import com.hys.mgt.view.product.common.ProductSpecificationsConverter;
import com.hys.mgt.view.product.component.IProductSpecificationsComp;
import com.hys.mgt.view.product.vo.ProductSpecificationsVo;
import com.hys.model.product.ProductSpecifications;
import com.hys.service.product.IProductSpecificationsService;
@Component
public class ProductSpecificationsCompImpl implements IProductSpecificationsComp{
	private final static Logger log = LogProxy.getLogger(ProductSpecificationsCompImpl.class);
	
	@Autowired
	private IProductSpecificationsService productSpecificationsService;

	@Override
	public ResultPrompt addProductSpecifications (ProductSpecificationsVo ProductSpecificationsVo){
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			//验证名称是否存在，不能重复
			
			ProductSpecifications  pc = productSpecificationsService.queryProductSpecificationsByName(ProductSpecificationsVo.getName(),ProductSpecificationsVo.getCreator());
			if(null != pc) {
				 rp.setStatusCode("200");
				 rp.setMessage("该分类名称已经存在！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/specificationslist"); // 要刷新的tab页id
				 return rp;
			}
			boolean b = productSpecificationsService.addProductSpecifications(ProductSpecificationsConverter.convert2Do(ProductSpecificationsVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/specificationslist"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/specificationslist"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	        rp.setNavTabId("product/specificationslist"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt updateProductSpecifications(ProductSpecificationsVo ProductSpecificationsVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			boolean b = productSpecificationsService.updateProductSpecifications(ProductSpecificationsConverter.convert2Do(ProductSpecificationsVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/specificationslist"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/specificationslist"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	        rp.setNavTabId("product/specificationslist"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt deleteProductSpecifications(Integer id,Integer creator) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			boolean b = productSpecificationsService.deleteProductSpecifications(id,creator);
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setNavTabId("product/specificationslist"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setNavTabId("product/specificationslist"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	        rp.setNavTabId("product/specificationslist"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ProductSpecificationsVo queryProductSpecificationsById(Integer id) {
		return ProductSpecificationsConverter.convert2Vo(productSpecificationsService.queryProductSpecificationsById(id));
	}

	@Override
	public List<ProductSpecificationsVo> queryProductSpecificationsByParentId(
			Integer parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductSpecificationsVo> queryProductSpecificationss() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductSpecificationsVo> queryProductSpecificationsOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageData<ProductSpecificationsVo> pageQueryProductSpecifications(ProductSpecificationsVo p) {
		PageData<ProductSpecificationsVo> pageVo = null;
        try {
        	ProductSpecifications pc = (ProductSpecifications) ProductSpecificationsConverter.convert2Do(p);  
            PageParam<ProductSpecifications> page = new PageParam<ProductSpecifications>();
            page.setP(pc);
            page.setPageNo(p.getPageNum());
            page.setPageSize(p.getNumPerPage());
            PageData<ProductSpecifications> pd = productSpecificationsService.pageQueryProductSpecifications(page);

            List<ProductSpecifications> list = pd.getPageData();
            List<ProductSpecificationsVo> listvo = new ArrayList<>();
            if (LogicUtil.isNotNull(list)) {
                for (ProductSpecifications pcl : list) {
                    listvo.add((ProductSpecificationsVo) ProductSpecificationsConverter.convert2Vo(pcl));
                }
            }
            pageVo = new PageData<ProductSpecificationsVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
        } catch (Exception e){
        	e.printStackTrace();
            log.debug("pageQueryProductSpecifications exception=>" + e);
        }
        return pageVo;
	}

}
