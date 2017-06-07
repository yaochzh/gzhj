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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="bdTestController.do?doAdd" tiptype="1" >
					
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							车辆:
						</label>
					</td>
					<td class="value">
					<t:comboBox url="jeecgDemoController.do?combox" name="sex" text="userName" id="id"></t:comboBox>
					<%--<t:dictSelect field="chel" type="list" dictTable="bd_vehicle" dictField="id" dictText="cdriver" defaultVal="${bdTestPage.chel}" hasLabel="false"  title="车辆"></t:dictSelect>--%>     
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
