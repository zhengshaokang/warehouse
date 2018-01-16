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
import com.hys.dal.select.ProductUnits;
import com.hys.dal.select.Status;
import com.hys.dal.select.Users;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.component.IProductUnitComp;
import com.hys.mgt.view.product.vo.ProductUnitVo;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/product/")
public class ProductUnitController {
	@Autowired
	private IProductUnitComp  productUnitComp;
	
	@Autowired
	private ProductUnits productUnits;
	
	@Autowired
	private Users users;
	
	 @RequestMapping(value = "unitlist")
	 public String Unitslist(ProductUnitVo vo, ModelMap modelMap, HttpServletRequest request) {
		 HttpSession session = SessionUtils.getSession();
    	 SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    	 vo.setCreator(Integer.parseInt(sysAdminVo.getId()));
		 PageData<ProductUnitVo> pageParam = productUnitComp.pageQueryProductUnit(vo);
         modelMap.put("pageParam", pageParam);
         modelMap.put("productUnitsParam", vo);// 查询时传入的参数
         modelMap.put("status", Status.getOptions());
         modelMap.put("users", users.getOptions());
         return "product/product_unit_list";
	 }
	
	 @RequestMapping("productUnitAdd")
	 public String productUnitAdd(ProductUnitVo vo,ModelMap modelMap){
		 
		 return "product/product_unit_add";
	 }
	 
	 @RequestMapping("productUnitUpdate")
	 public String productUnitUpdate(Integer id,ModelMap modelMap){
		 ProductUnitVo vo  = productUnitComp.queryProductUnitById(id);
		 modelMap.put("productUnit", vo);
		 return "product/product_unit_update";
	 }
	 
	 @RequestMapping("productUnitAddSubmit") 
	 @ResponseBody
	 public ResultPrompt productUnitAddSubmit(ProductUnitVo productUnitVo) {
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	     productUnitVo.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
	     productUnitVo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	     productUnitVo.setStatus(0);
	     ResultPrompt resultPrompt = productUnitComp.addProductUnit(productUnitVo);
	     return resultPrompt;
	 }
	 
	 @RequestMapping("productUnitDelete")
	 @ResponseBody
	 public ResultPrompt productUnitDelete(Integer id,ModelMap modelMap){
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		 ResultPrompt resultPrompt = productUnitComp.deleteProductUnit(id,Integer.parseInt(sysAdminVo.getId()));
	     return resultPrompt;
	 }
	 
	 @RequestMapping("productUnitUpdateSubmit") 
	 @ResponseBody
	 public ResultPrompt productUnitUpdateSubmit(ProductUnitVo productUnitVo) {
		 HttpSession session = SessionUtils.getSession();
	     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	     productUnitVo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	     ResultPrompt resultPrompt = productUnitComp.updateProductUnit(productUnitVo);
	     return resultPrompt;
	 }
}
