package com.supply.common.inter;
/**
 * 
* @ClassName: IBillUtil 
* @Description: TODO
* @author yaochzh
* @date 2016-7-27 上午10:14:27 
*
 */
public interface IBillUtil {
    public <T> void updateBillStatus(T entity,String status); 
    public <T> void updateCarStatus(T entity,String status);
}
