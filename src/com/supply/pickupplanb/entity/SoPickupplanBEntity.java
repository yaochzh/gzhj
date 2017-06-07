package com.supply.pickupplanb.entity;

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
 * @Description: 提货计划子表
 * @author onlineGenerator
 * @date 2016-07-06 13:11:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "so_pickupplan_b", schema = "")
@SuppressWarnings("serial")
public class SoPickupplanBEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**计划单号*/
	@Excel(name="计划单号")
	private java.lang.String pickupplan;
	/**车牌号*/
	@Excel(name="车牌号")
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
	/**标准载量*/
	@Excel(name="标准载量")
	private java.math.BigDecimal loadnum;
	/**预提包数*/
	@Excel(name="预提包数")
	private java.math.BigDecimal planpieces;
	/**预提数量*/
	@Excel(name="预提数量")
	private java.math.BigDecimal plannum;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String vnote;
	/**外键*/
	private java.lang.String fkId;
	/**删除标记*/
	private java.lang.String dr;
	/**收货数量*/
	@Excel(name="收货数量")
	private java.math.BigDecimal pickupnum;
	/**计划到货日期*/
	@Excel(name="计划到货日期",format = "yyyy-MM-dd")
	private java.util.Date plandate;
	/**包装类型*/
	@Excel(name="包装类型")
	private java.lang.String packtype;
	/**车辆状态*/
	@Excel(name="车辆状态")
	private java.lang.String carstatus;
	/**剩余数量*/
	@Excel(name="剩余数量")
	private java.math.BigDecimal surplusnum;
	/**承运单位*/
	@Excel(name="承运单位")
	private java.lang.String carrier;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计划单号
	 */
	@Column(name ="PICKUPPLAN",nullable=true,length=50)
	public java.lang.String getPickupplan(){
		return this.pickupplan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划单号
	 */
	public void setPickupplan(java.lang.String pickupplan){
		this.pickupplan = pickupplan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车牌号
	 */
	@Column(name ="VEHICLELICENSE",nullable=false,length=32)
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
	 *@return: java.math.BigDecimal  标准载量
	 */
	@Column(name ="LOADNUM",nullable=true,scale=2,length=9)
	public java.math.BigDecimal getLoadnum(){
		return this.loadnum;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  标准载量
	 */
	public void setLoadnum(java.math.BigDecimal loadnum){
		this.loadnum = loadnum;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  预提包数
	 */
	@Column(name ="PLANPIECES",nullable=true,scale=2,length=9)
	public java.math.BigDecimal getPlanpieces(){
		return this.planpieces;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  预提包数
	 */
	public void setPlanpieces(java.math.BigDecimal planpieces){
		this.planpieces = planpieces;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  预提数量
	 */
	@Column(name ="PLANNUM",nullable=true,scale=2,length=9)
	public java.math.BigDecimal getPlannum(){
		return this.plannum;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  预提数量
	 */
	public void setPlannum(java.math.BigDecimal plannum){
		this.plannum = plannum;
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
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  收货数量
	 */
	@Column(name ="PICKUPNUM",nullable=true,scale=2,length=9)
	public java.math.BigDecimal getPickupnum(){
		return this.pickupnum;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  收货数量
	 */
	public void setPickupnum(java.math.BigDecimal pickupnum){
		this.pickupnum = pickupnum;
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
	 *@return: java.lang.String  包装类型
	 */
	@Column(name ="PACKTYPE",nullable=true,length=32)
	public java.lang.String getPacktype(){
		return this.packtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装类型
	 */
	public void setPacktype(java.lang.String packtype){
		this.packtype = packtype;
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
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  剩余数量
	 */
	@Column(name ="SURPLUSNUM",nullable=true,scale=2,length=9)
	public java.math.BigDecimal getSurplusnum(){
		return this.surplusnum;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  剩余数量
	 */
	public void setSurplusnum(java.math.BigDecimal surplusnum){
		this.surplusnum = surplusnum;
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
