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
            <h2>Welcome to Registe Charisma</h2>
        </div>
        <!--/span-->
    </div><!--/row-->
    <div class="row">
        <div class="well col-md-5 center login-box">
            <div class="alert alert-info"><c:if test="${error != null}">${error}</c:if></div>
            <form class="form-horizontal" action="<%=request.getContextPath() %>/signup.html" method="post">
                <fieldset>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                        <input type="text" name="username" class="form-control" placeholder="用户名只能是字符和数字，且以字符开始">
                    </div>
                    <div class="clearfix"></div><br>

                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                        <input type="password" name="password" class="form-control" placeholder="密码长度为6-20位的字符组合">
                    </div>
                    <div class="clearfix"></div><br>
					<div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                        <input type="password" name="repassword" class="form-control" placeholder="重复密码">
                    </div>
                    <div class="clearfix"></div><br>

                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope red"></i></span>
                        <input name="email" class="form-control" placeholder="邮箱">
                    </div>
                    <div class="clearfix"></div><br>
                   <p>
                    <button type="submit" class="btn btn-primary btn-sm" style="width:120px;">确定注册</button>
                    <a class="btn btn-danger btn-sm" style="width:120px;" href="<%=request.getContextPath()%>/login.html">已有账户，去登陆</a>
               	   </p>
                </fieldset>
            </form>
        </div>
        <!--/span-->
    </div><!--/row-->
</div><!--/fluid-row-->

</div><!--/.fluid-container-->
<script type="text/javascript">
	$(".btn-primary").click(function(e){
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
		
		var repassword = $("input[name='repassword']").val();
		if(!patrnP.exec(repassword)){
			$(".alert-info").html("重复密码格式不正确,请重新填写");
			return false;
		}
		
		if(password != repassword){
			$(".alert-info").html("密码和重复密码不一致,请重新填写");
			return false;
		}
		
		var patrnE = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
		var email = $("input[name='email']").val();
		if(!patrnE.exec(email)){
			$(".alert-info").html("邮箱格式不正确");
			return false;
		}
	});
</script>

</body>
</html>
