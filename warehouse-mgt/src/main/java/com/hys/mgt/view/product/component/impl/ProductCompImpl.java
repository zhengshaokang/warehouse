package com.hys.mgt.view.product.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.commons.util.SysRemark;
import com.hys.dal.select.Users;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.product.common.ProductConverter;
import com.hys.mgt.view.product.common.ProductPriceConverter;
import com.hys.mgt.view.product.component.IProductComp;
import com.hys.mgt.view.product.vo.ProductInPriceVo;
import com.hys.mgt.view.product.vo.ProductInVo;
import com.hys.mgt.view.product.vo.ProductOutVo;
import com.hys.mgt.view.product.vo.ProductVo;
import com.hys.mgt.view.warehouse.common.InventoryConverter;
import com.hys.mgt.view.warehouse.vo.InventoryVo;
import com.hys.model.product.Product;
import com.hys.model.product.ProductInPrice;
import com.hys.model.warehouse.InoutRecord;
import com.hys.model.warehouse.Inventory;
import com.hys.service.product.IProductInPriceService;
import com.hys.service.product.IProductService;
import com.hys.service.warehouse.IInoutRecordService;
import com.hys.service.warehouse.IInventoryService;

@Component
public class ProductCompImpl implements IProductComp{
	
	private final static Logger log = LogProxy.getLogger(ProductCompImpl.class);
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IInventoryService inventoryService;
	
	@Autowired
	private IProductInPriceService productInPriceService;
	
	@Autowired
	private IInoutRecordService inoutRecordService; 
	
	@Autowired 
	private Users users;

	@Override
	public ResultPrompt addProduct(ProductVo product) {
		ResultPrompt rp = new ResultPrompt();
		try {
			
			Product cp = productService.queryProductByName(product.getName(),product.getCreator());
			
			if(LogicUtil.isNotNull(cp)) {
				 rp.setStatusCode("300");
				 rp.setMessage(product.getCode()+"已经存在，请直接入库！");
				 rp.setNavTabId("product/list"); // 要刷新的tab页id
				 return rp;
			} 
			
			Product p = ProductConverter.convert2Do(product);
			boolean b = productService.addProduct(p);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	        rp.setNavTabId("product/list"); // 要刷新的tab页id
		}
		
		return rp;
	}

	@Override
	public ResultPrompt updateProduct(ProductVo product) {
		ResultPrompt rp = new ResultPrompt();
		try {
			
//			Product cp = productService.queryProductByCode(product.getCode(),product.getCreator());
//			
//			if(LogicUtil.isNotNull(cp) && cp.getId() != product.getId()) { //要排除查询到的是当前的商品
//				 rp.setStatusCode("300");
//				 rp.setMessage(product.getCode()+"已经存在，请直接入库！");
//				 return rp;
//			} 
			Product productold = productService.queryProductById(product.getId());
			
			product.setStatus(productold.getStatus());
			product.setCreator(productold.getCreator());
			
			product.setSysRemark(SysRemark.append(productold.getSysRemark(), "于"+DateUtil.getCurrentDateTimeAsString()+"由"+users.getOptions().get(product.getUpdator().toString())+"修改！"));
			
			Product p = ProductConverter.convert2Do(product);
			
			boolean b = productService.updateProduct(p);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
		    	 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	        rp.setNavTabId("product/list"); // 要刷新的tab页id
		}
		
		return rp;
	}

	@Override
	public ResultPrompt deleteProduct(Integer id,Integer creator) {
		ResultPrompt rp = new ResultPrompt();
		try {
			boolean b = productService.deleteProduct(id,creator);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
		    rp.setMessage("操作失败！");
	        rp.setNavTabId("product/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ProductVo queryProductById(Integer id) {
		Product cp = productService.queryProductById(id);
		return ProductConverter.convert2Vo(cp);
	}

	@Override
	public ProductVo queryProductByCode(String code,Integer creator) {
		Product cp = null;// productService.queryProductByCode(code,creator);
		return ProductConverter.convert2Vo(cp);
	}

	@Override
	public List<ProductVo> queryProducts() {
		List<Product> pl = productService.queryProducts();
		List<ProductVo> vl = new ArrayList<ProductVo>();
		if(LogicUtil.isNotNullAndEmpty(pl)){
			for (Product product : pl) {
				vl.add(ProductConverter.convert2Vo(product));
			}
		}
		return vl;
	}

	@Override
	public PageData<ProductVo> pageQueryProduct(ProductVo v) {
		 PageData<ProductVo> pageVo = null;
	        try {
	            Product p = (Product) ProductConverter.convert2Do(v);  
	            PageParam<Product> page = new PageParam<Product>();
	            page.setP(p);
	            page.setPageNo(v.getPageNum());
	            page.setPageSize(v.getNumPerPage());
	            PageData<Product> pd = productService.pageQueryProduct(page);

	            List<Product> list = pd.getPageData();
	            List<ProductVo> listvo = new ArrayList<>();
	            if (LogicUtil.isNotNull(list)) {
	                for (Product product : list) {
	                    listvo.add((ProductVo) ProductConverter.convert2Vo(product));
	                }
	            }
	            pageVo = new PageData<ProductVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
	        } catch (Exception e){
	        	e.printStackTrace();
	            log.debug("pageQueryProduct exception=>" + e);
	        }
	        return pageVo;
	}

	@Override
	public ResultPrompt productInSubmit(ProductInVo productInVo ,Integer operator) {
		ResultPrompt rp = new ResultPrompt();
		try {
			
			//保存进价
			//根据入库日期+商品id查询，如果存在update
			boolean b1 = false;
			ProductInPrice  pprice = productInPriceService.queryProductInPriceByProductIdAndDate(productInVo.getProductId(), productInVo.getInWarehouseDate());
			if(LogicUtil.isNotNull(pprice)){
				if(pprice.getPrice() != productInVo.getPrice()) {
					 rp.setStatusCode("300");
					 rp.setMessage("一批商品进价不同，请核实！");
					 return rp;
				}
				pprice.setInQty(pprice.getInQty()+productInVo.getQty());
				pprice.setPrice(productInVo.getPrice());
				b1 = productInPriceService.updateProductInPrice(pprice);
			}  else {
				pprice = new ProductInPrice();
				pprice.setInDate(productInVo.getInWarehouseDate());
				pprice.setInQty(productInVo.getQty());
				pprice.setPrice(productInVo.getPrice());
				pprice.setProductId(productInVo.getProductId());
				pprice.setCreator(operator);
				pprice.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
				b1 = productInPriceService.addProductInPrice(pprice);
			}
			
			//批次号为入库日期+到期日期+商品id
			String batchNo = productInVo.getWarehouseCode()+"-"+productInVo.getInWarehouseDate().replaceAll("-", "")+"-"+productInVo.getMaturityDate().replaceAll("-", "")+"-"+productInVo.getProductId();
			//保存库存
			//如果该批次已经存在 ，库存相加，如果不存在，新增
			Inventory inventory = inventoryService.queryInventoryByBatchNo(batchNo);
			boolean b = false;
			Integer beforeInventory = 0;
			Integer afterInventory = 0;
			if(LogicUtil.isNotNull(inventory)){
				beforeInventory = inventory.getQty();
				afterInventory = inventory.getQty()+productInVo.getQty();
				inventory.setQty(afterInventory);
				b= inventoryService.updateInventory(inventory);
			} else {
				inventory = new Inventory();
				inventory.setBatchNo(batchNo);
				inventory.setInWarehouseDate(productInVo.getInWarehouseDate());
				inventory.setMaturityDate(productInVo.getMaturityDate());
				inventory.setProductId(productInVo.getProductId());
				inventory.setQty(productInVo.getQty());
				inventory.setWarehouseCode(productInVo.getWarehouseCode());
				afterInventory = productInVo.getQty();
				inventory.setCreator(operator);
				inventory.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
				b=inventoryService.addInventory(inventory);
			}
			
			
			//保存出入库记录
			InoutRecord inoutRecord = new InoutRecord();
			inoutRecord.setAfterInventory(afterInventory);
			inoutRecord.setBacthNo(batchNo);
			inoutRecord.setBeforeInventory(beforeInventory);
			inoutRecord.setChangeInventory(productInVo.getQty());
			inoutRecord.setOperateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd hh:mm:ss"));
			inoutRecord.setOperator(operator);
			inoutRecord.setProductId(productInVo.getProductId());
			inoutRecord.setRecordType(10);//采购入库
			inoutRecord.setWarehouseCode(productInVo.getWarehouseCode());
			inoutRecord.setMaturityDate(productInVo.getMaturityDate());
			inoutRecord.setCreator(operator);
			inoutRecord.setCreateTime(productInVo.getInWarehouseDate());
			inoutRecord.setRemark(productInVo.getRemark());
			boolean b2 = inoutRecordService.addInoutRecord(inoutRecord);
			
			if(b && b1 && b2) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("warehouse/inoutlist"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 //rp.setNavTabId("product/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
		   // rp.setMessage("操作失败！");
	        //rp.setNavTabId("product/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt productOutSubmit(ProductOutVo productOutVo, Integer operator) {
		ResultPrompt rp = new ResultPrompt();
		try {
			
			String batchNo = productOutVo.getBatchNo();
			String wcode= productOutVo.getWarehouseCode();
			String bcode = batchNo.substring(0, batchNo.indexOf("-"));
			if(!wcode.equals(bcode)) {
				 rp.setStatusCode("300");
				 rp.setMessage("出库位置和批次号不对应！");
				 return rp;
			}
			
			//修改库存
			//如果该批次已经存在 ，库存相加，如果不存在，新增
			Inventory inventory = inventoryService.queryInventoryByBatchNo(batchNo);
			boolean b = false;
			Integer beforeInventory = 0;
			Integer afterInventory = 0;
			String maturityDate = inventory.getMaturityDate();
			if(LogicUtil.isNotNull(inventory)){
				beforeInventory = inventory.getQty();
				
				if(beforeInventory < productOutVo.getQty()) {
					 rp.setStatusCode("300");
					 rp.setMessage("出库数量超过了该批次的库存，请重新填写！");
					 return rp;
				}
				afterInventory = inventory.getQty()-productOutVo.getQty();
				inventory.setQty(afterInventory);
				b= inventoryService.updateInventory(inventory);
			} else {
				 rp.setStatusCode("300");
				 rp.setMessage("批次号对应的库存不存在！");
				 return rp;
			}
			
			
			//保存出入库记录
			InoutRecord inoutRecord = new InoutRecord();
			inoutRecord.setAfterInventory(afterInventory);
			inoutRecord.setBacthNo(batchNo);
			inoutRecord.setBeforeInventory(beforeInventory);
			inoutRecord.setChangeInventory(productOutVo.getQty());
			inoutRecord.setOperateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
			inoutRecord.setOperator(operator);
			inoutRecord.setProductId(productOutVo.getProductId());
			inoutRecord.setRecordType(Integer.parseInt(productOutVo.getInoutRecordType()));
			inoutRecord.setWarehouseCode(productOutVo.getWarehouseCode());
			inoutRecord.setMaturityDate(maturityDate);
			inoutRecord.setCreator(operator);
			inoutRecord.setCreateTime(productOutVo.getOutWarehouseDate());
			inoutRecord.setRemark(productOutVo.getRemark());
			boolean b2 = inoutRecordService.addInoutRecord(inoutRecord);
			
			if(b2 && b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("warehouse/inoutlist"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 //rp.setNavTabId("product/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
		    rp.setMessage("操作失败！");
	        //rp.setNavTabId("product/list"); // 要刷新的tab页id
		}
		return rp;
	}

//	@Override
//	public ResultPrompt productOutSubmit(String code, Integer qty,
//			Integer recordType, Integer operator) {
//		ResultPrompt rp = new ResultPrompt();
//		try {
//			Product p = productService.queryProductByCode(code,operator);
//			if(null == p) {
//				 rp.setStatusCode("300");
//				 rp.setMessage("国际条码:"+code+",不存在或者已经下架！");
//				 return rp;
//			}
//			boolean b = productService.productOutSubmit(p, qty, recordType,operator);
//			if(b) {	
//				 rp.setStatusCode("200");
//				 rp.setMessage("操作成功！");
//				// rp.setCallbackType("closeCurrent"); // 关闭当前窗口
//				// rp.setNavTabId("product/list"); // 要刷新的tab页id
//		     } else {
//		    	 rp.setStatusCode("300");
//		    	 rp.setMessage("操作失败！");
//		    	// rp.setNavTabId("product/list"); // 要刷新的tab页id
//		     }
//		} catch (Exception e) {
//			e.printStackTrace();
//		    rp.setStatusCode("300");
//		    rp.setMessage("操作失败！");
//	     //   rp.setNavTabId("product/list"); // 要刷新的tab页id
//		}
//		return rp;
//	}

	@Override
	public ResultPrompt productOfflineOrUp(Integer id , Integer status) {
		ResultPrompt rp = new ResultPrompt();
		try {
			boolean b = productService.productOfflineOrUp(id,status);
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     } else {
		    	 rp.setStatusCode("300");
		    	 rp.setMessage("操作失败！");
		    	 rp.setNavTabId("product/list"); // 要刷新的tab页id
		     }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
		    rp.setMessage("操作失败！");
	        rp.setNavTabId("product/list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public List<InventoryVo> queryInventoryByProductId(Integer productId) {
		
		List<Inventory> list = inventoryService.queryInventoryByProductId(productId);
		List<InventoryVo> listVo = new ArrayList<InventoryVo>();
		if(LogicUtil.isNotNullAndEmpty(list)) {
			for (Inventory inventory : list) {
				listVo.add(InventoryConverter.convert2Vo(inventory));
			}
		}
		
		return listVo;
	}

	@Override
	public List<ProductInPriceVo> queryPriceByProductId(Integer productId) {
		List<ProductInPrice> list = productInPriceService.queryProductInPriceByProductId(productId);
		List<ProductInPriceVo> list1 =  new ArrayList<ProductInPriceVo>();
		if(LogicUtil.isNotNullAndEmpty(list)) {
			for (ProductInPrice productInPrice : list) {
				list1.add(ProductPriceConverter.convert2Vo(productInPrice));
			}
		}
		return list1;
	}
}
