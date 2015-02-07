<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Free HTML5 Bootstrap Admin Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">
	<jsp:include page="common/head.jsp"/>
</head>
<body>
<div class="ch-container">
    <div class="row">
    <div class="row">
        <div class="col-md-12 center login-header">
            <h2>Welcome to Charisma</h2>
        </div>
        <!--/span-->
    </div><!--/row-->
    <div class="row">
        <div class="well col-md-5 center login-box">
            <div class="alert alert-info">
                <c:if test="${error != null}">${error}</c:if>
                <c:if test="${error == null}">请输入用户名和密码</c:if>
            </div>
            <form class="form-horizontal" action="<%=request.getContextPath() %>/signin.html" method="post">
                <fieldset>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon" ><i class="glyphicon glyphicon-user red"></i></span>
                        <input type="text" name="username" class="form-control" placeholder="用户名">
                    </div>
                    <div class="clearfix"></div><br>

                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                        <input type="password" name="password" class="form-control" placeholder="密码">
                    </div>
                    <div class="clearfix"></div>

                    <div class="input-prepend">
                        <label class="remember" for="remember"><input type="checkbox" id="remember"> 记住我</label>
                    </div>
                    <div class="clearfix"></div>

					<p>
                    <button type="submit" class="btn btn-primary btn-sm" style="width:120px;">登陆</button>
                    <a class="btn btn-danger btn-sm" style="width:120px;" href="<%=request.getContextPath()%>/register.html">没有账户，去注册</a>
               	   	</p>
                </fieldset>
            </form>
        </div>
        <!--/span-->
    </div><!--/row-->
</div><!--/fluid-row-->

</div><!--/.fluid-container-->
<script type="text/javascript">
	$(".btn-primary").click(function(){
		var patrnU=/^[a-zA-Z]{1}([a-zA-Z0-9]){5,19}$/; 
		var username = $("input[name='username']").val();
		if(!patrnU.exec(username)){
			$(".alert-info").html("用户名格式不正确,请重新填写");
			return false;
		}
		
		var patrnP = /^(\w){6,20}$/;  
		var password = $("input[name='password']").val();
		if(!patrnP.exec(password)){
			$(".alert-info").html("密码格式不正确,请重新填写");
			return false;
		}
		return true;
	});
</script>

</body>
</html>
