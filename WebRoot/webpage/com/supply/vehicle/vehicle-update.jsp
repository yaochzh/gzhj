<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>车辆档案</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="vehicleController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${vehiclePage.id }">
					<input id="createBy" name="createBy" type="hidden" value="${vehiclePage.createBy }">
					<input id="updateName" name="updateName" type="hidden" value="${vehiclePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${vehiclePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${vehiclePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${vehiclePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${vehiclePage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${vehiclePage.bpmStatus }">
					<input id="def1" name="def1" type="hidden" value="${vehiclePage.def1 }">
					<input id="def2" name="def2" type="hidden" value="${vehiclePage.def2 }">
					<input id="def3" name="def3" type="hidden" value="${vehiclePage.def3 }">
					<input id="def4" name="def4" type="hidden" value="${vehiclePage.def4 }">
					<input id="def5" name="def5" type="hidden" value="${vehiclePage.def5 }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					
					<tr>
						
						<td align="right">
							<label class="Validform_label">
								车牌号:
							</label>
						</td>
						<td class="value">
						     	 <input id="vehiclelicense" name="vehiclelicense" type="text" style="width: 150px" class="inputxt"  value='${vehiclePage.vehiclelicense}' datatype = "*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">车牌号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								驾驶员:
							</label>
						</td>
						<td class="value">
						     	 <input id="cdriver" name="cdriver" type="text" style="width: 150px" class="inputxt"  value='${vehiclePage.cdriver}' datatype = "*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">驾驶员</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								身份证号码:
							</label>
						</td>
						<td class="value">
						     	 <input id="vlicensenumber" name="vlicensenumber" type="text" style="width: 150px" class="inputxt"  value='${vehiclePage.vlicensenumber}' datatype = "*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">身份证号码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								手机号码:
							</label>
						</td>
						<td class="value">
						     	 <input id="vdrivermobile" name="vdrivermobile" type="text" style="width: 150px" class="inputxt"  value='${vehiclePage.vdrivermobile}' datatype = "*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">手机号码</label>
						</td>
					</tr>
					<tr>
					<td align="right">
							<label class="Validform_label">
								标准载重:
							</label>
						</td>
						<td class="value">
						     	 <input id="nload" name="nload" type="text" style="width: 150px" class="inputxt"  value='${vehiclePage.nload}' datatype = "*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标准载重</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								标准皮重:
							</label>
						</td>
						<td class="value">
						     	 <input id="nemptyload" name="nemptyload" type="text" style="width: 150px" class="inputxt"  value='${vehiclePage.nemptyload}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标准皮重</label>
						</td>
						
					</tr>
					<tr>
					<td align="right">
							<label class="Validform_label">
								封存:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="bsealflag" typeGroupCode="sf_yn" hasLabel="false" defaultVal="${vehiclePage.bsealflag}"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">封存</label>
						</td>
						
					<td align="right">
							<label class="Validform_label">
								所属承运商
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="ccarrier" field="ccarrier" type="list"
							dictTable="bd_carrier" dictField="id" dictText="carriername" defaultVal="${vehiclePage.ccarrier}" hasLabel="false"  title="承运单位" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属承运商</label>
						</td>
					</tr>
					
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/supply/vehicle/vehicle.js"></script>		
