<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login Vinaenter EDU</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/png" href="<%=request.getContextPath() %>/templates/auth/images/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/auth/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/auth/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/auth/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/auth/css/main.css">
</head>
<body>
	<div class="limiter">
		<div class="container-login100" style="background-image: url('<%=request.getContextPath() %>/templates/auth/images/img-01.jpg');">
			<div class="wrap-login100 p-t-190 p-b-30">
				<form class="login100-form validate-form" action="<%=request.getContextPath() %>/auth/login" method="post"> 
					<div class="login100-form-avatar">
						<img src="<%=request.getContextPath() %>/templates/auth/images/avatar-01.jpg" alt="AVATAR">
					</div>

					<span class="login100-form-title p-t-20 p-b-45">
						Login Vinaenter EDU
					</span>
					<%
						if(request.getParameter("msg") != null){
					%>
					<div>
						<p style="color: red">Sai username hoặc password</p>
					</div>
					<%} %>
					<div class="wrap-input100 validate-input m-b-10" data-validate = "Username is required">
						<input class="input100" type="text" name="username" placeholder="Username">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-user"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input m-b-10" data-validate = "Password is required">
						<input class="input100" type="password" name="password" placeholder="Password">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock"></i>
						</span>
					</div>

					<div class="container-login100-form-btn p-t-10">
						<button class="login100-form-btn">
							Login
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath() %>/templates/auth/js/main.js"></script>
</body>
</html>