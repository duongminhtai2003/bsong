<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.Song"%>
<%@page import="model.dao.SongDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<%
	CategoryDAO categoryDAO = new CategoryDAO();
	if (request.getAttribute("objSongsOld") != null) {
		Song objSongsOld = (Song) request.getAttribute("objSongsOld");
%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Sửa bài hát</h2>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-12">
				<!-- Form Elements -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<form
									role="form" id="form" action="<%=request.getContextPath()%>/admin/song/edit?id=<%=objSongsOld.getId()%>"
									enctype="multipart/form-data" method="POST">

									<div style="width: 50%" class="form-group">
										<label for="name">Tên bài hát</label> <input type="text"
											value="<%=objSongsOld.getName()%>" name="name"
											class="form-control" id="name">
									</div>
									<div style="width: 50%" class="form-group">
										<label for="singer">Ca sĩ: </label> <input type="text"
											value="<%=objSongsOld.getSinger()%>" name="singer"
											class="form-control" id="singer">
									</div>
									<div style="width: 50%" class="form-group">
										<label for="category">Danh mục</label> <select name="categories" id="category"
											class="form-control" id="category">
											<option value="<%=objSongsOld.getCategory().getId()%>"><%=objSongsOld.getCategory().getName()%></option>
											<%
												for (Category c : categoryDAO.getItems()) {
											%>
											<option value="<%=c.getId()%>"><%=c.getName()%></option>
											<%
												}
											%>
										</select>
									</div>
									<div class="form-group">
										<label>Thêm hình ảnh</label> <input name="picture" type="file"
											class="btn btn-default" id="exampleInputFile1">
										<%
											if ("".equals(objSongsOld.getPicture())) {
										%>
										<p style="color: red">Không có hình ảnh</p>
										<%
											} else {
										%>
										<img style="margin-top: 10px" width="160px" height="90px"
											src="<%=request.getContextPath()%>/uploads/<%=objSongsOld.getPicture()%>">
										<%
											}
										%>
									</div>
									<div class="form-group">
										<label>Upload nhạc</label> <input name="filemusic" type="file"
											class="btn btn-default" id="exampleInputFile1">
										<%
											if (objSongsOld.getFilemusic().isEmpty()) {
										%>
										<p style="color: red">Không có nhạc</p>
										<%
											} else {
										%>
										<audio controls>
											<source
												src="<%=request.getContextPath()%>/uploads/<%=objSongsOld.getFilemusic()%>"
												type="audio/mpeg">
										</audio>
										<%
											}
										%>
									</div>
									<div class="form-group">
										<label for="preview">Mô tả</label>
										<textarea id="preview" name="preview_text" class="form-control" rows="3"><%=objSongsOld.getPreview_text()%></textarea>
									</div>
									<div class="form-group">
										<label>Chi tiết</label>
										<textarea cols="80" name="detail_text" class="ckeditor"
											rows="10" id="text"><%=objSongsOld.getDetail_text()%></textarea>
										<script>
											CKEDITOR.replace('text');
										</script>
									</div>

									<input type="submit" name="edit" class="btn btn-success btn-md"
										value="Sửa">
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
<%
	}
%>
<%@ include file="/templates/admin/inc/footer.jsp"%>