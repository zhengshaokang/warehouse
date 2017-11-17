package com.hys.mgt.view.inout.controller;

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
import com.hys.dal.select.InoutTypes;
import com.hys.dal.select.Users;
import com.hys.dal.select.conenum.EnumInoutRecordType;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.inout.component.IInoutRecordComp;
import com.hys.mgt.view.inout.vo.InoutRecordVo;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/inout/")
public class InoutRecordController {
	    @Autowired
	    private IInoutRecordComp inoutComp;
	    @Autowired 
	    private Users users;
	
	    // 查询用户列表
	    @RequestMapping(value = "list")
	    public String list(InoutRecordVo vo, ModelMap modelMap, HttpServletRequest request) {
	        PageData<InoutRecordVo> pageParam = inoutComp.pageQueryInoutRecord(vo);
	        modelMap.put("pageParam", pageParam);
	        modelMap.put("inoutRecordParam", vo);// 查询时传入的参数
	        
	        Map<String,String> inoutRecordTypes = InoutRecordTypes.getOptions();
	        Map<String,String> inoutTypes = InoutTypes.getOptions();
	        modelMap.put("inoutRecordTypes", inoutRecordTypes);
	        modelMap.put("inoutTypes", inoutTypes);
	        modelMap.put("users", users.getOptions());
	        
	        return "inout/inout_list";
	    }
	    
	    @RequestMapping("returnin")
	    public String returnin(ModelMap modelMap,HttpServletRequest request) {
	    	 modelMap.put("recordType", EnumInoutRecordType.RETURNORDERIN.getValue());
	    	 return "inout/returnin";
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
	    	 return "inout/returnstock";
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
	    	 return "inout/discardout";
	    }
	    
	    @ResponseBody
	    @RequestMapping("discardoutSubmit")
	    public ResultPrompt discardoutSubmit(String sku, String qty,String recordType, HttpServletRequest reqest) {
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
    		ResultPrompt resultPrompt = inoutComp.discardoutSubmit(sku,Integer.parseInt(qty),Integer.parseInt(recordType),Integer.parseInt(sysAdminVo.getId()));
    		return resultPrompt;
	    }
}
