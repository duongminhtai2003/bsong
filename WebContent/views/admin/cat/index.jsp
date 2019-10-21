<%@page import="util.CodeMessageUtil"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý danh mục</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-responsive">
							<div class="row" style="margin-bottom: 20px">
								<div class="col-sm-6">
									<a
										href="<%=request.getContextPath()%>/admin/cat/add"
										class="btn btn-success btn-md">Thêm</a>
								</div>
							</div>
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

							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>ID</th>
										<th>Danh mục</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
										if (request.getAttribute("listCat") != null) {
											ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
											if (listCat.size() > 0) {
												for (Category c : listCat) {
													String urlDel = request.getContextPath() + "/admin/cat/delete?id=" + c.getId();
													String urlEdit = request.getContextPath() + "/admin/cat/edit?id=" + c.getId();
									%>
									<tr>
										<td><%=c.getId()%></td>
										<td class="center"><%=c.getName()%></td>
										<td class="center"><a href="<%=urlEdit%>" title=""
											class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a> <a
											onclick="return confirm('Bạn có chắc chắn muốn xóa không?');"
											href="<%=urlDel%>" title="" class="btn btn-danger"><i
												class="fa fa-trash-o"></i> Xóa</a>
									</tr>
									<%
										}
											}
										}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
	</div>
</div>
<script>
	document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>