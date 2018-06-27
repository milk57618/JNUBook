<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="Book.BookDAO"%>
<%@ page import="Book.BookDTO"%>
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");

	BookDAO bookboard = new BookDAO();
	
	PrintWriter script = response.getWriter();
	script.println(bookboard.getBookData());		
	script.close();
	
%>