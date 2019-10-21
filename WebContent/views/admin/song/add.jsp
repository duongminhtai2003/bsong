<%@page import="util.CodeMessageUtil"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Thêm bài hát</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<%
			if (request.getParameter("msg") != null) {
				int code = Integer.valueOf(request.getParameter("msg"));
				switch (code) {
					case 0 :
						out.print(CodeMessageUtil.displayMessage(out, "Có lỗi trong quá trình xử lý!!!"));
						break;
					case 1 :
						out.print(CodeMessageUtil.displayMessage(out, "Thêm thành công"));
						break;
					case 2 :
						out.print(CodeMessageUtil.displayMessage(out, "Sửa thành công"));
						break;
					case 3 :
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
							<div class="col-md-12">
								<form action="<%=request.getContextPath()%>/admin/song/add"
									role="form" method="post" enctype="multipart/form-data"
									id="form">
									<div class="form-group">
										<label for="name">Tên bài hát</label> <input type="text"
											id="name" value="" name="name" class="form-control"
											required="required" />
									</div>
									<div class="form-group">
										<label for="singer">Ca sĩ</label> <input type="text"
											id="singer" value="" name="singer" class="form-control" />
									</div>
									<div class="form-group">
										<label for="category">Danh mục bài hát</label> <select
											id="category" name="category" class="form-control"
											required="required">
											<option value="" selected>--Xin lựa chọn--</option>
											<%
												CategoryDAO categoryDAO = new CategoryDAO();
												for (Category c : categoryDAO.getItems()) {
											%>
											<option value="<%=c.getId()%>"><%=c.getName()%></option>
											<%
												}
											%>
										</select>
									</div>
									<div class="form-group">
										<label for="picture">Upload ảnh</label> <input type="file"
											name="picture" />
									</div>

									<div class="form-group">
										<label for="filemusic">Upload nhạc</label> <input type="file"
											name="filemusic" />
									</div>
									<div class="form-group">
										<label for="preview">Mô tả</label>
										<textarea id="preview" class="form-control" rows="3"
											name="preview_text" required="required"></textarea>
									</div>
									<div class="form-group">
										<label>Chi tiết</label>
										<textarea cols="80" name="detail_text" class="ckeditor"
											rows="10" id="text"></textarea>
									</div>

									<button type="submit" name="submit"
										class="btn btn-success btn-md">Thêm</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
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
								name : {
									required : true,
								},
								singer : {
									required : true,
								},
								category : {
									required : true,
								},
								preview : {
									required : true,
								},
							},
							messages : {
								name : {
									required : " (Chưa nhập tên bài hát.)",
								},
								singer : {
									required : " (Chưa nhập tên ca sĩ.)",
								},
								category : {
									required : " (Chưa chọn danh mục.)",
								},
								preview : {
									required : " (Chưa nhập mô tả.)",
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
<script>
	document.getElementById("song").classList.add('active-menu');
</script>
<script>
	CKEDITOR.replace('text');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>