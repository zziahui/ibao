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
			            <a href="#">业务类型</a>
			        </li>
			    </ul>
			</div>
<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-star-empty"></i>业务类型</h2>
            </div>
            <div class="box-content">
                <ul class="nav nav-tabs" id="myTab">
                    <li class="active"><a href="#info">列表</a></li>
                    <li><a href="#custom" disabled="">添加业务类型</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active" id="info">
                    	<div class="alert alert-info">For help with such table please check <a href="http://datatables.net/" target="_blank">http://datatables.net/</a></div>
					    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
						    <thead>
						    <tr>
						    	<th>类别一</th>
						        <th>类别二</th>
						        <th>类型名称</th>
						        <th>类型代码</th>
						        <th>所属组</th>
						        <th>备注</th>
						        <th>是否删除</th>
						    </tr>
						    </thead>
						    <tbody>
						    	<c:forEach items="${list}" var="item">
						    	<tr>
		                            <td class="center">
		                            	<c:if test="${item.clazz1 == 1}">优酷</c:if>
		                            	<c:if test="${item.clazz1 == 2}">土豆</c:if>
		                            </td>
		                            <td class="center">
		                            	<c:if test="${item.clazz2 == 1}">刷量</c:if>
		                            	<c:if test="${item.clazz2 == 2}">刷赞</c:if>
		                            	<c:if test="${item.clazz2 == 3}">刷踩</c:if>
		                            	<c:if test="${item.clazz2 == 4}">刷收藏</c:if>
		                            	<c:if test="${item.clazz2 == 5}">刷订阅</c:if>
		                            	<c:if test="${item.clazz2 == 5}">刷评论</c:if>
		                            </td>
		                            <td class="center">${item.name}</td>
		                            <td class="center">${item.code}</td>
		                            <td class="center">
		                            	<c:if test="${item.group == 1}">视频类</c:if>
		                            </td>
		                            <td class="center">${item.comment}</td>
		                            <td class="center">
		                            	<a href="#" onclick="del(${item.id})">
		                            	<i class="glyphicon glyphicon-trash icon-white"></i>
               								 删除
            							</a>
		                            </td>
		                            
		                        </tr>
						    	</c:forEach>
						    </tbody>
					    </table>
                    </div>
                    <div class="tab-pane" id="custom">
                    	<div class="box-content">
                    	<form role="form">
		                    <div class="form-group col-md-6">
			                    <label>所属分组</label>
			                    <div class="controls">	
			                    	<select class="chzn-select" id="group">
			                    		<c:forEach items="${codes}" var="code">
			                            	<c:if test="${code.type == 3}">
			                            		<option value="${code.code}">${code.name}</option>
			                            	</c:if>
			                    		</c:forEach>
                        			</select>
                        		</div>
			                </div>
			                <div class="form-group col-md-6">
			                    <label>类别一</label>
			                    <div class="controls">	
			                    	<select class="chzn-select" id="clazz1">
			                    		<c:forEach items="${codes}" var="code">
			                            	<c:if test="${code.type == 1}">
			                            		<option value="${code.code}">${code.name}</option>
			                            	</c:if>
			                    		</c:forEach>
                        			</select>
                        		</div>
			                </div>
			                <div class="form-group col-md-6">
			                    <label>类别二</label>
			                    <div class="controls">	
			                    	<select class="chzn-select" id="clazz2">
			                    		<c:forEach items="${codes}" var="code">
			                            	<c:if test="${code.type == 2}">
			                            		<option value="${code.code}">${code.name}</option>
			                            	</c:if>
			                    		</c:forEach>
                        			</select>
                        		</div>
			                </div>
			                <div class="form-group col-md-6">
		                        <label>名称</label>
		                        <input name="name" class="form-control">
		                    </div>
		                    <div class="form-group col-md-12">
		                    	<label>是否支持： </label>
			                    <input type="checkbox" id="" value="option1">
			                </div>
			                <button type="submit" class="btn btn-primary">添加业务类型</button>
		                </form>
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

function del(id){
	$.ajax({
		url: '<%=request.getContextPath()%>/admin/style/del/'+ id+ '.json',
		type: 'get',
		success:function(data){
			if(data.success){
				window.location.href = "<%=request.getContextPath()%>/admin/style.html";
			}
		}
	});
}

$(".btn-primary").click(function(e){
	e.preventDefault();
	var group = $("#group").val();
	var clazz1 = $("#clazz1").val();
	var clazz2 = $("#clazz2").val();
	var name = $("input[name='name']").val();
	 
	if(name == null || name == ""){
		$("#error").html("名称不能为空");
		$('#myModal').modal('show');
		return false;
	}
	
	$.ajax({
		url: "<%= request.getContextPath()%>/admin/style/add.json",
		type: 'POST',
		data:{
			group: group,
			clazz1: clazz1,
			clazz2: clazz2,
			name: name
		},
		success: function(data){
			if(!data.success){
				$("#error").html(data.error);
				$('#myModal').modal('show');
				return;
			}
			$("#error").html("添加成功");
			$('#myModal').modal('show');
			window.location.href = "<%=request.getContextPath()%>/admin/style.html";
			
		}
	});
	
	
	
	
});
</script>
</body>
</html>
