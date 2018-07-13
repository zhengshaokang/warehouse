package com.hys.mgt.view.comment.component.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.comment.common.OrderConverter;
import com.hys.mgt.view.comment.component.IOrderViewComp;
import com.hys.mgt.view.comment.vo.OrderVo;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.model.comment.Order;
import com.hys.model.comment.Shop;
import com.hys.service.comment.IOrderService;
import com.hys.service.comment.IShopService;

@Component
public class OrderViewCompImpl implements IOrderViewComp {
	
	private final static Logger log = LogProxy.getLogger(OrderViewCompImpl.class);
	
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IShopService shopService;

	@Override
	public ResultPrompt addOrder(OrderVo orderVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			//验证订单是否存在，不能重复
			Order  pc = orderService.queryOrderByNo(orderVo.getOrderNo(), orderVo.getUserId());
			if(null != pc) {
				 rp.setStatusCode("200");
				 rp.setMessage("该订单号已经存在！");
				 rp.setNavTabId("comment/shop-list"); // 要刷新的tab页id
				 return rp;
			}
			boolean b = orderService.addOrder(OrderConverter.convert2Do(orderVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	    	 rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt updateOrder(OrderVo orderVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			boolean b = orderService.updateOrder(OrderConverter.convert2Do(orderVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	    	 rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
		}
		return rp;
	}


	@Override
	public PageData<OrderVo> pageQueryOrders(OrderVo vo) {
		PageData<OrderVo> pageVo = null;
        try
        {
        	Order u = (Order) OrderConverter.convert2Do(vo);  
            PageParam<Order> page = new PageParam<Order>();
            page.setP(u);
            page.setPageNo(vo.getPageNum());
            page.setPageSize(vo.getNumPerPage());
            PageData<Order> pd = orderService.pageQueryOrders(page);

            List<Order> list = pd.getPageData();
            List<OrderVo> listvo = new ArrayList<>();
            if (LogicUtil.isNotNull(list))
            {
                for (Order user : list)
                {
                    listvo.add((OrderVo) OrderConverter.convert2Vo(user));
                }
            }
            pageVo = new PageData<OrderVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
        }
        catch (Exception e)
        {
            log.debug("pageQueryUsers exception=>" + e);
        }
        return pageVo;
	}


	@Override
	public ResultPrompt deleteOrder(Integer id, Integer userId) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			boolean b = orderService.deleteOrder(id, userId);
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	        rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public OrderVo queryOrderById(Integer id, Integer userId) {
		Order order = orderService.queryOrderById(id, userId);
		return OrderConverter.convert2Vo(order);
	}
	
	 @Override
	 public ResultPrompt uploadOrder(MultipartFile file,Integer userId) {
	    	
	    	ResultPrompt rp = new ResultPrompt();
	    	try {
				checkFile(file,rp);
				 //获得Workbook工作薄对象
				Workbook workbook = getWorkBook(file);
				//创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
				List<Order> list = new ArrayList<Order>();
				List<String> orderNos = new ArrayList<String>();
				if(workbook != null){
				    for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
				        //获得当前sheet工作表
				        Sheet sheet = workbook.getSheetAt(sheetNum);
				        if(sheet == null){
				            continue; 
				        }
				        //获得当前sheet的开始行
				        int firstRowNum  = sheet.getFirstRowNum();
				        //获得当前sheet的结束行
				        int lastRowNum = sheet.getLastRowNum();
				        //循环除了第一行的所有行
				        for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
				            //获得当前行
				            Row row = sheet.getRow(rowNum);
				            if(row == null){
				                continue;
				            }
				            
				            Order order = new Order();
				            //循环当前行
				            
				            if(LogicUtil.isNotNull(row.getCell(0))){
								Order  pc = orderService.queryOrderByNo(getCellValue(row.getCell(0)), userId);
				            	if(LogicUtil.isNotNull(pc)) {
				            		rp.setStatusCode("300");
								    rp.setMessage("订单号"+getCellValue(row.getCell(0))+"订单号已经存在，请核实后再上传");
								    return rp;
				            	}
				            	
				            	for (String orderNo : orderNos) {
									if(orderNo.equals(getCellValue(row.getCell(0)).trim())){
										rp.setStatusCode("300");
									    rp.setMessage("订单号"+orderNo+"重复，请核实后再上传");
									    return rp;
									}
								}
				            	
				            	order.setOrderNo(getCellValue(row.getCell(0)).trim()); 
				            	orderNos.add(getCellValue(row.getCell(0)).trim());
				            } else {
				            	rp.setStatusCode("300");
							    rp.setMessage("订单号不能为空");
							    return rp;
				            }
				            
				            if(LogicUtil.isNotNull(row.getCell(1))){
				            	try {
									order.setShopId(Integer.parseInt(getCellValue(row.getCell(1))));
									
									Shop shop = shopService.queryShopById(Integer.parseInt(getCellValue(row.getCell(1))), userId);
									if(LogicUtil.isNull(shop)) {
										rp.setStatusCode("300");
									    rp.setMessage("为"+Integer.parseInt(getCellValue(row.getCell(1)))+"店铺不存在，请核实");
									    return rp;
									}
				            	} catch (Exception e) {
									rp.setStatusCode("300");
								    rp.setMessage("店铺ID必须是对应的数字");
								    return rp;
								}
				            } else {
				            	rp.setStatusCode("300");
							    rp.setMessage("店铺ID不能为空");
							    return rp;
				            }
				            if(LogicUtil.isNotNull(row.getCell(2))){
				            	try {
				            		order.setIsJoin(Integer.parseInt(getCellValue(row.getCell(2))));
								} catch (Exception e) {
									rp.setStatusCode("300");
								    rp.setMessage("是否参与活动必须是对应的数字");
								    return rp;
								}
				            }
				            if(LogicUtil.isNotNull(row.getCell(3))){
				            	try {
				            		 order.setIsPay(Integer.parseInt(getCellValue(row.getCell(3))));
								} catch (Exception e) {
									rp.setStatusCode("300");
								    rp.setMessage("是否返现必须是对应的数字");
								    return rp;
								}
				            }
				            if(LogicUtil.isNotNull(row.getCell(4))){
				            	try {
				            		order.setOrderStatus(Integer.parseInt(getCellValue(row.getCell(4))));
								} catch (Exception e) {
									rp.setStatusCode("300");
								    rp.setMessage("订单状态必须是对应的数字");
								    return rp;
								}
				            }
				            if(LogicUtil.isNotNull(row.getCell(5))){
				            	order.setCustomerName(getCellValue(row.getCell(5)));
				            }
				            if(LogicUtil.isNotNull(row.getCell(6))){
				            	 order.setCustomerMobile(getCellValue(row.getCell(6)));
				            }
				            if(LogicUtil.isNotNull(row.getCell(7))){
				            	order.setOrderTime(getCellValue(row.getCell(7)));
				            }
				            if(LogicUtil.isNotNull(row.getCell(8))){
				            	order.setRemark(getCellValue(row.getCell(8)));
				            }
				            order.setUserId(userId);
				            list.add(order);
				            
				        }
				    }
				}
				String errMsg = "";
				if(LogicUtil.isNotNullAndEmpty(list)) {
					for (Order order2 : list) {
						boolean b = orderService.addOrder(order2);
						if(b==false) {
							errMsg +="订单号"+order2.getOrderNo()+"保存失败!<br>";
						}
						
					}
				}
				
				if("".equals(errMsg)) {
					rp.setStatusCode("200");
				    rp.setMessage("所有订单保存成功！");
				    rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
				} else{
					rp.setStatusCode("200");
				    rp.setMessage(errMsg);
				    rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				rp.setStatusCode("300");
			    rp.setMessage("上传文件失败");
			}
	        return rp;
	    }

	
	/**
     * 检查文件
     * @param file
     * @throws IOException
     */
     public  void checkFile(MultipartFile file,ResultPrompt rp) throws Exception{
         //判断文件是否存在
         if(null == file){
             //log.error("文件不存在！");
        	 rp.setStatusCode("300");
			 rp.setMessage("文件不存在！");
			 //rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
         }
         //获得文件名
         String fileName = file.getOriginalFilename();
         //判断文件是否是excel文件
         if(!fileName.endsWith("xls") && !fileName.endsWith("xlsx")){
        	 rp.setStatusCode("300");
			 rp.setMessage(fileName + "不是excel文件");
			 //rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
         }
     }
     public  Workbook getWorkBook(MultipartFile file) throws Exception{
         //获得文件名
         String fileName = file.getOriginalFilename();
         //创建Workbook工作薄对象，表示整个excel
         Workbook workbook = null;
         //获取excel文件的io流
         InputStream is = file.getInputStream();
         //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
         if(fileName.endsWith("xls")){
             //2003
             workbook = new HSSFWorkbook(is);
         }else if(fileName.endsWith("xlsx")){
             //2007 及2007以上
             workbook = new XSSFWorkbook(is);
         }
         return workbook;
     }
     
     @SuppressWarnings("deprecation")
	public String getCellValue(Cell cell){
         String cellValue = "";
         if(cell == null){
             return cellValue;
         }
     //判断数据的类型
         switch (cell.getCellType()){
             case Cell.CELL_TYPE_NUMERIC: //数字
                 cellValue = stringDateProcess(cell);
                 break;
             case Cell.CELL_TYPE_STRING: //字符串
                 cellValue = String.valueOf(cell.getStringCellValue());
                 break;
             case Cell.CELL_TYPE_BOOLEAN: //Boolean
                 cellValue = String.valueOf(cell.getBooleanCellValue());
                 break;
             case Cell.CELL_TYPE_FORMULA: //公式
                 cellValue = String.valueOf(cell.getCellFormula());
                 break;
             case Cell.CELL_TYPE_BLANK: //空值
                 cellValue = "";
                 break;
             case Cell.CELL_TYPE_ERROR: //故障
                 cellValue = "非法字符";
                 break;
             default:
                 cellValue = "未知类型";
                 break;
         }
         return cellValue;
     }  
     
     /**
      * 时间格式处理
      * @return
      */
     public String stringDateProcess(Cell cell){
         String result = new String();  
         if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
             SimpleDateFormat sdf = null;  
             if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {  
                 sdf = new SimpleDateFormat("HH:mm");  
             } else {// 日期  
                 sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
             }  
             Date date = cell.getDateCellValue();  
             result = sdf.format(date);  
         } else if (cell.getCellStyle().getDataFormat() == 58) {  
             // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
             double value = cell.getNumericCellValue();  
             Date date = org.apache.poi.ss.usermodel.DateUtil  
                     .getJavaDate(value);  
             result = sdf.format(date);  
         } else {  
             Double value = cell.getNumericCellValue();  
            
             result = String.valueOf(value.intValue());
         }  
         
         return result;
     }
	
}
