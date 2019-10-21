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

<%
	User objUsersOld = new User();
	if(request.getAttribute("objUsersOld") != null){
		objUsersOld = (User)request.getAttribute("objUsersOld");
	}
%>
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
					out.print(CodeMessageUtil.displayMessage(out, "Chỉ có admin mới được sửa!!!"));
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
								<form action="<%=request.getContextPath()%>/admin/user/edit?id=<%=objUsersOld.getId() %>"
									role="form" method="POST">
									<!-- username -->
									<div class="form-group">
										<label>Username</label> <input
											pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
											placeholder="Tên đăng nhập" required="required" type="text"
											name="username" class="form-control" value="<%=objUsersOld.getUsername()%>">
									</div>
									<!-- Password -->
									<div class="form-group">
										<label>Password</label> <input placeholder="Mật khẩu"
											required="required" type="password" name="password"
											class="form-control">
									</div>
									<!-- Full name -->
									<div class="form-group">
										<label>Fullname</label> <input
											placeholder="Họ và tên người dùng" required="required"
											type="text" name="fullname" class="form-control" value="<%=objUsersOld.getFullname()%>">
									</div>

									<hr style="margin-top: 30px;">
									<input type="submit" name="add" class="btn btn-success btn-md"
										value="Sửa"> <input type="reset" name="reset"
										class="btn btn-defaut btn-md" value="Nhập lại">
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
<%@ include file="/templates/admin/inc/footer.jsp"%>