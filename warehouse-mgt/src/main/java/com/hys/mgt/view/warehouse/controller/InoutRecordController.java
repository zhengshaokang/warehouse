package com.hys.mgt.view.warehouse.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.page.PageData;
import com.hys.dal.select.InoutRecordTypes;
import com.hys.dal.select.Users;
import com.hys.dal.select.Warehouses;
import com.hys.dal.select.conenum.EnumInoutRecordType;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.vo.SysUserVo;
import com.hys.mgt.view.warehouse.component.IInoutRecordComp;
import com.hys.mgt.view.warehouse.vo.InoutRecordVo;

@Controller
@RequestMapping(value="/warehouse/")
public class InoutRecordController {
	    @Autowired
	    private IInoutRecordComp inoutComp;
	    @Autowired 
	    private Users users;
	    @Autowired
	    private Warehouses warehouses;
	
	    // 查询用户列表
	    @RequestMapping(value = "inoutlist")
	    public String list(InoutRecordVo vo, ModelMap modelMap, HttpServletRequest request) {
	    	HttpSession session = SessionUtils.getSession();
	    	 SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	 vo.setCreator(Integer.parseInt(sysAdminVo.getId()));
	    	PageData<InoutRecordVo> pageParam = inoutComp.pageQueryInoutRecord(vo);
	        modelMap.put("pageParam", pageParam);
	        modelMap.put("inoutRecordParam", vo);// 查询时传入的参数
	        
	        Map<String,String> inoutRecordTypes = InoutRecordTypes.getOptions();
	        modelMap.put("inoutRecordTypes", inoutRecordTypes);
	        modelMap.put("warehouses", warehouses.getWarehouses());
	        modelMap.put("users", users.getOptions());
	        
	        return "warehouse/inout_list";
	    }
	    
	    @RequestMapping("returnin")
	    public String returnin(ModelMap modelMap,HttpServletRequest request) {
	    	 modelMap.put("recordType", EnumInoutRecordType.RETURNORDERIN.getValue());
	    	 return "warehouse/returnin";
	    }
	    
	    @ResponseBody
	    @RequestMapping("returninSubmit")
	    public ResultPrompt returninSubmit(String sku,String productionDate, String qty,String recordType, HttpServletRequest reqest) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    		ResultPrompt resultPrompt = inoutComp.returninSubmit(sku,productionDate,Integer.parseInt(qty),Integer.parseInt(recordType),Integer.parseInt(sysAdminVo.getId()));
    		return resultPrompt;
	    }
	    
	    @RequestMapping("returnstock")
	    public String returnstock(ModelMap modelMap,HttpServletRequest request) {
	    	 modelMap.put("recordType", EnumInoutRecordType.RETURNPRODUCTIN.getValue());
	    	 return "warehouse/returnstock";
	    }
	    
	    @ResponseBody
	    @RequestMapping("returnstockSubmit")
	    public ResultPrompt returnstockSubmit(String sku,String productionDate, String qty,String recordType, HttpServletRequest reqest) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    		ResultPrompt resultPrompt = inoutComp.returnstockSubmit(sku, productionDate, Integer.parseInt(qty),Integer.parseInt(recordType),Integer.parseInt(sysAdminVo.getId()));
    		return resultPrompt;
	    }
	    
	    @RequestMapping("discardout")
	    public String discardout(ModelMap modelMap,HttpServletRequest request) {
	    	 modelMap.put("recordType", EnumInoutRecordType.PRODUCTOUT.getValue());
	    	 return "warehouse/discardout";
	    }
	    
//	    @ResponseBody
//	    @RequestMapping("discardoutSubmit")
//	    public ResultPrompt discardoutSubmit(String sku, String qty,String recordType, HttpServletRequest reqest) {
//	    	HttpSession session = SessionUtils.getSession();
//	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
//    		ResultPrompt resultPrompt = inoutComp.discardoutSubmit(sku,Integer.parseInt(qty),Integer.parseInt(recordType),Integer.parseInt(sysAdminVo.getId()));
//    		return resultPrompt;
//	    }
	    
}
