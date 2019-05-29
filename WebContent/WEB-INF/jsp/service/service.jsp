<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>智能空间管理系统 ——交互服务</title>
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
	    	   /** 获取上一次选中的部门数据 */
	    	   var boxs  = $("input[type='checkbox'][id^='box_']");
	    	   
	    	   /** 给全选按钮绑定点击事件  */
		    	$("#checkAll").click(function(){
		    		// this是checkAll  this.checked是true
		    		// 所有数据行的选中状态与全选的状态一致
		    		boxs.attr("checked",this.checked);
		    	})
		    	
	    	   /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
		    	$("tr[id^='data_']").hover(function(){
		    		$(this).css("backgroundColor","#eeccff");
		    	},function(){
		    		$(this).css("backgroundColor","#ffffff");
		    	})
		    	
		    	
	    	   /** 删除员工绑定点击事件 */
	    	   $("#delete").click(function(){
	    		   /** 获取到用户选中的复选框  */
	    		   var checkedBoxs = boxs.filter(":checked");
	    		   if(checkedBoxs.length < 1){
	    			   $.ligerDialog.error("请选择一个需要删除的规则！");
	    		   }else{
	    			   /** 得到用户选中的所有的需要删除的ids */
	    			   var ids = checkedBoxs.map(function(){
	    				   return this.value;
	    			   })
	    			   
	    			   $.ligerDialog.confirm("确认要删除吗?","删除此规则",function(r){
	    				   if(r){
	    					   // alert("删除："+ids.get());
	    					   // 发送请求
	    					   window.location = "${ctx }/service/removeService?ids=" + ids.get();
	    				   }
	    			   });
	    		   }
	    	   })
	       })
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：交互服务 &gt; 服务查询</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	<!-- 整个右边的界面是一个大表，包括查询区和数据展示区 -->
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 查询区  -->
	  <tr valign="top">
	    <td height="30">
	    <!-- 查询区属于一张表 -->
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr>
			  <td class="fftd">
			  	<form name="serviceform" method="post" id="serviceform" action="${ctx}/service/selectService">
				    <!-- 两行查询内容也属于一张表，cellpadding控制行距 -->
				    <table width="100%" border="0" cellpadding="6" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	用户：
							    <select name="user_id" style="width:135px;">
					    			<option value="0">--请选择用户--</option>
					    			<c:forEach items="${requestScope.users }" var="user">
					    				<option value="${user.id }">${user.name }</option>
					    			</c:forEach>
					    		</select>&nbsp;&nbsp;
					    	时间：
					    		<select id="moment" name="moment" style="width:135px;">
					    			<option value="0">--请选择时间--</option>
					    			<option value='Morning' >Morning</option>
					    			<option value='Noon' >Noon</option>
					    			<option value='Afternoon' >Afternoon</option>
					    			<option value='Noon' >Noon</option>
					    			<option value='Night' >Night</option>
					    			<option value='Latenight' >Latenight</option>
					    			<option value='Zero' >Zero</option>
					    		</select>&nbsp;&nbsp;
					    	地点：
					    		<select id="location" name="location" style="width:135px;">
					    			<option value="0">--请选择地点--</option>
					    			<option value='Livingroom' >Livingroom</option>
					    			<option value='Bedroom' >Bedroom</option>
					    			<option value='Kitchen' >Kitchen</option>
					    			<option value='Studyingroom' >Studyingroom</option>
					    			<option value='Zero' >Zero</option>
					    		</select>
					    </td>
					  </tr>
					  <tr>
					    <td class="font3">
					    	行为：
							    <select id="behavior" name="behavior" style="width:135px;">
					    			<option value="0">--请选择行为--</option>
					    			<option value='Standing' >Standing</option>
					    			<option value='Walking' >Walking</option>
					    			<option value='Sitting' >Sitting</option>
					    			<option value='Lying_bed' >Lying_bed</option>
					    			<option value='Lying_floor' >Lying_floor</option>
					    			<option value='Zero' >Zero</option>
					    		</select>&nbsp;&nbsp;
					    	压力：
					    		<select id="user_pressure" name="user_pressure" style="width:135px;">
					    			<option value="0">--请选择压力--</option>
					    			<option value='Normal' >Normal</option>
					    			<option value='Light' >Light</option>
					    			<option value='Heavy' >Heavy</option>
					    			<option value='Zero' >Zero</option>
					    		</select>&nbsp;&nbsp;
					    	服务：
					    		<select id="service" name="service" style="width:135px;">
					    			<option value="0">--请选择服务--</option>
					    			<option value='服务1' >服务1</option>
					    			<option value='服务2' >服务2</option>
					    			<option value='服务3' >服务3</option>
					    			<option value='服务4' >服务4</option>
					    			<option value='服务5' >服务5</option>
					    			<option value='服务6' >服务6</option>
					    			<option value='服务7' >服务7</option>
					    			<option value='服务8' >服务8</option>
					    		</select>&nbsp;&nbsp;
					    	<input type="submit" value="搜索"/>&nbsp;&nbsp;
					    	<input id="delete" type="button" value="删除"/>
					    </td>
					  </tr>
					</table>
				</form>
			  </td>
			</tr>
		  </table>
		</td>
	  </tr>
	  
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center">
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>用户</td>
			  <td>时间</td>
			  <td>地点</td>
			  <td>行为</td>
			  <td>状态持续时间（min）</td>
			  <td>用户压力程度</td>
			  <td>需求的服务</td>
			  <td align="center">操作</td>
			</tr>
			<c:forEach items="${requestScope.services}" var="service" varStatus="stat">
				<tr id="data_${stat.index}" class="main_trbg" align="center">
					<td><input type="checkbox" id="box_${stat.index}" value="${service.id}"></td>
					 <td>${service.user.name }</td>
					  <td>${service.moment }</td>
					  <td>${service.location }</td>
					  <td>${service.behavior }</td>
					  <td>${service.state_duration }</td>
					  <td>${service.user_pressure }</td>
					  <td>${service.service }</td>
					  <td align="center" width="40px;"><a href="${ctx}/service/updateService?flag=1&id=${service.id}">
							<img title="修改" src="${ctx}/images/update.gif"/></a>
					  </td>
				</tr>
			</c:forEach>
		  </table>
		</td>
	  </tr>
	  <!-- 分页标签 -->
	  <tr valign="top"><td align="center" class="font3">
	  	 <fkjava:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="digg"
	  	        submitUrl="${ctx}/service/selectService?pageIndex={0}"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>