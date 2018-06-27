<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="Notice.NoticeDAO"%>
<%@ page import="Notice.NoticeDTO"%>
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");

	NoticeDAO noticeboard = new NoticeDAO();
	
	PrintWriter script = response.getWriter();
	script.println(noticeboard.getNoticeData());		
	script.close();
	
%>