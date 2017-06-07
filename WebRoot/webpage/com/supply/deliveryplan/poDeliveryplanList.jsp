<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="poDeliveryplanList" checkbox="true" fitColumns="false" fit="true"  title="送货计划主表" actionUrl="poDeliveryplanController.do?datagrid" idField="id"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单据状态"  field="billstatus" replace="保存_0,审核_1,关闭_2,作废_-1"   width="60"></t:dgCol>
   <t:dgCol title="计划单号"  field="deliveryplancode"   query="true" queryMode="single"  width="170"></t:dgCol>
   <t:dgCol title="合同编号"  field="contractcode"   queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="采购订单号"  field="poordercode"    queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="物料编码"  field="materialcode"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物料名称"  field="materialname"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="规格"  field="spec"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商名称"  field="supplername"  query="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="承运商"  field="carrier"  query="true"  dictionary="bd_carrier,id,carriername" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="采购订单数量"  field="poordernum"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="剩余数量"  field="surplusnum"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="仓库"  field="store"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单据日期"  field="createDate" formatter="yyyy-MM-dd"  query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="到期日期"  field="expiredate" formatter="yyyy-MM-dd"  query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="卸货地点"  field="unloadplace"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="vnote"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="删除标志"  field="dr"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="时间戳"  field="ts" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审核人"  field="approve"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审核日期"  field="approvedate" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="作废人"  field="cancel"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="作废日期"  field="canceldate" formatter="yyyy-MM-dd hh:mm:ss" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single" dictionary="bpm_status" width="120"></t:dgCol><%--
   <t:dgCol title="创建日期时间"  field="createDatetime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="承运单位"  field="carrier"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期时间"  field="updateDatetime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="poDeliveryplanController.do?doDel&id={id}" />
   <t:dgToolBar title="新增" operationCode = "add" icon="icon-add" url="poDeliveryplanController.do?querySrc" funname="querySrc" width="1200" height="500"></t:dgToolBar>
   <t:dgToolBar title="编辑" operationCode = "update" icon="icon-edit" url="poDeliveryplanController.do?goUpdate" funname="update" width="1200" height="500"></t:dgToolBar>
   <%-- <t:dgToolBar title="批量删除"  icon="icon-remove" url="poDeliveryplanController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <t:dgToolBar title="审核"  icon="icon-edit" url="poDeliveryplanController.do?doApprove" funname="approveALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="poDeliveryplanController.do?goUpdate" funname="detail" width="1200" height="500"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/supply/deliveryplan/poDeliveryplanList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#poDeliveryplanListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#poDeliveryplanListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#poDeliveryplanListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#poDeliveryplanListtb").find("input[name='createDatetime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#poDeliveryplanListtb").find("input[name='updateDatetime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#poDeliveryplanListtb").find("input[name='expiredate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#poDeliveryplanListtb").find("input[name='expiredate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#poDeliveryplanListtb").find("input[name='ts']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#poDeliveryplanListtb").find("input[name='approvedate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#poDeliveryplanListtb").find("input[name='canceldate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'poDeliveryplanController.do?upload', "poDeliveryplanList");
}

//导出
function ExportXls() {
	JeecgExcelExport("poDeliveryplanController.do?exportXls","poDeliveryplanList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("poDeliveryplanController.do?exportXlsByT","poDeliveryplanList");
}
 </script>