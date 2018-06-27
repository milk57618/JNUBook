<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Notice.NoticeDTO"%>
<%@ page import="Notice.NoticeDAO"%>
<%@ page import="java.io.PrintWriter"%>

<%
   request.setCharacterEncoding("UTF-8");


 int n_id = -1;
 String post_date = null;
 String title = null;
 String content = null;

 if(request.getParameter("n_id") != null){
     try{
    	 n_id = Integer.parseInt(request.getParameter("n_id"));
     }catch(Exception e){
        System.out.println("게시물 번호 데이터 오류");
     }
  }

 
 if(request.getParameter("title") != null){
	 title = request.getParameter("title");
  }
 if(request.getParameter("content") != null){
	 content = request.getParameter("content");
  }
 
   if(title == null || content == null){
      PrintWriter script = response.getWriter();
      script.println("값 받아오기 오류");      
      script.close();
      return;
   }
   if(n_id == -1){
	   NoticeDAO noticeDAO = new NoticeDAO();
	   NoticeDTO noticeDTO = new NoticeDTO();
	   noticeDTO.setPost_date(post_date);
	   noticeDTO.setTitle(title);
	   noticeDTO.setContent(content);
	   
	   if(noticeDAO.summit(noticeDTO)!=-1){
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
	   NoticeDAO noticeDAO = new NoticeDAO();
	   NoticeDTO noticeDTO = new NoticeDTO();
	   noticeDTO.setN_id(n_id);
	   noticeDTO.setPost_date(post_date);
	   noticeDTO.setTitle(title);
	   noticeDTO.setContent(content);
	   
	   if(noticeDAO.summit(noticeDTO)!=-1){
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