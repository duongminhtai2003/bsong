<%@page import="util.StringUtil"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>
<%
	Category objCategory = new Category();
	ArrayList<Song> listSongs = new ArrayList<Song>();
	if (request.getAttribute("listSongs") != null && request.getAttribute("objCategory") != null) {
		listSongs = (ArrayList<Song>) request.getAttribute("listSongs");
		objCategory = (Category) request.getAttribute("objCategory");
%>
<div class="content_resize">
	<div class="mainbar">
		<div class="article">
			<h1><%=objCategory.getName()%></h1>
		</div>

		<%
			for (Song s : listSongs) {
		%>
		<div class="article">
			<h2>
				<a href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(s.getName()) %>-<%=s.getId() %>" title="Đổi thay"><%=s.getName()%></a>
			</h2>
			<p class="infopost">
				Ngày đăng:
				<%=s.getDate_create()%>
				<a href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(s.getName()) %>-<%=s.getId() %>" class="com"><span><%=s.getCounter()%></span></a>
			</p>
			<div class="clr"></div>
			<div class="img">
				<img src="<%=request.getContextPath()%>/uploads/<%=s.getPicture()%>"
					width="177" height="213" alt="Đổi thay" class="fl" />
			</div>
			<div class="post_content">
				<p><%=s.getPreview_text()%></p>
				<p class="spec">
					<a href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(s.getName()) %>-<%=s.getId() %>" class="rm">Chi tiết &raquo;</a>
				</p>
			</div>
			<div class="clr"></div>
		</div>
		<%
			}
				int numberOfPage = (int) request.getAttribute("numberOfPage");
				int currentPage = (int) request.getAttribute("currentPage");
				if (listSongs != null && listSongs.size() > 0) {
		%>
		<p class="pages">
			<small>Trang <%=currentPage%> của <%=numberOfPage%></small>
			<%
				String urlCat = request.getContextPath() + "/cat/" + StringUtil.makeSlug(objCategory.getName())
								+ "-" + objCategory.getId();
						for (int i = 1; i <= numberOfPage; i++) {
							if (currentPage == i) {
			%>
			<span><%=i%></span>
			<%
				} else {
			%>

			<a href="<%=urlCat%>/page-<%=i%>"><%=i%></a>
			<%
				}
						}
						if (currentPage == numberOfPage) {
			%>

			<a href="<%=urlCat%>/page-1">»</a>
			<%
				} else {
			%>
			<a href="<%=urlCat%>/page-<%=currentPage + 1%>">»</a>
			<%
				}
			%>
		</p>
		<%
			}
		%>
	</div>
	<div class="sidebar">
		<%@ include file="/templates/public/inc/leftbar.jsp"%>
	</div>
	<div class="clr"></div>
</div>
<%
	}
%>
<%@ include file="/templates/public/inc/footer.jsp"%>