package com.hys.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.commons.util.SysRemark;
import com.hys.dal.component.inout.IInoutRecordDalComp;
import com.hys.dal.component.product.IProductAttributeDalComp;
import com.hys.dal.component.product.IProductDalComp;
import com.hys.dal.select.Users;
import com.hys.dal.select.conenum.EnumInoutRecordType;
import com.hys.dal.select.conenum.EnumInoutType;
import com.hys.model.inout.InoutRecord;
import com.hys.model.product.Product;
import com.hys.model.product.ProductAttribute;
import com.hys.service.product.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private IProductDalComp productDalComp;
	@Autowired
	private IProductAttributeDalComp productAttributeDalComp;
	@Autowired
	private IInoutRecordDalComp inoutRecordDalComp;
	@Autowired 
	private Users users;

	@Override
	public boolean addProduct(Product product) {
		if(null == product){
			return false;
		}
		boolean b1 = true;
		boolean b2 = false;
		Integer id = productDalComp.addProduct(product);
		if(LogicUtil.isNotNull(id)) {
			List<ProductAttribute> pal = product.getProductAttribute();
			for (ProductAttribute productAttribute : pal) {
				productAttribute.setProductId(id);
				b1 = productAttributeDalComp.addProductAttribute(productAttribute);
				if(b1 == false) {
					break;
				}
			}
			InoutRecord inoutRecord = new InoutRecord();
			inoutRecord.setInoutType(EnumInoutType.IN.getValue());
			Integer qty = product.getInventoryAvailable();
			inoutRecord.setAfterTotal(qty);
			inoutRecord.setAfterInventory(qty);
			inoutRecord.setBeforeInventory(qty);
			inoutRecord.setBeforeTotal(qty);
			inoutRecord.setChangeInventory(qty);
			inoutRecord.setOperateTime(product.getCreateTime());
			inoutRecord.setOperator(product.getCreator());
			inoutRecord.setRecordType(EnumInoutRecordType.PRODUCTIN.getValue());
			inoutRecord.setProductId(id);
			b2 = inoutRecordDalComp.addInoutRecord(inoutRecord);
		}
		if(b1 && b2) {
			return true;
		} 
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		if(null == product || null == product.getId()){
			return false;
		}
		boolean b1 = true;
		boolean b3 = true;
		
		boolean b2 = productDalComp.updateProduct(product);
		if(b2) {
			List<ProductAttribute> pal = product.getProductAttribute();
			List<ProductAttribute> palold = productAttributeDalComp.queryProductAttributeByProductId(product.getId());
			if(LogicUtil.isNotNullAndEmpty(palold)) {
				for (ProductAttribute productAttribute : palold) {
					b3 = productAttributeDalComp.deleteProductAttribute(productAttribute.getId());
					if(b3 == false) {
						break;
					}
				}
			}
			if(LogicUtil.isNotNullAndEmpty(palold)) {
				for (ProductAttribute productAttribute : pal) {
					b1 = productAttributeDalComp.addProductAttribute(productAttribute);
					if(b1 == false) {
						break;
					}
				}
			}
		}
		if(b1 && b2 && b3) {
			return true;
		} 
		return false;
	}

	@Override
	public boolean deleteProduct(Integer id) {
		if(null == id) {
			return false;
		}
		boolean b1 = productDalComp.deleteProduct(id);
		boolean b2 = productAttributeDalComp.deleteProductAttributeByProductId(id);
		if(b1 && b2) {
			return true;
		} 
		return false;
	}

	@Override
	public Product queryProductById(Integer id) {
		if(null == id) {
			return null;
		}
		Product product = productDalComp.queryProductById(id);
		if(LogicUtil.isNotNull(product)) {
			List<ProductAttribute> pal  = productAttributeDalComp.queryProductAttributeByProductId(product.getId());
			product.setProductAttribute(pal);
		}
		return product;
	}

	@Override
	public Product queryProductBySku(String sku) {
		if(null == sku || "".equals(sku)) {
			return null;
		}
		Product product = productDalComp.queryProductBySku(sku);
		if(LogicUtil.isNotNull(product)) {
			List<ProductAttribute> pal  = productAttributeDalComp.queryProductAttributeByProductId(product.getId());
			product.setProductAttribute(pal);
		}
		return product;
	}

	@Override
	public List<Product> queryProducts() {
		List<Product> productlist = productDalComp.queryProducts();
		if(LogicUtil.isNotNullAndEmpty(productlist)) {
			for (Product product : productlist) {
				List<ProductAttribute> pal  = productAttributeDalComp.queryProductAttributeByProductId(product.getId());
				product.setProductAttribute(pal);
			}
		}
		return productlist;
	}

	@Override
	public PageData<Product> pageQueryProduct(PageParam<Product> page) {
	    if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		PageData<Product> pageDate = productDalComp.pageQueryProduct(page);
		List<Product> productlist = pageDate.getPageData();
		if(LogicUtil.isNotNullAndEmpty(productlist)) {
			for (Product product : productlist) {
				List<ProductAttribute> pal  = productAttributeDalComp.queryProductAttributeByProductId(product.getId());
				product.setProductAttribute(pal);
			}
		}
		pageDate.setPageData(productlist);
		return pageDate;
	}

	@Override
	public boolean productInSubmit(Product product, Integer qty,Integer recordType,Integer operator) {
		Integer oldQty = product.getInventoryAvailable();
		Integer newQty = oldQty + qty;
//		Integer oldCQty = product.getInventoryCheck();
//		Integer newCQty = oldCQty + qty;
		
		InoutRecord inoutRecord = new InoutRecord();
		Integer oldTQty = product.getInventoryTotal();
		
		if(recordType.equals(EnumInoutRecordType.PRODUCTIN.getValue())) {
			Integer newTQty = oldTQty + qty;
			product.setInventoryTotal(newTQty);
			inoutRecord.setAfterTotal(oldTQty);
			inoutRecord.setBeforeTotal(newTQty);
		} else {
			inoutRecord.setAfterTotal(oldTQty);
			inoutRecord.setBeforeTotal(oldTQty);
		}
		product.setInventoryAvailable(newQty);
//		product.setInventoryCheck(newCQty);
		
		
		product.setSysRemark(SysRemark.append(product.getSysRemark(), "于"+DateUtil.getCurrentDateTimeAsString()+"由"+users.getOptions().get(operator.toString())+"入库,数量为:"+qty));
		
		boolean b  = productDalComp.updateProduct(product);
		
		
		inoutRecord.setInoutType(EnumInoutType.IN.getValue());
		
		
		inoutRecord.setAfterInventory(newQty);
		inoutRecord.setBeforeInventory(oldQty);
		
		inoutRecord.setChangeInventory(qty);
		inoutRecord.setOperateTime(DateUtil.getCurrentDateTimeAsString());
		inoutRecord.setOperator(operator);
		inoutRecord.setRecordType(recordType);
		inoutRecord.setProductId(product.getId());
		boolean b1 = inoutRecordDalComp.addInoutRecord(inoutRecord);
		if(b && b1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean productOutSubmit(Product product, Integer qty, Integer recordType ,Integer operator) {
		Integer oldQty = product.getInventoryAvailable();
		Integer newQty = oldQty - qty;
		//Integer oldCQty = product.getInventoryCheck();
		//Integer newCQty = oldCQty - qty;
		Integer oldTQty = product.getInventoryTotal();
		//Integer newTQty = oldTQty - qty;
		product.setInventoryAvailable(newQty);
		//product.setInventoryCheck(newCQty);
		//product.setInventoryTotal(oldTQty);
		
		product.setSysRemark(SysRemark.append(product.getSysRemark(), "于"+DateUtil.getCurrentDateTimeAsString()+"由"+users.getOptions().get(operator.toString())+"出库,数量为:"+qty));
		
		boolean b  = productDalComp.updateProduct(product);
		
		InoutRecord inoutRecord = new InoutRecord();
		inoutRecord.setInoutType(EnumInoutType.OUT.getValue());
		
		inoutRecord.setAfterTotal(oldTQty);
		inoutRecord.setBeforeTotal(oldTQty);
		
		inoutRecord.setAfterInventory(newQty);
		inoutRecord.setBeforeInventory(oldQty);
		
		inoutRecord.setChangeInventory(qty);
		inoutRecord.setOperateTime(DateUtil.getCurrentDateTimeAsString());
		inoutRecord.setOperator(operator);
		inoutRecord.setRecordType(recordType);
		inoutRecord.setProductId(product.getId());
		boolean b1 = inoutRecordDalComp.addInoutRecord(inoutRecord);
		
		if(b && b1) {
			return true;
		}
		
		return false;
	}

	@Override
	public Product queryProductBySkuAndProductionDate(String sku, String productionDate) {
		if(null == sku || "".equals(sku)) {
			return null;
		}
		if(null == productionDate || "".equals(productionDate)) {
			return null;
		}
		Product product = productDalComp.queryProductBySkuAndProductionDate(sku, productionDate);
		if(LogicUtil.isNotNull(product)) {
			List<ProductAttribute> pal  = productAttributeDalComp.queryProductAttributeByProductId(product.getId());
			product.setProductAttribute(pal);
		}
		return product;
	}

	@Override
	public boolean productOfflineOrUp(Integer id,Integer status) {
		Product p = queryProductById(id);
		p.setStatus(status);
		return updateProduct(p);
	}

}
