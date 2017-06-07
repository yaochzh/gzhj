package com.supply.deliveryplan.service;
import com.supply.deliveryplan.entity.PoDeliveryplanEntity;
import com.supply.deliveryplanb.entity.PoDeliveryplanBEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface PoDeliveryplanServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(PoDeliveryplanEntity poDeliveryplan,
	        List<PoDeliveryplanBEntity> poDeliveryplanBList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(PoDeliveryplanEntity poDeliveryplan,
	        List<PoDeliveryplanBEntity> poDeliveryplanBList);
	public void delMain (PoDeliveryplanEntity poDeliveryplan);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(PoDeliveryplanEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(PoDeliveryplanEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(PoDeliveryplanEntity t);
}
