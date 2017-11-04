package com.hys.mgt.view.product.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.json.JsonConverter;
import com.hys.commons.page.PageData;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.select.ProductPackTypes;
import com.hys.dal.select.ProductAttrs;
import com.hys.dal.select.ProductStatus;
import com.hys.dal.select.ProductTypes;
import com.hys.dal.select.ProductUnits;
import com.hys.dal.select.Users;
import com.hys.dal.select.conenum.EnumInoutRecordType;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.component.IProductComp;
import com.hys.mgt.view.product.vo.ProductAttributeVo;
import com.hys.mgt.view.product.vo.ProductVo;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/product/")
public class ProductController {
	    @Autowired
	    private IProductComp productComp;
	    @Autowired 
	    private Users users;
	
	    // 查询用户列表
	    @RequestMapping(value = "list")
	    public String list(ProductVo vo, ModelMap modelMap, HttpServletRequest request) {
	        PageData<ProductVo> pageParam = productComp.pageQueryProduct(vo);
	        modelMap.put("pageParam", pageParam);
	        modelMap.put("productParam", vo);// 查询时传入的参数
	        
	        modelMap.put("unitsOptions", ProductUnits.getOptions());
	        modelMap.put("productStatus", ProductStatus.getOptions());
	        modelMap.put("productTypes", ProductTypes.getOptions());
	        modelMap.put("packTypes", ProductPackTypes.getOptions());
	       // modelMap.put("users", users.getOptions());
	        
	        return "product/product_list";
	    }
	    
	    @RequestMapping("productAdd")
	    public String productAdd(ModelMap modelMap,HttpServletRequest request) {
	    	 modelMap.put("productAttrs", ProductAttrs.getOptions());
	    	 modelMap.put("productAttrstr", JsonConverter.format(ProductAttrs.getOptions()));
	    	 modelMap.put("unitsOptions", ProductUnits.getOptions());
	    	 modelMap.put("productStatus", ProductStatus.getOptions());
	         modelMap.put("productTypes", ProductTypes.getOptions());
	         modelMap.put("packTypes", ProductPackTypes.getOptions());
	         getJsessionId(modelMap,request);
	    	 return "product/product_add";
	    }
	    
	    @RequestMapping("productUpdate")
	    public String productUpdate(String productId,ModelMap modelMap,HttpServletRequest request) {
	    	 if(LogicUtil.isNotNullAndEmpty(productId)) {
	    		 ProductVo pv= productComp.queryProductById(Integer.parseInt(productId));
	    		 modelMap.put("product", pv);
	    	 }
	    	 modelMap.put("productAttrstr", JsonConverter.format(ProductAttrs.getOptions()));
	    	 modelMap.put("productAttrs", ProductAttrs.getOptions());
	    	 modelMap.put("unitsOptions", ProductUnits.getOptions());
	    	 modelMap.put("productStatus", ProductStatus.getOptions());
	         modelMap.put("productTypes", ProductTypes.getOptions());
	         modelMap.put("packTypes", ProductPackTypes.getOptions());
	         getJsessionId(modelMap,request);
	    	 return "product/product_update";
	    }
	    
	    @ResponseBody
	    @RequestMapping(value = "productAddSubmit", method = RequestMethod.POST)
	    public ResultPrompt productAddSubmit(ProductVo vo, HttpServletRequest reqest) {
	    	List<ProductAttributeVo> attrlist = new ArrayList<ProductAttributeVo>();
	    	String productattrs = reqest.getParameter("productattrs");
	    	String productPic = reqest.getParameter("productpic");
	    	vo.setPicUrl(productPic);
	    	if(LogicUtil.isNotNullAndEmpty(productattrs)) {
		    	String[] attrs = productattrs.split(",");
		    	if(LogicUtil.isNotNullAndEmpty(attrs)) {
		    		for (String attr : attrs) {
						String[] value = attr.split("\\|");
						ProductAttributeVo  attrvo = new ProductAttributeVo();
						attrvo.setName(value[0]);
						attrvo.setValue(value[1]);
						attrlist.add(attrvo);
					}
		    	}
	    	}
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	vo.setProductAttribute(attrlist);
	    	vo.setInventoryTotal(vo.getInventoryAvailable());  //开始总库存为可用库存
	    	vo.setInventoryCheck(vo.getInventoryAvailable());  //开始盘点库存为可以用库存
	    	vo.setCheckDate(DateUtil.getCurrentDateAsString("yyyy-MM-dd"));
	    	vo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	    	vo.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
	    	vo.setUpdator(Integer.parseInt(sysAdminVo.getId()));
	    	vo.setUpdateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
	    	vo.setStatus(0);
	        ResultPrompt resultPrompt = productComp.addProduct(vo);
	        
	        return resultPrompt;
	    }
	    
	    @ResponseBody
	    @RequestMapping(value = "productUpdateSubmit", method = RequestMethod.POST)
	    public ResultPrompt productUpdateSubmit(ProductVo vo, HttpServletRequest reqest) {
	    	List<ProductAttributeVo> attrlist = new ArrayList<ProductAttributeVo>();
	    	String productattrs = reqest.getParameter("productattrs");
	    	String productPic = reqest.getParameter("productpic");
	    	vo.setPicUrl(productPic);
	    	if(LogicUtil.isNotNullAndEmpty(productattrs)) {
		    	String[] attrs = productattrs.split(",");
		    	if(LogicUtil.isNotNullAndEmpty(attrs)) {
		    		for (String attr : attrs) {
						String[] value = attr.split("\\|");
						ProductAttributeVo  attrvo = new ProductAttributeVo();
						attrvo.setProductId(vo.getId());
						attrvo.setName(value[0]);
						attrvo.setValue(value[1]);
						attrlist.add(attrvo);
					}
		    	}
	    	}
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	vo.setProductAttribute(attrlist);
	    	vo.setUpdator(Integer.parseInt(sysAdminVo.getId()));
	    	vo.setUpdateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
	        ResultPrompt resultPrompt = productComp.updateProduct(vo);
	        
	        return resultPrompt;
	    }
	    
	    @ResponseBody
	    @RequestMapping("productDelete")
	    public ResultPrompt productDelete(String productId, HttpServletRequest reqest) {
    		ResultPrompt resultPrompt = productComp.deleteProduct(Integer.parseInt(productId));
    		return resultPrompt;
	    }
	    
	    @RequestMapping("productIn")
	    public String productIn(String productId,ModelMap modelMap,HttpServletRequest request) {
	    	 if(LogicUtil.isNotNullAndEmpty(productId)) {
	    		 ProductVo pv= productComp.queryProductById(Integer.parseInt(productId));
	    		 modelMap.put("product", pv);
	    	 }
	    	 modelMap.put("recordType", EnumInoutRecordType.PRODUCTIN.getValue());
	    	 return "product/product_in";
	    }
	    
	    @ResponseBody
	    @RequestMapping("productInSubmit")
	    public ResultPrompt productInSubmit(String productId, String qty,String recordType, HttpServletRequest reqest) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    		ResultPrompt resultPrompt = productComp.productInSubmit(Integer.parseInt(productId),Integer.parseInt(qty),Integer.parseInt(recordType),Integer.parseInt(sysAdminVo.getId()));
    		return resultPrompt;
	    }
	    
	    
	    @RequestMapping("productOut")
	    public String productOut(String productId,ModelMap modelMap,HttpServletRequest request) {
	    	 if(LogicUtil.isNotNullAndEmpty(productId)) {
	    		 ProductVo pv= productComp.queryProductById(Integer.parseInt(productId));
	    		 modelMap.put("product", pv);
	    	 }
	    	 modelMap.put("recordType", EnumInoutRecordType.PRODUCTOUT.getValue());
	    	 return "product/product_out";
	    }
	    
	    @ResponseBody
	    @RequestMapping("productOutSubmit")
	    public ResultPrompt productOutSubmit(String productId, String qty,String recordType, HttpServletRequest reqest) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    		ResultPrompt resultPrompt = productComp.productOutSubmit(Integer.parseInt(productId),Integer.parseInt(qty),Integer.parseInt(recordType),Integer.parseInt(sysAdminVo.getId()));
    		return resultPrompt;
	    }
	    
	    //获取sessionid
	    private void getJsessionId(ModelMap modelMap, HttpServletRequest request) {
	        Cookie[] cookie = request.getCookies();
	        if (cookie != null)
	        {
	            for (int i = 0; i < cookie.length; i++)
	            {
	                if ("JSESSIONID".equals(cookie[i].getName()))
	                {
	                    modelMap.put("jsessionId", cookie[i].getValue());
	                }
	            }
	        }
	    }
	    
}
