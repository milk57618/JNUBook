<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Comment.CommentDTO"%>
<%@ page import="Comment.CommentDAO"%>
<%@ page import="java.io.PrintWriter"%>

<%
   request.setCharacterEncoding("UTF-8");



 int c_id = -1;
 int board_num = -1;
 int post_id = -1;
 int user_id = -1;
 String comment_date = null;
 String content = null;

 if(request.getParameter("c_id") != null){
     try{
    	 c_id = Integer.parseInt(request.getParameter("c_id"));
     }catch(Exception e){
        System.out.println("댓글 번호 데이터 오류");
     }
  }
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
 if(request.getParameter("user_id") != null){
     try{
    	 user_id = Integer.parseInt(request.getParameter("user_id"));
     }catch(Exception e){
        System.out.println("학번 데이터 오류");
     }
  }
 if(request.getParameter("comment_date") != null){
	 comment_date = request.getParameter("comment_date");
  }
 if(request.getParameter("content") != null){
	 content = request.getParameter("content");
  }
 
   if(c_id == -1 || post_id == -1 || content == null){
      PrintWriter script = response.getWriter();
      script.println("값 받아오기 오류");      
      script.close();
      return;
   }
   if(c_id == -1){
   CommentDAO commentDAO = new CommentDAO();
   CommentDTO commentDTO = new CommentDTO();
   commentDTO.setBoard_num(board_num);
   commentDTO.setPost_id(post_id);
   commentDTO.setUser_id(user_id);
   commentDTO.setComment_date(comment_date);
   commentDTO.setContent(content);
   
   if(commentDAO.summit(commentDTO)!=-1){
      PrintWriter script=response.getWriter();
      script.println("등록 완료");      
      script.close();
      
      return;
   } else {      
      PrintWriter script=response.getWriter();
      script.println("<script>");
      script.println("alert('오류 발생');");
      script.println("history.back();");
      script.println("</script>");
      script.close();
      return;
   } 
   }
   else{
	   CommentDAO commentDAO = new CommentDAO();
	   CommentDTO commentDTO = new CommentDTO();
	   commentDTO.setC_id(c_id);
	   commentDTO.setBoard_num(board_num);
	   commentDTO.setPost_id(post_id);
	   commentDTO.setUser_id(user_id);
	   commentDTO.setComment_date(comment_date);
	   commentDTO.setContent(content);
	   
	   if(commentDAO.summit(commentDTO)!=-1){
	      PrintWriter script=response.getWriter();
	      script.println("수정 완료");      
	      script.close();
	      
	      return;
	   } else {      
	      PrintWriter script=response.getWriter();
	      script.println("<script>");
	      script.println("alert('오류 발생');");
	      script.println("history.back();");
	      script.println("</script>");
	      script.close();
	      return;
	   } 
   }
   
   
%>