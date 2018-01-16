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
import com.hys.dal.select.Status;
import com.hys.dal.select.Users;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.component.IBrandComp;
import com.hys.mgt.view.product.vo.BrandVo;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/brand/")
public class BrandController {
	@Autowired
	private IBrandComp  brandComp;
	
	@Autowired
	private Users users;
	
	 @RequestMapping(value = "list")
	 public String Unitslist(BrandVo vo, ModelMap modelMap, HttpServletRequest request) {
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	     vo.setCreator(Integer.parseInt(sysAdminVo.getId()));
		 PageData<BrandVo> pageParam = brandComp.pageQueryBrand(vo);
         modelMap.put("pageParam", pageParam);
         modelMap.put("brandParam", vo);// 查询时传入的参数
         modelMap.put("status", Status.getOptions());
         modelMap.put("users", users.getOptions());
         return "product/brand_list";
	 }
	
	 @RequestMapping("brandAdd")
	 public String BrandAdd(BrandVo vo,ModelMap modelMap){
		 
		 return "product/brand_add";
	 }
	 
	 @RequestMapping("brandUpdate")
	 public String BrandUpdate(Integer id,ModelMap modelMap){
		 BrandVo vo  = brandComp.queryBrandById(id);
		 modelMap.put("brand", vo);
		 return "product/brand_update";
	 }
	 
	 @RequestMapping("brandAddSubmit") 
	 @ResponseBody
	 public ResultPrompt BrandAddSubmit(BrandVo BrandVo) {
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	     BrandVo.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
	     BrandVo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	     BrandVo.setStatus(0);
	     ResultPrompt resultPrompt = brandComp.addBrand(BrandVo);
	     return resultPrompt;
	 }
	 
	 @RequestMapping("brandDelete")
	 @ResponseBody
	 public ResultPrompt BrandDelete(Integer id,ModelMap modelMap){
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		 ResultPrompt resultPrompt = brandComp.deleteBrand(id,Integer.parseInt(sysAdminVo.getId()));
	     return resultPrompt;
	 }
	 
	 @RequestMapping("brandUpdateSubmit") 
	 @ResponseBody
	 public ResultPrompt BrandUpdateSubmit(BrandVo brandVo) {
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	     brandVo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	     ResultPrompt resultPrompt = brandComp.updateBrand(brandVo);
	     return resultPrompt;
	 }
}
