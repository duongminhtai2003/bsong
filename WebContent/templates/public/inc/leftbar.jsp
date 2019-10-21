<%@page import="util.StringUtil"%>
<%@page import="model.bean.Song"%>
<%@page import="model.dao.SongDAO"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
	<form id="formsearch" name="formsearch" method="get"
		action="<%=request.getContextPath()%>/search">
		<span> <input name="editbox_search" class="editbox_search"
			id="editbox_search" maxlength="80"
			value="" placeholder="Nhập bài hát..."
			type="text" />
		</span> <input name="button_search"
			src="<%=request.getContextPath()%>/templates/public/images/search.jpg"
			class="button_search" type="image" />
	</form>
</div>
<div class="clr"></div>
<div class="gadget">
	<h2 class="star">Danh mục bài hát</h2>
	<div class="clr"></div>
	<ul class="sb_menu">
		<%
			CategoryDAO categoryDAO = new CategoryDAO();
			for (Category c : categoryDAO.getItems()) {
		%>
		<li><a
			href="<%=request.getContextPath()%>/cat/<%=StringUtil.makeSlug(c.getName())%>-<%=c.getId()%>"><%=c.getName()%></a>
		</li>
		<%
			}
		%>
	</ul>
</div>

<div class="gadget">
	<h2 class="star">
		<span>Bài hát mới</span>
	</h2>
	<div class="clr"></div>
	<ul class="ex_menu">
		<%
			SongDAO songDAO = new SongDAO();
			for (Song s : songDAO.getNewItems()) {
		%>
		<li><a
			href="<%=request.getContextPath()%>/detail/<%=StringUtil.makeSlug(s.getName())%>-<%=s.getId()%>"><%=s.getName()%></a><br /><%=s.getSinger()%></li>
		<%
			}
		%>
	</ul>
</div>