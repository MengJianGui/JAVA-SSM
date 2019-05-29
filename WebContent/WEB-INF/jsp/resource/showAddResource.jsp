<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>智能空间服务系统——资源使用</title>
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
	    	$("#resourceForm").submit(function(){
				var datetime = $("#datetime");
				
				if ($.trim(datetime.val()) == ""){
					msg = "日期不能为空！";
					datetime.focus();
				}
				if (msg != ""){
					$.ligerDialog.error(msg);
					return false;
				}else{
					return true;
				}
				$("#resourceForm").submit();
			});
	    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：资源使用  &gt; 添加预约</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/resource/addResource" id="resourceForm" method="post">
		 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
			<tr>
				<td class="font3 fftd">
					使用日期：<input cssClass="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});" 
					name="datetime" id="datetime" size="15"/>&nbsp;&nbsp;
				</td>
		   	</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
		    		设备名称：
	    			 <select id="resourcename" name="resourcename" style="width:135px;">
				    		<option value="0">--请选择设备--</option>
			    			<option value='投影仪' >投影仪</option>
			    			<option value='会议室' >会议室</option>
			    			<option value='服务器1' >服务器1</option>
			    			<option value='服务器2' >服务器2</option>
			    			<option value='机器人1' >机器人1</option>
			    			<option value='机器人2' >机器人2</option>
				    </select>&nbsp;&nbsp;&nbsp;&nbsp;
		    		用户：
					<select name="user_id" style="width:135px;">
				    	<option value="0">--请选择用户--</option>
				    	<c:forEach items="${requestScope.users }" var="user">
				    		<option value="${user.id }">${user.name }</option>
				    	</c:forEach>
				    </select>&nbsp;&nbsp;&nbsp;&nbsp;
		    		
				</td>
		   	</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd">
			<input type="submit" value="添加">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>