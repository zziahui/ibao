<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Free HTML5 Bootstrap Admin Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">
    <link href='<%=request.getContextPath()%>/assets/style/style.css' rel='stylesheet'>
	<jsp:include page="../../common/head.jsp"/>
</head>
<body>
	<jsp:include page="../../common/top.jsp"/>
<div class="ch-container">
    <div class="row">
        
		<jsp:include page="../../common/left.jsp"/>
        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
			<div>
			    <ul class="breadcrumb">
			        <li>
			            <a href="#">首页</a>
			        </li>
			        <li>
			            <a href="#">未完成任务</a>
			        </li>
			    </ul>
			</div>
<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-star-empty"></i>未完成任务</h2>
            </div>
            <div class="box-content">
                <ul class="nav nav-tabs" id="myTab">
                    <li class="active"><a href="#info">未完成任务列表</a></li>
                    <li><a href="#custom" disabled="">添加任务</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active" id="info">
                    	<div class="alert alert-info">For help with such table please check <a href="http://datatables.net/" target="_blank">http://datatables.net/</a></div>
					    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
						    <thead>
						    <tr>
						    	<th style="width:100px;">视频地址</th>
						    	<th style="width:100px;">任务类型</th>
						    	<th style="width:100px;">待刷数量</th>
						    	<th style="width:100px;">已刷数量</th>
						    	<th style="width:90px;">起始数量</th>
						        <th style="width:90px;">结束数量</th>
						        <th style="width:100px;">添加时间</th>
						        <th style="width:100px;">开始时间</th>
						        <th style="width:100px;">结束时间</th>
						        <th>操作</th>
						    </tr>
						    </thead>
						    <tbody>
						    	<c:forEach items="${tasks}" var="item">
							    	<tr>
							    	<td class="center">${item.url}</td>
		                            <td class="center">
		                            	<c:if test="${item.style != null}">${item.style.name}</c:if>
		                            </td>
		                            <td class="center">${item.total}</td>
		                            <td class="center"></td>
		                            <td class="center">${item.begin}</td>
		                            <td class="center">${item.end}</td>
		                            <td class="center">${item.addTime}</td>
		                            <td class="center">${item.beginTime}</td>
		                            <td class="center">${item.endTime}</td>
		                            <td class="center">
		                            	<c:if test="${item.state == 0}">
									        <a data-toggle="tooltip" title="" href="#" data-original-title="点击开始任务" onclick="sendTask(${item.id})">
									            <span class="label-success label label-default">开始任务</span>
									        </a>
		                            	</c:if>
		                            	<c:if test="${item.state == 2}"></c:if>
							    	</tr>
						    	</c:forEach>
						    </tbody>
					    </table>
                    </div>
                    <div class="tab-pane" id="custom">
                    	<div class="box-content">
                    	<form role="form">
			                <div class="form-group col-md-6">
			                    <label>任务类型</label>
			                    <div class="controls">
			                    	<select class="chzn-select" id="code">
			                    		<option value=""></option>
			                    		<c:forEach items="${codes}" var="code">
			                            	<optgroup label="${code.name}">
			                            		<c:forEach items="${styles}" var="style">
			                            			<c:if test="${style.clazz1 == code.code}">
			                            				<option value="${style.code}">${style.name}</option>
			                            			</c:if>
			                            		</c:forEach>
				                            </optgroup>
			                    		</c:forEach>
                        			</select>
                        		</div>
			                </div>
			                <div class="form-group col-md-6">
			                    <label>视频地址</label>
		                        <input name="url" class="form-control" placeholder="如：http://v.youku.com/v_show/id_XODU3MTMyNjYw.html">
			                </div>
			                <div class="form-group col-md-6">
		                        <label>可刷余额</label>
		                        <input id="total" name="total" class="form-control" placeholder="请输入正确的数字" disabled="">
		                    </div>
			                <div class="form-group col-md-6">
			                    <label>待刷数量</label>
		                        <input id="num" name="num" class="form-control">
			                </div>
			                <div class="form-group col-md-6">
		                        <label>起始数量</label>
		                        <input name="begin" class="form-control" placeholder="请输入正确的数字" disabled="">
		                    </div>
		                    <div class="form-group col-md-6">
		                        <label>结束数量</label>
		                        <input name="end" class="form-control" placeholder="请输入正确的数字" disabled="">
		                    </div>
		                    <br>
			                <a id="submit" class="btn btn-primary" style="margin-left:15px;" >添加业务类型</a>
			                <a id="toCheck" data-toggle="modal" class="btn btn-info" style="margin-left:15px;">检测视频信息</a>
			                <a class="btn btn-warning" style="margin-left:15px;">补全视频信息</a>
		                </form>
		                </div>
		                <div class="box-inner" id="checkVedio" style="margin-left:20px;margin-right:20px;">
				            <div class="box-header well">
				                <div class="box-icon">
				                    <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
				                </div>
				            </div>
				            <div class="box-content">
				            <iframe id="cV" src="http://player.youku.com/embed/XNzg0MTEyMzky" style="width:600px;height:400px;" frameborder="0" scrolling="0"></iframe>
				            </div>
        				</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!--/row-->
    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->
    <hr>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="margin: 200px auto;height:500px;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h4>警告</h4>
                </div>
                <div class="modal-body">
                    <p id="error"></p>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>
    
    
    <jsp:include page="../../common/footer.jsp"/>

</div><!--/.fluid-container-->
<script type="text/javascript">
$(".chzn-select").chosen({
	width: '100%',
	height: '5%'
});

$("#code").change(function(e){
	e.preventDefault();
	var code = $("#code").val();
	$.ajax({
		url: "<%=request.getContextPath()%>/admin/task/account.json",
		type: 'POST',
		data: {
			code: code
		},
		success: function(data){
			var account = data.account;
			if(account){
				$("#total").val(account.total);
			}
		}
	});
});

$("#toCheck").click(function(){
	var flag = $("#checkVedio").is(":hidden");
	if(flag){
		var url = $("input[name='url']").val();
		var urlP = new RegExp("^((https|http|ftp|rtsp|mms)://)?[a-z0-9A-Z]{3}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}"
				+"?[a-z0-9A-Z]\.html|htm|shtml|jsp|asp|php|com|net|cn|cc (:s[0-9]{1-4})?/$");
		if(!urlP.test(url)){
			$("#error").html("请填写正确的视频地址");
			$('#myModal').modal('show');
			return false;
		}
		var codes = url.split("id_");
		codes = codes[1].split(".html")[0];
		var href = "http://player.youku.com/embed/"+ codes;
		$("#cV").attr("src", href);
		$("#checkVedio").show();
	}
	else
		$("#checkVedio").hide();
});


$("#num").blur(function(e){
	var num = $("#num").val();
	e.preventDefault();
	var reg = /^[1-9][0-9]*$/;
	if(!reg.test(num)){  
		$("#error").html("请输入正确的数字");
		$('#myModal').modal('show');
		return false;
    }
	return true;
});

$(".btn-warning").click(function(e){
	e.preventDefault();
	var code = $("#code").val();
	var url = $("input[name='url']").val();
	var num = $("#num").val();
	var urlP = new RegExp("^((https|http|ftp|rtsp|mms)://)?[a-z0-9A-Z]{3}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}"
			+"?[a-z0-9A-Z]\.html|htm|shtml|jsp|asp|php|com|net|cn|cc (:s[0-9]{1-4})?/$");
	if(!urlP.test(url)){
		$("#error").html("请填写正确的视频地址");
		$('#myModal').modal('show');
		return false;
	}
	
	var codeReg = /^([1-9][0-9]*)_([1-9][0-9]*)_([1-9][0-9]*)$/;
	if(!codeReg.test(code)){
		$("#error").html("请选择充值类型");
		$('#myModal').modal('show');
		return false;
	}
	$.ajax({
		url: "<%=request.getContextPath()%>/video/check.json",
		type: 'POST',
		data:{
			url : url,
			code : code
		},
		success: function(data){
			if(data.map.vv){
				$("input[name='begin']").val(data.map.vv);
				$("input[name='end']").val(parseInt(num) + parseInt(data.map.vv));
				console.log(parseInt(num) + parseInt(data.map.vv));
			}
		}
		
	});
});
$(".btn-primary").click(function(e){
	e.preventDefault();
	var code = $("#code").val();
	var url = $("input[name='url']").val();
	var num = $("#num").val();
	var begin = $("input[name='begin']").val();
	var end = $("input[name='end']").val();
	var total= $("input[name='total']").val();
	
	var urlP = new RegExp("^((https|http|ftp|rtsp|mms)://)?[a-z0-9A-Z]{3}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}"
			+"?[a-z0-9A-Z]\.html|htm|shtml|jsp|asp|php|com|net|cn|cc (:s[0-9]{1-4})?/$");
	if(!urlP.test(url)){
		$("#error").html("请填写正确的视频地址");
		$('#myModal').modal('show');
		return false;
	}
	
	var codeReg = /^([1-9][0-9]*)_([1-9][0-9]*)_([1-9][0-9]*)$/;
	if(!codeReg.test(code)){
		$("#error").html("请选择充值类型");
		$('#myModal').modal('show');
		return false;
	}
	
	var reg = /^[1-9][0-9]*$/;
	if(!reg.test(num)){  
		$("#error").html("请输入正确的数字");
		$('#myModal').modal('show');
		return false;
    }
	
	if(begin == ""){
		$("#error").html("请先补全视频信息（点击补全视频信息）");
		$('#myModal').modal('show');
		return false;
	}
	
	if(end == ""){
		$("#error").html("请先补全视频信息（点击补全视频信息）");
		$('#myModal').modal('show');
		return false;
	}
	
	if(parseInt(num) > parseInt(total)){
		$("#error").html("待刷数量不能大于可刷余额");
		$('#myModal').modal('show');
		return false;
	}
	
	$.ajax({
		url: '<%=request.getContextPath()%>/admin/task/add.json',
		type: 'POST',
		data: {
			num: num,
			begin: begin,
			end: end,
			code: code,
			url: url
		},
		success: function(data){
			console.log(data);
			if(data.success){
				$("#error").html("任务添加成功");
				$('#myModal').modal('show');
				window.location.href = "<%=request.getContextPath()%>/admin/task.html";
			}else{
				$("#error").html(data.error);
				$('#myModal').modal('show');
			}
		}
	});
	
	
});

function sendTask(id){
	$.ajax({
		url: '<%=request.getContextPath()%>/admin/task/'+id+'/send.json',
		success: function(data){
			if(data.success){
				$("#error").html("任务已经成功添加到执行列表");
				$('#myModal').modal('show');
				window.location.href="<%=request.getContextPath()%>/admin/task.html";
			}else{
				$("#error").html(data.error);
				$('#myModal').modal('show');
			}
		}
		
	});
}

$(document).ready(function(){
	$("#checkVedio").hide();
});
</script>
</body>
</html>
