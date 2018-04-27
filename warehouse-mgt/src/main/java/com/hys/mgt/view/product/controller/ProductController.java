package com.hys.mgt.view.product.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.page.PageData;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.select.Brands;
import com.hys.dal.select.InoutRecordTypes;
import com.hys.dal.select.ProductClassifications;
import com.hys.dal.select.ProductSpecificationss;
import com.hys.dal.select.ProductUnits;
import com.hys.dal.select.Suppliers;
import com.hys.dal.select.Users;
import com.hys.dal.select.Warehouses;
import com.hys.dal.select.conenum.EnumInoutRecordType;
import com.hys.mgt.view.common.utils.Agent;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.component.IProductComp;
import com.hys.mgt.view.product.vo.ProductInPriceVo;
import com.hys.mgt.view.product.vo.ProductInVo;
import com.hys.mgt.view.product.vo.ProductOutVo;
import com.hys.mgt.view.product.vo.ProductVo;
import com.hys.mgt.view.user.vo.SysUserVo;
import com.hys.mgt.view.warehouse.vo.InventoryVo;

@Controller
@RequestMapping(value="/product/")
public class ProductController {
	    @Autowired
	    private IProductComp productComp;
	    @Autowired 
	    private Users users;
	    @Autowired
	    private ProductUnits productUnits;
	    @Autowired
	    private ProductSpecificationss productSpecificationss;
	    @Autowired
	    private ProductClassifications productClassifications;
	    @Autowired
	    private Warehouses warehouses;
	    @Autowired
	    private Brands brands;
	    @Autowired
	    private Suppliers suppliers;
	    
	    @Autowired
	    private Agent agent;
	
	    // 查询商品列表
	    @RequestMapping(value = "list")
	    public String list(ProductVo vo, ModelMap modelMap, HttpServletRequest request) {
	    	
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	vo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	    	
	        PageData<ProductVo> pageParam = productComp.pageQueryProduct(vo);
	        modelMap.put("pageParam", pageParam);
	        modelMap.put("productParam", vo);// 查询时传入的参数
	        
	        modelMap.put("unitsOptions", productUnits.getOptions(Integer.parseInt(sysAdminVo.getId())));
	        modelMap.put("suppliers", suppliers.getSuppliers(Integer.parseInt(sysAdminVo.getId())));
	        modelMap.put("brands", brands.getOptions(Integer.parseInt(sysAdminVo.getId())));
	        modelMap.put("productSpecificationss", productSpecificationss.getSpecificationss(Integer.parseInt(sysAdminVo.getId())));
	        modelMap.put("productClassifications", productClassifications.getClassifications(Integer.parseInt(sysAdminVo.getId())));
	        modelMap.put("agent", agent.getAgent());
	        return "product/product_list";
	    }
	    
	    @RequestMapping("productAdd")
	    public String productAdd(ModelMap modelMap,HttpServletRequest request) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	 modelMap.put("unitsOptions", productUnits.getOptions(Integer.parseInt(sysAdminVo.getId())));
	    	 modelMap.put("productSpecificationss", productSpecificationss.getSpecificationssOne(Integer.parseInt(sysAdminVo.getId())));
	         modelMap.put("productClassifications", productClassifications.getClassificationsOne(Integer.parseInt(sysAdminVo.getId())));
	         modelMap.put("suppliers", suppliers.getSuppliers(Integer.parseInt(sysAdminVo.getId())));
	         modelMap.put("brands", brands.getOptions(Integer.parseInt(sysAdminVo.getId())));
	         modelMap.put("agent", agent.getAgent());
	         getJsessionId(modelMap,request);
	    	 return "product/product_add";
	    }
	    @RequestMapping("specificationSelectSub")
	    @ResponseBody
	    public Map<String,String> specificationSelectSub(Integer parentId){
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	return productSpecificationss.getSpecificationssSub(parentId,Integer.parseInt(sysAdminVo.getId()));
	    }
	    
	    @RequestMapping("classificationSelectSub")
	    @ResponseBody
	    public Map<String,String> classificationSelectSub(Integer parentId){
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	return productClassifications.getClassificationsSub(parentId,Integer.parseInt(sysAdminVo.getId()));
	    }
	    
	    
	    @RequestMapping("productUpdate")
	    public String productUpdate(String productId,ModelMap modelMap,HttpServletRequest request) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	 if(LogicUtil.isNotNullAndEmpty(productId)) {
	    		 ProductVo pv= productComp.queryProductById(Integer.parseInt(productId));
	    		 modelMap.put("product", pv);
	    		 if(pv !=null && pv.getClassificationId1() > -1) {
	    			 modelMap.put("productClassificationsSub", productClassifications.getClassificationsSub(pv.getClassificationId1()));
	    		 }
	    		 if(pv !=null && pv.getSpecificationId1() > -1) {
	    			 modelMap.put("productSpecificationssSub", productSpecificationss.getSpecificationssSub(pv.getSpecificationId1()));
	    		 }
	    	 }
	    	 modelMap.put("unitsOptions", productUnits.getOptions(Integer.parseInt(sysAdminVo.getId())));
	         modelMap.put("suppliers", suppliers.getSuppliers(Integer.parseInt(sysAdminVo.getId())));
	         modelMap.put("brands", brands.getOptions(Integer.parseInt(sysAdminVo.getId())));
	         modelMap.put("productSpecificationss", productSpecificationss.getSpecificationssOne(Integer.parseInt(sysAdminVo.getId())));
	         modelMap.put("productClassifications", productClassifications.getClassificationsOne(Integer.parseInt(sysAdminVo.getId())));
	         modelMap.put("agent", agent.getAgent());
	         getJsessionId(modelMap,request);
	    	 return "product/product_update";
	    }
	    
	    @ResponseBody
	    @RequestMapping(value = "productAddSubmit", method = RequestMethod.POST)
	    public ResultPrompt productAddSubmit(ProductVo vo, HttpServletRequest reqest) {
	    	String productPic = reqest.getParameter("productpic");
	    	vo.setPicUrl(productPic);
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	vo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	    	vo.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
	    	vo.setUpdator(Integer.parseInt(sysAdminVo.getId()));
	    	vo.setStatus(0);
	        ResultPrompt resultPrompt = productComp.addProduct(vo);
	        
	        return resultPrompt;
	    }
	    
	    @ResponseBody
	    @RequestMapping(value = "productUpdateSubmit", method = RequestMethod.POST)
	    public ResultPrompt productUpdateSubmit(ProductVo vo, HttpServletRequest reqest) {
	    	String productPic = reqest.getParameter("productpic");
	    	vo.setPicUrl(productPic);
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	vo.setUpdator(Integer.parseInt(sysAdminVo.getId()));
	    	vo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	        ResultPrompt resultPrompt = productComp.updateProduct(vo);
	        
	        return resultPrompt;
	    }
	    
	    @ResponseBody
	    @RequestMapping("productDelete")
	    public ResultPrompt productDelete(String productId, HttpServletRequest reqest) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    		ResultPrompt resultPrompt = productComp.deleteProduct(Integer.parseInt(productId),Integer.parseInt(sysAdminVo.getId()));
    		return resultPrompt;
	    }
	    
	    
	    @ResponseBody
	    @RequestMapping("productOffline")
	    public ResultPrompt productOffline(String productId, HttpServletRequest reqest) {
    		ResultPrompt resultPrompt = productComp.productOfflineOrUp(Integer.parseInt(productId),1);
    		return resultPrompt;
	    }
	    
	    @ResponseBody
	    @RequestMapping("productUp")
	    public ResultPrompt productUp(String productId, HttpServletRequest reqest) {
    		ResultPrompt resultPrompt = productComp.productOfflineOrUp(Integer.parseInt(productId),0);
    		return resultPrompt;
	    }
	    
	    @RequestMapping("productQty")
	    public String productQty(String productId,ModelMap modelMap,HttpServletRequest request){
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	List<InventoryVo>  list = productComp.queryInventoryByProductId(Integer.parseInt(productId));
	    	 Integer count = 0;
	    	 if(LogicUtil.isNotNullAndEmpty(list)) {
	    		 for (InventoryVo inventoryVo : list) {
					count = count + inventoryVo.getQty();
				}
	    	 }
	    	 modelMap.put("qty", count);
	    	 modelMap.put("inventorys", list);
	    	 modelMap.put("warehouses", warehouses.getWarehouses(Integer.parseInt(sysAdminVo.getId())));
	    	 return "product/product_qty";
	    }
	    
	    @RequestMapping("productPrice")
	    public String productPrice(String productId,ModelMap modelMap,HttpServletRequest request){
	    	 List<ProductInPriceVo>  list = productComp.queryPriceByProductId(Integer.parseInt(productId));
	    	 modelMap.put("productPrices", list);
	    	 return "product/product_price";
	    }
	    
	    @RequestMapping("productIn")
	    public String productIn(String productId,ModelMap modelMap,HttpServletRequest request) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin"); 
	    	if(LogicUtil.isNotNullAndEmpty(productId)) {
	    		 ProductVo pv= productComp.queryProductById(Integer.parseInt(productId));
	    		 modelMap.put("product", pv);
	    	 }
	    	 
	    	 List<InventoryVo>  list = productComp.queryInventoryByProductId(Integer.parseInt(productId));
	    	 Integer count = 0;
	    	 if(LogicUtil.isNotNullAndEmpty(list)) {
	    		 for (InventoryVo inventoryVo : list) {
					count = count + inventoryVo.getQty();
				}
	    	 }
	    	 modelMap.put("currdate", DateUtil.getCurrentDateAsString("yyyy-MM-dd"));
	    	 modelMap.put("qty", count);
	    	 modelMap.put("warehouses", warehouses.getWarehouses(Integer.parseInt(sysAdminVo.getId())));
	    	 return "product/product_in";
	    }
	    
	    
	    @ResponseBody
	    @RequestMapping("productInSubmit")
	    public ResultPrompt productInSubmit(ProductInVo productInVo, HttpServletRequest reqest) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    		ResultPrompt resultPrompt = productComp.productInSubmit(productInVo,Integer.parseInt(sysAdminVo.getId()));
    		return resultPrompt;
	    }
	    
	    @RequestMapping("orderOut")
	    public String orderOut(ModelMap modelMap,HttpServletRequest request) {
	    	 modelMap.put("recordType", EnumInoutRecordType.ORDEROUT.getValue());
	    	 return "product/order_out";
	    }
	    
//	    @RequestMapping("orderOutSubmit")
//	    @ResponseBody
//	    public ResultPrompt orderOutSubmit(String sku,String recordType, HttpServletRequest reqest) {
//	    	HttpSession session = SessionUtils.getSession();
//	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
//    		ResultPrompt resultPrompt = productComp.productOutSubmit(sku,1,Integer.parseInt(recordType),Integer.parseInt(sysAdminVo.getId()));
//    		return resultPrompt;
//	    }
	    
	    
	    @RequestMapping("productOut")
	    public String productOut(String productId,ModelMap modelMap,HttpServletRequest request) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	 if(LogicUtil.isNotNullAndEmpty(productId)) {
	    		 ProductVo pv= productComp.queryProductById(Integer.parseInt(productId));
	    		 modelMap.put("product", pv);
	    	 }
	    	 
	    	 List<InventoryVo>  list = productComp.queryInventoryByProductId(Integer.parseInt(productId));
	    	 Map<String,String> map = new LinkedHashMap<String,String>();
	    	 Integer count = 0;
	    	 if(LogicUtil.isNotNullAndEmpty(list)) {
	    		 for (InventoryVo inventoryVo : list) {
					count = count + inventoryVo.getQty();
					map.put(inventoryVo.getBatchNo(), "批次:"+inventoryVo.getBatchNo()+",库存:"+inventoryVo.getQty());
				}
	    	 }
	    	 modelMap.put("currdate", DateUtil.getCurrentDateAsString("yyyy-MM-dd"));
	    	 modelMap.put("qty", count);
	    	 modelMap.put("warehouses", warehouses.getWarehouses(Integer.parseInt(sysAdminVo.getId())));
	    	 modelMap.put("qtys", map);
	    	 modelMap.put("recordTypes", InoutRecordTypes.getOutOptions());
	    	 return "product/product_out";
	    }
	    
	    @ResponseBody
	    @RequestMapping("productOutSubmit")
	    public ResultPrompt productOutSubmit(ProductOutVo productOutVo, HttpServletRequest reqest) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    		ResultPrompt resultPrompt = productComp.productOutSubmit(productOutVo,Integer.parseInt(sysAdminVo.getId()));
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
