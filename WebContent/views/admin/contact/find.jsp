<%@page import="model.bean.Contact"%>
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
				<h2>Quản lý liên hệ</h2>
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
								<div class="col-md-4">
									<form action="<%=request.getContextPath()%>/admin/contact/index" method="post">
										<div class="input-group form">
											<input type="text" class="form-control"
												placeholder="Search..." name="name" value="<%=session.getAttribute("name")%>"> <span
												class="input-group-btn">
												<input class="btn btn-primary" type="submit" value="Tìm kiếm">
											</span>
										</div>
									</form>
								</div>
							</div>

							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên</th>
										<th>Email</th>
										<th>Website</th>
										<th>Messages</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
										ArrayList<Contact> listContacts = new ArrayList<Contact>();
										if (request.getAttribute("listContacts") != null) {
											listContacts = (ArrayList<Contact>) request.getAttribute("listContacts");
											if (listContacts.size() > 0) {
												for (Contact c : listContacts) {
													String urlDel = request.getContextPath() + "/admin/contact/del?id=" + c.getId();
									%>
									<tr class="gradeX">
										<td><%=c.getId() %></td>
										<td><%=c.getName() %></td>
										<td><%=c.getEmail() %></td>
										<td><%=c.getWebsite() %></td>
										<td><%=c.getMessage() %></td>
										<td class="text-center"><a onclick="return confirm('Bạn có chắc chắn muốn thực hiện');"
											href="<%=urlDel %>"
											title="" class="btn btn-danger"><i class="fa fa-trash-o"></i>
												Xóa</a></td>
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
								if (listContacts != null && listContacts.size() > 0) {
							%>

							<div class="row">
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
												href="<%=request.getContextPath()%>/admin/contact/index?pageid=<%=currentPage - 1%>">Trang
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
												href="<%=request.getContextPath()%>/admin/contact/index?pageid=<%=i%>"><%=i%></a></li>
											<%
												}
											%>
											<%
												if (currentPage < numberOfPage) {
											%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a
												href="<%=request.getContextPath()%>/admin/contact/index?pageid=<%=currentPage + 1%>">Trang
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