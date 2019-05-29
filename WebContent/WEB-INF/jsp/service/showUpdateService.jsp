<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>智能空间服务系统——修改规则</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script language="javascript" type="text/javascript" src="${ctx }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	
	 
	    $(function(){
	    	/** 员工表单提交 */
	    	$("#serviceForm").submit(function(){
				var state_duration = $("#state_duration");
				
				if ($.trim(state_duration.val()) == ""){
					$("#state_duration").val("Zero");
				}
				
				$("#serviceForm").submit();
			});
	    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：交互服务  &gt; 修改规则</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/service/updateService" id="serviceForm" method="post">
		 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
    	 	<input type="hidden" name="id" value="${service.id }">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
			<tr>
		    	<td class="font3 fftd">用户：
					<select  name="user_id" style="width:135px;">
						   <option value="Zero">-- 用户选择 --</option>
						   <c:forEach items="${requestScope.users }" var="user">
						   		<c:choose>
			    					<c:when test="${service.user.id == user.id }">
			    						<option value="${user.id }" selected="selected">${user.name }</option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value="${user.id }">${user.name }</option>
			    					</c:otherwise>
			    				</c:choose>
			    			</c:forEach>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;
		    		时间：
	    			 <select id="moment" name="moment" style="width:135px;">
				    		<option value='Zero'>--请选择时间--</option>
				    		<option value='Morning'  <c:if test="${service.moment eq 'Morning' }">selected</c:if> >Morning</option>
				    		<option value='Noon'  <c:if test="${service.moment eq 'Noon' }">selected</c:if> >Noon</option>
				    		<option value='Afternoon'  <c:if test="${service.moment eq 'Afternoon' }">selected</c:if> >Afternoon</option>
				    		<option value='Noon'  <c:if test="${service.moment eq 'Noon' }">selected</c:if> >Noon</option>
				    		<option value='Night'  <c:if test="${service.moment eq 'Night' }">selected</c:if> >Night</option>
				    		<option value='Latenight'  <c:if test="${service.moment eq 'Latenight' }">selected</c:if> >Latenight</option>
				    		<option value='Zero'  <c:if test="${service.moment eq 'Zero' }">selected</c:if> >Zero</option>
				    </select>
				</td>
		   	</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">地点：
					<select id="location" name="location" style="width:135px;">
		    			<option value='Zero'>--请选择地点--</option>
		    			<option value='Livingroom'  <c:if test="${service.location eq 'Livingroom' }">selected</c:if> >Livingroom</option>
		    			<option value='Bedroom'  <c:if test="${service.location eq 'Bedroom' }">selected</c:if> >Bedroom</option>
		    			<option value='Kitchen'  <c:if test="${service.location eq 'Kitchen' }">selected</c:if> >Kitchen</option>
		    			<option value='Studyingroom'  <c:if test="${service.location eq 'Studyingroom' }">selected</c:if> >Studyingroom</option>
		    			<option value='Zero'  <c:if test="${service.location eq 'Zero' }">selected</c:if> >Zero</option>
		    		</select>&nbsp;&nbsp;&nbsp;&nbsp;
					行为：
	    			 <select id="behavior" name="behavior" style="width:135px;">
		    			<option value='Zero'>--请选择行为--</option>
		    			<option value='Standing'  <c:if test="${service.behavior eq 'Standing' }">selected</c:if> >Standing</option>
		    			<option value='Walking'  <c:if test="${service.behavior eq 'Walking' }">selected</c:if> >Walking</option>
		    			<option value='Sitting'  <c:if test="${service.behavior eq 'Sitting' }">selected</c:if> >Sitting</option>
		    			<option value='Lying_bed'  <c:if test="${service.behavior eq 'Lying_bed' }">selected</c:if> >Lying_bed</option>
		    			<option value='Lying_floor'  <c:if test="${service.behavior eq 'Lying_floor' }">selected</c:if> >Lying_floor</option>
		    			<option value='Zero'  <c:if test="${service.behavior eq 'Zero' }">selected</c:if> >Zero</option>
		    		</select>&nbsp;&nbsp;
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			<tr>
				<td class="font3 fftd">压力：
					<select id="user_pressure" name="user_pressure" style="width:135px;">
		    			<option value='Zero'>--请选择压力--</option>
		    			<option value='Normal'  <c:if test="${service.user_pressure eq 'Normal' }">selected</c:if> >Normal</option>
		    			<option value='Light'  <c:if test="${service.user_pressure eq 'Light' }">selected</c:if> >Light</option>
		    			<option value='Heavy'  <c:if test="${service.user_pressure eq 'Heavy' }">selected</c:if> >Heavy</option>
		    			<option value='Zero'  <c:if test="${service.user_pressure eq 'Zero' }">selected</c:if> >Zero</option>
		    		</select>&nbsp;&nbsp;&nbsp;&nbsp;
		    		状态持续时间：<input name="state_duration" id="state_duration" size="9" value="${service.state_duration }"/>
				</td>
			</tr>

			<tr><td class="main_tdbor"></td></tr>
			<tr>
				<td class="font3 fftd">服务：
					<select id="service" name="service" style="width:135px;">
		    			<option value='null'>--请选择服务--</option>
		    			<option value='服务1'  <c:if test="${service.service eq '服务1' }">selected</c:if> >服务1</option>
		    			<option value='服务2'  <c:if test="${service.service eq '服务2' }">selected</c:if> >服务2</option>
		    			<option value='服务3'  <c:if test="${service.service eq '服务3' }">selected</c:if> >服务3</option>
		    			<option value='服务4'  <c:if test="${service.service eq '服务4' }">selected</c:if> >服务4</option>
		    			<option value='服务5'  <c:if test="${service.service eq '服务5' }">selected</c:if> >服务5</option>
		    			<option value='服务6'  <c:if test="${service.service eq '服务6' }">selected</c:if> >服务6</option>
		    			<option value='服务7'  <c:if test="${service.service eq '服务7' }">selected</c:if> >服务7</option>
		    			<option value='服务8'  <c:if test="${service.service eq '服务8' }">selected</c:if> >服务8</option>
		    		</select>&nbsp;&nbsp;
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd">
			<input type="submit" value="修改">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>