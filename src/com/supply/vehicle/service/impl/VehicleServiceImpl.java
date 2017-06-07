package com.supply.vehicle.service.impl;
import com.supply.vehicle.service.VehicleServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.supply.vehicle.entity.VehicleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.io.Serializable;

@Service("vehicleService")
@Transactional
public class VehicleServiceImpl extends CommonServiceImpl implements VehicleServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((VehicleEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((VehicleEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((VehicleEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(VehicleEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(VehicleEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(VehicleEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,VehicleEntity t){
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
 		sql  = sql.replace("#{bsealflag}",String.valueOf(t.getBsealflag()));
 		sql  = sql.replace("#{vehiclelicense}",String.valueOf(t.getVehiclelicense()));
 		sql  = sql.replace("#{cdriver}",String.valueOf(t.getCdriver()));
 		sql  = sql.replace("#{vdrivermobile}",String.valueOf(t.getVdrivermobile()));
 		sql  = sql.replace("#{vlicensenumber}",String.valueOf(t.getVlicensenumber()));
 		sql  = sql.replace("#{fusestatusflag}",String.valueOf(t.getFusestatusflag()));
 		sql  = sql.replace("#{ccarrier}",String.valueOf(t.getCcarrier()));
 		sql  = sql.replace("#{cvehicletype}",String.valueOf(t.getCvehicletype()));
 		sql  = sql.replace("#{dstartdate}",String.valueOf(t.getDstartdate()));
 		sql  = sql.replace("#{nemptyload}",String.valueOf(t.getNemptyload()));
 		sql  = sql.replace("#{nload}",String.valueOf(t.getNload()));
 		sql  = sql.replace("#{nrunmile}",String.valueOf(t.getNrunmile()));
 		sql  = sql.replace("#{def1}",String.valueOf(t.getDef1()));
 		sql  = sql.replace("#{def2}",String.valueOf(t.getDef2()));
 		sql  = sql.replace("#{def3}",String.valueOf(t.getDef3()));
 		sql  = sql.replace("#{def4}",String.valueOf(t.getDef4()));
 		sql  = sql.replace("#{def5}",String.valueOf(t.getDef5()));
 		sql  = sql.replace("#{vnote}",String.valueOf(t.getVnote()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}