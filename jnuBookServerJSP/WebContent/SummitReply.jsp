<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Reply.ReplyDTO"%>
<%@ page import="Reply.ReplyDAO"%>
<%@ page import="java.io.PrintWriter"%>

<%
   request.setCharacterEncoding("UTF-8");


 int r_id = -1;
 int c_id = -1;
 int user_id = -1;
 String reply_date = null;
 String content = null;

 if(request.getParameter("r_id") != null){
     try{
    	 r_id = Integer.parseInt(request.getParameter("r_id"));
     }catch(Exception e){
        System.out.println("답글 번호 데이터 오류");
     }
  }
 if(request.getParameter("c_id") != null){
     try{
    	 c_id = Integer.parseInt(request.getParameter("c_id"));
     }catch(Exception e){
        System.out.println("댓글 번호 데이터 오류");
     }
  }
 if(request.getParameter("user_id") != null){
     try{
    	 user_id = Integer.parseInt(request.getParameter("user_id"));
     }catch(Exception e){
        System.out.println("학번 데이터 오류");
     }
  }
 if(request.getParameter("reply_date") != null){
	 reply_date = request.getParameter("reply_date");
  }
 if(request.getParameter("content") != null){
	 content = request.getParameter("content");
  }
 
   if(c_id == -1 || content == null){
      PrintWriter script = response.getWriter();
      script.println("값 받아오기 오류");      
      script.close();
      return;
   }
   if(r_id==-1){
   ReplyDAO replyDAO = new ReplyDAO();
   ReplyDTO replyDTO = new ReplyDTO();
   replyDTO.setC_id(c_id);
   replyDTO.setUser_id(user_id);
   replyDTO.setReply_date(reply_date);
   replyDTO.setContent(content);
   
   if(replyDAO.summit(replyDTO)!=-1){
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
	   ReplyDAO replyDAO = new ReplyDAO();
	   ReplyDTO replyDTO = new ReplyDTO();
	   replyDTO.setR_id(r_id);
	   replyDTO.setC_id(c_id);
	   replyDTO.setUser_id(user_id);
	   replyDTO.setReply_date(reply_date);
	   replyDTO.setContent(content);
	   
	   if(replyDAO.summit(replyDTO)!=-1){
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