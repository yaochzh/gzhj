package com.supply.pickupplan.service.impl;
import com.supply.pickupplan.service.SoPickupplanServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.supply.pickupplan.entity.SoPickupplanEntity;
import com.supply.pickupplanb.entity.SoPickupplanBEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("soPickupplanService")
@Transactional
public class SoPickupplanServiceImpl extends CommonServiceImpl implements SoPickupplanServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((SoPickupplanEntity)entity);
 	}
	
	public void addMain(SoPickupplanEntity soPickupplan,
	        List<SoPickupplanBEntity> soPickupplanBList){
			//保存主信息
			this.save(soPickupplan);
		
			/**保存-提货计划子表*/
			for(SoPickupplanBEntity soPickupplanB:soPickupplanBList){
				//外键设置
				soPickupplanB.setFkId(soPickupplan.getId());
				this.save(soPickupplanB);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(soPickupplan);
	}

	
	public void updateMain(SoPickupplanEntity soPickupplan,
	        List<SoPickupplanBEntity> soPickupplanBList) {
		//保存主表信息
		this.saveOrUpdate(soPickupplan);
		//===================================================================================
		//获取参数
		Object id0 = soPickupplan.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-提货计划子表
	    String hql0 = "from SoPickupplanBEntity where 1 = 1 AND fK_ID = ? ";
	    List<SoPickupplanBEntity> soPickupplanBOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-提货计划子表
		if(soPickupplanBList!=null&&soPickupplanBList.size()>0){
		for(SoPickupplanBEntity oldE:soPickupplanBOldList){
			boolean isUpdate = false;
				for(SoPickupplanBEntity sendE:soPickupplanBList){
					//需要更新的明细数据-提货计划子表
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-提货计划子表
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-提货计划子表
			for(SoPickupplanBEntity soPickupplanB:soPickupplanBList){
				if(oConvertUtils.isEmpty(soPickupplanB.getId())){
					//外键设置
					soPickupplanB.setFkId(soPickupplan.getId());
					this.save(soPickupplanB);
				}
			}
		}
		    else {
			      deleteAllEntitie(soPickupplanBOldList);
			    }
		//执行更新操作配置的sql增强
 		this.doUpdateSql(soPickupplan);
	}

	
	public void delMain(SoPickupplanEntity soPickupplan) {
		//删除主表信息
		this.delete(soPickupplan);
		//===================================================================================
		//获取参数
		Object id0 = soPickupplan.getId();
		//===================================================================================
		//删除-提货计划子表
	    String hql0 = "from SoPickupplanBEntity where 1 = 1 AND fK_ID = ? ";
	    List<SoPickupplanBEntity> soPickupplanBOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(soPickupplanBOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(SoPickupplanEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(SoPickupplanEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(SoPickupplanEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,SoPickupplanEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{create_datetime}",String.valueOf(t.getCreateDatetime()));
 		sql  = sql.replace("#{update_datetime}",String.valueOf(t.getUpdateDatetime()));
 		sql  = sql.replace("#{pickupplancode}",String.valueOf(t.getPickupplancode()));
 		sql  = sql.replace("#{soordercode}",String.valueOf(t.getSoordercode()));
 		sql  = sql.replace("#{expiredate}",String.valueOf(t.getExpiredate()));
 		sql  = sql.replace("#{customername}",String.valueOf(t.getCustomername()));
 		sql  = sql.replace("#{materialcode}",String.valueOf(t.getMaterialcode()));
 		sql  = sql.replace("#{materialname}",String.valueOf(t.getMaterialname()));
 		sql  = sql.replace("#{spec}",String.valueOf(t.getSpec()));
 		sql  = sql.replace("#{unloadplace}",String.valueOf(t.getUnloadplace()));
 		sql  = sql.replace("#{store}",String.valueOf(t.getStore()));
 		sql  = sql.replace("#{delivercustomer}",String.valueOf(t.getDelivercustomer()));
 		sql  = sql.replace("#{surplusnum}",String.valueOf(t.getSurplusnum()));

 		sql  = sql.replace("#{planassnum}",String.valueOf(t.getPlanassnum()));
 		sql  = sql.replace("#{vnote}",String.valueOf(t.getVnote()));
 		sql  = sql.replace("#{dr}",String.valueOf(t.getDr()));
 		sql  = sql.replace("#{pickupbegindate}",String.valueOf(t.getPickupbegindate()));
 		sql  = sql.replace("#{carrier}",String.valueOf(t.getCarrier()));
 		sql  = sql.replace("#{unit}",String.valueOf(t.getUnit()));
 		sql  = sql.replace("#{billstatus}",String.valueOf(t.getBillstatus()));
 		sql  = sql.replace("#{ts}",String.valueOf(t.getTs()));
 		sql  = sql.replace("#{approve}",String.valueOf(t.getApprove()));
 		sql  = sql.replace("#{approvedate}",String.valueOf(t.getApprovedate()));
 		sql  = sql.replace("#{cancel}",String.valueOf(t.getCancel()));
 		sql  = sql.replace("#{canceldate}",String.valueOf(t.getCanceldate()));
 		sql  = sql.replace("#{soordernum}",String.valueOf(t.getSoordernum()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}