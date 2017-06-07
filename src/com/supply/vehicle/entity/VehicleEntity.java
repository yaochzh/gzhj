package com.supply.vehicle.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 车辆档案
 * @author onlineGenerator
 * @date 2016-07-06 16:14:46
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bd_vehicle", schema = "")
@SuppressWarnings("serial")
public class VehicleEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	@Excel(name="创建人名称")
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name="创建日期",format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**封存*/
	@Excel(name="封存")
	private java.lang.String bsealflag;
	/**车牌号*/
	@Excel(name="车牌号")
	private java.lang.String vehiclelicense;
	/**司机*/
	@Excel(name="司机")
	private java.lang.String cdriver;
	/**司机手机号*/
	@Excel(name="司机手机号")
	private java.lang.String vdrivermobile;
	/**许可证编号*/
	@Excel(name="许可证编号")
	private java.lang.String vlicensenumber;
	/**使用状态*/
	@Excel(name="使用状态")
	private java.lang.String fusestatusflag;
	/**承运商*/
	@Excel(name="承运商")
	private java.lang.String ccarrier;
	/**车型*/
	@Excel(name="车型")
	private java.lang.String cvehicletype;
	/**开始运营日期*/
	@Excel(name="开始运营日期",format = "yyyy-MM-dd")
	private java.util.Date dstartdate;
	/**空车重量*/
	@Excel(name="空车重量")
	private java.lang.Double nemptyload;
	/**载重*/
	@Excel(name="载重")
	private java.lang.Double nload;
	/**行驶里程*/
	@Excel(name="行驶里程")
	private java.lang.Double nrunmile;
	/**自定义项1*/
	private java.lang.String def1;
	/**自定义项2*/
	private java.lang.String def2;
	/**自定义项3*/
	private java.lang.String def3;
	/**自定义项4*/
	private java.lang.String def4;
	/**自定义项5*/
	private java.lang.String def5;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String vnote;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  封存
	 */
	@Column(name ="BSEALFLAG",nullable=true,length=32)
	public java.lang.String getBsealflag(){
		return this.bsealflag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  封存
	 */
	public void setBsealflag(java.lang.String bsealflag){
		this.bsealflag = bsealflag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车牌号
	 */
	@Column(name ="VEHICLELICENSE",nullable=true,length=32)
	public java.lang.String getVehiclelicense(){
		return this.vehiclelicense;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车牌号
	 */
	public void setVehiclelicense(java.lang.String vehiclelicense){
		this.vehiclelicense = vehiclelicense;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机
	 */
	@Column(name ="CDRIVER",nullable=true,length=32)
	public java.lang.String getCdriver(){
		return this.cdriver;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机
	 */
	public void setCdriver(java.lang.String cdriver){
		this.cdriver = cdriver;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司机手机号
	 */
	@Column(name ="VDRIVERMOBILE",nullable=true,length=32)
	public java.lang.String getVdrivermobile(){
		return this.vdrivermobile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司机手机号
	 */
	public void setVdrivermobile(java.lang.String vdrivermobile){
		this.vdrivermobile = vdrivermobile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  许可证编号
	 */
	@Column(name ="VLICENSENUMBER",nullable=true,length=32)
	public java.lang.String getVlicensenumber(){
		return this.vlicensenumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  许可证编号
	 */
	public void setVlicensenumber(java.lang.String vlicensenumber){
		this.vlicensenumber = vlicensenumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用状态
	 */
	@Column(name ="FUSESTATUSFLAG",nullable=true,length=32)
	public java.lang.String getFusestatusflag(){
		return this.fusestatusflag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用状态
	 */
	public void setFusestatusflag(java.lang.String fusestatusflag){
		this.fusestatusflag = fusestatusflag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  承运商
	 */
	@Column(name ="CCARRIER",nullable=true,length=200)
	public java.lang.String getCcarrier(){
		return this.ccarrier;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承运商
	 */
	public void setCcarrier(java.lang.String ccarrier){
		this.ccarrier = ccarrier;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型
	 */
	@Column(name ="CVEHICLETYPE",nullable=true,length=32)
	public java.lang.String getCvehicletype(){
		return this.cvehicletype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型
	 */
	public void setCvehicletype(java.lang.String cvehicletype){
		this.cvehicletype = cvehicletype;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始运营日期
	 */
	@Column(name ="DSTARTDATE",nullable=true,length=32)
	public java.util.Date getDstartdate(){
		return this.dstartdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始运营日期
	 */
	public void setDstartdate(java.util.Date dstartdate){
		this.dstartdate = dstartdate;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  空车重量
	 */
	@Column(name ="NEMPTYLOAD",nullable=true,scale=2,length=32)
	public java.lang.Double getNemptyload(){
		return this.nemptyload;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  空车重量
	 */
	public void setNemptyload(java.lang.Double nemptyload){
		this.nemptyload = nemptyload;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  载重
	 */
	@Column(name ="NLOAD",nullable=true,scale=2,length=32)
	public java.lang.Double getNload(){
		return this.nload;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  载重
	 */
	public void setNload(java.lang.Double nload){
		this.nload = nload;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  行驶里程
	 */
	@Column(name ="NRUNMILE",nullable=true,scale=2,length=32)
	public java.lang.Double getNrunmile(){
		return this.nrunmile;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  行驶里程
	 */
	public void setNrunmile(java.lang.Double nrunmile){
		this.nrunmile = nrunmile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  自定义项1
	 */
	@Column(name ="DEF1",nullable=true,length=200)
	public java.lang.String getDef1(){
		return this.def1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  自定义项1
	 */
	public void setDef1(java.lang.String def1){
		this.def1 = def1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  自定义项2
	 */
	@Column(name ="DEF2",nullable=true,length=200)
	public java.lang.String getDef2(){
		return this.def2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  自定义项2
	 */
	public void setDef2(java.lang.String def2){
		this.def2 = def2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  自定义项3
	 */
	@Column(name ="DEF3",nullable=true,length=200)
	public java.lang.String getDef3(){
		return this.def3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  自定义项3
	 */
	public void setDef3(java.lang.String def3){
		this.def3 = def3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  自定义项4
	 */
	@Column(name ="DEF4",nullable=true,length=200)
	public java.lang.String getDef4(){
		return this.def4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  自定义项4
	 */
	public void setDef4(java.lang.String def4){
		this.def4 = def4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  自定义项5
	 */
	@Column(name ="DEF5",nullable=true,length=200)
	public java.lang.String getDef5(){
		return this.def5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  自定义项5
	 */
	public void setDef5(java.lang.String def5){
		this.def5 = def5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="VNOTE",nullable=true,length=200)
	public java.lang.String getVnote(){
		return this.vnote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setVnote(java.lang.String vnote){
		this.vnote = vnote;
	}
}
