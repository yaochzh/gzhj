<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addSoPickupplanBBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delSoPickupplanBBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addSoPickupplanBBtn').bind('click', function(){   
 		 var tr =  $("#add_soPickupplanB_table_template tr").clone();
	 	 $("#add_soPickupplanB_table").append(tr);
	 	 resetTrNum('add_soPickupplanB_table');
	 	 return false;
    });  
	$('#delSoPickupplanBBtn').bind('click', function(){   
      	$("#add_soPickupplanB_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_soPickupplanB_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#soPickupplanB_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addSoPickupplanBBtn" href="#">添加</a> <a id="delSoPickupplanBBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="soPickupplanB_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						计划单号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						车牌号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						车型
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						司机姓名
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						身份证号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						手机号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						核载量
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						预装件数
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						预装数量
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						备注
				  </td>
	</tr>
	<tbody id="add_soPickupplanB_table">
	<c:if test="${fn:length(soPickupplanBList)  > 0 }">
		<c:forEach items="${soPickupplanBList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="soPickupplanBList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="soPickupplanBList[${stuts.index }].fkId" type="hidden" value="${poVal.fkId }"/>
					<input name="soPickupplanBList[${stuts.index }].dr" type="hidden" value="${poVal.dr }"/>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].pickupplan" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.pickupplan }">
					  <label class="Validform_label" style="display: none;">计划单号</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].vehiclelicense" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.vehiclelicense }">
					  <label class="Validform_label" style="display: none;">车牌号</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].vehicletype" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.vehicletype }">
					  <label class="Validform_label" style="display: none;">车型</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].drivername" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.drivername }">
					  <label class="Validform_label" style="display: none;">司机姓名</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].idcard" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.idcard }">
					  <label class="Validform_label" style="display: none;">身份证号</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].phone" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.phone }">
					  <label class="Validform_label" style="display: none;">手机号</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].loadnum" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.loadnum }">
					  <label class="Validform_label" style="display: none;">核载量</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].planpieces" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.planpieces }">
					  <label class="Validform_label" style="display: none;">预装件数</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].plannum" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.plannum }">
					  <label class="Validform_label" style="display: none;">预装数量</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].vnote" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.vnote }">
					  <label class="Validform_label" style="display: none;">备注</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
