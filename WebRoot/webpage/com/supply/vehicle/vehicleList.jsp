<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="vehicleList" checkbox="true" fitColumns="false" title="车辆档案" actionUrl="vehicleController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single" dictionary="bpm_status" width="120"></t:dgCol>
   <t:dgCol title="车牌号"  field="vehiclelicense"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="驾驶员"  field="cdriver"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="承运商"  field="ccarrier"  query="true" dictionary="bd_carrier,id,carriername" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="身份证号码"  field="vlicensenumber"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机号码"  field="vdrivermobile"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标准皮重"  field="nemptyload"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标准载重"  field="nload"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="封存"  field="bsealflag" replace="已封存_Y,正常_0,正常_N" queryMode="single"  width="120"></t:dgCol>
   <%--
   <t:dgCol title="使用状态"  field="fusestatusflag"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="注册时间"  field="dstartdate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
  
   <t:dgCol title="车型"  field="cvehicletype"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="行驶里程"  field="nrunmile"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="自定义项1"  field="def1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="自定义项2"  field="def2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="自定义项3"  field="def3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="自定义项4"  field="def4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="自定义项5"  field="def5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="vnote"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <%--<t:dgDelOpt title="删除" url="vehicleController.do?doDel&id={id}" />--%>
   <t:dgToolBar title="录入" icon="icon-add" url="vehicleController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="vehicleController.do?goUpdate" funname="update"></t:dgToolBar>
     <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="vehicleController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="vehicleController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/supply/vehicle/vehicleList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#vehicleListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#vehicleListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#vehicleListtb").find("input[name='dstartdate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'vehicleController.do?upload', "vehicleList");
}

//导出
function ExportXls() {
	JeecgExcelExport("vehicleController.do?exportXls","vehicleList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("vehicleController.do?exportXlsByT","vehicleList");
}
 </script>