<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>
<%
	Song objSong = new Song();
	if (request.getAttribute("objSong") != null) {
		objSong = (Song) request.getAttribute("objSong");
	}
%>
<div class="content_resize">
	<div class="mainbar">
		<div class="article">
			<h1><%=objSong.getCategory().getName()%></h1>
			<div class="clr"></div>
			<p>
				Ngày đăng:
				<%=objSong.getDate_create()%>. Lượt xem:
				<%=objSong.getCounter()%></p>
			<div class="vnecontent">
				<%=objSong.getDetail_text()%>
			</div>
			<article>
				<div id="john" itemscope
					itemtype="http://microformats.org/profile/hcard">
					<h2 itemprop="fn">
						<span itemprop="n" itemscope> <span itemprop="given-name"><%=objSong.getSinger()%></span>
						</span>
					</h2>
				</div>
			</article>
			<div>
				<%
					if ("".equals(objSong.getFilemusic())) {
				%>
				<p>Không có nhạc</p>
				<%
					} else {
				%>
				<audio controls>
					<source
						src="<%=request.getContextPath()%>/uploads/<%=objSong.getFilemusic()%>"
						type="audio/mpeg">
				</audio>

				<%
					}
				%>
			</div>
		</div>


		<div class="article">
			<h2>Bài viết liên quan</h2>

			<div class="comment">
				<%
						SongDAO songDAO1 = new SongDAO();
						for (Song s : songDAO1.getItemsBySinger(objSong.getSinger())) {
							
					%>
				<a href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(s.getName()) %>-<%=s.getId() %>"> <img style="max-width: 72px;"
					width="72px" height="72px"
					src="<%=request.getContextPath()%>/uploads/<%=s.getPicture() %>"
					alt="" class="userpic">
				</a>
				<div class="detail-content">
					<h2 class="lq-title">
						<a href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(s.getName()) %>-<%=s.getId() %>"><%=s.getName() %></a>
					</h2>
					<p class="lq-des"><%=s.getPreview_text() %></p>
				</div>
				<div class="clr"></div>

				<%
						}
					%>
			</div>


		</div>



	</div>
	<div class="sidebar">
		<%@ include file="/templates/public/inc/leftbar.jsp"%>
	</div>
	<div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp"%>
