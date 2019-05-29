<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>智能空间服务系统——修改预约信息</title>
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
	<script type="text/javascript">
	
	$(function(){
    	/** 导师信息表单提交 */
		$("#alarmForm").submit(function(){
			var alarm_time = $("#alarm_time");
			var msg = "";
			if ($.trim(alarm_time.val()) == ""){
				msg = "时间不能为空！";
				alarm_time.focus();
			}
			
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#alarmForm").submit();
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：交互服务  &gt; 修改预约</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/alarm/updateAlarm" id="alarmForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
			<input type="hidden" name="id" value="${alarm.id }">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">时间：<input type="text" name="alarm_time" id="alarm_time" size="20" value="${alarm.alarm_time }"/></td>
		    		</tr>
		    			
		    		<tr>
		    			<td class="font3 fftd">服务：
							<select id="alarm_service" name="alarm_service" style="width:125px;">
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
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="修改 ">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>