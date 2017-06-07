package com.supply.deliveryplan.service.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.util.DynamicDBUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.common.SupplyConstants;
import com.supply.deliveryplan.service.PoDeliveryplanU8ServiceI;

@Service("poDeliveryplanU8Service")
@Transactional
/**
 * 
 * @ClassName: PoDeliveryplanU8ServiceImpl 
 * @Description: TODO
 * @author yaochzh
 * @date 2016-7-26 下午5:31:04 
 *
 */
public class PoDeliveryplanU8ServiceImpl implements PoDeliveryplanU8ServiceI {
    /*
     * (non Javadoc)
     * 
     * @Title: queryU8PoOrder
     * 
     * @Description: TODO
     * 
     * @return
     * 
     * @see
     * com.supply.deliveryplan.service.PoDeliveryplanU8ServiceI#queryU8PoOrder()
     */
    public List<Map<String, Object>> queryU8PoOrder(String custcode,String custname)
	    throws Exception {
	String sql2 = " select * from " + SupplyConstants.U8PONAME
		+ " where CVENCODE like '" + custname + "%' and CVENNAME like '"+custcode+"%' order by poid";
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

    /*
     * (non Javadoc)
     * 
     * @Title: queryU8PoOrderByOrdercode
     * 
     * @Description: 根据订单号查询U8采购订单信息
     * 
     * @param id
     * 
     * @return
     * 
     * @see com.supply.deliveryplan.service.PoDeliveryplanU8ServiceI#
     * queryU8PoOrderByOrdercode(java.lang.String)
     */
    public List<Map<String, Object>> queryU8PoOrderByOrdercode(String id)
	    throws Exception {
	String sql = "select * from " + SupplyConstants.U8PONAME
		+ " where POID = " + id + "";
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
