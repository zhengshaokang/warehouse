package com.hys.mgt.view.warehouse.controller;


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
import com.hys.mgt.view.user.vo.SysUserVo;
import com.hys.mgt.view.warehouse.component.IWarehouseComp;
import com.hys.mgt.view.warehouse.vo.WarehouseVo;

@Controller
@RequestMapping(value="/warehouse/")
public class WarehouseController {
	    @Autowired
	    private IWarehouseComp warehouseComp;
	    @Autowired 
	    private Users users;
	
	    @RequestMapping(value = "list")
	    public String list(WarehouseVo vo, ModelMap modelMap, HttpServletRequest request) {
	    	HttpSession session = SessionUtils.getSession();
	    	 SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	 vo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	    	PageData<WarehouseVo> pageParam = warehouseComp.pageQueryWarehouse(vo);
	        modelMap.put("pageParam", pageParam);
	        modelMap.put("warehouseParam", vo);// 查询时传入的参数
	        
	        modelMap.put("users", users.getOptions());
	        
	        return "warehouse/warehouse_list";
	    }
	    
	    @RequestMapping("warehouseAdd")
		 public String warehouseAdd(WarehouseVo vo,ModelMap modelMap){
			 
			 return "warehouse/warehouse_add";
		 }
		 
		 @RequestMapping("warehouseUpdate")
		 public String warehouseUpdate(Integer id,ModelMap modelMap){
			 WarehouseVo vo = warehouseComp.queryWarehouseById(id);
			 modelMap.put("warehouse", vo);
			 return "warehouse/warehouse_update";
		 }
		 
		 @RequestMapping("warehouseDelete")
		 @ResponseBody
		 public ResultPrompt warehouseDelete(Integer id,ModelMap modelMap){
			 ResultPrompt resultPrompt = warehouseComp.deleteWarehouse(id);
		     return resultPrompt;
		 }
		 
		 @RequestMapping("warehouseAddSubmit") 
		 @ResponseBody
		 public ResultPrompt warehouseAddSubmit(WarehouseVo warehouseVo) {
			 HttpSession session = SessionUtils.getSession();
		     SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		     warehouseVo.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
		     warehouseVo.setCreator(Integer.parseInt(sysAdminVo.getId()));
		     ResultPrompt resultPrompt = warehouseComp.addWarehouse(warehouseVo);
		     return resultPrompt;
		 }
		 
		 @RequestMapping("updateWarehouseSubmit") 
		 @ResponseBody
		 public ResultPrompt updateWarehouseSubmit(WarehouseVo warehouseVo) {
		     ResultPrompt resultPrompt = warehouseComp.updateWarehouse(warehouseVo);
		     return resultPrompt;
		 }
	    
}
