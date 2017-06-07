<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="soPickupplanList" checkbox="false" showPageList="false" fitColumns="false" title="提货计划主表" actionUrl="soPickupplanController.do?datagridU8" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="销售订单号"  field="soordercode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户编码"  field="customercode" query="true" queryMode="single"  width="220"></t:dgCol>
   <t:dgCol title="客户名称"  field="customername" query="true" queryMode="single"  width="220"></t:dgCol>
   <t:dgCol title="物料编码"  field="materialcode"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物料名称"  field="materialname"    queryMode="single"  width="220"></t:dgCol>
   <t:dgCol title="规格型号"  field="spec"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="订单数量"  field="store"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="出库数量"  field="unloadplace" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="剩余数量"  field="surplusnum"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="单价"  field="vnote"    queryMode="group"  width="120"></t:dgCol>
    <t:dgToolBar title="确定" icon="icon-add" url="soPickupplanController.do?goAdd" funname="srcAdd" width="100%" height="100%"></t:dgToolBar>
    <%--
    t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="卸货地点"  field="unloadplace"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期时间"  field="createDatetime" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改日期时间"  field="updateDatetime"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计划单号"  field="pickupplancode"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货客户"  field="delivercustomer"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单据日期"  field="createDate" formatter="yyyy-MM-dd"  query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="删除标志"  field="dr"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="提单起始日"  field="pickupbegindate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="到期日期"  field="expiredate" formatter="yyyy-MM-dd"  query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="承运单位"  field="carrier"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单位"  field="unit"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单据状态"  field="billstatus"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="时间戳"  field="ts"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审核人"  field="approve"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审核时间"  field="approvedate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="作废人"  field="cancel"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="作废时间"  field="canceldate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="销售订单数量"  field="soordernum"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   --%></t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/supply/pickupplan/soPickupplanList.js"></script>		
 