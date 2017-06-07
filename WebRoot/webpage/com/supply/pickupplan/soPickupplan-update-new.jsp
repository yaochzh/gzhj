<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  
  function checkvehicle(){ 
	  var size = $('#add_soPickupplanB_table tr').length;
	  var surplusnum = document.getElementById("surplusnum").value;
	   var planassnum = document.getElementById("planassnum").value;
	   if(eval(planassnum) > eval(surplusnum)) {
		   $.messager.alert('错误', "计划数量不能大于剩余数量！");
          return false;
	   }
	  for(var i=0;i<size;i++) {
		  var vehiclelicense = document.getElementById("soPickupplanBList["+i+"].vehiclelicense").value;
		  if(vehiclelicense.length==0 ){
			  $.messager.alert('错误', "车号不能为空！");
	            return false;
		  }
	  }
	  for(var i=0;i<size;i++) {
		  for(var j=i+1;j<size;j++) {
			  var vehiclelicense = document.getElementById("soPickupplanBList["+i+"].vehiclelicense").value;
			  var vehiclelicense1 = document.getElementById("soPickupplanBList["+j+"].vehiclelicense").value;
			  if(vehiclelicense==vehiclelicense1){
				  $.messager.alert('错误', "车号不能重复！");  
				  return false;
			  }
		  }
	  }
	}
  

  function myChange(code,id)	{
		var str = id.split(".");
  	$.ajax({
  		type : "POST",
  		url : "soPickupplanController.do?queryByCode&code="+code+"&id="+id+"",// 请求的action路径
  		error : function() {// 请求失败处理函数
  			alert("请检查改车牌号对应的车辆档案信息是否完整！");
  		},
  		success : function(data) {
  			var d = $.parseJSON(data);
  			if (d.success) {
  				var vehicletype = d.attributes.vehicletype;
  				var idcard = d.attributes.idcard;
  				var phone = d.attributes.phone;
  				var loadnum = d.attributes.loadnum;
  				var drivername = d.attributes.drivername;
					document.getElementById(str[0]+".idcard").value = idcard;
					document.getElementById(str[0]+".phone").value = phone;
					document.getElementById(str[0]+".drivername").value = drivername;
					document.getElementById(str[0]+".loadnum").value = loadnum;
  			}		
  		}
  	});
  }
 </script>
</head>
<body style="overflow-x: hidden;">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" tiptype="1" action="soPickupplanController.do?doUpdate"
		beforeSubmit="checkvehicle()">
		<input id="id" name="id" type="hidden" value="${soPickupplanPage.id }">
		<input id="createName" name="createName" type="hidden"
			value="${soPickupplanPage.createName }">
		<input id="createBy" name="createBy" type="hidden"
			value="${soPickupplanPage.createBy }">
		<input id="updateName" name="updateName" type="hidden"
			value="${soPickupplanPage.updateName }">
		<input id="updateBy" name="updateBy" type="hidden"
			value="${soPickupplanPage.updateBy }">
		<input id="updateDate" name="updateDate" type="hidden"
			value="${soPickupplanPage.updateDate }">
		<input id="sysOrgCode" name="sysOrgCode" type="hidden"
			value="${soPickupplanPage.sysOrgCode }">
		<input id="sysCompanyCode" name="sysCompanyCode" type="hidden"
			value="${soPickupplanPage.sysCompanyCode }">
		<input id="bpmStatus" name="bpmStatus" type="hidden"
			value="${soPickupplanPage.bpmStatus }">
		<input id="createDatetime" name="createDatetime" type="hidden"
			value="${soPickupplanPage.createDatetime }">
		<input id="updateDatetime" name="updateDatetime" type="hidden"
			value="${soPickupplanPage.updateDatetime }">
		<input id="unloadplace" name="unloadplace" type="hidden"
			value="${soPickupplanPage.unloadplace }">
		<input id="dr" name="dr" type="hidden" value="${soPickupplanPage.dr }">
		<input id="pickupbegindate" name="pickupbegindate" type="hidden"
			value="${soPickupplanPage.pickupbegindate }">
		<input id="billstatus" name="billstatus" type="hidden"
			value="${soPickupplanPage.billstatus }">
		<input id="ts" name="ts" type="hidden" value="${soPickupplanPage.ts }">
		<input id="approve" name="approve" type="hidden"
			value="${soPickupplanPage.approve }">
		<input id="approvedate" name="approvedate" type="hidden"
			value="${soPickupplanPage.approvedate }">
		<input id="cancel" name="cancel" type="hidden"
			value="${soPickupplanPage.cancel }">
		<input id="canceldate" name="canceldate" type="hidden"
			value="${soPickupplanPage.canceldate }">
		<input id="soordernum" name="soordernum" type="hidden"
			value="${soPickupplanPage.soordernum }">
		<input id="customercode" name="customercode" type="hidden"
			value="${soPickupplanPage.customercode }">
		<input id="carriercode" name="carriercode" type="hidden" value="${soPickupplanPage.carriercode }">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right"><label class="Validform_label">单据日期:</label>
				</td>
				<td class="value"><input id="createDate" name="createDate"
					type="text" style="width: 150px" class="Wdate"
					onClick="WdatePicker()" readOnly="true"
					value='<fmt:formatDate value='${soPickupplanPage.createDate}' type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">单据日期</label></td>
				<td align="right"><label class="Validform_label">计划单号:</label>
				</td>
				<td class="value"><input id="pickupplancode"
					name="pickupplancode" type="text" style="width: 150px" readOnly="true"
					class="inputxt" value='${soPickupplanPage.pickupplancode}'>
					<span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">计划单号</label></td>
				<td align="right"><label class="Validform_label">销售订单号:</label>
				</td>
				<td class="value"><input id="soordercode" name="soordercode"
					type="text" style="width: 150px" class="inputxt"
					value='${soPickupplanPage.soordercode}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">销售订单号</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">物料编码:</label>
				</td>
				<td class="value"><input id="materialcode" name="materialcode"
					type="text" style="width: 150px" class="inputxt"
					value='${soPickupplanPage.materialcode}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">物料编码</label></td>
				<td align="right"><label class="Validform_label">物料名称:</label>
				</td>
				<td class="value"><input id="materialname" name="materialname"
					type="text" style="width: 150px" class="inputxt"
					value='${soPickupplanPage.materialname}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">物料名称</label></td>
				<td align="right"><label class="Validform_label">规格型号:</label>
				</td>
				<td class="value"><input id="spec" name="spec" type="text"
					style="width: 150px" class="inputxt"
					value='${soPickupplanPage.spec}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">规格型号</label></td>

			</tr>
			<tr>
				<td align="right"><label class="Validform_label">单位:</label></td>
				<td class="value"><input id="unit" name="unit" type="text"
					style="width: 150px" class="inputxt"
					value='${soPickupplanPage.unit}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">单位</label></td>
				<td align="right"><label class="Validform_label">客户名称:</label>
				</td>
				<td class="value"><input id="customername" name="customername"
					type="text" style="width: 150px" class="inputxt"
					value='${soPickupplanPage.customername}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">客户名称</label></td>
				<td align="right"><label class="Validform_label">付款客户:</label>
				</td>
				<td class="value"><t:dictSelect id="delivercustomer"
						field="delivercustomer" type="list" dictTable="t_s_depart"
						dictField="id" dictText="departname"  readonly="readonly"
						defaultVal="${soPickupplanPage.delivercustomer}" hasLabel="false"
						title="客户档案" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">付款客户</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">仓库:</label></td>
				<td class="value"><input id="store" name="store" type="text"
					style="width: 150px" class="inputxt"
					value='${soPickupplanPage.store}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">仓库</label></td>
				<td align="right"><label class="Validform_label">剩余数量:</label>
				</td>
				<td class="value"><input id="surplusnum" name="surplusnum"
					type="text" style="width: 150px" class="inputxt"
					value='${soPickupplanPage.surplusnum}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">剩余数量</label></td>
				<td align="right"><label class="Validform_label">到期日期:</label>
				</td>
				<td class="value"><input id="expiredate" name="expiredate"
					type="text" style="width: 150px" class="Wdate"
					value='<fmt:formatDate value='${soPickupplanPage.expiredate}' type="date" pattern="yyyy-MM-dd"/>' datatype="*" readOnly="true">
					<span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">到期日期</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">计划分配量</label>
				</td>
				<td class="value"><input id="planassnum" name="planassnum"
					type="text" style="width: 150px" class="inputxt"
					value='${soPickupplanPage.planassnum}' datatype="n" readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">计划分配量</label></td>
				<td align="right"><label class="Validform_label">接收单位</label></td>
				<td class="value"><input id="receiver" name="receiver"
					type="text" style="width: 150px" class="inputxt"
					value='${soPickupplanPage.receiver}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">接收单位</label></td>
				<td align="right"><label class="Validform_label">备注:</label></td>
				<td class="value"><input id="vnote" name="vnote" type="text"
					style="width: 150px" class="inputxt"
					value='${soPickupplanPage.vnote}' readOnly="true"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">备注</label></td>
			</tr>
			<tr>
			<td align="right">
				<label class="Validform_label">承运单位</label>
			</td>
			<td class="value">
		      <t:dictSelect id="carrier" field="carrier" type="list" dictCondition="fuck" dictTable="bd_carrier" dictField="id" readonly="readonly" dictText="carriername" defaultVal="${soPickupplanPage.carrier}" hasLabel="false" title="承运单位" ></t:dictSelect>    
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">承运单位</label>
			</td>
			<td align="right">
				<label class="Validform_label"></label>
			</td>
			<td class="value">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;"></label>
			</td>
			
			<td align="right">
				<label class="Validform_label"></label>
			</td>
			<td class="value">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;"></label>
			</td>
		</tr>
		</table>
		<div style="width: auto;height: 200px;">
			<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
			<div style="width:800px;height:1px;"></div>
			<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				<t:tab
					href="soPickupplanController.do?soPickupplanBList&id=${soPickupplanPage.id}"
					icon="icon-search" title="提货计划子表" id="soPickupplanB"></t:tab>
			</t:tabs>
		</div>
	</t:formvalid>
	<!-- 添加 附表明细 模版 -->
	<table style="display:none">
		<tbody id="add_soPickupplanB_table_template">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh"></div>
				</td>
				<td align="center"><input style="width:20px;" type="checkbox"
					name="ck" />
				</td>

				
				<td align="left"><form class="vehiclelicense">
						<input id="soPickupplanBList[#index#].vehiclelicense"
							name="soPickupplanBList[#index#].vehiclelicense" maxlength="32"
					type="text" class="inputxt" style="width:120px;" datatype="*"  readOnly="true"></input>
					</form> <label class="Validform_label" style="display: none;">车牌号</label>
				</td>
				<td align="left"><input
					id="soPickupplanBList[#index#].drivername"
					name="soPickupplanBList[#index#].drivername" maxlength="32"
					type="text" class="inputxt" style="width:120px;" datatype="*"  readOnly="true">
					<label class="Validform_label" style="display: none;">驾驶员</label></td>
				<td align="left"><input id="soPickupplanBList[#index#].idcard"
					name="soPickupplanBList[#index#].idcard" maxlength="32" type="text"  readOnly="true"
					class="inputxt" style="width:120px;" datatype="*">
					<label class="Validform_label" style="display: none;">身份证号</label>
				</td>
				<td align="left"><input id="soPickupplanBList[#index#].phone"
					name="soPickupplanBList[#index#].phone" maxlength="32" type="text"  readOnly="true" 
					class="inputxt" style="width:120px;" datatype="*">
					<label class="Validform_label" style="display: none;">手机号</label></td>
				<td align="left"><input id="soPickupplanBList[#index#].loadnum"
					name="soPickupplanBList[#index#].loadnum" maxlength="9" type="text"  readOnly="true"
					class="inputxt" style="width:120px;"> <label
					class="Validform_label" style="display: none;">标准载量</label></td>
				<td align="left"><input
					name="soPickupplanBList[#index#].planpieces" maxlength="9"
					type="text" class="inputxt" style="width:120px;">
					<label class="Validform_label" style="display: none;">预提包数</label>
				</td>
				<td align="left"><input
					name="soPickupplanBList[#index#].plannum" maxlength="9" type="text"
					class="inputxt" style="width:120px;"> <label
					class="Validform_label" style="display: none;">预提数量</label></td>
				<td align="left"><input name="soPickupplanBList[#index#].vnote"
					maxlength="200" type="text" class="inputxt" style="width:120px;">
					<label class="Validform_label" style="display: none;">备注</label></td>
				<td align="left"><input
					name="soPickupplanBList[#index#].pickupnum" maxlength="9"
					type="text" class="inputxt" style="width:120px;">
					<label class="Validform_label" style="display: none;">收货数量</label>
				</td>
				<td align="left"><input
					name="soPickupplanBList[#index#].plandate" maxlength="32"
					type="text" class="Wdate" onClick="WdatePicker()"
					style="width:120px;"> <label
					class="Validform_label" style="display: none;">计划到货日期</label></td>
				<td align="left"><t:dictSelect
						field="soPickupplanBList[#index#].packtype" type="list"
						typeGroupCode="packtype" defaultVal="" hasLabel="false"
						title="包装类型"></t:dictSelect> <label class="Validform_label"
					style="display: none;">包装类型</label></td>
				<td align="left"><input
					name="soPickupplanBList[#index#].carstatus" maxlength="32"
					type="text" class="inputxt" style="width:120px;">
					<label class="Validform_label" style="display: none;">车辆状态</label>
				</td>
				<td align="left"><input
					name="soPickupplanBList[#index#].surplusnum" maxlength="9"
					type="text" class="inputxt" style="width:120px;">
					<label class="Validform_label" style="display: none;">剩余数量</label>
				</td>
			</tr>
		</tbody>
	</table>
</body>
<script src="webpage/com/supply/pickupplan/soPickupplan.js"></script>