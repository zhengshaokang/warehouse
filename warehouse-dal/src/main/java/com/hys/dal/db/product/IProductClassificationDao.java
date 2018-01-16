package com.hys.dal.db.product;

import java.util.List;





import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.product.ProductClassification;

public interface IProductClassificationDao {
	public int addProductClassification(ProductClassification productClassification);
	public int updateProductClassification(ProductClassification productClassification);
	public int deleteProductClassification(@Param("id") Integer id,@Param("creator") Integer creator);
	public ProductClassification queryProductClassificationById(Integer id);
	public List<ProductClassification> queryProductClassificationByParentId(Integer parentId);
	public List<ProductClassification> queryProductClassificationByParentIdAndCreator(@Param("parentId") Integer parentId,@Param("creator")Integer creator);
	public List<ProductClassification> queryProductClassifications();
	public List<ProductClassification> queryProductClassificationsByCreator(@Param("creator") Integer creator);
	public List<ProductClassification> queryProductClassificationOne();
	public List<ProductClassification> queryProductClassificationOneByCreator(@Param("creator") Integer creator);
	public List<ProductClassification> pageQueryProductClassification(PageParam<ProductClassification> page);
	public ProductClassification queryProductClassificationByName(@Param("name") String name,@Param("creator") Integer creator);
}
