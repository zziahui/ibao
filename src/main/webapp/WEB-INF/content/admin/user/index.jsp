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
			            <a href="#">用户列表</a>
			        </li>
			    </ul>
			</div>
<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-star-empty"></i>用户</h2>
            </div>
            <div class="box-content">
                <ul class="nav nav-tabs" id="myTab">
                    <li class="active"><a href="#info">列表</a></li>
                    <li><a href="#custom" disabled="">用户详情</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active" id="info">
                    	<div class="alert alert-info">For help with such table please check <a href="http://datatables.net/" target="_blank">http://datatables.net/</a></div>
					    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
						    <thead>
						    <tr>
						    	<th>用户名</th>
						        <th>用户邮箱</th>
						        <th>注册日期</th>
						        <th>登录日期</th>
						        <th>密码重试次数</th>
						        <th>用户状态</th>
						        <th>用户操作</th>
						    </tr>
						    </thead>
						    <tbody>
						    	<c:forEach items="${users}" var="user">
						    	<tr>
		                            <td class="center">${user.username}</td>
		                            <td class="center">${user.email}</td>
		                            <td class="center">${user.addTime}</td>
		                            <td class="center">${user.signTime}</td>
		                            <td class="center">${user.retry}</td>
		                            <td class="center">${user.state}</td>
		                            <td class="center">
		                            	<a href="#" onclick="view(${item.id})">
		                            	<i class="glyphicon glyphicon-trash icon-white"></i>
               								 查看
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
			                    <label>充值用户</label>
			                    <div class="controls">	
			                    	<select class="chzn-select" id="user">
			                    		<option value=""></option>
			                    		<c:forEach items="${users}" var="user">
			                            		<option value="${user.id}">${user.username}</option>
			                    		</c:forEach>
                        			</select>
                        		</div>
			                </div>
			                <div class="form-group col-md-6">
			                    <label>充值类型</label>
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
			                    <label>所剩余额</label>
		                        <input name="total" class="form-control" disabled="">
			                </div>
			                <div class="form-group col-md-6">
		                        <label>待充数量</label>
		                        <input id="num" name="num" class="form-control" placeholder="请输入正确的数字">
		                    </div>
			                <button type="submit" class="btn btn-primary" style="margin-left:15px;" >添加业务类型</button>
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

function view(id){
	$('#myTab a').click();
}
</script>
</body>
</html>
