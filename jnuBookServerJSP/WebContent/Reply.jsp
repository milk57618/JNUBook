<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="Reply.ReplyDAO"%>
<%@ page import="Reply.ReplyDTO"%>
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");

	ReplyDAO reply = new ReplyDAO();
	int c_id = -1;

	 if(request.getParameter("c_id") != null){
	     try{
	    	 c_id = Integer.parseInt(request.getParameter("c_id"));
	     }catch(Exception e){
	        System.out.println("댓글 번호 데이터 오류");
	     }
	  }
	 
	PrintWriter script = response.getWriter();
	script.println(reply.getReplyData(c_id));		
	script.close();
	
%>