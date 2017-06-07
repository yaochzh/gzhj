<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>承运单位</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="bdCarrierController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${bdCarrierPage.id }">
					<input id="createBy" name="createBy" type="hidden" value="${bdCarrierPage.createBy }">
					<input id="updateBy" name="updateBy" type="hidden" value="${bdCarrierPage.updateBy }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${bdCarrierPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${bdCarrierPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${bdCarrierPage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							单位编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="carriercode" name="carriercode" type="text" style="width: 150px" class="inputxt"  datatype = "*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位编码</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单位名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="carriername" name="carriername" type="text" style="width: 150px" class="inputxt"  datatype = "*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位名称</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							上级承运单位:
						</label>
					</td>
					<td class="value">
					  <t:dictSelect id = "fathercarrier"  field="fathercarrier" type="list" 
										dictTable="bd_carrier" dictField="id" dictText="carriername" defaultVal="" hasLabel="false"  title="上级承运单位"></t:dictSelect>   
								<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上级承运单位</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/supply/carrier/bdCarrier.js"></script>		
