<%@page import="model.bean.User"%>
<%@page import="util.CodeMessageUtil"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.Song"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Thêm người dùng</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr>
		<%
			if (request.getParameter("msg") != null) {
				int code = Integer.valueOf(request.getParameter("msg"));
				switch (code) {
				case 0:
					out.print(CodeMessageUtil.displayMessage(out, "Có lỗi trong quá trình xử lý!!!"));
					break;
				case 1:
					out.print(CodeMessageUtil.displayMessage(out, "Thêm thành công"));
					break;
				case 2:
					out.print(CodeMessageUtil.displayMessage(out, "Sửa thành công"));
					break;
				case 3:
					out.print(CodeMessageUtil.displayMessage(out, "Xóa thành công"));
					break;
				}

			}
		%>
		<div class="row">
			<div class="col-md-12">
				<!-- Form Elements -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-6">
								<form action="<%=request.getContextPath()%>/admin/user/add" role="form" method="POST" id="form">
									<!-- username -->
									<div class="form-group">
										<label for="username">Username</label> <input
											pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
											placeholder="Tên đăng nhập" type="text"
											name="username" class="form-control" id="username">
									</div>
									<!-- Password -->
									<div class="form-group">
										<label for="password">Password</label> <input placeholder="Mật khẩu"
											type="password" name="password"
											class="form-control" id="password">
									</div>
									<!-- Password -->
									<div class="form-group">
										<label for="password1">Password*</label> <input placeholder="Mật khẩu"
											type="password" name="password1"
											class="form-control" id="password1">
									</div>
									<!-- Full name -->
									<div class="form-group">
										<label for="fullname">Fullname</label> <input
											placeholder="Họ và tên người dùng" 
											type="text" name="fullname" class="form-control" id="fullname">
									</div>

									<hr style="margin-top: 30px;">
									<input type="submit" name="add" class="btn btn-success btn-md" value="Thêm">
									<input type="reset" name="reset" class="btn btn-defaut btn-md" value="Nhập lại">
								</form>

							</div>

						</div>
					</div>
				</div>
				<!-- End Form Elements -->
			</div>
		</div>
		<!-- /. ROW  -->
	</div>
	<!-- /. PAGE INNER  -->
</div>
<script type="text/javascript">
	$().ready(
			function() {
				var validator = $("#form").validate(
						{
							errorPlacement : function(error, element) {
								$(element).closest("form").find(
										"label[for='" + element.attr("id")
												+ "']").append(error);
							},
							errorElement : "span",
							rules : {
								username : {
									required : true,
								},
								password : {
									required : true,
								},
								password1 : {
									required: true,
								},
								fullname : {
									required : true,
								},
							},
							messages : {
								username : {
									required : " (Chưa nhập username.)",
								},
								password : {
									required : " (Chưa nhập password.)",
								},
								password1 : {
									required : " (password chưa chính xác.)",
								},
								fullname : {
									required : " (Chưa nhập fullname.)",
								},

							}
						});
			});
</script>
<style>
.error {
	display: block;
	float: none;
	color: red;
}
</style>
<%@ include file="/templates/admin/inc/footer.jsp"%>