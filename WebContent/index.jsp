<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
	    <script language="javascript" type="text/javascript"
			src="<%=request.getContextPath()%>/js/My97DatePicker-4.72/WdatePicker.js"
			defer="defer"></script>
<script type="text/javascript">
	function search() {
		var type = $("#page_hidden").val();
		var month = $("#month").val();
		doSearch(type,month);
	}

	function goJump(type){
		doSearch(type,'');
	}
	function doSearch(type,month){
		$.ajax({
			url:'./WebJump',
			type:'post',
			dataType:'json',
			data:{'page':type,'month':month,'company_name':$("#company_name").val()},
			success:function(result){
				var content = "" ;
				var json = eval(result);
				var productList = json["list"];
				//数据显示
	            for(var i=0; i<productList.length; i++){
	            	content += "<tr>"+"<td>"+productList[i].number+"</td>"+"<td>"+productList[i].name+"</td>"+"<td>"+productList[i].money+"元</td></tr>";
	            }
				if(productList.length>0){
					$("#month").html(productList[0].month+"月");
				}
	            	
	            $('#list').html(content);
	            $("#page_hidden").val(type);
			}
		})
	}
	function onload(){
		if('should_receive'==$("#page_hidden").val() 
				||'should_pay'!=$("#page_hidden").val() 
				||'pre_receive'!=$("#page_hidden").val()
				||'pre_pay'!=$("#page_hidden").val()){
			doSearch($("#page_hidden").val(),'');
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
					<td><a href="#" onclick="goJump('should_receive')">应收账款</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="#" onclick="goJump('should_pay')">应付账款</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="#" onclick="goJump('pre_receive')">预收账款</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="#" onclick="goJump('pre_pay')">预付账款</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><a href="data_manage.jsp">数据维护</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
		</div>
		<div>
			<table>
				<tr>
					<td>单位名称：<input type="text" name="company_name"
						id="company_name" value="" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<!-- <td>月份：<input type="text" name="month" id="month" value=""  onclick="WdatePicker({dateFmt:'yyyy-MM'})"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td> -->
					<td><input type="button" value="查询" id="search"
						onclick="search();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
		</div>
		<div>
			<h2 id="title2"></h2>
			<div style="width: 100%;border:1">
				<label
					style="align: center">单位：元</label>
					<label id="month" style="align: right"></label> 
			</div>
			<table border="1" width="80%">
				<tr>
					<th width="20%">单位编号</th>
					<th width="50%">单位名称</th>
					<th width="30%">余额</th>
				</tr>
				<tbody id="list">
				
				</tbody>
			</table>
		</div>
	</center>
	<input type="hidden" id="page_hidden" name="page_hidden"
		value="<%=request.getAttribute("type") %>" />
</body>
</html>