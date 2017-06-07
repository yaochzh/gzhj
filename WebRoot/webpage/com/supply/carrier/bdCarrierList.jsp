<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="bdCarrierList" checkbox="true" fitColumns="false" title="承运单位" actionUrl="bdCarrierController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单位编码"  field="carriercode"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单位名称"  field="carriername"   query="true" queryMode="single"  width="220"></t:dgCol>
   <t:dgCol title="上级单位名称"  field="fathercarrier" dictionary="bd_carrier,id,carriername" queryMode="single"  width="220"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"    queryMode="single"  width="220"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="220"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single" dictionary="bpm_status" width="120"></t:dgCol>
   <t:dgCol title="是否封存"  field="bsealflag"  width="120"  replace="已封存_Y,正常_0,正常_N" ></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="bdCarrierController.do?doDel&id={id}" /> --%>
   <t:dgToolBar title="录入" icon="icon-add" url="bdCarrierController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="bdCarrierController.do?goUpdate" funname="update"></t:dgToolBar>
    <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="bdCarrierController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="bdCarrierController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/supply/carrier/bdCarrierList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#bdCarrierListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#bdCarrierListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'bdCarrierController.do?upload', "bdCarrierList");
}

//导出
function ExportXls() {
	JeecgExcelExport("bdCarrierController.do?exportXls","bdCarrierList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("bdCarrierController.do?exportXlsByT","bdCarrierList");
}
 </script>