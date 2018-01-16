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
import com.hys.dal.select.ProductClassifications;
import com.hys.dal.select.Status;
import com.hys.dal.select.Users;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.component.IProductClassificationComp;
import com.hys.mgt.view.product.vo.ProductClassificationVo;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/product/")
public class ProductClassificationController {
	@Autowired
	private IProductClassificationComp  productClassificationComp;
	
	@Autowired
	private ProductClassifications productClassifications;
	
	@Autowired
	private Users users;
	
	 @RequestMapping(value = "classificationlist")
	 public String classificationlist(ProductClassificationVo vo, ModelMap modelMap, HttpServletRequest request) {
		 HttpSession session = SessionUtils.getSession();
    	 SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    	 vo.setCreator(Integer.parseInt(sysAdminVo.getId()));
		 PageData<ProductClassificationVo> pageParam = productClassificationComp.pageQueryProductClassification(vo);
         modelMap.put("pageParam", pageParam);
         modelMap.put("productClassificationParam", vo);// 查询时传入的参数
         modelMap.put("status", Status.getOptions());
         modelMap.put("users", users.getOptions());
         modelMap.put("productClassificationOne", productClassifications.getClassificationsOne(Integer.parseInt(sysAdminVo.getId())));
         return "product/product_classification_list";
	 }
	
	 @RequestMapping("productClassificationAdd")
	 public String productClassificationAdd(ProductClassificationVo vo,ModelMap modelMap){
		 HttpSession session = SessionUtils.getSession();
    	 SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		 modelMap.put("productClassificationOne", productClassifications.getClassificationsOne(Integer.parseInt(sysAdminVo.getId())));
		 return "product/product_classification_add";
	 }
	 
	 @RequestMapping("productClassificationUpdate")
	 public String productClassificationUpdate(Integer id,ModelMap modelMap){
		 HttpSession session = SessionUtils.getSession();
    	 SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		 ProductClassificationVo vo = productClassificationComp.queryProductClassificationById(id);
		 modelMap.put("productClassification", vo);
		 modelMap.put("productClassificationOne", productClassifications.getClassificationsOne(Integer.parseInt(sysAdminVo.getId())));
		 return "product/product_classification_update";
	 }
	 
	 @RequestMapping("productClassificationAddSubmit") 
	 @ResponseBody
	 public ResultPrompt productClassificationAddSubmit(ProductClassificationVo productClassificationVo) {
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	     productClassificationVo.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
	     productClassificationVo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	     productClassificationVo.setStatus(0);
	     ResultPrompt resultPrompt = productClassificationComp.addProductClassification(productClassificationVo);
	     return resultPrompt;
	 }
	 
	 @RequestMapping("productClassificationUpdateSubmit") 
	 @ResponseBody
	 public ResultPrompt productClassificationUpdateSubmit(ProductClassificationVo productClassificationVo) {
		 HttpSession session = SessionUtils.getSession();
    	 SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    	 productClassificationVo.setCreator(Integer.parseInt(sysAdminVo.getId()));
		 ResultPrompt resultPrompt = productClassificationComp.updateProductClassification(productClassificationVo);
	     return resultPrompt;
	 }
	 
	 @RequestMapping("productClassificationDelete") 
	 @ResponseBody
	 public ResultPrompt productClassificationDelete(Integer id) {
		 HttpSession session = SessionUtils.getSession();
    	 SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	     ResultPrompt resultPrompt = productClassificationComp.deleteProductClassification(id,Integer.parseInt(sysAdminVo.getId()));
	     return resultPrompt;
	 }
}
