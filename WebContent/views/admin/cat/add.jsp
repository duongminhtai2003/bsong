<%@page import="util.CodeMessageUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Thêm Danh Mục</h2>
			</div>
		</div>
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
								<form action="<%=request.getContextPath()%>/admin/cat/add" method="POST" id="from" role="form">
									<div class="form-group">
										<label for="name">Tên danh mục</label> 
										<input type="text" name="name" class="form-control" id="name">
									</div>
									<input type="submit" name="add" class="btn btn-success btn-md"
										value="Thêm">
								</form>
							</div>

						</div>
					</div>
				</div>
				<!-- End Form Elements -->
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$().ready(
			function() {
				var validator = $("#from").validate(
						{
							errorPlacement : function(error, element) {
								$(element).closest("form").find(
										"label[for='" + element.attr("id")
												+ "']").append(error);
							},
							errorElement : "span",
							rules : {
								name : {
									required : true,
								},
							},
							messages : {
								name : {
									required : " (Chưa nhập tên danh mục.)",
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