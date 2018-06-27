<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="Lecture.LectureDAO"%>
<%@ page import="Lecture.LectureDTO"%>
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");

	LectureDAO lectureboard = new LectureDAO();
	
	PrintWriter script = response.getWriter();
	script.println(lectureboard.getboardData());		
	script.close();
	
%>