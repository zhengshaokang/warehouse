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
import com.hys.dal.select.Users;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.component.ISupplierComp;
import com.hys.mgt.view.product.vo.SupplierVo;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/product/")
public class SupplierController {
	@Autowired
	private ISupplierComp  supplierComp;
	
	@Autowired
	private Users users;
	
	 @RequestMapping(value = "supplierlist")
	 public String Unitslist(SupplierVo vo, ModelMap modelMap, HttpServletRequest request) {
		 HttpSession session = SessionUtils.getSession();
    	 SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    	 vo.setCreator(Integer.parseInt(sysAdminVo.getId()));
		 PageData<SupplierVo> pageParam = supplierComp.pageQuerySupplier(vo);
         modelMap.put("pageParam", pageParam);
         modelMap.put("supplierParam", vo);// 查询时传入的参数
         modelMap.put("users", users.getOptions());
         return "product/supplier_list";
	 }
	
	 @RequestMapping("supplierAdd")
	 public String supplierAdd(SupplierVo vo,ModelMap modelMap){
		 
		 return "product/supplier_add";
	 }
	 
	 @RequestMapping("supplierUpdate")
	 public String supplierUpdate(Integer id,ModelMap modelMap){
		 SupplierVo vo = supplierComp.querySupplierById(id);
		 modelMap.put("supplier", vo);
		 return "product/supplier_update";
	 }
	 
	 @RequestMapping("supplierDelete")
	 @ResponseBody
	 public ResultPrompt supplierDelete(Integer id,ModelMap modelMap){
		 HttpSession session = SessionUtils.getSession();
    	 SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		 ResultPrompt resultPrompt = supplierComp.deleteSupplier(id,Integer.parseInt(sysAdminVo.getId()));
	     return resultPrompt;
	 }
	 
	 @RequestMapping("supplierAddSubmit") 
	 @ResponseBody
	 public ResultPrompt supplierAddSubmit(SupplierVo supplierVo) {
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	     supplierVo.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
	     supplierVo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	     ResultPrompt resultPrompt = supplierComp.addSupplier(supplierVo);
	     return resultPrompt;
	 }
	 
	 @RequestMapping("updateSupplierSubmit") 
	 @ResponseBody
	 public ResultPrompt updateSupplierSubmit(SupplierVo supplierVo) {
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		 supplierVo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	     ResultPrompt resultPrompt = supplierComp.updateSupplier(supplierVo);
	     return resultPrompt;
	 }
	 
}
