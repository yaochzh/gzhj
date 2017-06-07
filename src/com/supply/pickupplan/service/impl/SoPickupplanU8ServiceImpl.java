package com.supply.pickupplan.service.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.util.DynamicDBUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.common.SupplyConstants;
import com.supply.pickupplan.service.SoPickupplanU8ServiceI;

@Service("soPickupplanU8Service")
@Transactional
public class SoPickupplanU8ServiceImpl implements SoPickupplanU8ServiceI {

    public List<Map<String, Object>> queryU8SoOrder(String custcode,String custname)
	    throws Exception {
	String sql2 = " select * from " + SupplyConstants.U8SONAME
		+ " where cCusCode like '" + custname + "%' and cCusName like '"+custcode+"%' order by csocode" ;
	try {
	    List<Map<String, Object>> list = DynamicDBUtil.findList(
		    SupplyConstants.U8TEST, sql2, null);
	    if (list != null) {
		return list;
	    }
	} catch (Exception e) {
	    throw new Exception("U8数据库查询异常");
	}
	return null;
    }

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

    public List<Map<String, Object>> queryU8SoOrderByOrdercode(String id) throws Exception {
	String sql = "select * from " + SupplyConstants.U8SONAME
		+ " where cSOCode = " + id + "";
	try {
	    List<Map<String, Object>> list = DynamicDBUtil.findList(
		    SupplyConstants.U8TEST, sql, null);
	    if (list != null) {
		return list;
	    }
	} catch (Exception e) {
	    throw new Exception("U8数据库查询异常");
	}
	return null;
    }

}