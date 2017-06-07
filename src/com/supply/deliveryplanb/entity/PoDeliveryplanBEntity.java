package com.supply.deliveryplanb.entity;

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
 * @Description: 送货计划子表
 * @author onlineGenerator
 * @date 2016-07-05 11:16:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "po_deliveryplan_b", schema = "")
@SuppressWarnings("serial")
public class PoDeliveryplanBEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**计划单号*/
	@Excel(name="计划单号")
	private java.lang.String deliveryplan;
	/**车号*/
	@Excel(name="车号")
	private java.lang.String vehiclelicense;
	/**车型*/
	@Excel(name="车型")
	private java.lang.String vehicletype;
	/**驾驶员*/
	@Excel(name="驾驶员")
	private java.lang.String drivername;
	/**身份证号*/
	@Excel(name="身份证号")
	private java.lang.String idcard;
	/**手机号*/
	@Excel(name="手机号")
	private java.lang.String phone;
	/**标准载重*/
	@Excel(name="标准载重")
	private java.math.BigDecimal loadnum;
	/**卸货地点*/
	@Excel(name="卸货地点")
	private java.lang.String unloadplace;
	/**含量*/
	@Excel(name="含量")
	private java.math.BigDecimal content;
	/**供应商送货数量*/
	@Excel(name="供应商送货数量")
	private java.math.BigDecimal deliverynum;
	/**送货日期*/
	@Excel(name="送货日期",format = "yyyy-MM-dd")
	private java.util.Date deliverydate;
	/**计划到货日期*/
	@Excel(name="计划到货日期",format = "yyyy-MM-dd")
	private java.util.Date plandate;
	/**折百*/
	@Excel(name="折百")
	private java.lang.String discou;
	/**车辆状态*/
	@Excel(name="车辆状态")
	private java.lang.String carstatus;
	/**承运单位*/
	@Excel(name="承运单位")
	private java.lang.String carrier;
	/**删除标记*/
	private java.lang.String dr;
	/**时间戳*/
	private java.util.Date ts;
	/**外键*/
	private java.lang.String fkId;
	
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
	 *@return: java.lang.String  计划单号
	 */
	@Column(name ="DELIVERYPLAN",nullable=true,length=50)
	public java.lang.String getDeliveryplan(){
		return this.deliveryplan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划单号
	 */
	public void setDeliveryplan(java.lang.String deliveryplan){
		this.deliveryplan = deliveryplan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车号
	 */
	@Column(name ="VEHICLELICENSE",nullable=false,length=32)
	public java.lang.String getVehiclelicense(){
		return this.vehiclelicense;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车号
	 */
	public void setVehiclelicense(java.lang.String vehiclelicense){
		this.vehiclelicense = vehiclelicense;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型
	 */
	@Column(name ="VEHICLETYPE",nullable=true,length=32)
	public java.lang.String getVehicletype(){
		return this.vehicletype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型
	 */
	public void setVehicletype(java.lang.String vehicletype){
		this.vehicletype = vehicletype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  驾驶员
	 */
	@Column(name ="DRIVERNAME",nullable=false,length=200)
	public java.lang.String getDrivername(){
		return this.drivername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  驾驶员
	 */
	public void setDrivername(java.lang.String drivername){
		this.drivername = drivername;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证号
	 */
	@Column(name ="IDCARD",nullable=false,length=32)
	public java.lang.String getIdcard(){
		return this.idcard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号
	 */
	public void setIdcard(java.lang.String idcard){
		this.idcard = idcard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号
	 */
	@Column(name ="PHONE",nullable=false,length=32)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  标准载重
	 */
	@Column(name ="LOADNUM",nullable=true,scale=2,length=9)
	public java.math.BigDecimal getLoadnum(){
		return this.loadnum;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  标准载重
	 */
	public void setLoadnum(java.math.BigDecimal loadnum){
		this.loadnum = loadnum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  卸货地点
	 */
	@Column(name ="UNLOADPLACE",nullable=true,length=200)
	public java.lang.String getUnloadplace(){
		return this.unloadplace;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卸货地点
	 */
	public void setUnloadplace(java.lang.String unloadplace){
		this.unloadplace = unloadplace;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  含量
	 */
	@Column(name ="CONTENT",nullable=true,scale=2,length=9)
	public java.math.BigDecimal getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  含量
	 */
	public void setContent(java.math.BigDecimal content){
		this.content = content;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  供应商送货数量
	 */
	@Column(name ="DELIVERYNUM",nullable=true,scale=2,length=9)
	public java.math.BigDecimal getDeliverynum(){
		return this.deliverynum;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  供应商送货数量
	 */
	public void setDeliverynum(java.math.BigDecimal deliverynum){
		this.deliverynum = deliverynum;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  送货日期
	 */
	@Column(name ="DELIVERYDATE",nullable=true,length=32)
	public java.util.Date getDeliverydate(){
		return this.deliverydate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  送货日期
	 */
	public void setDeliverydate(java.util.Date deliverydate){
		this.deliverydate = deliverydate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  计划到货日期
	 */
	@Column(name ="PLANDATE",nullable=true,length=32)
	public java.util.Date getPlandate(){
		return this.plandate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计划到货日期
	 */
	public void setPlandate(java.util.Date plandate){
		this.plandate = plandate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  折百
	 */
	@Column(name ="DISCOU",nullable=true,length=9)
	public java.lang.String getDiscou(){
		return this.discou;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  折百
	 */
	public void setDiscou(java.lang.String discou){
		this.discou = discou;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车辆状态
	 */
	@Column(name ="CARSTATUS",nullable=true,length=32)
	public java.lang.String getCarstatus(){
		return this.carstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车辆状态
	 */
	public void setCarstatus(java.lang.String carstatus){
		this.carstatus = carstatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  删除标记
	 */
	@Column(name ="DR",nullable=true,length=32)
	public java.lang.String getDr(){
		return this.dr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  删除标记
	 */
	public void setDr(java.lang.String dr){
		this.dr = dr;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  时间戳
	 */
	@Column(name ="TS",nullable=true,length=32)
	public java.util.Date getTs(){
		return this.ts;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  时间戳
	 */
	public void setTs(java.util.Date ts){
		this.ts = ts;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外键
	 */
	@Column(name ="FK_ID",nullable=true,length=32)
	public java.lang.String getFkId(){
		return this.fkId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外键
	 */
	public void setFkId(java.lang.String fkId){
		this.fkId = fkId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  承运单位
	 */
	@Column(name ="CARRIER",nullable=false,length=200)
	public java.lang.String getCarrier(){
		return this.carrier;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承运单位
	 */
	public void setCarrier(java.lang.String carrier){
		this.carrier = carrier;
	}
}
