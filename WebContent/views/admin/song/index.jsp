<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="util.CodeMessageUtil"%>
<%@page import="model.bean.Song"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<%
CategoryDAO categoryDAOfind = new CategoryDAO();

%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý bài hát</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr>
		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-responsive">
							<div class="row" style="margin-bottom: 30px;">
								<div class="col-sm-8">
									<a href="<%=request.getContextPath()%>/admin/song/add"
										class="btn btn-success btn-md" id=""> <i
										class="fa fa-plus"></i>&nbsp;Thêm
									</a>
								</div>
								<div class="col-md-4">
									<form action="<%=request.getContextPath()%>/admin/song/search"
										method="get">
										<div class="input-group form">
											<input type="text" class="form-control"
												placeholder="Nhập tên bài hát..." name="sname">
											<span class="input-group-btn"> <input
												class="btn btn-primary" type="submit" value="Tìm kiếm">
											</span>
										</div>
									</form>
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
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên</th>
										<th>Ca sĩ</th>
										<th>Danh mục</th>
										<th>Hình ảnh</th>
										<th>Nhạc</th>
										<th>Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
										ArrayList<Song> listSongs = new ArrayList<Song>();
										if (request.getAttribute("listSongs") != null) {
											listSongs = (ArrayList<Song>) request.getAttribute("listSongs");
											if (listSongs.size() > 0) {
												for (Song s : listSongs) {
													String urlDel = request.getContextPath() + "/admin/song/del?id=" + s.getId();
													String urlEdit = request.getContextPath() + "/admin/song/edit?id=" + s.getId();
									%>
									<tr class="gradeX">
										<td><%=s.getId()%></td>
										<td><a href="<%=urlEdit%>"><%=s.getName()%></a></td>
										<td><%=s.getSinger()%></td>
										<td><%=s.getCategory().getName()%></td>
										<td class="center">
											<%
												if (s.getPicture() == null) {
											%>
											<p style="color: red">Chưa có hình ảnh</p> <%
											 	} else {
											 %> <img src="<%=request.getContextPath()%>/uploads/<%=s.getPicture()%>"
											alt="" height="200px" width="200px">
										</td>
										<td class="center">
											<%
												if (s.getFilemusic() == null) {
											%>
												<p style="color: red">Chưa có Nhạc</p> <%
											 	} else {
											 %> <audio controls>
												<source
													src="<%=request.getContextPath()%>/uploads/<%=s.getFilemusic()%>"
													type="audio/mpeg">
											</audio>
										</td>

										<%
											}
										%>

										<td class="center"><a href="<%=urlEdit%>" title=""
											class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a> <a
											href="<%=urlDel%>" title="" class="btn btn-danger"
											onclick="return confirm('Bạn chắc chắn muốn xóa không ?');">
												<i class="fa fa-trash-o"> </i> Xóa
										</a></td>
									</tr>
									<%
										}
												}
											}
									%>

								</tbody>
							</table>

							<%
								int numberOfPage = (int) request.getAttribute("numberOfPage");
									int currentPage = (int) request.getAttribute("currentPage");
									if (listSongs != null && listSongs.size() > 0) {
							%>

							<div class="row">
								<div class="col-sm-6">
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">
										<%-- <a href="<%=request.getContextPath()%>/admin/song/index?destroy=destroy">Hủy Tìm Kiếm</a> --%></div>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">

											<%
												if (numberOfPage > 1) {
															if (currentPage > 1) {
											%>
											<li class="paginate_button previous"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a
												href="<%=request.getContextPath()%>/admin/song/index?pageid=<%=currentPage - 1%>">Trang
													trước</a></li>
											<%
												} else {
											%>
											<li class="paginate_button previous"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a href="#">Trang
													trước</a></li>
											<%
												}
											%>
											<%
												String active = "";
															for (int i = 1; i <= numberOfPage; i++) {
																if (currentPage == i) {
																	active = "active";
																} else {
																	active = "";
																}
											%>
											<li class="paginate_button <%=active%>"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/song/index?pageid=<%=i%>"><%=i%></a></li>
											<%
												}
											%>
											<%
												if (currentPage < numberOfPage) {
											%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a
												href="<%=request.getContextPath()%>/admin/song/index?pageid=<%=currentPage + 1%>">Trang
													tiếp</a></li>
											<%
												} else {
											%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a href="#">Trang tiếp</a></li>
											<%
												}
														}
											%>
										</ul>
									</div>
								</div>
							</div>
							<%
								}
								}
							%>
						</div>

					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
	</div>
</div>
<script>
	document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>