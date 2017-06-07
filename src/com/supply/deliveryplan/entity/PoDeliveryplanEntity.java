package com.supply.deliveryplan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name="po_deliveryplan", schema="")
public class PoDeliveryplanEntity
  implements Serializable
{
  private String id;
  private String createName;
  private String createBy;
  private Date createDate;
  private String updateName;
  private String updateBy;
  private Date updateDate;
  private String sysOrgCode;
  private String sysCompanyCode;
  private String bpmStatus;
  private Date createDatetime;
  private String updateDatetime;

  @Excel(name="计划单号")
  private String deliveryplancode;

  @Excel(name="合同编号")
  private String contractcode;

  @Excel(name="到期日期", format="yyyy-MM-dd")
  private Date expiredate;

  @Excel(name="供应商编码")
  private String supplercode;

  @Excel(name="供应商名称")
  private String supplername;

  @Excel(name="物料编码")
  private String materialcode;

  @Excel(name="物料名称")
  private String materialname;

  @Excel(name="规格")
  private String spec;

  @Excel(name="卸货地点")
  private String unloadplace;

  @Excel(name="仓库")
  private String store;

  @Excel(name="承运单位")
  private String carrier;

  @Excel(name="承运单位编码")
  private String carriercode;

  @Excel(name="剩余数量")
  private BigDecimal surplusnum;

  @Excel(name="备注")
  private String vnote;
  private String dr;

  @Excel(name="采购订单号")
  private String poordercode;
  private String billstatus;
  private Date ts;
  private String approve;
  private Date approvedate;
  private String cancel;
  private Date canceldate;
  private BigDecimal poordernum;

  @Id
  @GeneratedValue(generator="paymentableGenerator")
  @GenericGenerator(name="paymentableGenerator", strategy="uuid")
  @Column(name="ID", nullable=false, length=36)
  public String getId()
  {
    return this.id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  @Column(name="CREATE_NAME", nullable=true, length=50)
  public String getCreateName()
  {
    return this.createName;
  }

  public void setCreateName(String createName)
  {
    this.createName = createName;
  }

  @Column(name="CREATE_BY", nullable=true, length=50)
  public String getCreateBy()
  {
    return this.createBy;
  }

  public void setCreateBy(String createBy)
  {
    this.createBy = createBy;
  }

  @Column(name="CREATE_DATE", nullable=true, length=20)
  public Date getCreateDate()
  {
    return this.createDate;
  }

  public void setCreateDate(Date createDate)
  {
    this.createDate = createDate;
  }

  @Column(name="UPDATE_NAME", nullable=true, length=50)
  public String getUpdateName()
  {
    return this.updateName;
  }

  public void setUpdateName(String updateName)
  {
    this.updateName = updateName;
  }

  @Column(name="UPDATE_BY", nullable=true, length=50)
  public String getUpdateBy()
  {
    return this.updateBy;
  }

  public void setUpdateBy(String updateBy)
  {
    this.updateBy = updateBy;
  }

  @Column(name="UPDATE_DATE", nullable=true, length=20)
  public Date getUpdateDate()
  {
    return this.updateDate;
  }

  public void setUpdateDate(Date updateDate)
  {
    this.updateDate = updateDate;
  }

  @Column(name="SYS_ORG_CODE", nullable=true, length=50)
  public String getSysOrgCode()
  {
    return this.sysOrgCode;
  }

  public void setSysOrgCode(String sysOrgCode)
  {
    this.sysOrgCode = sysOrgCode;
  }

  @Column(name="SYS_COMPANY_CODE", nullable=true, length=50)
  public String getSysCompanyCode()
  {
    return this.sysCompanyCode;
  }

  public void setSysCompanyCode(String sysCompanyCode)
  {
    this.sysCompanyCode = sysCompanyCode;
  }

  @Column(name="BPM_STATUS", nullable=true, length=32)
  public String getBpmStatus()
  {
    return this.bpmStatus;
  }

  public void setBpmStatus(String bpmStatus)
  {
    this.bpmStatus = bpmStatus;
  }

  @Column(name="CREATE_DATETIME", nullable=true, length=32)
  public Date getCreateDatetime()
  {
    return this.createDatetime;
  }

  public void setCreateDatetime(Date createDatetime)
  {
    this.createDatetime = createDatetime;
  }

  @Column(name="UPDATE_DATETIME", nullable=true, length=32)
  public String getUpdateDatetime()
  {
    return this.updateDatetime;
  }

  public void setUpdateDatetime(String updateDatetime)
  {
    this.updateDatetime = updateDatetime;
  }

  @Column(name="DELIVERYPLANCODE", nullable=true, length=50)
  public String getDeliveryplancode()
  {
    return this.deliveryplancode;
  }

  public void setDeliveryplancode(String deliveryplancode)
  {
    this.deliveryplancode = deliveryplancode;
  }

  @Column(name="CONTRACTCODE", nullable=true, length=32)
  public String getContractcode()
  {
    return this.contractcode;
  }

  public void setContractcode(String contractcode)
  {
    this.contractcode = contractcode;
  }

  @Column(name="EXPIREDATE", nullable=true, length=32)
  public Date getExpiredate()
  {
    return this.expiredate;
  }

  public void setExpiredate(Date expiredate)
  {
    this.expiredate = expiredate;
  }

  @Column(name="SUPPLERNAME", nullable=true, length=200)
  public String getSupplername()
  {
    return this.supplername;
  }

  public void setSupplername(String supplername)
  {
    this.supplername = supplername;
  }

  @Column(name="SUPPLERCODE", nullable=true, length=200)
  public String getSupplercode()
  {
    return this.supplercode;
  }

  public void setSupplercode(String supplercode)
  {
    this.supplercode = supplercode;
  }

  @Column(name="MATERIALCODE", nullable=true, length=200)
  public String getMaterialcode()
  {
    return this.materialcode;
  }

  public void setMaterialcode(String materialcode)
  {
    this.materialcode = materialcode;
  }

  @Column(name="MATERIALNAME", nullable=true, length=200)
  public String getMaterialname()
  {
    return this.materialname;
  }

  public void setMaterialname(String materialname)
  {
    this.materialname = materialname;
  }

  @Column(name="SPEC", nullable=true, length=32)
  public String getSpec()
  {
    return this.spec;
  }

  public void setSpec(String spec)
  {
    this.spec = spec;
  }

  @Column(name="UNLOADPLACE", nullable=true, length=200)
  public String getUnloadplace()
  {
    return this.unloadplace;
  }

  public void setUnloadplace(String unloadplace)
  {
    this.unloadplace = unloadplace;
  }

  @Column(name="STORE", nullable=true, length=200)
  public String getStore()
  {
    return this.store;
  }

  public void setStore(String store)
  {
    this.store = store;
  }

  @Column(name="CARRIER", nullable=true, length=200)
  public String getCarrier()
  {
    return this.carrier;
  }

  public void setCarrier(String carrier)
  {
    this.carrier = carrier;
  }

  @Column(name="CARRIERCODE", nullable=true, length=200)
  public String getCarriercode()
  {
    return this.carriercode;
  }

  public void setCarriercode(String carriercode)
  {
    this.carriercode = carriercode;
  }

  @Column(name="SURPLUSNUM", nullable=true, scale=2, length=9)
  public BigDecimal getSurplusnum()
  {
    return this.surplusnum;
  }

  public void setSurplusnum(BigDecimal surplusnum)
  {
    this.surplusnum = surplusnum;
  }

  @Column(name="VNOTE", nullable=true, length=200)
  public String getVnote()
  {
    return this.vnote;
  }

  public void setVnote(String vnote)
  {
    this.vnote = vnote;
  }

  @Column(name="DR", nullable=true, length=32)
  public String getDr()
  {
    return this.dr;
  }

  public void setDr(String dr)
  {
    this.dr = dr;
  }

  @Column(name="POORDERCODE", nullable=true, length=32)
  public String getPoordercode()
  {
    return this.poordercode;
  }

  public void setPoordercode(String poordercode)
  {
    this.poordercode = poordercode;
  }

  @Column(name="BILLSTATUS", nullable=true, length=32)
  public String getBillstatus()
  {
    return this.billstatus;
  }

  public void setBillstatus(String billstatus)
  {
    this.billstatus = billstatus;
  }

  @Column(name="TS", nullable=true, length=32)
  public Date getTs()
  {
    return this.ts;
  }

  public void setTs(Date ts)
  {
    this.ts = ts;
  }

  @Column(name="APPROVE", nullable=true, length=32)
  public String getApprove()
  {
    return this.approve;
  }

  public void setApprove(String approve)
  {
    this.approve = approve;
  }

  @Column(name="APPROVEDATE", nullable=true, length=32)
  public Date getApprovedate()
  {
    return this.approvedate;
  }

  public void setApprovedate(Date approvedate)
  {
    this.approvedate = approvedate;
  }

  @Column(name="CANCEL", nullable=true, length=32)
  public String getCancel()
  {
    return this.cancel;
  }

  public void setCancel(String cancel)
  {
    this.cancel = cancel;
  }

  @Column(name="CANCELDATE", nullable=true, length=32)
  public Date getCanceldate()
  {
    return this.canceldate;
  }

  public void setCanceldate(Date canceldate)
  {
    this.canceldate = canceldate;
  }

  @Column(name="POORDERNUM", nullable=true, scale=2, length=9)
  public BigDecimal getPoordernum()
  {
    return this.poordernum;
  }

  public void setPoordernum(BigDecimal poordernum)
  {
    this.poordernum = poordernum;
  }
}