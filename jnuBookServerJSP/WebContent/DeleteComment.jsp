<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Comment.CommentDAO"%>
<%@ page import="java.io.PrintWriter"%>

<%
   request.setCharacterEncoding("UTF-8");


 int c_id = -1;
 
 if(request.getParameter("c_id") != null){
     try{
    	 c_id = Integer.parseInt(request.getParameter("c_id"));
     }catch(Exception e){
        System.out.println("게시물 번호 데이터 오류");
     }
  }
 
   if(c_id == -1){
      PrintWriter script = response.getWriter();
      script.println("값 받아오기 오류");      
      script.close();
      return;
   }

   CommentDAO commentDAO = new CommentDAO();
   
   if(commentDAO.delete_comment(c_id)!=-1){
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