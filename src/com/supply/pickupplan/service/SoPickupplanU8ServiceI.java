package com.supply.pickupplan.service;

import java.util.List;
import java.util.Map;

public interface SoPickupplanU8ServiceI{
	/**
	 * 
	* @param string 
	 * @Title: queryU8SoOrder 
	* @Description: 查询U8的销售订单
	* @param @return
	* @return List<U8SoOrderEntity>
	 * @throws Exception 
	* @throws 
	* @author yaochzh
	 */
	public List<Map<String, Object>> queryU8SoOrder(String custcode, String string) throws Exception;
	/**
	 * 
	* @Title: queryU8PoOrderByOrdercode 
	* @Description: TODO
	* @param @param id
	* @param @return
	* @return List<Map<String,Object>>
	 * @throws Exception 
	* @throws 
	* @author yaochzh
	 */
	public List<Map<String, Object>> queryU8SoOrderByOrdercode(String id) throws Exception;
}
