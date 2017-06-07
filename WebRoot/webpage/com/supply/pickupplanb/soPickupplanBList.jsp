<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addSoPickupplanBBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delSoPickupplanBBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#cutSoPickupplanBBtn').linkbutton({   
	    iconCls: 'icon-cut'  
	}); 
	$('#cutSoPickupplanBBtn').bind('click', function(){   
		 var tr =  $("#add_soPickupplanB_table").find("input:checked").parent().parent().clone();
	 	 $("#add_soPickupplanB_table").append(tr);
	 	 resetTrNum('add_soPickupplanB_table');
	 	 return false;
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
    	if(location.href.indexOf("load=update")!=-1){
    		document.getElementById("cutSoPickupplanBBtn").style.display = "none";
    	}
		//将表格的表头固定
	    $("#soPickupplanB_table").createhftable({
	    	height:'300px',
			width:'auto'
			});
    });

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

	/*function myclick(id)	{
		var str = id.split(".");
		var drivername = document.getElementById(str[0]+".drivername").value;
		document.getElementById(str[0]+".vehiclelicense").innerHTML = "";
		var carrier = document.getElementById(str[0]+".carrier").value;
		if(carrier.length == 0){
			alert("请先选择承运商！");
    	}else{
    		$.ajax({
        		type : "POST",
        		url : "soPickupplanController.do?queryByCarrier&code="+carrier+"",// 请求的action路径
        		error : function() {// 请求失败处理函数
        			alert("请检查车辆档案的承运商信息是否完整！");
        		},
        		success : function(data) {
        			var d = $.parseJSON(data);
        			if (d.success) {
        				var result = d.attributes;
        				var select= document.getElementById(str[0]+".vehiclelicense");
        				 select.options.add(new Option("---请选择---",""))
        				 select.disabled=false;
        				  $.each(result,function(key,value){
                              select.options.add(new Option(value,key))
        				  })
        			}		
        		}
        	});
    	}
	}*/
	function myclick2(id)	{
		var str = id.split(".");
		var carrier = document.getElementById(str[0]+".carrier").value;
		if(carrier.length == 0){
			alert("请先选择承运商！");
    	}
	}
	function addVehicles() {
	 	 var vehicleids = document.getElementById("vehicleid").value;
	 	 var size = $('#add_soPickupplanB_table tr').length;
	 	 if(vehicleids.length < 20) {
	 		 return false
	 	 }

	 	 var str = vehicleids.split(",");
		 	for(var i = 0;i<str.length;i++) {
		   		 var tr =  $("#add_soPickupplanB_table_template tr").clone();
			 	 $("#add_soPickupplanB_table").append(tr);
		 	}
				resetTrNum('add_soPickupplanB_table');
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
							document.getElementById("soPickupplanBList["+linenum+"].idcard").value = idcard;
							document.getElementById("soPickupplanBList["+linenum+"].phone").value = phone;
							document.getElementById("soPickupplanBList["+linenum+"].loadnum").value = loadnum;
							document.getElementById("soPickupplanBList["+linenum+"].drivername").value = drivername;
							document.getElementById("soPickupplanBList["+linenum+"].vehiclelicense").value = vehiclelicense;
	    					
	    			 	}
	    				}
	    			}		
	    		}
	    	});
	 	 return false;	
	    }
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar"> 
	<t:chooseNew hiddenName="vehicleid" hiddenid="id" url="vehicleController.do?vehicles&carrier="  name="vehicleList" icon="icon-search" title="车辆选择" textname="vehiclelicense" isInit="false" choseButName="车辆选择" fun="addVehicles"></t:chooseNew>
<a id="delSoPickupplanBBtn" href="#">删除</a>
<input name="vehicleid" id="vehicleid" type="hidden"/>
					<input name="vehiclelicense" id="vehiclelicense" type="hidden"/>
	<!-- <a id="addSoPickupplanBBtn" href="#">添加</a><a id="cutSoPickupplanBBtn" href="#">复制</a> -->
</div>
<table border="0" cellpadding="2" cellspacing="0" id="soPickupplanB_table"style="padding: 3px; height: 30px;width:2060px;">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
		
				  <!-- <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						承运单位
				  </td> -->
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						车牌号
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
						标准载量
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						预提包数
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						预提数量
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						备注
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						收货数量
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						计划到货日期
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						包装类型
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						车辆状态
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 120px;">
						剩余数量
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
							<input id="soPickupplanBList[${stuts.index }].vehiclelicense" field="soPickupplanBList[${stuts.index }].vehiclelicense" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.vehiclelicense }" readOnly="true"></input>     
					  <label class="Validform_label" style="display: none;">车牌号</label>
				   </td>
				   <td align="left">
				   	<input id="soPickupplanBList[${stuts.index }].drivername" name="soPickupplanBList[${stuts.index }].drivername" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.drivername }" datatype = "*" readOnly="true">
					  <label class="Validform_label" style="display: none;">驾驶员</label>
				   </td>
				   <td align="left">
					  	<input id="soPickupplanBList[${stuts.index }].idcard" name="soPickupplanBList[${stuts.index }].idcard" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.idcard }" datatype = "*" readOnly="true">
					  <label class="Validform_label" style="display: none;">身份证号</label>
				   </td>
				   <td align="left">
					  	<input id="soPickupplanBList[${stuts.index }].phone" name="soPickupplanBList[${stuts.index }].phone" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.phone }" datatype = "*" readOnly="true">
					  <label class="Validform_label" style="display: none;">手机号</label>
				   </td>
				   <td align="left">
					  	<input id="soPickupplanBList[${stuts.index }].loadnum" name="soPickupplanBList[${stuts.index }].loadnum" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.loadnum }" readOnly="true">
					  <label class="Validform_label" style="display: none;">标准载量</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].planpieces" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.planpieces }">
					  <label class="Validform_label" style="display: none;">预提包数</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].plannum" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.plannum }">
					  <label class="Validform_label" style="display: none;">预提数量</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].vnote" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.vnote }">
					  <label class="Validform_label" style="display: none;">备注</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].pickupnum" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.pickupnum }">
					  <label class="Validform_label" style="display: none;">收货数量</label>
				   </td>
				   <td align="left">
							<input name="soPickupplanBList[${stuts.index }].plandate" maxlength="32" 
					  		type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;"    value="<fmt:formatDate value='${poVal.plandate}' type="date" pattern="yyyy-MM-dd"/>">
					  <label class="Validform_label" style="display: none;">计划到货日期</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="soPickupplanBList[${stuts.index }].packtype" type="list"
										typeGroupCode="packtype" defaultVal="${poVal.packtype }" hasLabel="false"  title="包装类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">包装类型</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].carstatus" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.carstatus }">
					  <label class="Validform_label" style="display: none;">车辆状态</label>
				   </td>
				   <td align="left">
					  	<input name="soPickupplanBList[${stuts.index }].surplusnum" maxlength="9" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.surplusnum }">
					  <label class="Validform_label" style="display: none;">剩余数量</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
