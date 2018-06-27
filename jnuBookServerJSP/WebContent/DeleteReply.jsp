<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Reply.ReplyDAO"%>
<%@ page import="java.io.PrintWriter"%>

<%
   request.setCharacterEncoding("UTF-8");


 int r_id = -1;
 
 if(request.getParameter("r_id") != null){
     try{
    	 r_id = Integer.parseInt(request.getParameter("r_id"));
     }catch(Exception e){
        System.out.println("게시물 번호 데이터 오류");
     }
  }
 
   if(r_id == -1){
      PrintWriter script = response.getWriter();
      script.println("값 받아오기 오류");      
      script.close();
      return;
   }

   ReplyDAO replyDAO = new ReplyDAO();
   
   if(replyDAO.delete_reply(r_id)!=-1){
      PrintWriter script=response.getWriter();
      script.println("삭제 완료");      
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
   
%>