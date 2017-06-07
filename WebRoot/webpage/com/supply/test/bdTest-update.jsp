<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>测试</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="bdTestController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${bdTestPage.id }">
					<input id="createName" name="createName" type="hidden" value="${bdTestPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${bdTestPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${bdTestPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${bdTestPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${bdTestPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${bdTestPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${bdTestPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${bdTestPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${bdTestPage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								车辆:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="chel" type="list"
										dictTable="bd_vehicle" dictField="id" dictText="cdriver" defaultVal="${bdTestPage.chel}" hasLabel="false"  title="车辆"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">车辆</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/supply/test/bdTest.js"></script>		
