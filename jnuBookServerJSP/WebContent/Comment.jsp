<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="Comment.CommentDAO"%>
<%@ page import="Comment.CommentDTO"%>
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");
	
	int board_num = -1;
	int post_id = -1;

	CommentDAO comment = new CommentDAO();

	 if(request.getParameter("board_num") != null){
	     try{
	    	 board_num = Integer.parseInt(request.getParameter("board_num"));
	     }catch(Exception e){
	        System.out.println("게시판 번호 데이터 오류");
	     }
	  }
	 if(request.getParameter("post_id") != null){
	     try{
	    	 post_id = Integer.parseInt(request.getParameter("post_id"));
	     }catch(Exception e){
	        System.out.println("게시물 번호 데이터 오류");
	     }
	  }
	 
	PrintWriter script = response.getWriter();
	script.println(comment.getCommentData(board_num, post_id));		
	script.close();
	
%>