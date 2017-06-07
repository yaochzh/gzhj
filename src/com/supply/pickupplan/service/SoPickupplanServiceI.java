package com.supply.pickupplan.service;
import com.supply.pickupplan.entity.SoPickupplanEntity;
import com.supply.pickupplanb.entity.SoPickupplanBEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface SoPickupplanServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(SoPickupplanEntity soPickupplan,
	        List<SoPickupplanBEntity> soPickupplanBList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(SoPickupplanEntity soPickupplan,
	        List<SoPickupplanBEntity> soPickupplanBList);
	public void delMain (SoPickupplanEntity soPickupplan);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(SoPickupplanEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(SoPickupplanEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(SoPickupplanEntity t);
}
