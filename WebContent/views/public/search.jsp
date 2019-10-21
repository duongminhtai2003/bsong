<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>
<%
	ArrayList<Song> listSearch = new ArrayList<Song>();
	Song objSongSearch = new Song();
	if (request.getAttribute("objSong") != null && request.getAttribute("listSearch") != null) {
		listSearch = (ArrayList<Song>) request.getAttribute("listSearch");
		objSongSearch = (Song) request.getAttribute("objSong");
%>
<div class="content_resize">
	<div class="mainbar">
		<%
			for (Song s : listSearch) {
		%>
		<div class="article">
			<h2>
				<a
					href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(s.getName())%>-<%=s.getId()%>"
					title="Đổi thay"><%=s.getName()%></a>
			</h2>
			<p class="infopost">
				Ngày đăng:
				<%=s.getDate_create()%>
				<a
					href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(s.getName())%>-<%=s.getId()%>"
					class="com"><span><%=s.getCounter()%></span></a>
			</p>
			<div class="clr"></div>
			<div class="img">
				<img src="<%=request.getContextPath()%>/uploads/<%=s.getPicture()%>"
					width="177" height="213" alt="Đổi thay" class="fl" />
			</div>
			<div class="post_content">
				<p><%=s.getPreview_text()%></p>
				<p class="spec">
					<a
						href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(s.getName())%>-<%=s.getId()%>"
						class="rm">Chi tiết &raquo;</a>
				</p>
			</div>
			<div class="clr"></div>
		</div>
		<%
			}
				int numberOfPage = (int) request.getAttribute("numberOfPage");
				int currentPage = (int) request.getAttribute("currentPage");
				if (listSearch != null && listSearch.size() > 0) {
		%>
		<p class="pages">
			<small>Trang <%=currentPage%> của <%=numberOfPage%></small>
			<%
				for (int i = 1; i <= numberOfPage; i++) {
							if (currentPage == i) {
			%>
			<span><%=i%></span>
			<%
				} else {
			%>

			<a href="<%=request.getContextPath()%>/search/page-<%=i%>"><%=i%></a>
			<%
				}
						}
						if (currentPage == numberOfPage) {
			%>

			<a href="<%=request.getContextPath()%>/search/page-1">»</a>
			<%
				} else {
			%>
			<a
				href="<%=request.getContextPath()%>/search/page-<%=currentPage + 1%>">»</a>
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
	} else {
%>
<div class="content_resize">
	<div class="mainbar">
		<h3 style="color: red; font-size: large;">Không tìm thấy bài hát!!!</h3>
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