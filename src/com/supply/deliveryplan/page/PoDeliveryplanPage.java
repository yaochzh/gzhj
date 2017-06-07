package com.supply.deliveryplan.page;

import com.supply.deliveryplanb.entity.PoDeliveryplanBEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

public class PoDeliveryplanPage
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

  @ExcelCollection(name="送货计划子表")
  private List<PoDeliveryplanBEntity> poDeliveryplanBList = new ArrayList();

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

  public String getDeliveryplancode()
  {
    return this.deliveryplancode;
  }

  public void setDeliveryplancode(String deliveryplancode)
  {
    this.deliveryplancode = deliveryplancode;
  }

  public String getContractcode()
  {
    return this.contractcode;
  }

  public void setContractcode(String contractcode)
  {
    this.contractcode = contractcode;
  }

  public Date getExpiredate()
  {
    return this.expiredate;
  }

  public void setExpiredate(Date expiredate)
  {
    this.expiredate = expiredate;
  }

  public String getSupplername()
  {
    return this.supplername;
  }

  public void setSupplername(String supplername)
  {
    this.supplername = supplername;
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

  public String getCarrier()
  {
    return this.carrier;
  }

  public void setCarriercode(String carriercode)
  {
    this.carriercode = carriercode;
  }

  public String getCarriercode()
  {
    return this.carriercode;
  }

  public void setCarrier(String carrier)
  {
    this.carrier = carrier;
  }

  public BigDecimal getSurplusnum()
  {
    return this.surplusnum;
  }

  public void setSurplusnum(BigDecimal surplusnum)
  {
    this.surplusnum = surplusnum;
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

  public String getPoordercode()
  {
    return this.poordercode;
  }

  public void setPoordercode(String poordercode)
  {
    this.poordercode = poordercode;
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

  public BigDecimal getPoordernum()
  {
    return this.poordernum;
  }

  public void setPoordernum(BigDecimal poordernum)
  {
    this.poordernum = poordernum;
  }

  public List<PoDeliveryplanBEntity> getPoDeliveryplanBList()
  {
    return this.poDeliveryplanBList;
  }
  public void setPoDeliveryplanBList(List<PoDeliveryplanBEntity> poDeliveryplanBList) {
    this.poDeliveryplanBList = poDeliveryplanBList;
  }
}