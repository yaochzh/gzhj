<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>提货计划主表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
  <%--$('#cc').combogrid({    
	    delay: 500,    
	    mode: 'remote',    
	    url: 'soPickupplanController.do?doDel',    
	    idField: 'id',    
	    textField: 'name',    
	    columns: [[    
	        {field:'code',title:'Code',width:120,sortable:true},    
	        {field:'name',title:'Name',width:400,sortable:true}    
	    ]]    
	});--%>
	
	 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="soPickupplanController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${soPickupplanPage.id }">
					<input id="createBy" name="createBy" type="hidden" value="${soPickupplanPage.createBy }">
					<input id="updateBy" name="updateBy" type="hidden" value="${soPickupplanPage.updateBy }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${soPickupplanPage.bpmStatus }">
					<input id="updateDatetime" name="updateDatetime" type="hidden" value="${soPickupplanPage.updateDatetime }">
					<input id="dr" name="dr" type="hidden" value="${soPickupplanPage.dr }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">创建人名称:</label>
			</td>
			<td class="value">
		     	 <input id="createName" name="createName" type="text" style="width: 150px" class="inputxt"  value='${soPickupplanPage.createName}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">创建人名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">创建日期:</label>
			</td>
			<td class="value">
					  <input id="createDate" name="createDate" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()"  value='${soPickupplanPage.createDate}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">创建日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">更新人名称:</label>
			</td>
			<td class="value">
		     	 <input id="updateName" name="updateName" type="text" style="width: 150px" class="inputxt"  value='${soPickupplanPage.updateName}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">更新人名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">更新日期:</label>
			</td>
			<td class="value">
					  <input id="updateDate" name="updateDate" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()"  value='${soPickupplanPage.updateName}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">更新日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">所属部门:</label>
			</td>
			<td class="value">
		     	 <input id="sysOrgCode" name="sysOrgCode" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.updateName}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">所属部门</label>
			</td>
			<td align="right">
				<label class="Validform_label">所属公司:</label>
			</td>
			<td class="value">
		     	 <input id="sysCompanyCode" name="sysCompanyCode" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.updateName}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">所属公司</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">创建日期时间:</label>
			</td>
			<td class="value">
					  <input id="createDatetime" name="createDatetime" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()"  value='${soPickupplanPage.updateName}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">创建日期时间</label>
			</td>
			<td align="right">
				<label class="Validform_label">计划单号:</label>
			</td>
			<td class="value">
		     	 <input id="pickupplancode" name="pickupplancode" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.updateName}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">计划单号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">提货单号:</label>
			</td>
			<td class="value">
		     	 <input id="pickupcode" name="pickupcode" type="text" style="width: 150px" class="inputxt"  value='${soPickupplanPage.pickupcode}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提货单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">到期日期:</label>
			</td>
			<td class="value">
					  <input id="expiredate" name="expiredate" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()"  value='${soPickupplanPage.expiredate}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">到期日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">客户:</label>
			</td>
			<td class="value">
		     	 <input id="customername" name="customername" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.customername}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户</label>
			</td>
			<td align="right">
				<label class="Validform_label">物料编码:</label>
			</td>
			<td class="value">
		     	 <input id="materialcode" name="materialcode" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.materialcode}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物料编码</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">物料名称:</label>
			</td>
			<td class="value">
		     	 <input id="materialname" name="materialname" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.materialname}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物料名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">规格型号:</label>
			</td>
			<td class="value">
		     	 <input id="spec" name="spec" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.spec}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">规格型号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">卸货地点:</label>
			</td>
			<td class="value">
		     	 <input id="unloadplace" name="unloadplace" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.unloadplace}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">卸货地点</label>
			</td>
			<td align="right">
				<label class="Validform_label">仓库:</label>
			</td>
			<td class="value">
		     	 <input id="store" name="store" type="text" style="width: 150px" class="inputxt" value='${soPickupplanPage.store}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">仓库</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">收货单位:</label>
			</td>
			<td class="value">
		     	 <input id="deliver" name="deliver" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收货单位</label>
			</td>
			<td align="right">
				<label class="Validform_label">剩余数量:</label>
			</td>
			<td class="value">
		     	 <input id="surplusnum" name="surplusnum" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">剩余数量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">备注:</label>
			</td>
			<td class="value">
		     	 <input id="vnote" name="vnote" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
			<td align="right">
				<label class="Validform_label">包装类型:</label>
			</td>
			<td class="value">
		     	 <input id="packtype" name="packtype" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">提单起始日:</label>
			</td>
			<td class="value">
					  <input id="pickupbegindate" name="pickupbegindate" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">提单起始日</label>
			</td>
			<td align="right">
				<label class="Validform_label">承运单位:</label>
			</td>
			<td class="value">
		     	 <input id="carrier" name="carrier" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">承运单位</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="soPickupplanController.do?soPickupplanBList&id=${soPickupplanPage.id}" icon="icon-search" title="提货计划子表" id="soPickupplanB"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_soPickupplanB_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].pickupplan" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;" >
				  </td>
				  <td align="left">
				  <select id="cc" class="easyui-combogrid" name="soPickupplanBList[#index#].vehiclelicense" maxlength="32" style="width:120px;" data-options="    
				            panelWidth:450,    
				            value:'006',    
				            idField:'code',    
				            textField:'name',    
				            url:'soPickupplanController.do?doDel',    
				            columns:[[    
				                {field:'code',title:'Code',width:60},    
				                {field:'name',title:'Name',width:100},    
				                {field:'addr',title:'Address',width:120},    
				                {field:'col4',title:'Col41',width:100}    
				            ]]    
       			 "></select>
					  <label class="Validform_label" style="display: none;">车牌号</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].vehicletype" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">车型</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].drivername" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">司机姓名</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].idcard" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">身份证号</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].phone" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">手机号</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].loadnum" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">核载量</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].planpieces" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">预装件数</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].plannum" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">预装数量</label>
				  </td>
				  <td align="left">
					  	<input name="soPickupplanBList[#index#].vnote" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">备注</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/supply/pickup/soPickupplan.js"></script>
	