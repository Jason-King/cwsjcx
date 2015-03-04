<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="zh-cn">
<meta http-equiv="pragma" content="no-cache" />
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="js/ocupload/jquery.ocupload-1.1.2.js"></script>
<script language="javascript" type="text/javascript"
			src="js/My97DatePicker-4.72/WdatePicker.js"
			defer="defer"></script>
<script type="text/javascript">
function submitExcel(type) {

	var excelFile = $("#"+type).val();
	var month = $("#month").val();
	if (excelFile == '') {
		alert("请选择需上传的文件!");
		return false;
	}
	if(month == ''){
		alert("请选择时间段！");
		return false;
	}
	//上传的文件后缀同时为 -1,则文件类型有误
	if (excelFile.indexOf('.xls') == -1 && excelFile.indexOf('.xlsx') == -1) {
		alert("文件格式不正确，请选择正确的Excel文件(后缀名.xls或.xlsx)！");
		return false;
	} else {
		if(confirm("上传文件会将该月份的历史数据清空，确认继续上传吗？")){
			$("#type").val(type);
			$("#fileUpload").attr("action","./ImpData?type="+type+"&month="+month);
			$("#fileUpload").submit();
		}
		
	}

}
</script>
<title>机械财会网数据查询系统</title>
</head>
<body>
	<center>
		<h1>机械财会网数据查询系统</h1>
		<div>
			<table>
				<tr>
					<td><a href="./Index?page=should_receive">应收账款</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="./Index?should_pay">应付账款</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="./Index?pre_receive">预收账款</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="./Index?pre_pay">预付账款</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="data_manage.jsp">数据维护</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
		</div>
	<form id="fileUpload" action="./ImpData" enctype="multipart/form-data" method="post">
	<table>
		<tr>
			<td>时间：</td>
			<td colspan="2">
				<input type="datetime" id="month" name="month" value="" onclick="WdatePicker({dateFmt:'yyyy-MM'})"
											readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td>
				应收账款
			</td>
			<td>
				<input id="should_receive" name="file" type="file" />
			</td>
			<td>
				<input type="button" value="导入" onclick="submitExcel('should_receive')" />
			</td>
			<td><a href="demo/yingshouzhangkuan.xls">下载模板</a></td>
		</tr>
		<tr>
			<td>
				应付账款
			</td>
			<td>
				<input id="should_pay" name="file" type="file" />
			</td>
			<td>
				<input type="button" value="导入" onclick="submitExcel('should_pay')" />
			</td>
			<td><a href="demo/yingfuzhangkuan.xls">下载模板</a></td>
		</tr>
		<tr>
			<td>
				预收账款
			</td>
			<td>
				<input id="pre_receive" name="file" type="file" />
			</td>
			<td>
				<input type="button" value="导入" onclick="submitExcel('pre_receive')" />
			</td>
			<td><a href="demo/yushouzhangkuan.xls">下载模板</a></td>
		</tr>
		<tr>
			<td>
				预付账款
			</td>
			<td>
				<input id="pre_pay" name="file" type="file" />
			</td>
			<td>
				<input type="button" value="导入" onclick="submitExcel('pre_pay')" />
			</td>
			<td><a href="demo/yufuzhangkuan.xls">下载模板</a></td>
		</tr>
	</table>
	<input type="hidden" name="type" value="" id="type"/>
	</form>
	</center>
</body>
</html>