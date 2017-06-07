<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#cutPoDeliveryplanBBtn').linkbutton({   
	    iconCls: 'icon-cut'  
	}); 
	$('#cutPoDeliveryplanBBtn').bind('click', function(){   
		 var tr =  $("#add_poDeliveryplanB_table").find("input:checked").parent().parent().clone();
	 	 $("#add_poDeliveryplanB_table").append(tr);
	 	 resetTrNum('add_poDeliveryplanB_table');
	 	 return false;
	});
	$('#addPoDeliveryplanBBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delPoDeliveryplanBBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addPoDeliveryplanBBtn').bind('click', function(){   
 		 var tr =  $("#add_poDeliveryplanB_table_template tr").clone();
	 	 $("#add_poDeliveryplanB_table").append(tr);
	 	 resetTrNum('add_poDeliveryplanB_table');
	 	 return false;
    });  
	$('#delPoDeliveryplanBBtn').bind('click', function(){   
      	$("#add_poDeliveryplanB_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_poDeliveryplanB_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
    	if(location.href.indexOf("load=update")!=-1){
    		document.getElementById("cutPoDeliveryplanBBtn").style.display = "none";
    	}
		//将表格的表头固定
	    $("#poDeliveryplanB_table").createhftable({
	    	height:'300px',
			width:'auto'
			});
    });
   	function addVehicles() {
	 	 var vehicleids = document.getElementById("vehicleid").value;
	 	 var size = $('#add_poDeliveryplanB_table tr').length;
	 	 if(vehicleids.length < 20) {
	 		 return false
	 	 }

	 	 var str = vehicleids.split(",");
		 	for(var i = 0;i<str.length;i++) {
		   		 var tr =  $("#add_poDeliveryplanB_table_template tr").clone();
			 	 $("#add_poDeliveryplanB_table").append(tr);
		 	}
				resetTrNum('add_poDeliveryplanB_table');
	 		$.ajax({
	 			url : "poDeliveryplanController.do?queryByCodes",  
	 			data :  "codes="+ vehicleids,  
	 			type : "POST",
	    		error : function() {// 请求失败处理函数
	    			alert("请检查改车牌号对应的车辆档案信息是否完整！");
	    		},
	    		success : function(msg) {
	    			var d = $.parseJSON(msg);
	    			if (d.success) {
	    				var results= d.obj;
	    				if(results.length > 0 ){
	    				for(var i = 0;i<results.length;i++) {
	    					var linenum = size + i;
		    				var vehicletype = results[i].vehicletype;
		    				var idcard = results[i].idcard;
		    				var phone = results[i].phone;
		    				var loadnum = results[i].loadnum;
		    				var drivername = results[i].drivername;
		    				var vehiclelicense = results[i].vehiclelicense;
							document.getElementById("poDeliveryplanBList["+linenum+"].idcard").value = idcard;
							document.getElementById("poDeliveryplanBList["+linenum+"].phone").value = phone;
							document.getElementById("poDeliveryplanBList["+linenum+"].loadnum").value = loadnum;
							document.getElementById("poDeliveryplanBList["+linenum+"].drivername").value = drivername;
							document.getElementById("poDeliveryplanBList["+linenum+"].vehiclelicense").value = vehiclelicense;
	    					
	    			 	}
	    				}
	    			}		
	    		}
	    	});
	 	 return false;	
	    }
   /* function myChange(code,id)	{
		var str = id.split(".");
    	$.ajax({
    		type : "POST",
    		url : "poDeliveryplanController.do?queryByCode&code="+code+"&id="+id+"",// 请求的action路径
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
					document.getElementById(str[0]+".loadnum").value = loadnum;
					document.getElementById(str[0]+".drivername").value = drivername;
    			}		
    		}
    	});
    }
*/
	
	/*function myclick2(id)	{
		var str = id.split(".");
		var carrier = document.getElementById(str[0]+".carrier").value;
		if(carrier.length == 0){
			alert("请先选择承运商！");
    	}
	}*/
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
<t:chooseNew hiddenName="vehicleid" hiddenid="id" url="vehicleController.do?vehicles&carrier="  name="vehicleList" icon="icon-search" title="车辆选择" textname="vehiclelicense" isInit="false" choseButName="车辆选择" fun="addVehicles"></t:chooseNew>
 <a id="delPoDeliveryplanBBtn" href="#">删除</a> 
 
					<input name="vehicleid" id="vehicleid" type="hidden"/>
					<input name="vehiclelicense" id="vehiclelicense" type="hidden"/>
 <!-- <a id="cutPoDeliveryplanBBtn" href="#" >复制</a> 	<a id="addPoDeliveryplanBBtn" href="#">添加</a> -->
</div>
<table border="0" cellpadding="2" cellspacing="0" id="poDeliveryplanB_table" style="padding: 3px; height: 30px;width:1880px;">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 140px;">
						车号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						驾驶员
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						身份证号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						手机号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						标准载重
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						卸货地点
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						含量
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						供应商送货量
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						送货日期
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						计划到货日期
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						折百
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						车辆状态
				  </td>
	</tr>
	<tbody id="add_poDeliveryplanB_table">
	<c:if test="${fn:length(poDeliveryplanBList)  > 0 }">
		<c:forEach items="${poDeliveryplanBList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="poDeliveryplanBList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="poDeliveryplanBList[${stuts.index }].dr" type="hidden" value="${poVal.dr }"/>
					<input name="poDeliveryplanBList[${stuts.index }].ts" type="hidden" value="${poVal.ts }"/>
					<input name="poDeliveryplanBList[${stuts.index }].fkId" type="hidden" value="${poVal.fkId }"/>
					<input name="poDeliveryplanBList[${stuts.index }].deliveryplan" type="hidden" value="${poVal.deliveryplan }"/>
				  
				  <td align="left">
				<input id="poDeliveryplanBList[${stuts.index }].vehiclelicense" name="poDeliveryplanBList[${stuts.index }].vehiclelicense" type="text" class="inputxt"  style="width:120px;"  value="${poVal.vehiclelicense }" datatype = "*" readOnly="true"></input>     
					  <label class="Validform_label" style="display: none;">车牌号</label>
				   </td>
				   <td align="left">
				   <input name="poDeliveryplanBList[${stuts.index }].drivername" maxlength="32" id = "poDeliveryplanBList[${stuts.index }].drivername" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.drivername }" datatype = "*" readOnly="true">    
					  <label class="Validform_label" style="display: none;">驾驶员</label>
				   </td>
				   <td align="left">
					  	<input id="poDeliveryplanBList[${stuts.index }].idcard" name="poDeliveryplanBList[${stuts.index }].idcard" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.idcard }" datatype = "*" readOnly="true">
					  <label class="Validform_label" style="display: none;">身份证号</label>
				   </td>
				   <td align="left">
					  	<input id="poDeliveryplanBList[${stuts.index }].phone"  name="poDeliveryplanBList[${stuts.index }].phone" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.phone }" datatype = "*" readOnly="true">
					  <label class="Validform_label" style="display: none;">手机号</label>
				   </td>
				   <td align="left">
					  	<input id="poDeliveryplanBList[${stuts.index }].loadnum" name="poDeliveryplanBList[${stuts.index }].loadnum" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.loadnum }" readOnly="true">
					  <label class="Validform_label" style="display: none;">标准载重</label>
				   </td>
				   <td align="left">
					  	<input name="poDeliveryplanBList[${stuts.index }].unloadplace" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.unloadplace }">
					  <label class="Validform_label" style="display: none;">卸货地点</label>
				   </td>
				   <td align="left">
					  	<input name="poDeliveryplanBList[${stuts.index }].content" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.content }">
					  <label class="Validform_label" style="display: none;">含量</label>
				   </td>
				   <td align="left">
					  	<input name="poDeliveryplanBList[${stuts.index }].deliverynum" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.deliverynum }">
					  <label class="Validform_label" style="display: none;">供应商送货量</label>
				   </td>
				   <td align="left">
							<input name="poDeliveryplanBList[${stuts.index }].deliverydate" maxlength="32" 
					  		type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"    value="<fmt:formatDate value='${poVal.deliverydate}' type="date" pattern="yyyy-MM-dd"/>">
					  <label class="Validform_label" style="display: none;">送货日期</label>
				   </td>
				   <td align="left">
							<input name="poDeliveryplanBList[${stuts.index }].plandate" maxlength="32" 
					  		type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"    value="<fmt:formatDate value='${poVal.plandate}' type="date" pattern="yyyy-MM-dd"/>">
					  <label class="Validform_label" style="display: none;">计划到货日期</label>
				   </td>
				   <td align="left">
					  	<input name="poDeliveryplanBList[${stuts.index }].discou" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.discou }">
					  <label class="Validform_label" style="display: none;">折百</label>
				   </td>
				   <td align="left">
					  	<input name="poDeliveryplanBList[${stuts.index }].carstatus" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.carstatus }">
					  <label class="Validform_label" style="display: none;">车辆状态</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
