<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="soPickupplanList" checkbox="true"  fitColumns="false" fit="true" title="提货计划主表" actionUrl="soPickupplanController.do?datagrid" idField="id" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计划单号"  field="pickupplancode"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提货单号"  field="pickupcode"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="到期日期"  field="expiredate" formatter="yyyy-MM-dd"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户"  field="customername"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物料编码"  field="materialcode"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物料名称"  field="materialname"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="规格型号"  field="spec"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="卸货地点"  field="unloadplace"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="仓库"  field="store"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货单位"  field="deliver"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="剩余数量"  field="surplusnum"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="vnote"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="删除标志"  field="dr"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="包装类型"  field="packtype"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提单起始日"  field="pickupbegindate" formatter="yyyy-MM-dd"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="承运单位"  field="carrier"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期时间"  field="createDatetime" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改日期时间"  field="updateDatetime"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="soPickupplanController.do?doDel&id={id}" />
   <t:dgToolBar title="参照销售订单" icon="icon-add" funname="querySrc" url="soPickupplanController.do?querySrc" width="1800" height="500"></t:dgToolBar>
   <t:dgToolBar title="录入" icon="icon-add" url="soPickupplanController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="soPickupplanController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="soPickupplanController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <!--<t:dgToolBar title="审核" icon="icon-put" funname="szqm"  ></t:dgToolBar>  -->
   <t:dgToolBar title="查看" icon="icon-search" url="soPickupplanController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
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
function szqm(id) {
	createwindow('审核', 'soPickupplanController.do?doDel&id=' + id);
}

//导入
function ImportXls() {
	openuploadwin('Excel导入', 'soPickupplanController.do?upload', "soPickupplanList");
}

//导出
function ExportXls() {
	JeecgExcelExport("soPickupplanController.do?exportXls","soPickupplanList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("soPickupplanController.do?exportXlsByT","soPickupplanList");
}
 </script>