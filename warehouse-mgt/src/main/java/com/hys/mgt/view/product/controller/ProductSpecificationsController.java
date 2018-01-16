package com.hys.mgt.view.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.page.PageData;
import com.hys.commons.util.DateUtil;
import com.hys.dal.select.ProductSpecificationss;
import com.hys.dal.select.Status;
import com.hys.dal.select.Users;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.component.IProductSpecificationsComp;
import com.hys.mgt.view.product.vo.ProductSpecificationsVo;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/product/")
public class ProductSpecificationsController {
	@Autowired
	private IProductSpecificationsComp  productSpecificationsComp;
	
	@Autowired
	private ProductSpecificationss productSpecificationss;
	
	@Autowired
	private Users users;
	
	 @RequestMapping(value = "specificationslist")
	 public String specificationsslist(ProductSpecificationsVo vo, ModelMap modelMap, HttpServletRequest request) {
		 HttpSession session = SessionUtils.getSession();
    	 SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    	 vo.setCreator(Integer.parseInt(sysAdminVo.getId()));
		 PageData<ProductSpecificationsVo> pageParam = productSpecificationsComp.pageQueryProductSpecifications(vo);
         modelMap.put("pageParam", pageParam);
         modelMap.put("productSpecificationssParam", vo);// 查询时传入的参数
         modelMap.put("status", Status.getOptions());
         modelMap.put("users", users.getOptions());
         modelMap.put("productSpecificationssOne", productSpecificationss.getSpecificationssOne(Integer.parseInt(sysAdminVo.getId())));
         return "product/product_specifications_list";
	 }
	
	 @RequestMapping("productSpecificationssAdd")
	 public String productSpecificationssAdd(ProductSpecificationsVo vo,ModelMap modelMap){
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin"); 
		 modelMap.put("productSpecificationssOne", productSpecificationss.getSpecificationssOne(Integer.parseInt(sysAdminVo.getId())));
		 return "product/product_specifications_add";
	 }
	 
	 @RequestMapping("productSpecificationsUpdate")
	 public String productSpecificationsUpdate(Integer id,ModelMap modelMap){
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin"); 
		 modelMap.put("productSpecificationssOne", productSpecificationss.getSpecificationssOne(Integer.parseInt(sysAdminVo.getId())));
		 ProductSpecificationsVo vo =productSpecificationsComp.queryProductSpecificationsById(id);
		 modelMap.put("productSpecifications", vo);
		 modelMap.put("productSpecificationssOne", productSpecificationss.getSpecificationssOne(Integer.parseInt(sysAdminVo.getId())));
		 return "product/product_specifications_update";
	 }
	 
	 @RequestMapping("productSpecificationssAddSubmit") 
	 @ResponseBody
	 public ResultPrompt ProductSpecificationsAddSubmit(ProductSpecificationsVo productSpecificationsVo) {
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	     productSpecificationsVo.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
	     productSpecificationsVo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	     productSpecificationsVo.setStatus(0);
	     ResultPrompt resultPrompt = productSpecificationsComp.addProductSpecifications(productSpecificationsVo);
	     return resultPrompt;
	 }
	 
	 @RequestMapping("productSpecificationssUpdateSubmit") 
	 @ResponseBody
	 public ResultPrompt productSpecificationssUpdateSubmit(ProductSpecificationsVo productSpecificationsVo) {
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	     productSpecificationsVo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	     ResultPrompt resultPrompt = productSpecificationsComp.updateProductSpecifications(productSpecificationsVo);
	     return resultPrompt;
	 }
	 
	 @RequestMapping("productSpecificationsDelete") 
	 @ResponseBody
	 public ResultPrompt productSpecificationsDelete(Integer id) {
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	     ResultPrompt resultPrompt = productSpecificationsComp.deleteProductSpecifications(id,Integer.parseInt(sysAdminVo.getId()));
	     return resultPrompt;
	 }
}
