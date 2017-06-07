package com.supply.pickupplan.page;

import com.supply.pickupplanb.entity.SoPickupplanBEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

public class SoPickupplanPage
  implements Serializable
{
  private String id;
  private String createName;
  private String createBy;

  @Excel(name="创建日期", format="yyyy-MM-dd")
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
  private String pickupplancode;

  @Excel(name="销售订单号")
  private String soordercode;

  @Excel(name="到期日期", format="yyyy-MM-dd")
  private Date expiredate;

  @Excel(name="客户名称")
  private String customername;

  @Excel(name="物料编码")
  private String materialcode;

  @Excel(name="物料名称")
  private String materialname;

  @Excel(name="规格型号")
  private String spec;
  private String unloadplace;

  @Excel(name="仓库")
  private String store;

  @Excel(name="收货客户")
  private String delivercustomer;

  @Excel(name="剩余数量")
  private BigDecimal surplusnum;

  @Excel(name="计划数量")
  private BigDecimal planassnum;

  @Excel(name="备注")
  private String vnote;
  private String dr;
  private Date pickupbegindate;

  @Excel(name="承运单位")
  private String carrier;

  @Excel(name="承运单位编码")
  private String carriercode;

  @Excel(name="单位")
  private String unit;
  private String billstatus;
  private Date ts;
  private String approve;
  private Date approvedate;
  private String cancel;
  private Date canceldate;
  private BigDecimal soordernum;

  @ExcelCollection(name="提货计划子表")
  private List<SoPickupplanBEntity> soPickupplanBList = new ArrayList();

  public String getId()
  {
    return this.id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getCreateName()
  {
    return this.createName;
  }

  public void setCreateName(String createName)
  {
    this.createName = createName;
  }

  public String getCreateBy()
  {
    return this.createBy;
  }

  public void setCreateBy(String createBy)
  {
    this.createBy = createBy;
  }

  public Date getCreateDate()
  {
    return this.createDate;
  }

  public void setCreateDate(Date createDate)
  {
    this.createDate = createDate;
  }

  public String getUpdateName()
  {
    return this.updateName;
  }

  public void setUpdateName(String updateName)
  {
    this.updateName = updateName;
  }

  public String getUpdateBy()
  {
    return this.updateBy;
  }

  public void setUpdateBy(String updateBy)
  {
    this.updateBy = updateBy;
  }

  public Date getUpdateDate()
  {
    return this.updateDate;
  }

  public void setUpdateDate(Date updateDate)
  {
    this.updateDate = updateDate;
  }

  public String getSysOrgCode()
  {
    return this.sysOrgCode;
  }

  public void setSysOrgCode(String sysOrgCode)
  {
    this.sysOrgCode = sysOrgCode;
  }

  public String getSysCompanyCode()
  {
    return this.sysCompanyCode;
  }

  public void setSysCompanyCode(String sysCompanyCode)
  {
    this.sysCompanyCode = sysCompanyCode;
  }

  public String getBpmStatus()
  {
    return this.bpmStatus;
  }

  public void setBpmStatus(String bpmStatus)
  {
    this.bpmStatus = bpmStatus;
  }

  public Date getCreateDatetime()
  {
    return this.createDatetime;
  }

  public void setCreateDatetime(Date createDatetime)
  {
    this.createDatetime = createDatetime;
  }

  public String getUpdateDatetime()
  {
    return this.updateDatetime;
  }

  public void setUpdateDatetime(String updateDatetime)
  {
    this.updateDatetime = updateDatetime;
  }

  public String getPickupplancode()
  {
    return this.pickupplancode;
  }

  public void setPickupplancode(String pickupplancode)
  {
    this.pickupplancode = pickupplancode;
  }

  public String getSoordercode()
  {
    return this.soordercode;
  }

  public void setSoordercode(String soordercode)
  {
    this.soordercode = soordercode;
  }

  public Date getExpiredate()
  {
    return this.expiredate;
  }

  public void setExpiredate(Date expiredate)
  {
    this.expiredate = expiredate;
  }

  public String getCustomername()
  {
    return this.customername;
  }

  public void setCustomername(String customername)
  {
    this.customername = customername;
  }

  public String getMaterialcode()
  {
    return this.materialcode;
  }

  public void setMaterialcode(String materialcode)
  {
    this.materialcode = materialcode;
  }

  public String getMaterialname()
  {
    return this.materialname;
  }

  public void setMaterialname(String materialname)
  {
    this.materialname = materialname;
  }

  public String getSpec()
  {
    return this.spec;
  }

  public void setSpec(String spec)
  {
    this.spec = spec;
  }

  public String getUnloadplace()
  {
    return this.unloadplace;
  }

  public void setUnloadplace(String unloadplace)
  {
    this.unloadplace = unloadplace;
  }

  public String getStore()
  {
    return this.store;
  }

  public void setStore(String store)
  {
    this.store = store;
  }

  public String getDelivercustomer()
  {
    return this.delivercustomer;
  }

  public void setDelivercustomer(String delivercustomer)
  {
    this.delivercustomer = delivercustomer;
  }

  public BigDecimal getSurplusnum()
  {
    return this.surplusnum;
  }

  public void setSurplusnum(BigDecimal surplusnum)
  {
    this.surplusnum = surplusnum;
  }

  public BigDecimal getPlanassnum()
  {
    return this.planassnum;
  }

  public void setPlanassnum(BigDecimal planassnum)
  {
    this.planassnum = planassnum;
  }

  public String getVnote()
  {
    return this.vnote;
  }

  public void setVnote(String vnote)
  {
    this.vnote = vnote;
  }

  public String getDr()
  {
    return this.dr;
  }

  public void setDr(String dr)
  {
    this.dr = dr;
  }

  public Date getPickupbegindate()
  {
    return this.pickupbegindate;
  }

  public void setPickupbegindate(Date pickupbegindate)
  {
    this.pickupbegindate = pickupbegindate;
  }

  public String getCarrier()
  {
    return this.carrier;
  }

  public void setCarrier(String carrier)
  {
    this.carrier = carrier;
  }

  public String getCarriercode()
  {
    return this.carriercode;
  }

  public void setCarriercode(String carriercode)
  {
    this.carriercode = carriercode;
  }

  public String getUnit()
  {
    return this.unit;
  }

  public void setUnit(String unit)
  {
    this.unit = unit;
  }

  public String getBillstatus()
  {
    return this.billstatus;
  }

  public void setBillstatus(String billstatus)
  {
    this.billstatus = billstatus;
  }

  public Date getTs()
  {
    return this.ts;
  }

  public void setTs(Date ts)
  {
    this.ts = ts;
  }

  public String getApprove()
  {
    return this.approve;
  }

  public void setApprove(String approve)
  {
    this.approve = approve;
  }

  public Date getApprovedate()
  {
    return this.approvedate;
  }

  public void setApprovedate(Date approvedate)
  {
    this.approvedate = approvedate;
  }

  public String getCancel()
  {
    return this.cancel;
  }

  public void setCancel(String cancel)
  {
    this.cancel = cancel;
  }

  public Date getCanceldate()
  {
    return this.canceldate;
  }

  public void setCanceldate(Date canceldate)
  {
    this.canceldate = canceldate;
  }

  public BigDecimal getSoordernum()
  {
    return this.soordernum;
  }

  public void setSoordernum(BigDecimal soordernum)
  {
    this.soordernum = soordernum;
  }

  public List<SoPickupplanBEntity> getSoPickupplanBList()
  {
    return this.soPickupplanBList;
  }
  public void setSoPickupplanBList(List<SoPickupplanBEntity> soPickupplanBList) {
    this.soPickupplanBList = soPickupplanBList;
  }
}