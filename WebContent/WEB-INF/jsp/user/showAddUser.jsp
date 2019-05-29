<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>智能空间服务系统——添加成员</title>
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
	    	/** 成员表单提交 */
			$("#userForm").submit(function(){
				var name = $("#name");
				var eduid = $("#eduid");
				var grade = $("#grade");
				var phone = $("#phone");
				var wechat = $("#wechat");
				var qq = $("#qq");
				var email = $("#email");
				var homeaddress = $("#homeaddress");
				var field = $("#field");
				var address = $("#address");
				var birthday = $("#birthday");
				var company = $("#company");//可以为空
				var remark = $("#remark");//可以为空
				var msg = "";
				if ($.trim(name.val()) == ""){
					msg = "姓名不能为空！";
					name.focus();
				}else if ($.trim(eduid.val()) == ""){
					msg = "学号不能为空！";
					eduid.focus();
				}else if ($.trim(grade.val()) == ""){
					msg = "年级不能为空！";
					grade.focus();
				}else if ($.trim(email.val()) == ""){
					msg = "邮箱不能为空！";
					email.focus();
				}else if (!/^\w+[-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test($.trim(email.val()))){
					msg = "邮箱格式不正确！";
					email.focus();
				}else if ($.trim(phone.val()) == ""){
					msg = "手机号码不能为空！";
					phone.focus();
				}else if (!/^1[3|5|8]\d{9}$/.test($.trim(phone.val()))){
					msg = "手机号码格式不正确！";
					phone.focus();
				}else if ($.trim(wechat.val()) == ""){
					msg = "微信号不能为空！";
					wechat.focus();
				}else if ($.trim(qq.val()) == ""){
					msg = "QQ号码不能为空！";
					qq.focus();
				}else if (!/^\d{6,}$/.test($.trim(qq.val()))){
					msg = "QQ号码格式不正确！";
					qq.focus();
				}else if ($.trim(address.val()) == ""){
					msg = "地址不能为空！";
					address.focus();
				}else if ($.trim(homeaddress.val()) == ""){
					msg = "家庭地址不能为空！";
					homeaddress.focus();
				}else if ($.trim(birthday.val()) == ""){
					msg = "出生日期不能为空！";
					birthday.focus();
				}else if (!birthday.val()){
// 					!/^\d{4}-\d{2}-\d{2}$/.test($.trim(birthday.val()))
					msg = "出生日期格式不正确！";
					birthday.focus();
				}
				if (msg != ""){
					$.ligerDialog.error(msg);
					return false;
				}else{
					return true;
				}
				$("#userForm").submit();
			});
	    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：成员管理  &gt; 添加成员</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/user/addUser" id="userForm" method="post">
		 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">姓名：<input type="text" name="name" id="name" size="20"/></td>
		    			<td class="font3 fftd">学号：<input type="text" name="eduid" id="eduid" size="20"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">性别：
								<select id="sex" name="sex" style="width:130px;">
					    			<option value='null'>--请选择性别--</option>
					    			<option value='男' >男</option>
					    			<option value='女' >女</option>
					    		</select>
					    </td>
		    			<td class="font3 fftd">导师：
		    			 <select name="tutor_id" style="width:140px;">
					    			<option value="0">--请选择导师--</option>
					    			<c:forEach items="${requestScope.tutors }" var="tutor">
					    				<option value="${tutor.id }">${tutor.name }</option>
					    			</c:forEach>
					    		</select>
					    </td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">年级：<input name="grade" id="grade" size="20"/></td>
		    			<td class="font3 fftd">Q&nbsp;&nbsp;Q：<input name="qq" id="qq" size="20"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">手机：<input name="phone" id="phone" size="20"/></td>
		    			<td class="font3 fftd">微信：<input name="wechat" id="wechat" size="20"/></td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					家庭住址：<input name="homeaddress" id="homeaddress" size="40"/>&nbsp;&nbsp;
					电子邮箱：<input name="email" id="email" size="30"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					出生日期：<input cssClass="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});" 
					name="birthday" id="birthday" size="40"/>&nbsp;&nbsp;
					当前住址：<input name="address" id="address" size="30"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					研究方向：<input  name="field" id="field" size="40"/>&nbsp;&nbsp;
					所属公司：<input name="company" id="company" size="30"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					备&nbsp;&nbsp;&nbsp;&nbsp;注：<input name="remark" id="remark" size="40"/>
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