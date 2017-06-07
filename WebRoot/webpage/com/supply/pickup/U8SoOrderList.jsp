<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px" >
  <t:datagrid name="soPickupplanList" checkbox="false"  fitColumns="false" fit="true" title="U8销售订单" actionUrl="soPickupplanController.do?datagridU8" idField="id" queryMode="group">
   <t:dgCol title="id"  field="id"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计划单号"  field="pickupplancode"   query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提货单号"  field="pickupcode"   query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="到期日期"  field="expiredate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户"  field="customername"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物料编码"  field="materialcode"    query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物料名称"  field="materialname"    query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="规格型号"  field="spec"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="卸货地点"  field="unloadplace"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="仓库"  field="store"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货单位"  field="deliver"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="剩余数量"  field="surplusnum"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="vnote"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="删除标志"  field="dr"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="包装类型"  field="packtype"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提单起始日"  field="pickupbegindate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="承运单位"  field="carrier"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期时间"  field="createDatetime" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改日期时间"  field="updateDatetime"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgToolBar title="录入" icon="icon-add" url="soPickupplanController.do?goAdd" funname="srcAdd" width="100%" height="100%"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/supply/pickup/soPickupplanList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#soPickupplanListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#soPickupplanListtb").find("input[name='updateDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#soPickupplanListtb").find("input[name='updateDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#soPickupplanListtb").find("input[name='createDatetime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#soPickupplanListtb").find("input[name='createDatetime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#soPickupplanListtb").find("input[name='expiredate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#soPickupplanListtb").find("input[name='pickupbegindate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 </script>