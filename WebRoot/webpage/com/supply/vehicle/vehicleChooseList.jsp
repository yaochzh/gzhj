<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<script>
    function setUserIds() {
        $("#vehicleIds").val(getVehicleListSelections('id'));
        return true;
    }

    function getVehicleListSelections(field) {
        var ids = [];
        var rows = $('#vehicleList').datagrid('getSelections');
        for (var i = 0; i < rows.length; i++) {
            ids.push(rows[i][field]);
        }
        ids.join(',');
        return ids
    }
</script>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body >
<t:datagrid name="vehicleList" title="车辆选择"  actionUrl="vehicleController.do?datagridVehicle&ccarrier=${ccarrier}" idField="id" checkbox="true" showRefresh="false"  fit="true"  queryMode="group">
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="车牌号" field="vehiclelicense" width="100" query="true" ></t:dgCol>
	<t:dgCol title="驾驶员" field="cdriver" width="100" query="true" ></t:dgCol>
	<t:dgCol title="身份证" field="vlicensenumber" width="100" ></t:dgCol>
	<t:dgCol title="手机号" field="vdrivermobile" width="100" ></t:dgCol>
	<t:dgCol title="标准载重" field="nload" width="100" ></t:dgCol>  
</t:datagrid>
</body>
</html>