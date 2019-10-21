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
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý người dùng</h2>
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
								<div class="col-md-8">
									<a href="<%=request.getContextPath()%>/admin/user/add"
										class="btn btn-success btn-md" id=""> <i
										class="fa fa-plus"></i>&nbsp;Thêm
									</a>
								</div>
								<div class="col-md-4">
									<div class="input-group form">
										<form action="<%=request.getContextPath()%>/admin/user/find"
											method="post">
											<div class="input-group form">
												<input type="text" class="form-control"
													placeholder="fullname..." name="fullname" value="<%=session.getAttribute("fullname") %>"> <span
													class="input-group-btn"> <input
													class="btn btn-primary" type="submit" value="Tìm kiếm">
												</span>
											</div>
										</form>
									</div>
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
										<th>Tên người dùng</th>
										<th>Fullname</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
										ArrayList<User> listUsers = new ArrayList<User>();
										if (request.getAttribute("listUsers") != null) {
											listUsers = (ArrayList<User>) request.getAttribute("listUsers");
											if (listUsers.size() > 0) {
												for (User u : listUsers) {
													String urlDel = request.getContextPath() + "/admin/user/del?id=" + u.getId();
													String urlEdit = request.getContextPath() + "/admin/user/edit?id=" + u.getId();
									%>
									<tr class="gradeX">
										<td><%=u.getId()%></td>
										<td><%=u.getUsername()%></td>
										<td><%=u.getFullname()%></td>
										<td class="text-center"><a href="<%=urlEdit%>" title=""
											class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a> <a
											onclick="return confirm('Bạn có chắc chắn muốn xóa ?');"
											href="<%=urlDel%>" title="" class="btn btn-danger"><i
												class="fa fa-trash-o"></i> Xóa</a></td>
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
								if (listUsers != null && listUsers.size() > 0) {
							%>

							<div class="row">
								<div class="col-sm-6">
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">Hiển thị từ 1 đến 5 của 24
										truyện</div>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">

											<%
												if (numberOfPage > 1) {
														if (currentPage > 1) {
											%>
											<li class="paginate_button previous disabled"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a
												href="<%=request.getContextPath()%>/admin/user/index?pageid=<%=currentPage - 1%>">Trang
													trước</a></li>
											<%
												} else {
											%>
											<li class="paginate_button previous disabled"
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
												href="<%=request.getContextPath()%>/admin/user/index?pageid=<%=i%>"><%=i%></a></li>
											<%
												}
											%>
											<%
												if (currentPage < numberOfPage) {
											%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a
												href="<%=request.getContextPath()%>/admin/user/index?pageid=<%=currentPage + 1%>">Trang
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
							%>
						</div>

					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
	</div>

</div>
<%@ include file="/templates/admin/inc/footer.jsp"%>