<%@page import="model.bean.Song"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>
<div class="content_resize">
	<div class="mainbar">
		<%
			ArrayList<Song> listSongs = new ArrayList<Song>();
			if (request.getAttribute("listSongs") != null) {
				listSongs = (ArrayList<Song>) request.getAttribute("listSongs");
				if (listSongs.size() > 0) {
					for (Song s : listSongs) {
						String urlDetail = request.getContextPath() + "/detail/" + StringUtil.makeSlug(s.getName())
								+ "-" + s.getId();
		%>

		<div class="article">
			<h2>
				<a href="<%=urlDetail%>" title=""> <%=s.getName()%>
				</a>
			</h2>

			<p class="infopost" style="border-radius: 6px;">

				Ngày đăng:
				<%=s.getDate_create()%>
				<a href="<%=urlDetail%>" class="com"> <span><%=s.getCounter()%>
				</span>
				</a>

			</p>
			<div class="clr"></div>
			<div class="img">
				<img src="<%=request.getContextPath()%>/uploads/<%=s.getPicture()%>"
					width="180" height="130" alt="" class="fl">

			</div>
			<div class="post_content">
				<p><%=s.getPreview_text()%></p>
				<p class="spec">
					<a href="<%=urlDetail%>" class="rm">Chi tiết »</a>
				</p>
			</div>
			<div class="clr"></div>
			<div class="line"></div>
		</div>

		<%
			}
				}
			}
			int numberOfPage = (int) request.getAttribute("numberOfPage");
			int currentPage = (int) request.getAttribute("currentPage");
			if (listSongs != null && listSongs.size() > 0) {
		%>
		<p class="pages">
			<small>Trang <%=currentPage%> của <%=numberOfPage%></small>
			<%
				String urlIndex = "";
					for (int i = 1; i <= numberOfPage; i++) {
						urlIndex = request.getContextPath() + "/index";
						if (currentPage == i) {
			%>
			<span><%=i%></span>
			<%
				} else {
			%>

			<a href="<%=urlIndex%>/page-<%=i%>"><%=i%></a>
			<%
				}
					}
					if (currentPage == numberOfPage) {
			%>

			<a href="<%=urlIndex%>/page-<%=1%>">»</a>
			<%
				} else {
			%>
			<a href="<%=urlIndex%>/page-<%=currentPage + 1%>">»</a>
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
<script>
	document.getElementById("index").classList.add('active');
</script>
<%@ include file="/templates/public/inc/footer.jsp"%>
